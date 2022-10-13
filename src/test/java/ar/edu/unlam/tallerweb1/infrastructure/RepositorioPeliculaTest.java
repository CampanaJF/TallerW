package ar.edu.unlam.tallerweb1.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

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
		peli1.setFechaEstreno(new Date("2022/10/15") );
		peli1.setTitulo("Escalera al infierno");
		peli2.setFechaEstreno(new Date("2022/10/17") );
		peli2.setTitulo("El ladron de los siglos");
		peli3.setFechaEstreno(new Date("2021/10/01") );
		peli3.setTitulo("Can el volador");
		session().save(peli1);
		session().save(peli2);
		session().save(peli3);
		
	}
	
	
	
}
