package utils;

import com.microsoft.aad.msal4j.*;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.search.FlagTerm;
import jakarta.mail.search.SubjectTerm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Outlook Email Helper with OAuth2 Authentication (supports MFA/Authenticator)
 * Uses Microsoft Device Code Flow for interactive authentication
 */
public class OutlookOAuthHelper {

    private static final Logger logger = LoggerFactory.getLogger(OutlookOAuthHelper.class);

    private static final String OUTLOOK_IMAP_HOST = "outlook.office365.com";
    private static final int OUTLOOK_IMAP_PORT = 993;

    // Microsoft OAuth2 endpoints and scopes
    private static final String AUTHORITY = "https://login.microsoftonline.com/common/";
    private static final Set<String> SCOPES = Collections.singleton("https://outlook.office365.com/IMAP.AccessAsUser.All");

    // Default Client ID for public client apps (you can register your own in Azure AD)
    // This is Microsoft's default public client ID for Office apps
    private static final String DEFAULT_CLIENT_ID = "d3590ed6-52b3-4102-aeff-aad2292ab01c";

    private final String email;
    private final String clientId;
    private String accessToken;
    private Store store;
    private Folder inbox;

    /**
     * Constructor with default Microsoft client ID
     * @param email the Outlook email address
     */
    public OutlookOAuthHelper(String email) {
        this.email = email;
        this.clientId = DEFAULT_CLIENT_ID;
    }

    /**
     * Constructor with custom Azure AD client ID
     * @param email the Outlook email address
     * @param clientId your registered Azure AD application client ID
     */
    public OutlookOAuthHelper(String email, String clientId) {
        this.email = email;
        this.clientId = clientId;
    }

    /**
     * Authenticate using Device Code Flow
     * This will display a URL and code - user must open URL and enter code to authenticate
     * Supports MFA/Authenticator
     */
    public void authenticateWithDeviceCode() throws Exception {
        logger.info("Starting OAuth2 Device Code authentication for: {}", email);

        PublicClientApplication app = PublicClientApplication.builder(clientId)
                .authority(AUTHORITY)
                .build();

        // Device code flow - displays URL and code for user to authenticate
        Consumer<DeviceCode> deviceCodeConsumer = (DeviceCode deviceCode) -> {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("AUTHENTICATION REQUIRED");
            System.out.println("=".repeat(60));
            System.out.println(deviceCode.message());
            System.out.println("=".repeat(60) + "\n");
        };

        DeviceCodeFlowParameters parameters = DeviceCodeFlowParameters.builder(SCOPES, deviceCodeConsumer)
                .build();

        CompletableFuture<IAuthenticationResult> future = app.acquireToken(parameters);
        IAuthenticationResult result = future.join();

        this.accessToken = result.accessToken();
        logger.info("OAuth2 authentication successful for: {}", email);
        System.out.println("Authentication successful!");
    }

    /**
     * Connect to Outlook mailbox using OAuth2 token
     */
    public void connect() throws MessagingException {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new MessagingException("Access token is null. Call authenticateWithDeviceCode() first.");
        }

        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", OUTLOOK_IMAP_HOST);
        properties.put("mail.imaps.port", String.valueOf(OUTLOOK_IMAP_PORT));
        properties.put("mail.imaps.ssl.enable", "true");
        properties.put("mail.imaps.ssl.trust", OUTLOOK_IMAP_HOST);
        properties.put("mail.imaps.auth.mechanisms", "XOAUTH2");
        properties.put("mail.imaps.auth.login.disable", "true");
        properties.put("mail.imaps.auth.plain.disable", "true");

        Session session = Session.getInstance(properties);
        store = session.getStore("imaps");

