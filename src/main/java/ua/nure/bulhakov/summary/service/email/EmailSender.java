package ua.nure.bulhakov.summary.service.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    private static Properties properties;

    private static String sender;

    private static String password;

    private EmailSender(){

    }

    public static String sendThroughRemote(String topic, String text, String recipient) throws EmailException {
        try {
            Session session = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            message.setSubject(topic);
            message.setText(text);

            Transport tr = session.getTransport();
            tr.connect(sender, password);
            tr.sendMessage(message, message.getAllRecipients());
        }catch (MessagingException e2) {
            throw new EmailException("MessagingException was thrown", e2);
        }
        return "OK";
    }

    public static void setProperties(Properties properties) {
        EmailSender.properties = properties;
    }

    public static void setPassword(String password) {
        EmailSender.password = password;
    }

    public static void setSender(String sender) {
        EmailSender.sender = sender;
    }
}