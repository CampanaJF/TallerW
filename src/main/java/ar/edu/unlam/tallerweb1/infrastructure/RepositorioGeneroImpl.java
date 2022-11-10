package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.genero.RepositorioGenero;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.GeneroUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("repositorioGenero")
@Transactional
public class RepositorioGeneroImpl implements RepositorioGenero {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioGeneroImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
    public List<Genero> getGeneros() {

        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Genero.class).list();
 
    }

	@Override
	public Genero getDescripcionById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		
	return (Genero) session.createCriteria(Genero.class)
			.add(Restrictions.eq("id", id)).list().get(0);
	}

    @Override
    public List<Genero> obtenerDescrpcionesGeneroPorId(Long idGenero) {
        Session session = sessionFactory.getCurrentSession();
      return  session.createCriteria(Genero.class)
                .add(Restrictions.eq("id", idGenero))
                .list();
    }
    @Override
    public void guardarGeneroElegidoPorUsuario(GeneroUsuario generoUsuario) {
        sessionFactory.getCurrentSession().save(generoUsuario);
    }

    @Override
    public Genero getGenero(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Genero) session.createCriteria(Genero.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }
}
