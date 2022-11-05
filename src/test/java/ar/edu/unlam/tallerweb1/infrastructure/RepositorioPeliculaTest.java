package ar.edu.unlam.tallerweb1.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import ar.edu.unlam.tallerweb1.domain.pelicula.Valoracion;
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.PeliculaConEtiquetaDTO;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
import ar.edu.unlam.tallerweb1.domain.pelicula.Etiqueta;
import ar.edu.unlam.tallerweb1.domain.pelicula.EtiquetaPelicula;
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
	
		Pelicula P1 = givenPelicula("Can el volador","2022/08/22");
		Pelicula P2 = givenPelicula("Marvel DC","2022/10/24");
		Pelicula P3 = givenPelicula("Dragon ball super","2022/10/21");
		Pelicula P4 = givenPelicula("El conjuro","2022/10/12");
		Pelicula P5 = givenPelicula("Nop","2022/10/19");
			
		session().save(P1);
    	session().save(P2);
    	session().save(P3);
    	session().save(P4);
    	session().save(P5);
		
    	
    	List <EtiquetaPelicula> peliculas = whenSeListanTodasLasPeliculas();
    	
    	thenSeObtienenTodasLasPeliculas(peliculas);
		
	}
	
	@Test
    @Transactional
    @Rollback
	public void devolverPeliculasConEtiquetas() {
		givenPeliculasConEtiquetas();
		List<EtiquetaPelicula>peliculas=whenConsultoPeliculasConEtiquetas();
		thenSeObtienePeliculasConEtiquetas(peliculas);
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
		Pelicula pelicula3= givenTengoPeliculas("Indiana Jones",genero);

		//cuando consulto por la pelicula
		List<Pelicula> peliculasSimilares= whenSeListanPeliculasSimilaresPorGenero(genero,pelicula);
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

		List<Pelicula> peliculasSimilares= whenSeListanPeliculasSimilaresPorGenero(genero,pelicula);
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
	public void meDebeDevolverCeroValoracionesDeUnaPeliculaPorqueNoExisten(){

		//dado que existen peliculas
		Pelicula pelicula = givenExistePelicula();
		//cuando listo valoraciones
		List<Valoracion>valoraciones= whenConsultoPorCalificacionesDeUnaPelicula(pelicula);
		//entonces obtengo valoraciones
		thenObtengoCalificaciones(valoraciones,0);
	}
    @Test
	@Transactional
	@Rollback
	public void meDebeDevolverUnaListaDeValoracionesDeUnaPelicula(){

		Pelicula pelicula = givenExistePelicula();
			//dado que existen valoraciones de peliculas
		 givenExistenValoracionesDeUnaPelicula(5,pelicula,"Muy buena");
		 givenExistenValoracionesDeUnaPelicula(3,pelicula,"Excelente");
		 givenExistenValoracionesDeUnaPelicula(4,pelicula,"Malisima");
		// cuando listo las calficaciones
		List<Valoracion> calificaciones = whenConsultoPorCalificacionesDeUnaPelicula(pelicula);
		//obtengo calif de la pelicula
		thenObtengoCalificaciones(calificaciones,3);
	}
    

	
	private void givenPeliculasConEtiquetas() {
	Pelicula pelicula=givenPelicula("Dragon ball Super","2022/11/10");
	Etiqueta susto=crearEtiqueta("Susto");
	Etiqueta pelea=crearEtiqueta("Pelea");
	session().save(pelicula);
	EtiquetaPelicula etiquetaPelicula=crearEtiquetaPelicula(susto,pelicula);
	EtiquetaPelicula etiquetaPelicula2=crearEtiquetaPelicula(pelea,pelicula);
	
		
	}
	
	private void thenSeObtienePeliculasConEtiquetas(List<EtiquetaPelicula>peliculasConEtiquetas) {
		// TODO Auto-generated method stub
		assertEquals(1,peliculasConEtiquetas.size());
		
	}

	private List<EtiquetaPelicula> whenConsultoPeliculasConEtiquetas() {
		List<String>historialEtiquetas=new ArrayList<>();
		historialEtiquetas.add("Susto");
		historialEtiquetas.add("Pelea");
		List<EtiquetaPelicula>peliculasConEtiquetas=this.repositorioPelicula.obtenerPeliculasConEtiquetas(historialEtiquetas);
		
		return peliculasConEtiquetas;
	}
	
	
	
	
	

	private EtiquetaPelicula crearEtiquetaPelicula(Etiqueta susto, Pelicula pelicula) {
	EtiquetaPelicula etiquetaPelicula=new EtiquetaPelicula();
	etiquetaPelicula.setPelicula(pelicula);
	etiquetaPelicula.setEtiqueta(susto);
	session().save(etiquetaPelicula);
	return etiquetaPelicula;
	}

	private Etiqueta crearEtiqueta(String descripcion) {
		Etiqueta etiqueta=new Etiqueta();
		etiqueta.setDescripcion(descripcion);
		session().save(etiqueta);
		return etiqueta;
	}

	private void thenSeObtienenTodasLasPeliculas(List<EtiquetaPelicula> peliculas) {
		assertThat(peliculas.size()).isEqualTo(5);
		assertThat(peliculas.get(2).getPelicula().getTitulo()).isEqualTo("Dragon ball super");
		
	}
	
	private List<EtiquetaPelicula> whenSeListanTodasLasPeliculas() {
		Filtro filtro=new Filtro(null,null,null);
		return this.repositorioPelicula.getPeliculasFiltro(filtro);
		
	}
	
	
	private Pelicula givenPelicula(String titulo,String fecha) {
		Pelicula pelicula = new Pelicula();
		//pelicula.setId(new Random().nextLong());
		pelicula.setTitulo(titulo);
		pelicula.setFechaEstreno(new Date(fecha));
		return pelicula;
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


	private void thenObtengoCalificaciones(List<Valoracion> calificaciones, int cantidadEsperada) {
		assertThat(calificaciones).isNotNull();
		assertThat(calificaciones).hasSize(cantidadEsperada);
	}

	private void givenExistenValoracionesDeUnaPelicula(int puntos, Pelicula pelicula,String comentario) {
		Valoracion valoracion = new Valoracion();
		valoracion.setPuntos(puntos);
		valoracion.setPelicula(pelicula);
		valoracion.setComentario(comentario);
		session().save(valoracion);
	}

	private Pelicula givenExistePelicula() {
		Pelicula pelicula = new Pelicula();
		session().save(pelicula);
		return pelicula;
	}

	private List<Valoracion> whenConsultoPorCalificacionesDeUnaPelicula(Pelicula pelicula) {
		return this.repositorioPelicula.listarValoracionesPorPelicula(pelicula);
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
		pelicula.setProtagonista(protagonista);
		this.session().save(pelicula);
	}

	private void thenObtengoListaPeliculasSimilares(List<Pelicula> peliculasSimilares, int cantidadEsperada) {
        assertThat(peliculasSimilares).isNotNull();
		assertThat(peliculasSimilares).hasSize(cantidadEsperada);
	}

	private List<Pelicula> whenSeListanPeliculasSimilaresPorGenero(Genero genero, Pelicula pelicula) {
		return this.repositorioPelicula.obtenerPeliculasSimilaresPorGenero(genero,pelicula);
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
	



	@Test
	@Transactional
	@Rollback
	public void consultaQueDevuelveLosEstrenosDelMes() {
		givenQueHayPeliculasEstrenosCargadas();
		List<Pelicula> peliculas= whenConsultoPorLosEstrenos();
		
		thenObtengoEstrenos(peliculas,2);
		
		
		
	}

	private void thenObtengoEstrenos(List<Pelicula> peliculas, int i) {
		assertEquals(i,peliculas.size());
		
	}

	private List<Pelicula> whenConsultoPorLosEstrenos() {
		
		return repositorioPelicula.getEstrenosDelMes();
	}

	private void givenQueHayPeliculasEstrenosCargadas() {
		Pelicula peli1=new Pelicula();
		Pelicula peli2=new Pelicula();
		Pelicula peli3=new Pelicula();
		peli1.setFechaEstreno(new Date("2022/11/15") );
		peli1.setTitulo("Escalera al infierno");
		peli2.setFechaEstreno(new Date("2022/11/17") );
		peli2.setTitulo("El ladron de los siglos");
		peli3.setFechaEstreno(new Date("2021/11/01") );
		peli3.setTitulo("Can el volador");
		session().save(peli1);
		session().save(peli2);
		session().save(peli3);
		
	}
	
	
	

}

