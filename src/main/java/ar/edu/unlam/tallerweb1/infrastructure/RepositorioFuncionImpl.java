package ar.edu.unlam.tallerweb1.infrastructure;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.funcion.RepositorioFuncion;


@SuppressWarnings({ "unchecked", "deprecation"})
@Repository("repositorioFuncion")
@Transactional
public class RepositorioFuncionImpl implements RepositorioFuncion {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	private RepositorioFuncionImpl (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Funcion> getFuncionesDeUnCine(Long cine,Long pelicula) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.eq("cine.id",cine);
		Criterion rest2 = Restrictions.eq("pelicula.id", pelicula);
		
		return session.createCriteria(Funcion.class)
							 .createAlias("sala", "salas")
							 .createAlias("salas.cine", "cine")
							 .add(rest1).add(rest2).list();
	
	}
	  
	@Override
	public Funcion getFuncion(Long funcionId) {
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.eq("id", funcionId);
		
		return (Funcion) session.createCriteria(Funcion.class).add(rest1).uniqueResult();
	}

	@Override
	public void update(Funcion funcion) {
		sessionFactory.getCurrentSession().update(funcion);
		
	}



}
