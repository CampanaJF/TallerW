package ar.edu.unlam.tallerweb1.domain.usuario;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioUsuario {

	Usuario loginUsuario(String email, String password);
	
	void registrarUsuario(String email, String password, String nombre);
	
	Boolean validarEmail(String email);
	
	Usuario getUsuario(Long id);

	
}
