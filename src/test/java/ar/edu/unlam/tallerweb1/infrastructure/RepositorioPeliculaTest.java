package ar.edu.unlam.tallerweb1.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.RepositorioPelicula;

public class RepositorioPeliculaTest extends SpringTest {
	
	@Autowired
	private RepositorioPelicula repositorioPelicula;
	
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
		return this.repositorioPelicula.getPeliculas();
		
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
		//dado que existen peliculas
		dadoQueHayPeliculasCargadas();
		//cuando se busca la pelicula
		List<Pelicula> peliculasList=cuandoConsultoPorLaPelicula("Back to the future");
		//entonces se obtiene una pelicula
		entoncesObtengoUnaPelicula(peliculasList, 1);
	}
	private void dadoQueHayPeliculasCargadas(){
		Pelicula pelicula1 = new Pelicula();
		pelicula1.setTitulo("Back to the future");
        this.session().save(pelicula1);

		Pelicula pelicula2 = new Pelicula();
		pelicula2.setTitulo("Indiana Jones: Raiders of the Lost Ark");
		this.session().save(pelicula2);
	}
	private List<Pelicula> cuandoConsultoPorLaPelicula(String titulo){
		return this.repositorioPelicula.buscarPeliculas(titulo);
	}
    private void entoncesObtengoUnaPelicula(List<Pelicula> peliculas, int cantidadEsperada){
		assertThat(peliculas).hasSize(cantidadEsperada);
	}
	@Test
	@Transactional
	@Rollback
	public void alBuscarUnaPeliculaQueNoEstaCargadaMeDevuelveCero(){
		//dado que hay peliculas cargadas
		dadoQueHayPeliculasCargadas();
		//cuando consulto por la pelicula
		List<Pelicula> peliculas= cuandoConsultoPorLaPelicula("Avatar");
		//entonces obtengo 0 porque no existe
		entoncesMeDevuelveCeroPeliculas(peliculas,0);
	}

	private void entoncesMeDevuelveCeroPeliculas(List<Pelicula> peliculas, int cantidadEsperada){
		assertThat(peliculas).hasSize(cantidadEsperada);
	}
}
