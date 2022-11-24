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
import ar.edu.unlam.tallerweb1.domain.entrada.EntradaPendiente;
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
		final Session session = sessionFactory.getCurrentSession();
		
		Entrada entrada = getEntrada(funcion.getId(),asiento.getId());
		
		entrada.setUsuario(usuario);
		
		entrada.getAsiento().setOcupado(true);
		
		session.update(entrada);
		
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

	@Override
	public void cancelarReserva(Long entradaId) {
		final Session session = sessionFactory.getCurrentSession();
		
		Entrada entrada = getEntrada(entradaId);
		
		entrada.setUsuario(null);
		
		session.update(entrada);
		
	}

	@Override
	public void agregarAPendientes(EntradaPendiente entradaPendiente) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		session.save(entradaPendiente);
		
	}

	@Override
	public List<EntradaPendiente> getPendientes(Long entrada) {
		final Session session = sessionFactory.getCurrentSession();
		
		Entrada entradaLiberada = getEntrada(entrada);
		
		Criterion rest1 = Restrictions.eq("funcion", entradaLiberada.getFuncion());
		Criterion rest2 = Restrictions.isNull("activa");

		return session.createCriteria(EntradaPendiente.class).add(rest1).add(rest2).list();
	}
	@Override
	public List<EntradaPendiente> getPendientesParaEnviarMail(Long entrada) {
		final Session session = sessionFactory.getCurrentSession();

		Entrada entradaLiberada = getEntrada(entrada);

		Criterion rest1 = Restrictions.eq("funcion", entradaLiberada.getFuncion());
		Criterion rest2 = Restrictions.eq("activa",true);

		return session.createCriteria(EntradaPendiente.class).add(rest1).add(rest2).list();
	}
	@Override
	public List<EntradaPendiente> getPendientesActivasDelUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		

		Criterion rest1 = Restrictions.eq("usuario", usuario);
		Criterion rest2 = Restrictions.eq("activa", true);

		return session.createCriteria(EntradaPendiente.class).add(rest1).add(rest2).list();
	}

	@Override
	public void actualizarPendiente(EntradaPendiente entradaPendiente) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		session.update(entradaPendiente);
	}

	@Override
	public List<Entrada> getEntradasCanceladas(Long funcion) {
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.isNull("usuario");
		Criterion rest2 = Restrictions.eq("funcion.id",funcion);
		
		Criteria crit = session.createCriteria(Entrada.class);
		crit.createAlias("funcion", "funcionJoin");
		crit.add(rest1);
		crit.add(rest2);
				
		return crit.list();
	}

	@Override
	public void comprarPendiente(Entrada entrada) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.update(entrada);	
	}

	@Override
	public void eliminarPendiente(EntradaPendiente entradaPendiente) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.remove(entradaPendiente);		
	}

	@Override
	public List<EntradaPendiente> getPendientes(Long entrada, Long usuario) {
		final Session session = sessionFactory.getCurrentSession();
		
		Entrada entradaLiberada = getEntrada(entrada);
		
		Criterion rest1 = Restrictions.eq("funcion", entradaLiberada.getFuncion());
		Criterion rest2 = Restrictions.eq("usuario",entradaLiberada.getUsuario());

		return session.createCriteria(EntradaPendiente.class).add(rest1).add(rest2).list();
	}

	@Override
	public EntradaPendiente obtenerPendiente(Funcion funcion, Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.eq("funcion.id", funcion.getId());
		Criterion rest2 = Restrictions.eq("usuario.id",usuario.getId());

		return (EntradaPendiente) session.createCriteria(EntradaPendiente.class).add(rest1).add(rest2)
																				.setMaxResults(1).uniqueResult();
	}
	
	


}
