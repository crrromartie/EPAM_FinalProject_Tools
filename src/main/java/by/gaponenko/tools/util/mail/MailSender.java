package by.gaponenko.tools.util.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
    static Logger logger = LogManager.getLogger();

    private MailSender() {
    }

    public static void sendMessage(String subject, String text, String email) {
        Session session = Session.getDefaultInstance(MailPropertiesManager.getProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailPropertiesManager.getUserName(),
                        MailPropertiesManager.getUserPassword());
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MailPropertiesManager.getUserName()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }
}
