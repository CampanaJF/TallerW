package ar.edu.unlam.tallerweb1.infrastructure;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.domain.clasificacionPelicula.ClasificacionPelicula;
import ar.edu.unlam.tallerweb1.domain.clasificacionPelicula.RepositorioClasificacion;
import ar.edu.unlam.tallerweb1.domain.genero.Genero;

@Repository("repositorioCalificacion")
public class RepositorioClasificacionImpl implements RepositorioClasificacion {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ClasificacionPelicula> getClasificaciones() {

		return sessionFactory.getCurrentSession()
				.createCriteria(ClasificacionPelicula.class).list();
	}

	@Override
	public ClasificacionPelicula getDescripcionById(Long id) {
		Session session = sessionFactory.getCurrentSession();

		return (ClasificacionPelicula) session
				.createCriteria(ClasificacionPelicula.class)
				.add(Restrictions.eq("id", id)).list().get(0);

	}

}
