package ar.edu.unlam.tallerweb1.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import ar.edu.unlam.tallerweb1.domain.pelicula.Valoracion;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.RepositorioPelicula;

public class RepositorioPeliculaTest extends SpringTest {
	
	@Inject
	private RepositorioPelicula repositorioPelicula;
	public static final String PELICULA_TITULO ="Back to the future";
	public static final String PELICULA_TITULO2 ="Indiana Jones: Raiders of the Lost Ark";
	@Test
    @Transactional
    @Rollback
	public void queSeListenTodasLasPeliculas() {
	
		Pelicula P1 = givenPelicula("1");
		Pelicula P2 = givenPelicula("2");
		Pelicula P3 = givenPelicula("3");
		Pelicula P4 = givenPelicula("4");
		Pelicula P5 = givenPelicula("5");
			
		session().save(P1);
    	session().save(P2);
    	session().save(P3);
    	session().save(P4);
    	session().save(P5);
		
    	
    	List <Pelicula> peliculas = whenSeListanTodasLasPeliculas();
    	
    	thenSeObtienenTodasLasPeliculas(peliculas);
		
	}
	
	private void thenSeObtienenTodasLasPeliculas(List<Pelicula> peliculas) {
		assertThat(peliculas.size()).isEqualTo(5);
		assertThat(peliculas.get(2).getTitulo()).isEqualTo("3");
		
	}

	
	private List<Pelicula> whenSeListanTodasLasPeliculas() {
		Filtro filtro=new Filtro(null,null,null);
		return this.repositorioPelicula.getPeliculasFiltro(filtro);
		
	}
	
	
	private Pelicula givenPelicula(String titulo) {
		Pelicula pelicula = new Pelicula();
		pelicula.setId(new Random().nextLong());
		pelicula.setTitulo(titulo);
		return pelicula;
	}

	@Test
	@Transactional
	@Rollback
	public void alBuscarUnaPeliculaPorSuTituloMeDevuelveUno(){

		givenQueHayPeliculasCargadas();

		List<Pelicula> peliculasList=whenConsultoPorLaPelicula(PELICULA_TITULO);

		thenObtengoUnaCantidadPeliculas(peliculasList, 1);
	}
	@Test
	@Transactional
	@Rollback
	public void alBuscarUnaPeliculaQueNoEstaCargadaMeDevuelveCero(){
		//dado que hay peliculas cargadas
		givenQueHayPeliculasCargadas();
		//cuando consulto por la pelicula
		List<Pelicula> peliculas= whenConsultoPorLaPelicula("Avatar");
		//entonces obtengo 0 porque no existe
		thenObtengoUnaCantidadPeliculas(peliculas,0);
	}
	@Test
	@Transactional
	@Rollback
	public void meDebeDevolverUnaListaDePeliculasSimilaresPorGenero(){
		//dado que hay peliculas cargadas

	    Genero genero= givenTengoGeneroDePelicula("Aventura");
		Pelicula pelicula= givenTengoPeliculas("Back to the future",genero);
		Pelicula pelicula2= givenTengoPeliculas("Indiana Jones: Raiders of the Lost Ark",genero);

		//cuando consulto por la pelicula
		List<Pelicula> peliculasSimilares= whenSeListanPeliculasSimilaresPorDescrpcionDeGenero(genero.getDescripcion());
		//entonces obtengo
	    thenObtengoListaPeliculasSimilares(peliculasSimilares,2);
	}
	@Test
	@Transactional
	@Rollback
	public void meDebeDevolverCeroAlConsultarPorPeliculasSimilaresPorGeneroYQueLasPeliculasNoTenganGeneroAsignado(){
		Genero genero= givenTengoGeneroDePelicula("Aventura");
		Pelicula pelicula= givenTengoPeliculas("Back to the future",null);
		Pelicula pelicula2= givenTengoPeliculas("Indiana Jones: Raiders of the Lost Ark",null);

		List<Pelicula> peliculasSimilares= whenSeListanPeliculasSimilaresPorDescrpcionDeGenero(genero.getDescripcion());
		thenObtengoListaPeliculasSimilares(peliculasSimilares,0);
	}
    @Test
	@Transactional
	@Rollback
	public void meDebeDevolverUnaPeliculaDeUnActorCuandoLoBusco(){
		//dado que hay un actor
		  givenHayUnActorEnUnaPelicula("Michael J. Fox");
		//cuando lo busco
          List<Pelicula> peliculasEncontradas = whenBuscoPeliculaPorActor("Michael J. Fox");
		//entonces obtengo un actor
         thenEncuentroPeliculasDeUnActor(peliculasEncontradas,1);
	}
	@Test
	@Transactional
	@Rollback
	public void meDebeDevolverUnaListaDeValoracionesDeUnaPelicula(){

		//dado que existen peliculas
		Pelicula pelicula = givenExistenPeliculas();
		//dado que existen valoraciones de peliculas
		givenExistenValoracionesDePeliculas(5,pelicula);
		givenExistenValoracionesDePeliculas(3,pelicula);
		//cuando listo valoraciones
		List<Valoracion>valoraciones= whenListoValoracionesDePeliculas(pelicula);
		//entonces obtengo valoraciones
		thenObtengoValoraciones(valoraciones,2);
	}
	@Test
	@Transactional
	@Rollback
	public void meDebeDevolverCeroValoracionesDeUnaPeliculaPorqueNoSeHicieronValoraciones(){

		//dado que existen peliculas
		Pelicula pelicula = givenExistenPeliculas();
		//cuando listo valoraciones
		List<Valoracion>valoraciones= whenListoValoracionesDePeliculas(pelicula);
		//entonces obtengo valoraciones
		thenObtengoValoraciones(valoraciones,0);
	}

