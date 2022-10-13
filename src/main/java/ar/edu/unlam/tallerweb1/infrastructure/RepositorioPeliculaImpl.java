package ar.edu.unlam.tallerweb1.infrastructure;

import java.util.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.pelicula.Valoracion;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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

}
