package Utilities;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class MediaMailer {

    private static final String USER_EMAIL = "beastcentermax@gmail.com";
    private static final String USER_PASSWORD = "mieirocks";

    private Session session;

    public MediaMailer() {
        session = null;
    }

    private void login() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MediaMailer.USER_EMAIL, MediaMailer.USER_PASSWORD);
                    }
                });
    }

    public void sendMessage(String to, String subject, String msg) throws MessagingException {
        this.login();

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(MediaMailer.USER_EMAIL));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)
        );
        message.setSubject(subject);
        message.setText(msg);

        Transport.send(message);
    }

}