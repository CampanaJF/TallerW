package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioUsuario {

	Usuario loginUsuario(String email, String password);
	
	void registrarUsuario(Usuario usuario);
	
	Boolean validarEmail(Usuario usuario);
	
	Boolean validarPassLenght(Usuario usuario);
	
	Boolean validarPass(Usuario usuario);
	
	Usuario getUsuario(Long id);

	
	
}
