package ar.edu.unlam.tallerweb1.domain.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.exceptions.EmailEnUsoException;
import ar.edu.unlam.tallerweb1.exceptions.PasswordLenghtException;
import ar.edu.unlam.tallerweb1.exceptions.PasswordsDiferentesException;

// Implelemtacion del Servicio de usuarios, la anotacion @Service indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.servicios
// para encontrar esta clase.
// La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio,
// dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido
// en hibernateCOntext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

	private RepositorioUsuario repositorioUsuario;

	@Autowired
	public ServicioLoginImpl(RepositorioUsuario repositorioUsuario){
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
	
	

}
