package pojo;


import lombok.*;

import java.util.*;

@Getter
@Setter
public class Mailer {

    private String emailId;

    private String firstName;

    private String lastName;

    private String password;

    private String host;

    private String port;

    private String protocol;

    private String mailStore;

    private String emailContent;

    private String emailSubject;

    private String emailFrom;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }
public void setPort(String port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getMailStore() {
        return mailStore;
    }

    public void setMailStore(String mailStore) {
        this.mailStore = mailStore;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public List getPolygon() {
        return polygon;
    }

    public void setPolygon(List polygon) {
        this.polygon = polygon;
    }

    public String getInActiveEmail() {
        return inActiveEmail;
    }

    public void setInActiveEmail(String inActiveEmail) {
        this.inActiveEmail = inActiveEmail;
    }

    public String getNoWebAccessEmail() {
        return noWebAccessEmail;
    }

    public void setNoWebAccessEmail(String noWebAccessEmail) {
        this.noWebAccessEmail = noWebAccessEmail;
    }

    private String replyContent;

    private List polygon;

    private String inActiveEmail;

    private String noWebAccessEmail;


}
