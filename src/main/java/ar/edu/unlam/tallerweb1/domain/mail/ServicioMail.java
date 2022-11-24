package ar.edu.unlam.tallerweb1.domain.mail;

public interface ServicioMail {
    void mandarMailDeRegistracion(String mail, String asunto, String mensaje);
}