        // Connect using OAuth2 token
        store.connect(OUTLOOK_IMAP_HOST, email, accessToken);
        logger.info("Successfully connected to Outlook mailbox: {}", email);
    }

    /**
     * Authenticate and connect in one step
     */
    public void authenticateAndConnect() throws Exception {
        authenticateWithDeviceCode();
        connect();
    }

    /**
     * Open inbox folder
     */
    public void openInbox() throws MessagingException {
        inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);
        logger.info("Inbox opened successfully. Total messages: {}", inbox.getMessageCount());
    }

    /**
     * Get OTP from the latest email matching the subject
     * @param subjectKeyword keyword to search in email subject
     * @param otpLength expected OTP length (e.g., 4, 6, 8)
     * @return extracted OTP or null if not found
     */
    public String getOtpFromLatestEmail(String subjectKeyword, int otpLength) throws MessagingException, IOException {
        Message[] messages = inbox.search(new SubjectTerm(subjectKeyword));

        if (messages.length == 0) {
            logger.warn("No emails found with subject containing: {}", subjectKeyword);
            return null;
        }

        // Get the latest email (last in the array)
        Message latestMessage = messages[messages.length - 1];
        String content = getTextFromMessage(latestMessage);

        logger.info("Email Subject: {}", latestMessage.getSubject());
        logger.debug("Email Content: {}", content);

        return extractOtp(content, otpLength);
    }

    /**
     * Get OTP from the latest unread email matching the subject
     * @param subjectKeyword keyword to search in email subject
     * @param otpLength expected OTP length
     * @return extracted OTP or null if not found
     */
    public String getOtpFromUnreadEmail(String subjectKeyword, int otpLength) throws MessagingException, IOException {
        // Search for unread emails
        FlagTerm unreadFlag = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
        Message[] unreadMessages = inbox.search(unreadFlag);

        for (int i = unreadMessages.length - 1; i >= 0; i--) {
            Message message = unreadMessages[i];
            String subject = message.getSubject();

            if (subject != null && subject.toLowerCase().contains(subjectKeyword.toLowerCase())) {
                String content = getTextFromMessage(message);
                logger.info("Found unread OTP email: {}", subject);

                // Mark as read
                message.setFlag(Flags.Flag.SEEN, true);

                return extractOtp(content, otpLength);
            }
        }

        logger.warn("No unread emails found with subject containing: {}", subjectKeyword);
        return null;
    }

    /**
     * Wait and fetch OTP with retry mechanism
     * @param subjectKeyword keyword to search in email subject
     * @param otpLength expected OTP length
     * @param maxWaitTimeSeconds maximum time to wait for email
     * @param pollIntervalSeconds interval between checks
     * @return extracted OTP or null if not found within timeout
     */
    public String waitAndGetOtp(String subjectKeyword, int otpLength, int maxWaitTimeSeconds, int pollIntervalSeconds)
            throws MessagingException, IOException, InterruptedException {

        long startTime = System.currentTimeMillis();
        long maxWaitTimeMillis = maxWaitTimeSeconds * 1000L;

        logger.info("Waiting for OTP email with subject containing '{}' (max wait: {}s)", subjectKeyword, maxWaitTimeSeconds);

        while ((System.currentTimeMillis() - startTime) < maxWaitTimeMillis) {
            // Refresh inbox to get new messages
            inbox.close(false);
            inbox.open(Folder.READ_WRITE);

            String otp = getOtpFromUnreadEmail(subjectKeyword, otpLength);
            if (otp != null) {
                logger.info("OTP found: {}", otp);
                return otp;
            }

            logger.debug("OTP not found yet, waiting {}s before retry...", pollIntervalSeconds);
            Thread.sleep(pollIntervalSeconds * 1000L);
        }

        logger.error("Timeout: OTP email not received within {}s", maxWaitTimeSeconds);
        return null;
    }

    /**
     * Extract text content from email message
     */
    private String getTextFromMessage(Message message) throws MessagingException, IOException {
        Object content = message.getContent();

        if (content instanceof String) {
            return (String) content;
        }

        if (content instanceof MimeMultipart) {
            return getTextFromMimeMultipart((MimeMultipart) content);
        }

        return content.toString();
    }

    /**
     * Extract text from multipart message
     */
    private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();

        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent());
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result.append(html.replaceAll("<[^>]*>", "")); // Strip HTML tags
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }

        return result.toString();
    }

    /**
     * Extract OTP using regex pattern
     * @param content email content
     * @param otpLength expected OTP length
     * @return extracted OTP or null
     */
    private String extractOtp(String content, int otpLength) {
        if (content == null || content.isEmpty()) {
            return null;
        }

        // Common OTP patterns
        String[] patterns = {
                // Pattern: OTP is 123456
                "(?i)(?:otp|code|verification|pin)\\s*(?:is|:)?\\s*(\\d{" + otpLength + "})",
                // Pattern: Your OTP: 123456
                "(?i)(?:your|the)?\\s*(?:otp|code|verification|pin)\\s*(?:is|:)?\\s*(\\d{" + otpLength + "})",
                // Pattern: 123456 is your OTP
                "(\\d{" + otpLength + "})\\s*(?:is)?\\s*(?:your)?\\s*(?:otp|code|verification|pin)",
                // Pattern: standalone digits
                "\\b(\\d{" + otpLength + "})\\b"
        };

        for (String patternStr : patterns) {
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(content);
            if (matcher.find()) {
                String otp = matcher.group(1);
                logger.info("Extracted OTP: {} using pattern: {}", otp, patternStr);
                return otp;
            }
        }

        logger.warn("Could not extract OTP from email content");
        return null;
    }

    /**
     * Close connection and cleanup
     */
    public void disconnect() {
        try {
            if (inbox != null && inbox.isOpen()) {
                inbox.close(true);
            }
            if (store != null && store.isConnected()) {
                store.close();
            }
            logger.info("Disconnected from Outlook mailbox");
        } catch (MessagingException e) {
            logger.error("Error while disconnecting: {}", e.getMessage());
        }
    }

    /**
     * Get the count of unread messages in inbox
     */
    public int getUnreadMessageCount() throws MessagingException {
        return inbox.getUnreadMessageCount();
    }

    /**
     * Get total message count in inbox
     */
    public int getTotalMessageCount() throws MessagingException {
        return inbox.getMessageCount();
    }

    /**
     * Get the access token (for debugging/logging)
     */
    public String getAccessToken() {
        return accessToken;
    }
}
