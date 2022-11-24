package ar.edu.unlam.tallerweb1.domain.mail;

import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

public interface ServicioMail {
    void enviarMail(String mail, String asunto, String mensaje);
    String getAsuntoConfirmacionCompra();
    String getMensajeConfirmacionCompra(Usuario usuario, Funcion funcion);
}
