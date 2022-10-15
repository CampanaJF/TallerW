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
	public Entrada getEntrada(Long entrada) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.eq("id", entrada);

		return  (Entrada)session.createCriteria(Entrada.class)
				.add(rest1).uniqueResult();	
	}

	@Override
	public void comprarEntrada(Entrada entrada) {
		
		sessionFactory.getCurrentSession().save(entrada);
		
	}

	
	@Override
	public List<Entrada> getEntradasCompradasDelUsuario(Long usuario,Long funcion) {
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.eq("usuario.id", usuario);
		Criterion rest2 = Restrictions.eq("funcion.id", funcion);
		
		return session.createCriteria(Entrada.class).add(rest1).add(rest2).list();
	}

		// Usa Entrada, entrada tiene acceso facil a las dos entidades, y tiene todo lo necesario para realizar esto
	
//		@Override
//		public Long getAsientosOcupados(Long funcionId) {
//			final Session session = sessionFactory.getCurrentSession();
//			
//			Criterion rest1 = Restrictions.eq("F.id", funcionId);
//			Criterion rest2 = Restrictions.eq("A.ocupado", true);
//			
//			
//			session.createCriteria(Entrada.class).createAlias("asiento", "A")
//												 .createAlias("funcion", "F")
//			 									 .add(rest1).add(rest2).list();
//			
//		}

}
