package com.rocco.birthdays;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
    public static void sendEmail(String from, String pass, String[] to, String subject, String body) {
        // Change the host to Libero's SMTP server
        String host = "smtp.libero.it"; // Libero SMTP server

        // Setup mail server properties
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true"); // TLS support
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587"); // Port for TLS
        props.put("mail.smtp.auth", "true"); // SMTP Authentication

        try {
            // Create a session with the properties
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            // Create the recipients' addresses array
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // Convert the recipient email addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            // Add all recipients to the message
            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            // Set the email subject and body content
            message.setSubject(subject);
            message.setText(body);

            // Send the email via SMTP server
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException mx) {
            System.out.println("Impossible to send email" + mx.getMessage());
            mx.printStackTrace();
        }
    }
}
