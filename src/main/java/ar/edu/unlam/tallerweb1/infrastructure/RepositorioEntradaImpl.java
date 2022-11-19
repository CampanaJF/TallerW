package ar.edu.unlam.tallerweb1.infrastructure;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.domain.cine.Asiento;
import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.entrada.RepositorioEntrada;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;


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
	public void comprarEntrada(Funcion funcion,Usuario usuario,Asiento asiento) {
		
		Entrada entrada = getEntrada(funcion.getId(),asiento.getId());
		
		entrada.setUsuario(usuario);
		
		entrada.getAsiento().setOcupado(true);
		
		sessionFactory.getCurrentSession().update(entrada);
		
	}

	
	@Override
	public List<Entrada> getEntradasCompradasDelUsuario(Long usuario,Long funcion) {
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.eq("usuario.id", usuario);
		Criterion rest2 = Restrictions.eq("funcion.id", funcion);
		
		return session.createCriteria(Entrada.class).add(rest1).add(rest2).list();
	}

	@Override
	public Entrada getEntrada(Long funcion, Long asiento) {
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.eq("asiento.id", asiento);
		Criterion rest2 = Restrictions.eq("funcion.id", funcion);
		
		return (Entrada) session.createCriteria(Entrada.class).add(rest1).add(rest2).uniqueResult();
	}
	
	@Override
	public Integer getCantidadAsientosVacios(Long funcion) {
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.eq("funcion.id",funcion);
		Criterion rest2 = Restrictions.eq("ocupado",false);
		
		Criteria crit = session.createCriteria(Asiento.class);
		crit.createAlias("entrada", "entradaJoin");
		crit.createAlias("entradaJoin.funcion", "funcion");
		crit.add(rest1);
		crit.add(rest2);
		crit.setProjection(Projections.rowCount());
				
		return Math.toIntExact((long) crit.uniqueResult());
	}
	
	@Override
	public Usuario getUsuario(Long Id) {
		final Session session = sessionFactory.getCurrentSession();
		Criterion rest1 = Restrictions.eq("id", Id);
		
		return (Usuario) session.createCriteria(Usuario.class).add(rest1).uniqueResult();
		
	}

	@Override
	public Asiento getAsiento(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Criterion rest1 = Restrictions.eq("id", id);
		
		return (Asiento) session.createCriteria(Asiento.class).add(rest1).uniqueResult();

	}

	@Override
	public List<Entrada> getEntradasCompradasDelUsuario(Usuario usuarioLogueado) {
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.eq("usuario.id", usuarioLogueado.getId());

		return session.createCriteria(Entrada.class).add(rest1).list();
	}
	
	


}
