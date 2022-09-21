package ar.edu.unlam.tallerweb1.infrastructure;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.entrada.RepositorioEntrada;


@SuppressWarnings({ "unchecked", "deprecation" })
@Repository("repositorioEntrada")
@Transactional
public class RepositorioEntradaImpl implements RepositorioEntrada {
	
	private SessionFactory sessionFactory;
	 
	@Autowired
	private RepositorioEntradaImpl (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Entrada getEntrada(Long uId,Long fId) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.eq("usuario.id", uId);
		Criterion rest2 = Restrictions.eq("funcion.id", fId);

		return  (Entrada)session.createCriteria(Entrada.class)
				.add(rest1).add(rest2).uniqueResult();	
	}

	@Override
	public Entrada comprarEntrada(Entrada entrada) {
		
		sessionFactory.getCurrentSession().save(entrada);
		
		return entrada;
		
	}

	
	@Override
	public List<Entrada> getEntradas(Long uId) {
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.eq("usuario.id", uId);
		
		return session.createCriteria(Entrada.class).add(rest1).list();
	}

}
