package com.sendemail;


import java.util.Properties; /*The Properties class represents a persistent set of properties. 
                               The Properties can be saved to a stream or loaded from a stream.*/

import javax.mail.Message;   /*This class models an email message. To send a message,
                               subclass of Message (e.g. MimeMessage) is instantiated, 
                               the attributes and content are filled in, 
                               and message is sent using the Transport.send method.*/

import javax.mail.MessagingException; /*This is base class for all exceptions thrown 
                                        by the Messaging classes */

import javax.mail.PasswordAuthentication; /*This class is simply a repository for a user name and a password.*/

import javax.mail.Session; /*Session class represents a mail session.*/

import javax.mail.Transport; /*This is abstract class that models a message transport.*/

import javax.mail.internet.InternetAddress; /*This class represents an Internet email address 
                                              using the syntax of RFC822.*/

import javax.mail.internet.MimeMessage;  /*This class represents a MIME style email message. 
                                           It implements the Message abstract class and the 
                                           MimePart interface.*/

public class SendEmail {

	public static void main(String[] args) {
		// Recipient's email ID needs to be mentioned.
        String to = "fromaddress@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "toaddress@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        
     // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("fromaddress@gmail.com", "*******");

            }

        });
        
     // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
