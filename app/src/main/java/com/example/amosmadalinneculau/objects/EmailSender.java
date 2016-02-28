/*
package com.example.amosmadalinneculau.objects;


*/
/**
 * Created by Amos Madalin Neculau on 13/02/2016.
 *//*


import android.util.Log;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


public class EmailSender {

    protected String to;
    protected String from;
    protected String host;
    protected String title;
    protected String message;


    public EmailSender(String emailTo){
        CodeGenerator cg = new CodeGenerator();
        to = emailTo;
        from = "donotreply@[COMPANYNAME].[EXTENSION]";
        host = "localhost";

        subject = "[NAME OF APP] Activation code.";
        message = "Hello,\n\n\n Please confirm the email via inserting the following activation code in the [NAME OF APP].\n\n\nCode: "+cg.toString()+"\n\nThank you,\n[COMPANY NAME]";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText(message);

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);


            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            Log.i("Email status: ","Successfuly sent");
        }catch (MessagingException mex) {
            mex.printStackTrace();
            Log.i("Email status: ", "Couldn't send");
        }



    }

}
*/
