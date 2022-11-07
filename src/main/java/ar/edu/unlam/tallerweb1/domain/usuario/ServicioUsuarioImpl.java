package ar.edu.unlam.tallerweb1.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.exceptions.EmailEnUsoException;

@Service("servicioLogin")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	private RepositorioUsuario repositorioUsuario;

	@Autowired
	public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario){
		this.repositorioUsuario = repositorioUsuario;
	}

	@Override
	public Usuario loginUsuario (String email, String password) {
		return repositorioUsuario.loginUsuario(email, password);
	}

	@Override
	public void registrarUsuario(String email, String password, String nombre) {

		if(!validarEmail(email))
			throw new EmailEnUsoException();
			
		Usuario nuevo = new Usuario();
		nuevo.setEmail(email);
		nuevo.setNombre(nombre);
		nuevo.setPassword(password);
		
		repositorioUsuario.guardar(nuevo);
		
	}
	
	@Override
	public Boolean validarEmail(String email) {
		return repositorioUsuario.validarEmail(email);
	}

	@Override
	public Usuario getUsuario(Long Id) {
		
		return this.repositorioUsuario.getUsuario(Id);
		
	}
	
	

}
