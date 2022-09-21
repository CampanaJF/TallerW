package ar.edu.unlam.tallerweb1.infrastructure;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.domain.usuario.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

// implelemtacion del repositorio de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@SuppressWarnings({ "deprecation" })
@Repository("repositorioUsuario")
@Transactional
public class RepositorioUsuarioImpl implements RepositorioUsuario {

	// Maneja acciones de persistencia, normalmente estara inyectado el session factory de hibernate
	// el mismo esta difinido en el archivo hibernateContext.xml
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioUsuarioImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Usuario loginUsuario(String email, String password) {

		// Se obtiene la sesion asociada a la transaccion iniciada en el servicio que invoca a este metodo y se crea un criterio
		// de busqueda de Usuario donde el email y password sean iguales a los del objeto recibido como parametro
		// uniqueResult da error si se encuentran mas de un resultado en la busqueda.
		final Session session = sessionFactory.getCurrentSession();
		Criterion rest1 = Restrictions.eq("email", email);
		Criterion rest2 = Restrictions.eq("password", password);
		
		return  (Usuario)session.createCriteria(Usuario.class)
				.add(rest1).add(rest2).uniqueResult();	
	}

	@Override
	public void guardar(Usuario usuario) {
		sessionFactory.getCurrentSession().save(usuario);
	}

	@Override
	public Usuario buscar(String email) {
		return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
				.add(Restrictions.eq("email", email))
				.uniqueResult();
	}

	@Override
	public void modificar(Usuario usuario) {
		sessionFactory.getCurrentSession().update(usuario);
	}
	
	@Override
	public Boolean validarEmail(String email) {
		
		final Session session = sessionFactory.getCurrentSession();
		Criterion rest1 = Restrictions.eq("email", email);

		Usuario encontrado = (Usuario) session.createCriteria(Usuario.class).add(rest1).uniqueResult();
		
		if(encontrado == null)
			return true;
		
		return false;
		
	}

	@Override
	public Usuario getUsuario(Long Id) {
		final Session session = sessionFactory.getCurrentSession();
		Criterion rest1 = Restrictions.eq("id", Id);
		
		Usuario encontrado = (Usuario) session.createCriteria(Usuario.class).add(rest1).uniqueResult();
		
		return encontrado;
	}

}
