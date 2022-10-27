package ar.edu.unlam.tallerweb1.infrastructure;

import java.util.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.pelicula.Valoracion;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.RepositorioPelicula;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository("repositorioPelicula")
@Transactional
public class RepositorioPeliculaImpl implements RepositorioPelicula {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	private RepositorioPeliculaImpl (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Pelicula> getPeliculasFiltro(Filtro filtro) {
		final Session session = sessionFactory.getCurrentSession();
		Date fechaActual=new Date();
		Criteria criteria = session.createCriteria(Pelicula.class);
		
		if (filtro.getGenero() != null) {
			criteria.createAlias("genero", "g",JoinType.INNER_JOIN);
			criteria.add(Restrictions.eq("g.id", filtro.getGenero()));
		}
		if (filtro.getClasificacion() != null) {
			criteria.createAlias("clasificacionPelicula", "p",JoinType.INNER_JOIN);
			criteria.add(Restrictions.eq("p.id", filtro.getClasificacion()));
		}
		if (filtro.getOrden() != null) {
			if (filtro.getOrden().equals("Director")) {
				criteria.addOrder(Order.asc("director"));
			} else if (filtro.getOrden().equals("Titulo")) {
				criteria.addOrder(Order.asc("titulo"));
			}else if (filtro.getOrden().equals("Calificacion")) {
				criteria.addOrder(Order.desc("calificacion"));
			}

		}
		
		criteria
		.add(Restrictions.sqlRestriction("Month({alias}.fechaEstreno)<=?",fechaActual.getMonth()+1,new IntegerType()))
		.add(Restrictions.sqlRestriction("YEAR({alias}.fechaEstreno)<=?",fechaActual.getYear()+1900,new IntegerType()));
		return criteria.list();
	}
	
	@Override
	public List<Pelicula> buscarPeliculas(String titulo) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Pelicula.class)
				.add(Restrictions.ilike("titulo",titulo, MatchMode.ANYWHERE))
				.list();
	}

	@Override
	public Pelicula buscarPeliculaPorId(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Pelicula) session.createCriteria(Pelicula.class)
				.add(Restrictions.eq("id",id))
				.uniqueResult();
	}


	@Override
	public List<Pelicula> buscarPeliculasPorActor(String protagonista) {
		final Session session= sessionFactory.getCurrentSession();
		return session.createCriteria(Pelicula.class).add(Restrictions.eq("protagonista",protagonista))
				.list();
	}

	@Override
	public void guardarValoracionPelicula(int puntos, Pelicula pelicula, String comentario, Usuario usuario) {
		final Session session= sessionFactory.getCurrentSession();
		Valoracion valoracionPelicula = new Valoracion(puntos,pelicula,comentario,usuario);
		session.save(valoracionPelicula);
	}


	@Override
	public List<Valoracion> listarValoracionesPorPelicula(Pelicula pelicula) {
		final Session session= sessionFactory.getCurrentSession();
		return session.createCriteria(Valoracion.class)
				.add(Restrictions.eq("pelicula",pelicula))
				.list();
	}

	@Override
	public List<Pelicula> buscarPeliculaPorGenero(Genero genero) {
		final Session session= sessionFactory.getCurrentSession();
		return session.createCriteria(Pelicula.class)
				.add(Restrictions.eq("genero",genero))
				.list();
	}

	@Override
	public List<Pelicula> obtenerPeliculasSimilaresPorGenero(Genero genero, Pelicula pelicula) {
		 final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Pelicula.class)
				.add(Restrictions.eq("genero",genero))
				.add(Restrictions.ne("id",pelicula.getId()))
				.list();
	}




	@Override
	public List<Pelicula> getPeliculas() {
		final Session session = sessionFactory.getCurrentSession();
		Date fechaActual=new Date();
		return session.createCriteria(Pelicula.class)
				.add(Restrictions.sqlRestriction("Month({alias}.fechaEstreno)<=?",fechaActual.getMonth()+1,new IntegerType()))
				.add(Restrictions.sqlRestriction("YEAR({alias}.fechaEstreno)<=?",fechaActual.getYear()+1900,new IntegerType()))
				.addOrder(Order.desc("fechaEstreno"))
				.list();
	}

	@Override
	//Alias => Cada uno de los registros de la tabla
	// ? => Lugar  que va a ser remplazado x una variable
	
	public List<Pelicula> getEstrenosDelMes() {
		final Session session = sessionFactory.getCurrentSession();
		Date fechaActual=new Date();
	
		return (List<Pelicula>) session.createCriteria(Pelicula.class)
				.add(Restrictions.sqlRestriction("Month({alias}.fechaEstreno)=?",fechaActual.getMonth()+1,new IntegerType()))
				.add((Restrictions.sqlRestriction("year({alias}.fechaEstreno)=?",fechaActual.getYear()+1900,new IntegerType())))
				.addOrder(Order.desc("fechaEstreno"))
				.setMaxResults(4)
				.list();
	}
	
	
	@Override
	public List<Pelicula> getProximosEstrenos() {
		final Session session = sessionFactory.getCurrentSession();
		Date fechaActual=new Date();
	
		return  (List<Pelicula>) session.createCriteria(Pelicula.class)
				.add((Restrictions.sqlRestriction("year({alias}.fechaEstreno)>=?",fechaActual.getYear()+1900,new IntegerType())))
				.add(Restrictions.sqlRestriction("Month({alias}.fechaEstreno)>?",fechaActual.getMonth()+1,new IntegerType()))
				.setMaxResults(4)
				.addOrder(Order.asc("fechaEstreno"))
				.list();
	}


}
