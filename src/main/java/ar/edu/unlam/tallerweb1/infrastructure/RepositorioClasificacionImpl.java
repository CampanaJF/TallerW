package ar.edu.unlam.tallerweb1.infrastructure;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.domain.clasificacionPelicula.ClasificacionPelicula;
import ar.edu.unlam.tallerweb1.domain.clasificacionPelicula.RepositorioClasificacion;

@Repository("repositorioCalificacion")
public class RepositorioClasificacionImpl implements RepositorioClasificacion {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ClasificacionPelicula> getClasificaciones() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM clasificacionpelicula")
				.addEntity(ClasificacionPelicula.class).list();
	}

	

}
