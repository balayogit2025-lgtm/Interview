package api.utils;

import jakarta.mail.*;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.search.FlagTerm;
import jakarta.mail.search.SubjectTerm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutlookEmailHelper {

    private static final Logger logger = LoggerFactory.getLogger(OutlookEmailHelper.class);

    private static final String OUTLOOK_IMAP_HOST = "outlook.office365.com";
    private static final int OUTLOOK_IMAP_PORT = 993;

    private final String email;
    private final String password;
    private Store store;
    private Folder inbox;

    public OutlookEmailHelper(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void connect() throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", OUTLOOK_IMAP_HOST);
        properties.put("mail.imaps.port", String.valueOf(OUTLOOK_IMAP_PORT));
        properties.put("mail.imaps.ssl.enable", "true");
        properties.put("mail.imaps.ssl.trust", OUTLOOK_IMAP_HOST);

        Session session = Session.getInstance(properties);
        store = session.getStore("imaps");
        store.connect(OUTLOOK_IMAP_HOST, email, password);
        logger.info("Successfully connected to Outlook mailbox: {}", email);
    }

    public void openInbox() throws MessagingException {
        inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);
        logger.info("Inbox opened successfully. Total messages: {}", inbox.getMessageCount());
    }

    public String getOtpFromLatestEmail(String subjectKeyword, int otpLength) throws MessagingException, IOException {
        Message[] messages = inbox.search(new SubjectTerm(subjectKeyword));

        if (messages.length == 0) {
            logger.warn("No emails found with subject containing: {}", subjectKeyword);
            return null;
        }

        Message latestMessage = messages[messages.length - 1];
        String content = getTextFromMessage(latestMessage);

        logger.info("Email Subject: {}", latestMessage.getSubject());
        logger.debug("Email Content: {}", content);

        return extractOtp(content, otpLength);
    }

    public String getOtpFromUnreadEmail(String subjectKeyword, int otpLength) throws MessagingException, IOException {
        FlagTerm unreadFlag = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
        Message[] unreadMessages = inbox.search(unreadFlag);

        for (int i = unreadMessages.length - 1; i >= 0; i--) {
            Message message = unreadMessages[i];
            String subject = message.getSubject();

            if (subject != null && subject.toLowerCase().contains(subjectKeyword.toLowerCase())) {
                String content = getTextFromMessage(message);
                logger.info("Found unread OTP email: {}", subject);
                message.setFlag(Flags.Flag.SEEN, true);
                return extractOtp(content, otpLength);
            }
        }

        logger.warn("No unread emails found with subject containing: {}", subjectKeyword);
        return null;
    }

    public String waitAndGetOtp(String subjectKeyword, int otpLength, int maxWaitTimeSeconds, int pollIntervalSeconds)
            throws MessagingException, IOException, InterruptedException {

        long startTime = System.currentTimeMillis();
        long maxWaitTimeMillis = maxWaitTimeSeconds * 1000L;

        logger.info("Waiting for OTP email with subject containing '{}' (max wait: {}s)", subjectKeyword, maxWaitTimeSeconds);

        while ((System.currentTimeMillis() - startTime) < maxWaitTimeMillis) {
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

    private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();

        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent());
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result.append(html.replaceAll("<[^>]*>", ""));
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }

        return result.toString();
    }

    private String extractOtp(String content, int otpLength) {
        if (content == null || content.isEmpty()) {
            return null;
        }

        String[] patterns = {
                "(?i)(?:otp|code|verification|pin)\\s*(?:is|:)?\\s*(\\d{" + otpLength + "})",
                "(?i)(?:your|the)?\\s*(?:otp|code|verification|pin)\\s*(?:is|:)?\\s*(\\d{" + otpLength + "})",
                "(\\d{" + otpLength + "})\\s*(?:is)?\\s*(?:your)?\\s*(?:otp|code|verification|pin)",
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

    public int deleteEmailsBySubject(String subjectKeyword) throws MessagingException {
        Message[] messages = inbox.search(new SubjectTerm(subjectKeyword));
        int count = 0;

        for (Message message : messages) {
            message.setFlag(Flags.Flag.DELETED, true);
            count++;
        }

        logger.info("Marked {} emails for deletion with subject containing: {}", count, subjectKeyword);
        return count;
    }

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

    public int getUnreadMessageCount() throws MessagingException {
        return inbox.getUnreadMessageCount();
    }

    public int getTotalMessageCount() throws MessagingException {
        return inbox.getMessageCount();
    }
}