	private void thenObtengoValoraciones(List<Valoracion> valoraciones,int cantidadEsperada) {
		assertThat(valoraciones).isNotNull();
		assertThat(valoraciones).hasSize(cantidadEsperada);
	}

	private List<Valoracion> whenListoValoracionesDePeliculas(Pelicula pelicula) {
		return this.repositorioPelicula.listarValoracionesPorPelicula(pelicula);
	}

	private Pelicula givenExistenPeliculas() {
		Pelicula pelicula=new Pelicula();
		this.session().save(pelicula);
		return pelicula;
	}

	private void givenExistenValoracionesDePeliculas(int estrellas, Pelicula pelicula) {
		Valoracion valoracion = new Valoracion(estrellas,pelicula);
		this.session().save(valoracion);
	}



	private void thenEncuentroPeliculasDeUnActor(List<Pelicula> peliculasEncontradas, int cantidadEsperada) {
		assertThat(peliculasEncontradas).isNotNull();
		assertThat(peliculasEncontradas).hasSize(cantidadEsperada);
	}

	private List<Pelicula> whenBuscoPeliculaPorActor(String protagonista) {
		return this.repositorioPelicula.buscarPeliculasPorActor(protagonista);
	}

	private void givenHayUnActorEnUnaPelicula(String protagonista) {
		Pelicula pelicula= new Pelicula();
		pelicula.setProtegonista(protagonista);
		this.session().save(pelicula);
	}

	private void thenObtengoListaPeliculasSimilares(List<Pelicula> peliculasSimilares, int cantidadEsperada) {
        assertThat(peliculasSimilares).isNotNull();
		assertThat(peliculasSimilares).hasSize(cantidadEsperada);
	}

	private List<Pelicula> whenSeListanPeliculasSimilaresPorDescrpcionDeGenero(String genero) {
		return this.repositorioPelicula.obtenerPeliculasSimilaresPorGenero(genero);
	}

	private Genero givenTengoGeneroDePelicula(String descripcion){
		Genero genero = new Genero();
		genero.setDescripcion(descripcion);
		this.session().save(genero);
		return genero;
	}
    private Pelicula givenTengoPeliculas(String titulo,Genero genero){
		Pelicula pelicula1 = new Pelicula();
		pelicula1.setGenero(genero);
		pelicula1.setTitulo(titulo);
		this.session().save(pelicula1);
		return pelicula1;
	}
	private void givenQueHayPeliculasCargadas(){
		Pelicula pelicula1 = new Pelicula();
		pelicula1.setTitulo(PELICULA_TITULO);
        this.session().save(pelicula1);

		Pelicula pelicula2 = new Pelicula();
		pelicula2.setTitulo(PELICULA_TITULO2);
		this.session().save(pelicula2);
	}
	private List<Pelicula> whenConsultoPorLaPelicula(String titulo){
		return this.repositorioPelicula.buscarPeliculas(titulo);
	}
    private void thenObtengoUnaCantidadPeliculas(List<Pelicula> peliculas, int cantidadEsperada){
		assertThat(peliculas).hasSize(cantidadEsperada);
	}


}
