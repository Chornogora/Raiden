package ua.nure.bulhakov.summary.service.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {

    private static Properties properties;

    public static String sendThroughRemote(String sender, String password, String recipient) throws EmailException {
        Properties properties = System.getProperties();

        String fileName = "target/classes/mailproperties.txt";
        File file = new File(fileName);

        try {
            properties.load(new FileInputStream(file));
            Session session = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            message.setSubject("Activation Code");
            message.setText("Temp");

            Transport tr = session.getTransport();
            tr.connect(sender, password);
            tr.sendMessage(message, message.getAllRecipients());
        } catch (FileNotFoundException e) {
            return "Fail. Properties were not found";
        } catch (IOException e) {
            throw new EmailException("Can't load property", e);
        } catch (MessagingException e2) {
            throw new EmailException("MessagingException was thrown", e2);
        }
        return "OK";
    }

    public static void setProperties(Properties properties) {
        EmailSender.properties = properties;
    }
}