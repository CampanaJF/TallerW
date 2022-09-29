package ar.edu.unlam.tallerweb1.infrastructure;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.cine.CinePelicula;
import ar.edu.unlam.tallerweb1.domain.cine.RepositorioCine;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository("repositorioCine")
@Transactional
public class RepositorioCineImpl implements RepositorioCine{
	
	private SessionFactory sessionFactory;
	
	@Autowired
	private RepositorioCineImpl (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Cine> getCines() {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Cine.class).list();
	}

	@Override
	public List<CinePelicula> getCines(Long pelicula) {
		Criterion rest1 = Restrictions.eq("pelicula.id", pelicula);

		
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(CinePelicula.class).add(rest1).list();
	}

}
