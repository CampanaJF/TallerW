package ar.edu.unlam.tallerweb1.infrastructure;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.entrada.RepositorioEntrada;

@SuppressWarnings({ "deprecation" })
@Repository("repositorioEntrada")
@Transactional
public class RepositorioEntradaImpl implements RepositorioEntrada {
	
	private SessionFactory sessionFactory;
	 
	@Autowired
	private RepositorioEntradaImpl (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Entrada getEntrada(Long id) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.eq("id", id);

		return  (Entrada)session.createCriteria(Entrada.class)
				.add(rest1).uniqueResult();	
	}

	@Override
	public void comprarEntrada(Entrada entrada) {
		
		sessionFactory.getCurrentSession().save(entrada);
		
	}

}
