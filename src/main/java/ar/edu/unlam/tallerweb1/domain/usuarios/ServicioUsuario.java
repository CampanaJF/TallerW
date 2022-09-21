package ar.edu.unlam.tallerweb1.domain.usuarios;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioUsuario {

	Usuario loginUsuario(String email, String password);
	
	void registrarUsuario(String email, String password, String passwordRe, String nombre);
	
	Boolean validarEmail(String email);
	
	Boolean validarPassLenght(String password);
	
	Boolean validarPass(String password,String passwordRe);
	
	Usuario getUsuario(Long id);

	
}
