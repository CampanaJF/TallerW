package ar.edu.unlam.tallerweb1.domain.mail;

import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
@Service("servicioMail")
@Transactional
public class ServicioMailImpl implements ServicioMail{

    @Override
    public void enviarMail(String mail, String asunto, String mensaje) {

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
    @Override
    public String getAsuntoConfirmacionCompra() {
        return "Confirmacion de compra";
    }

    @Override
    public String getMensajeConfirmacionCompra(Usuario usuario, Funcion funcion){
        String mensaje= "<h2>¡GRACIAS POR ELEGIRNOS, " + usuario.getNombre()+"!</h2>\n"
                + "<h4>Tu compra se realizo con exito"+"</h4><br>"
                +"<p>Informacion de tu compra</p><br>"
                +"<table>\n" +
                "<tr><th>Cine </th>\n" +
                "    <th>Pelicula</th>\n" +
                "    <th>Fecha </th>\n" +
                "    <th>Horario </th>\n" +
                "    <th>Sala </th>\n" +
                "  </tr>\n" +
                "  <tr><td>"+funcion.getSala().getCine().getNombreCine()+"</td>\n" +
                "      <td>"+funcion.getPelicula().getTitulo()+"</td>\n" +
                "      <td>"+funcion.getFechaStr()+"</td>\n" +
                "      <td>"+funcion.getHorario()+"</td>\n" +
                "      <td>"+funcion.getSala().getNombreSala()+"</td>\n" +
                "  </tr>\n" +
                "</table><br>";
        return mensaje;
    }
}
