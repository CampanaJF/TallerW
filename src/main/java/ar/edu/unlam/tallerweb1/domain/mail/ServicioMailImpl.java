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
//        String from = "cineclub.tallerweb@gmail.com";
//        //provide Mailtrap's username
//        final String username = "Cineclub Taller";
//        //provide Mailtrap's password
//        //final String password = "lrhxyerzktgedzaq"; contraseï¿½a gmail
//        final String password = "hnfkwjeeupjqnrcb";
        
      //provide sender's email ID
        String from = "gauchorocket30@gmail.com";
        //provide Mailtrap's username
        final String username = "gauchorocket30";
        //provide Mailtrap's password
        final String password = "tnfeghestzihwnnp";

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
            
            message.setContent(mensaje,"text/html; charset=utf-8");

            //set the content of the email message
            //message.setText(mensaje);

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
    public String getAsuntoEntradasDisponibles() {
        return "Entradas disponibles";
    }
    @Override
    public String getMensajeConfirmacionCompra(Usuario usuario, Funcion funcion){
        String mensaje= "¡GRACIAS POR ELEGIRNOS, " + usuario.getNombre()+"!\n"
                + "Tu compra se realizo con exito\n"
                +"Informacion de tu compra\n"
                +"Cine      " + funcion.getSala().getCine().getNombreCine()+"\n"+
                "Pelicula   " + funcion.getPelicula().getTitulo()+"\n"+
                "Fecha      " +funcion.getFechaStr()+"\n"+
                "Horario    " +funcion.getHorario()+"\n"+
                "Sala       " +funcion.getSala().getNombreSala()+"\n";
        return mensaje;
    }
    
    public String getMensajeEntradasDisponibles(String nombreUsuario, String tituloPelicula){
        String mensaje="Hola, " + nombreUsuario+ "!\n"
                     + "Te informamos que la pelicula " + tituloPelicula + " ya cuenta con entradas disponibles.";
        return mensaje;
    }
    @Override
    public String getMensajeEntradasDisponiblesHTML(String nombreUsuario, String tituloPelicula,Long funcionId){
        String mensaje="<h2>¡Hola, " + nombreUsuario + "!</h2> </br> "
        				+"<h4>Te informamos que la pelicula " + tituloPelicula + " ya cuenta con entradas disponibles.</h4></br>"
        				+"<a href='http://localhost:8080/proyecto-limpio-spring/mis-entradas'> <h4>¡Comprala Ya!</h4> </a>"
        +"<a href='http://localhost:8080/proyecto-limpio-spring/entradas-pendientes?funcion=" + funcionId +   "'>"
        				+ "<h4>¡Comprala Ya!</h4>" ;
        		
        return mensaje;
    }
    
}
