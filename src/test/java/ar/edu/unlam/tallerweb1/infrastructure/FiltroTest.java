package ar.edu.unlam.tallerweb1.infrastructure;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.clasificacionPelicula.ClasificacionPelicula;
import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
import ar.edu.unlam.tallerweb1.domain.pelicula.EtiquetaPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.RepositorioPelicula;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FiltroTest extends SpringTest {

	@Autowired
	private RepositorioPelicula repositorioPelicula;

	@Test
	@Transactional
	@Rollback
	public void verificarFiltroPorGenero() {
		givenQueHayPeliculasPorGenero();
		List<EtiquetaPelicula> peliculasObtenidasPorGeneroAccion = whenBuscoPeliculasPorGeneroAccion();
		List<EtiquetaPelicula> peliculasObtenidasPorGeneroTerror = whenBuscoPeliculasPorGeneroTerror();
		thenObtengoPeliculasPorGeneroAccion(peliculasObtenidasPorGeneroAccion);
		thenObtengoPeliculasPorGeneroTerror(peliculasObtenidasPorGeneroTerror);

	}

	@Test
	@Transactional
	@Rollback
	public void verificaFiltroPorClasificacion() {
		givenQueHayPeliculasPorClasificacion();
		List<EtiquetaPelicula> peliculasObtenidasPorClasificacionATP = whenBuscoPeliculasPorClasificacionATP();
		List<EtiquetaPelicula> peliculasObtenidasPorClasificacionMas16 = whenBuscoPeliculasPorClasificacionMas16();
		thenObtengoPeliculasPorClasificacionATP(peliculasObtenidasPorClasificacionATP);
		thenObtengoPeliculasPorClasificacionMas16(peliculasObtenidasPorClasificacionMas16);

	}

	@Test
	@Transactional
	@Rollback
	public void verificaFiltroCombinado() {
		givenQueHayPeliculas();
		List<EtiquetaPelicula> peliculasObtenidasPorClasificacionYGenero = whenBuscoPeliculasPorClasificacionYGenero();
		thenObtengoPeliculasPorClasificacionYGenero(peliculasObtenidasPorClasificacionYGenero);

	}

	@Test
	@Transactional
	@Rollback
	public void verificarQueTraigaPeliculasOrdenadasPorDirector() {
		givenHayPeliculasPorDirector();
		List<EtiquetaPelicula> peliculasObtenidasPorDirector = whenBuscoPeliculasPorDirector();
		thenObtengoPeliculasPorDirector(peliculasObtenidasPorDirector);
	}

	@Test
	@Transactional
	@Rollback
	public void verificarQueTraigaPeliculasOrdenadasPorTitulo() {
		givenHayPeliculasPorTitulo();
		List<EtiquetaPelicula> peliculasObtenidasPorTitulo = whenBuscoPeliculasPorTitulo();
		thenObtengoPeliculasPorTitulo(peliculasObtenidasPorTitulo);
	}

	@Test
	@Transactional
	@Rollback
	public void verificarQueTraigaPeliculasOrdenadasPorCalificacion() {
		givenHayPeliculasPorCalificacion();
		List<EtiquetaPelicula> peliculasObtenidasPorCalificacion = whenBuscoPeliculasPorCalificacion();
		thenObtengoPeliculasPorCalificacion(peliculasObtenidasPorCalificacion);
	}

	private void thenObtengoPeliculasPorCalificacion(List<EtiquetaPelicula> peliculasObtenidasPorCalificacion) {
		assertEquals(4, peliculasObtenidasPorCalificacion.size());
		assertEquals(5, peliculasObtenidasPorCalificacion.get(0).getPelicula().getCalificacion(), 0.1f);

	}

	private List<EtiquetaPelicula> whenBuscoPeliculasPorCalificacion() {
		List<EtiquetaPelicula> peliculasObtenidasPorCalificacion = repositorioPelicula
				.getPeliculasFiltro(Filtro.crearConOrden("Calificacion"));
		return peliculasObtenidasPorCalificacion;
	}

	private void givenHayPeliculasPorCalificacion() {
		Pelicula p1 = crearPelicula(null,new Date("2022/10/10"), null,5);
		Pelicula p2 = crearPelicula(null,new Date("2022/10/10"), null,2);
		Pelicula p3 = crearPelicula(null,new Date("2022/10/10"), null,1);
		Pelicula p4 = crearPelicula(null,new Date("2022/10/10"), null,4);

	}

	private void thenObtengoPeliculasPorTitulo(List<EtiquetaPelicula> peliculasObtenidasPorTitulo) {
		assertEquals(4, peliculasObtenidasPorTitulo.size());
		assertEquals("30 noches con mi ex", peliculasObtenidasPorTitulo.get(0).getPelicula().getTitulo());

	}

	private List<EtiquetaPelicula> whenBuscoPeliculasPorTitulo() {

		List<EtiquetaPelicula> peliculasObtenidasPorTitulo = repositorioPelicula
				.getPeliculasFiltro(Filtro.crearConOrden("Titulo"));
		return peliculasObtenidasPorTitulo;
	}

	private void givenHayPeliculasPorTitulo() {
		Pelicula p1 =crearPelicula("Bienvenidos al infierno",new Date("2022/10/10"), null, null);
		Pelicula p2 =crearPelicula("30 noches con mi ex",new Date("2022/10/10"), null, null);
		Pelicula p3 = crearPelicula("El paraiso",new Date("2022/10/10"), null, null);
		Pelicula p4 = crearPelicula("Nop",new Date("2022/10/10"), null, null);

	}

	private void thenObtengoPeliculasPorDirector(List<EtiquetaPelicula> peliculasObtenidasPorDirector) {
		assertEquals(4, peliculasObtenidasPorDirector.size());
		assertEquals("Adrian Suar", peliculasObtenidasPorDirector.get(0).getPelicula().getDirector());

	}

	private List<EtiquetaPelicula> whenBuscoPeliculasPorDirector() {
		List<EtiquetaPelicula> peliculasObtenidasPorDirector = repositorioPelicula
				.getPeliculasFiltro(Filtro.crearConOrden("Director"));
		return peliculasObtenidasPorDirector;
	}

	private void givenHayPeliculasPorDirector() {
		Pelicula p1 = crearPelicula(null,new Date("2022/10/10"),"Adrian Suar",null);
		Pelicula p2 = crearPelicula(null,new Date("2022/10/10"),"Lucas Combina",null);
		Pelicula p3 = crearPelicula(null,new Date("2022/10/10"),"Julio Chavez",null);
		Pelicula p4 = crearPelicula(null,new Date("2022/10/10"),"Tetsuro Kodama",null);

	}

	private void givenQueHayPeliculas() {
		Genero generoAccion=crearGenero("Accion");
		Genero generoTerror=crearGenero("Terror");
		
		ClasificacionPelicula clasificacionATP=crearClasificacion("ATP");
		ClasificacionPelicula clasificacionMas16=crearClasificacion("+16");

		Pelicula pelicula = crearPelicula("Jack en la caja maldita 2",new Date("2022/10/10"),null,null);
		Pelicula pelicula1 = crearPelicula("Dragon ball super",new Date("2022/10/10"),null,null);
		Pelicula pelicula2 = crearPelicula("Thor",new Date("2022/10/10"),null,null);

	
		pelicula.setGenero(generoAccion); // Genero Accion
		pelicula1.setGenero(generoTerror);// Genero Terror
		pelicula2.setGenero(generoAccion);// Genero Accion

		pelicula.setClasificacionPelicula(clasificacionATP);
		pelicula1.setClasificacionPelicula(clasificacionMas16);
		pelicula2.setClasificacionPelicula(clasificacionMas16); // +16

	}

	private ClasificacionPelicula crearClasificacion(String clasificacionPelicula) {
		ClasificacionPelicula clasificacion = new ClasificacionPelicula();	
		clasificacion.setDescripcion(clasificacionPelicula);
		session().save(clasificacion);
		return clasificacion;
	}

	private Genero crearGenero(String genero) {
		Genero genero1 = new Genero();
		genero1.setDescripcion(genero);
		session().save(genero1);
		return genero1;
		
	}
	private Pelicula crearPelicula(String titulo,Date fechaEstreno,String director,Integer calificacion) {
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo(titulo);
		pelicula.setFechaEstreno(fechaEstreno);
		pelicula.setDirector(director);
		pelicula.setCalificacion(calificacion);
		session().save(pelicula);
		return pelicula;
	}

	private List<EtiquetaPelicula> whenBuscoPeliculasPorClasificacionYGenero() {
		List<EtiquetaPelicula> peliculasObtenidasPorClasificacionYGenero = repositorioPelicula
				.getPeliculasFiltro(Filtro.crearConClasificacionYGenero(1L, 2L));
		return peliculasObtenidasPorClasificacionYGenero;
	}

	private void thenObtengoPeliculasPorClasificacionYGenero(List<EtiquetaPelicula> peliculasObtenidasPorClasificacionYGenero) {
		assertEquals(1, peliculasObtenidasPorClasificacionYGenero.size());
		assertEquals("Thor", peliculasObtenidasPorClasificacionYGenero.get(0).getPelicula().getTitulo());

	}

	private void thenObtengoPeliculasPorClasificacionMas16(List<EtiquetaPelicula> peliculasObtenidasPorClasificacionMas16) {
		assertEquals(1, peliculasObtenidasPorClasificacionMas16.size());

	}

	private void thenObtengoPeliculasPorClasificacionATP(List<EtiquetaPelicula> peliculasObtenidasPorClasificacionATP) {
		assertEquals(2, peliculasObtenidasPorClasificacionATP.size());

	}

	private List<EtiquetaPelicula> whenBuscoPeliculasPorClasificacionMas16() {

		List<EtiquetaPelicula> peliculasObtenidasPorClasificacionMas16 = repositorioPelicula
				.getPeliculasFiltro(Filtro.crearConClasificacion(4L));
		return peliculasObtenidasPorClasificacionMas16;

	}

	private List<EtiquetaPelicula> whenBuscoPeliculasPorClasificacionATP() {
		List<EtiquetaPelicula> peliculasObtenidasPorClasificacionATP = repositorioPelicula
				.getPeliculasFiltro(Filtro.crearConClasificacion(3L));
		return peliculasObtenidasPorClasificacionATP;

	}

	private void thenObtengoPeliculasPorGeneroTerror(List<EtiquetaPelicula> peliculasObtenidasPorGeneroTerror) {
		// TODO Auto-generated method stub
		assertEquals(2, peliculasObtenidasPorGeneroTerror.size());
	}

	private void thenObtengoPeliculasPorGeneroAccion(List<EtiquetaPelicula> peliculasObtenidasPorGeneroAccion) {

		assertEquals(1, peliculasObtenidasPorGeneroAccion.size());

	}

	private List<EtiquetaPelicula> whenBuscoPeliculasPorGeneroAccion() {
		List<EtiquetaPelicula> peliculasObtenidasPorGenero = repositorioPelicula.getPeliculasFiltro(Filtro.crearConGenero(3L));
		return peliculasObtenidasPorGenero;
	}

	private List<EtiquetaPelicula> whenBuscoPeliculasPorGeneroTerror() {
		List<EtiquetaPelicula> peliculasObtenidasPorGenero = repositorioPelicula.getPeliculasFiltro(Filtro.crearConGenero(4L));
		return peliculasObtenidasPorGenero;
	}

	private void givenQueHayPeliculasPorGenero() {
		Genero generoAccion = crearGenero("Accion");
		Genero generoTerror =crearGenero("Terror");
	
		Pelicula pelicula = crearPelicula("Jack en la caja maldita 2",new Date("2022/10/10"),null,null);
		Pelicula pelicula1 = crearPelicula("Dragon ball super",new Date("2022/10/10"),null,null);
		Pelicula pelicula2 = crearPelicula("Thor",new Date("2022/10/10"),null,null);
		
		pelicula.setGenero(generoAccion);
		pelicula1.setGenero(generoTerror);
		pelicula2.setGenero(generoTerror);

		
	}

	private void givenQueHayPeliculasPorClasificacion() {

		ClasificacionPelicula clasificacionATP=crearClasificacion("ATP");
		ClasificacionPelicula clasificacionMas16=crearClasificacion("+16");

		Pelicula pelicula = crearPelicula("Jack en la caja maldita 2",new Date("2022/10/10"),null,null);
		Pelicula pelicula1 = crearPelicula("Dragon ball super",new Date("2022/10/10"),null,null);
		Pelicula pelicula2 = crearPelicula("Thor",new Date("2022/10/10"),null,null);

		pelicula.setClasificacionPelicula(clasificacionATP);
		pelicula1.setClasificacionPelicula(clasificacionMas16);
		pelicula2.setClasificacionPelicula(clasificacionATP);

	}

}
