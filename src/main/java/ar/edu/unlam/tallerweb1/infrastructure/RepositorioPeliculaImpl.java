package ar.edu.unlam.tallerweb1.infrastructure;

import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Order.desc;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.in;
import static org.hibernate.criterion.Restrictions.ne;
import static org.hibernate.criterion.Restrictions.sqlRestriction;

import java.util.*;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.pelicula.Valoracion;
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.PeliculaConEtiquetaDTO;
import ar.edu.unlam.tallerweb1.domain.usuario.GeneroUsuario;
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
import ar.edu.unlam.tallerweb1.domain.pelicula.Etiqueta;
import ar.edu.unlam.tallerweb1.domain.pelicula.EtiquetaPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.RepositorioPelicula;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository("repositorioPelicula")
@Transactional
public class RepositorioPeliculaImpl implements RepositorioPelicula {

	private SessionFactory sessionFactory;

	@Autowired
	private RepositorioPeliculaImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<EtiquetaPelicula> getPeliculasFiltro(Filtro filtro) {
		final Session session = sessionFactory.getCurrentSession();
		Date fechaActual = new Date();
		Criteria criteria = session.createCriteria(EtiquetaPelicula.class);
		criteria.createAlias("pelicula", "pe", JoinType.INNER_JOIN);

		if (filtro.getGenero() != null) {
			criteria.createAlias("pe.genero", "g", JoinType.INNER_JOIN);
			criteria.add(eq("g.id", filtro.getGenero()));
		}
		if (filtro.getClasificacion() != null) {
			criteria.createAlias("pe.clasificacionPelicula", "p", JoinType.INNER_JOIN);
			criteria.add(eq("p.id", filtro.getClasificacion()));
		}
		if (filtro.getOrden() != null) {
			if (filtro.getOrden().equals("Director")) {
				criteria.addOrder(asc("pe.director"));
			} else if (filtro.getOrden().equals("Titulo")) {
				criteria.addOrder(asc("pe.titulo"));
			} else if (filtro.getOrden().equals("Calificacion")) {
				criteria.addOrder(desc("pe.calificacion"));

			}

		}

		criteria.add(sqlRestriction("Month(fechaEstreno)<=?", fechaActual.getMonth() + 1, new IntegerType()))
				.add(sqlRestriction("YEAR(fechaEstreno)<=?", fechaActual.getYear() + 1900, new IntegerType()))
				.addOrder(Order.desc("pe.id"));

		return criteria.list();
	}

	@Override
	public List<Pelicula> buscarPeliculas(String titulo) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Pelicula.class).add(Restrictions.ilike("titulo", titulo, MatchMode.ANYWHERE))
				.list();
	}

	@Override
	public Pelicula buscarPeliculaPorId(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Pelicula) session.createCriteria(Pelicula.class).add(eq("id", id)).uniqueResult();
	}

	@Override
	public List<Pelicula> buscarPeliculasPorActor(String protagonista) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Pelicula.class).add(eq("protagonista", protagonista)).list();
	}

	@Override
	public void guardarValoracionPelicula(int puntos, Pelicula pelicula, String comentario, Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		Valoracion valoracionPelicula = new Valoracion(puntos, pelicula, comentario, usuario);
		session.save(valoracionPelicula);
	}

	@Override
	public List<Valoracion> listarValoracionesPorPelicula(Pelicula pelicula) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Valoracion.class).add(eq("pelicula", pelicula)).list();
	}

	@Override
	public List<Pelicula> buscarPeliculaPorGenero(Genero genero) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Pelicula.class).add(eq("genero", genero)).list();
	}

	@Override
	public List<Pelicula> obtenerPeliculasSimilaresPorGenero(Genero genero, Pelicula pelicula) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Pelicula.class).add(eq("genero", genero)).add(ne("id", pelicula.getId())).list();
	}

	@Override
	public List<Pelicula> getPeliculas() {
		final Session session = sessionFactory.getCurrentSession();
		Date fechaActual = new Date();
		return session.createCriteria(Pelicula.class)
				.add(sqlRestriction("Month({alias}.fechaEstreno)<=?", fechaActual.getMonth() + 1, new IntegerType()))
				.add(sqlRestriction("YEAR({alias}.fechaEstreno)<=?", fechaActual.getYear() + 1900, new IntegerType()))
				.addOrder(desc("fechaEstreno")).list();
	}

	@Override
	// Alias => Cada uno de los registros de la tabla
	// ? => Lugar que va a ser remplazado x una variable

	public List<EtiquetaPelicula> getEstrenosDelMes() {
		final Session session = sessionFactory.getCurrentSession();
		Date fechaActual = new Date();

		return (List<EtiquetaPelicula>) session.createCriteria(EtiquetaPelicula.class)
				.createAlias("pelicula", "p", JoinType.INNER_JOIN)
				.add(sqlRestriction("Month(fechaEstreno)=?", fechaActual.getMonth() + 1, new IntegerType()))
				.add((sqlRestriction("year(fechaEstreno)=?", fechaActual.getYear() + 1900, new IntegerType())))
				.addOrder(desc("p.fechaEstreno")).addOrder(asc("p.id")).list();
	}

	@Override
	public List<EtiquetaPelicula> getProximosEstrenos() {
		final Session session = sessionFactory.getCurrentSession();
		Date fechaActual = new Date();

		return (List<EtiquetaPelicula>) session.createCriteria(EtiquetaPelicula.class)

				.createAlias("pelicula", "p", JoinType.INNER_JOIN)
				.add(Restrictions.disjunction()
						.add((sqlRestriction("year(fechaEstreno)>?", fechaActual.getYear() + 1900, new IntegerType())))
						.add(Restrictions.conjunction()
								.add(sqlRestriction("year(fechaEstreno)=?", fechaActual.getYear() + 1900,
										new IntegerType()))
								.add(sqlRestriction("Month(fechaEstreno)>?", fechaActual.getMonth() + 1,
										new IntegerType()))))

				.addOrder(desc("p.fechaEstreno")).addOrder(asc("p.id")).list();
	}

	@Override
	public List<EtiquetaPelicula> obtenerPeliculasConEtiquetas(List<String> historialDeEtiquetas) {
		final Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(EtiquetaPelicula.class);
		criteria.createAlias("etiqueta", "e", JoinType.INNER_JOIN);
		criteria.createAlias("pelicula", "p", JoinType.INNER_JOIN);

		criteria.add(in("e.descripcion", historialDeEtiquetas));

		return criteria.list();
	}

	public List<Genero> obtenerGeneroUsuario(Usuario usuario){
		final Session session = sessionFactory.getCurrentSession();

		 session.createCriteria(GeneroUsuario.class)
				 .add(Restrictions.eq("usuario",usuario))
		          .list();

		return null;
	}
}
