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
	public List<Historial> obtenerHistorial(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		Criterion rest1 = Restrictions.eq("usuario.id", usuario.getId());
		
		return session.createCriteria(Historial.class).add(rest1).list();
	}

	@Override
	public void agregarAlHistorial(Usuario usuario, List<Etiqueta> etiquetasDeLaPelicula) {
		final Session session = sessionFactory.getCurrentSession();
		
		for (Etiqueta etiqueta : etiquetasDeLaPelicula) {
			
			Historial historial = new Historial();
			
			historial.setUsuario(usuario);
			
			historial.setEtiqueta(etiqueta);
			
			session.save(historial);
		}
		
	}

	@Override
	public void actualizarHistorial(Usuario usuario, List<Etiqueta> nuevasEtiquetas) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		List<Historial> historialDelUsuario = obtenerHistorial(usuario);
		
		for (int i = 0; i < nuevasEtiquetas.size(); i++) {
			
		historialDelUsuario.get(i).setEtiqueta(nuevasEtiquetas.get(i));
		
		session.update(historialDelUsuario.get(i));
		
		}
				
	}

}
