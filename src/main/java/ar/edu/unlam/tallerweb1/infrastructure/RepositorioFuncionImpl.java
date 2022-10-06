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


@SuppressWarnings({ "unchecked", "deprecation" })
@Repository("repositorioFuncion")
@Transactional
public class RepositorioFuncionImpl implements RepositorioFuncion {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	private RepositorioFuncionImpl (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Funcion> getFuncionesDeUnCine(Long sala,Long pelicula) {
		//TO DO
		// ordenar por horario y  filtrar horarios repetidos una ves se implemente la clase definitiva para manejarlos
		// filtrar por formato
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.eq("sala.id", sala);
		Criterion rest2 = Restrictions.eq("pelicula.id", pelicula);
		
		return session.createCriteria(Funcion.class).add(rest1).add(rest2).list();
	}

	@Override
	public Funcion getFuncion(Long funcionId) {
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.eq("id", funcionId);
		
		return (Funcion) session.createCriteria(Funcion.class).add(rest1).uniqueResult();
	}

}
