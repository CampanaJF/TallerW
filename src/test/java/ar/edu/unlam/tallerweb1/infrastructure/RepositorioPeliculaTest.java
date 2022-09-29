package ar.edu.unlam.tallerweb1.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

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
		return this.repositorioPelicula.getPeliculas(filtro);
		
	}
	
	
	private Pelicula givenPelicula(String titulo) {
		Pelicula pelicula = new Pelicula();
		pelicula.setId(new Random().nextLong());
		pelicula.setTitulo(titulo);
		return pelicula;
	}

}
