package ua.nure.bulhakov.summary.service.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Class that is used to send email using current properties.
 * Is configured by foreign launcher
 * @author A.Bulhakov
 * @see ua.nure.bulhakov.summary.controller.launch.EmailLauncher
 */
public class EmailSender {

    /**
     * foreign smtp properties
     */
    private static Properties properties;

    /**
     * sender email address
     */
    private static String sender;

    /**
     * sender email password
     */
    private static String password;

    private EmailSender(){

    }

    /**
     * @param topic topic of message
     * @param text content of message
     * @param recipient email address of recipient
     * @throws EmailException when it's impossible to send message
     */
    static void sendThroughRemote(String topic, String text, String recipient) throws EmailException {
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
            throw new EmailException("Can't send email", e2);
        }
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