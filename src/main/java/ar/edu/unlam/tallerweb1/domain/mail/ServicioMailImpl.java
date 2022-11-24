package ar.edu.unlam.tallerweb1.domain.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ServicioMailImpl implements ServicioMail{

    @Override
    public void mandarMailDeRegistracion(String mail, String asunto, String mensaje) {

        String to = mail;
        //provide sender's email ID
        String from = "cineclub.tallerweb@gmail.com";
        //provide Mailtrap's username
        final String username = "Cineclub";
        //provide Mailtrap's password
        final String password = "lrhxyerzktgedzaq";

        //provide Mailtrap's host address
        String host = "smtp.gmail.com";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        //create the Session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);

            //set From email field
            message.setFrom(new InternetAddress(from));

            //set To email field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            //set email subject field
            message.setSubject(asunto);

            //set the content of the email message
            message.setText(mensaje);

            //send the email message
            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
