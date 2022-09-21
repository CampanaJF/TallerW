package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;


public interface RepositorioUsuario {
	
	Usuario loginUsuario(String email, String password);
	
	void guardar(Usuario usuario);
	
    Usuario buscar(String email);
    
	void modificar(Usuario usuario);

	Boolean validarEmail(String email);

	Usuario getUsuario(Long Id);
}
