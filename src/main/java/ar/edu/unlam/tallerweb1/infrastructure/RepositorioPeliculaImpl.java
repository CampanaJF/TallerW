package ar.edu.unlam.tallerweb1.infrastructure;

import java.util.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.pelicula.Valoracion;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
		Criteria criteria = session.createCriteria(Pelicula.class);
		if (filtro.getGenero() != null) {
			criteria.createAlias("genero", "g");
			criteria.add(Restrictions.eq("g.id", filtro.getGenero()));
		}
		if (filtro.getClasificacion() != null) {
			criteria.createAlias("clasificacionPelicula", "p");
			criteria.add(Restrictions.eq("p.id", filtro.getClasificacion()));
		}
		if (filtro.getOrden() != null) {
			if (filtro.getOrden().equals("genero")) {
				criteria.addOrder(Order.asc("genero"));
			} else if (filtro.getOrden().equals("clasificacion")) {
			}

		}
		return criteria.list();
	}
	/*
	@Override
	public List<Pelicula> getPeliculasFiltro(Filtro filtro) {
		
		final Session session = sessionFactory.getCurrentSession();
		String queryString="SELECT * from pelicula";
		
		if(filtro.getGenero()!=null && filtro.getClasificacion()!=null){
			queryString=queryString.concat(" WHERE genero_id =:genero AND clasificacionPelicula_id =:clasificacion");
		}
		else 
		if(filtro.getGenero()!=null) {
			queryString=queryString.concat(" WHERE genero_id =:genero");
			
		}
		else
		if(filtro.getClasificacion()!=null) {
			queryString=queryString.concat(" WHERE clasificacionPelicula_id =:clasificacion");
		}
		if(filtro.getOrden()!=null) {
			queryString=queryString.concat(" ORDER BY :orden");
		}
		
		Query query=session.createSQLQuery(queryString).addEntity(Pelicula.class);
		if(filtro.getGenero()!=null) query.setString("genero", filtro.getGenero().toString());
			
		if(filtro.getClasificacion()!=null)query.setString("clasificacion", filtro.getClasificacion().toString());
		if(filtro.getOrden()!=null)	query.setString("orden", filtro.getOrden().toString());
		
		return query.getResultList();
		
	}
*/
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
	public List<Pelicula> obtenerPeliculasSimilaresPorGenero(String genero) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Pelicula.class)
				.createAlias("genero","genero")
				.add(Restrictions.eq("genero.descripcion",genero))
				.list();
	}

	@Override
	public List<Pelicula> buscarPeliculasPorActor(String protagonista) {
		final Session session= sessionFactory.getCurrentSession();
		return session.createCriteria(Pelicula.class).add(Restrictions.eq("protagonista",protagonista))
				.list();
	}

	@Override
	public void guardarValoracionPelicula(int estrellas, Pelicula pelicula) {
		final Session session= sessionFactory.getCurrentSession();
		Valoracion valoracionPelicula = new Valoracion(estrellas,pelicula);
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
	public List<Pelicula> getPeliculas() {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Pelicula.class).list();
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

}
