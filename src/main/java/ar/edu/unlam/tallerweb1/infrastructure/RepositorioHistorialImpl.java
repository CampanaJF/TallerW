package ar.edu.unlam.tallerweb1.infrastructure;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.domain.historial.Historial;
import ar.edu.unlam.tallerweb1.domain.historial.RepositorioHistorial;
import ar.edu.unlam.tallerweb1.domain.pelicula.Etiqueta;
import ar.edu.unlam.tallerweb1.domain.pelicula.EtiquetaPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository("repositorioHistorial")
@Transactional
public class RepositorioHistorialImpl implements RepositorioHistorial{
	
	private SessionFactory sessionFactory;
	
	@Autowired
	private RepositorioHistorialImpl (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void guardarEnElHistorial(Historial historial) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		session.save(historial);
		
	}
	
	@Override
	public void actualizarHistorial(Historial historialActualizado) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		session.update(historialActualizado);
	}
	
	@Override
	public List<Historial> obtenerHistorial(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		Criterion rest1 = Restrictions.eq("usuario.id", usuario.getId());
		
		return session.createCriteria(Historial.class).add(rest1).list();
	}
	
	@Override
	public List<EtiquetaPelicula> obtenerEtiquetasDePelicula(Pelicula pelicula) {
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.eq("pelicula.id", pelicula.getId());
		
		return session.createCriteria(EtiquetaPelicula.class).add(rest1).list();

	}

	@Override
	public List<EtiquetaPelicula> obtenerPeliculasDeLaEtiqueta(Etiqueta etiqueta) {
		final Session session = sessionFactory.getCurrentSession();
		
		Criterion rest1 = Restrictions.eq("etiqueta.id", etiqueta.getId());

		return session.createCriteria(EtiquetaPelicula.class).add(rest1).list();
	}

	

}
