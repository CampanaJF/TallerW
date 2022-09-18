package ar.edu.unlam.tallerweb1.domain.usuario;

import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;


public interface RepositorioUsuario {
	
	Usuario loginUsuario(String email, String password);
	
	void guardar(Usuario usuario);
	
    Usuario buscar(String email);
    
	void modificar(Usuario usuario);

	Boolean validarEmail(String email);

	Usuario getUsuario(Long Id);
}
