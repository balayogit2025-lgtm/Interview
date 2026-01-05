package utils;
import pojo.*;
import org.testng.*;


import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.*;
import java.util.*;
import java.util.regex.*;
public class OTPReader {

    public static String getOTPFromGmail(Mailer mailer) throws InterruptedException {
Thread.sleep(1000);
        Properties properties = new Properties();
        properties.put("mail.imap.host", mailer.getHost());
        properties.put("mail.imap.port", "993");
        properties.put("mail.imap.ssl.enable", "true");
        properties.put("mail.imap.ssl.trust", "*");


        Session session = Session.getInstance(properties);
        String otp = null;
        try {
            Store store = session.getStore("imap");
            store.connect(mailer.getEmailId(), mailer.getPassword());

            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);

            Message[] messages = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            if (messages.length > 0) { // Check if there are any messages
                Message message = messages[messages.length - 1]; // Get the last message
                if (message.getSubject() != null && message.getSubject().equals(mailer.getEmailSubject())) {
                    Address[] fromAddresses = message.getFrom();
                    String fromEmail = fromAddresses[0].toString(); // Get the sender's email address

                    String content = getContent(message); // Get the content of the message

                    System.out.println("Mail with subject \"" + mailer.getEmailSubject() + "\" found!");
                    System.out.println("From: " + fromEmail);
                    System.out.println("Content: " + content);

                    otp = extractOtp(content); // Extract the OTP from the content
                    if (otp != null) {
                        System.out.println("Extracted OTP: " + otp);
                    } else {
                        System.out.println("No OTP found in the email content.");
                        Assert.assertTrue(false, "Email not received");
                    }

                    message.setFlag(Flags.Flag.SEEN, true); // Mark the message as seen
                } else {
                    System.out.println("No matching email found with subject: " + mailer.getEmailSubject());
                }
            } else {
                System.out.println("No unseen messages found in the folder.");
            }

            folder.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return otp;
    }

    private static String getContent(Message message) throws Exception {
        StringBuilder content = new StringBuilder();
        if (message.isMimeType("text/plain")) {
            content.append(message.getContent().toString());
        } else if (message.isMimeType("text/html")) {
            content.append(message.getContent().toString());
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            int count = mimeMultipart.getCount();
            for (int i = 0; i < count; i++) {
                BodyPart bodyPart = mimeMultipart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain")) {
                    content.append(bodyPart.getContent());
                } else if (bodyPart.isMimeType("text/html")) {
                    content.append(bodyPart.getContent());
                }
            }
        }
        return content.toString();
    }

    private static String extractOtp(String content) {
        Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

}
