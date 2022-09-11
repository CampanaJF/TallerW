package ar.edu.unlam.tallerweb1.domain.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.exceptions.EmailEnUsoException;
import ar.edu.unlam.tallerweb1.exceptions.PasswordLenghtException;
import ar.edu.unlam.tallerweb1.exceptions.PasswordsDiferentesException;

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
	public void registrarUsuario(Usuario usuario) {

		if(!validarEmail(usuario))
			throw new EmailEnUsoException();
		
		if(!validarPass(usuario))
			throw new PasswordsDiferentesException();
		
		if(!validarPassLenght(usuario))
			throw new PasswordLenghtException();
			
		repositorioUsuario.guardar(usuario);
		
	}
	
	@Override
	public Boolean validarEmail(Usuario usuario) {
		return repositorioUsuario.buscarEmail(usuario);
	}

	@Override
	public Boolean validarPassLenght(Usuario usuario) {
		return usuario.getPassword().length()>12;	
	}

	@Override
	public Boolean validarPass(Usuario usuario) {
		if(usuario.getPassword()!=usuario.getPasswordRe())
			return true;
		
		return false;
	}

	@Override
	public Usuario getUsuario(Long Id) {
		
		return this.repositorioUsuario.getUsuario(Id);
		
	}
	
	

}
