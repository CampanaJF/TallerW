package ar.edu.unlam.tallerweb1.infrastructure;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	public List<Pelicula> getPeliculas(Filtro filtro) {
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

}
