package ar.edu.unlam.tallerweb1.domain.funcion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.cine.Sala;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;

public class ServicioFuncionTest {
	
	private RepositorioFuncion repositorioFuncion= mock(RepositorioFuncion.class);
	
	private ServicioFuncionImpl servicioFuncion = new ServicioFuncionImpl(repositorioFuncion);
	
	@Test
	public void queSeListenTodasLasFuncionesDeUnCineParaUnaDeterminadaPelicula() {
		
		 Cine cineUno = givenCine("1");
		 Cine cineDos = givenCine("2");
		 Sala salaUno = givenSala(cineUno,"salaUno");
		 Sala salaDos = givenSala(cineUno,"salaDos");
		 Sala salaTres = givenSala(cineDos,"salaTres");
		 
		 Pelicula peliculaUno = givenPelicula("Indiana Jones");
		 Pelicula peliculaDos = givenPelicula("Back to the Future");
		 
		 Funcion funcionUno = givenFuncion(salaUno,cineUno);
		 Funcion funcionDos = givenFuncion(salaDos,cineUno);
		 Funcion funcionTres = givenFuncion(salaTres,cineDos);
		 
		 funcionUno.setPelicula(peliculaUno);
		 funcionTres.setPelicula(peliculaUno);
		 funcionDos.setPelicula(peliculaDos);
		 
		 List<Funcion> funciones = new ArrayList();

			funciones.add(funcionUno);
			funciones.add(funcionDos);
			funciones.add(funcionTres);
	
		whenSeListanTodasLasFunciones(cineUno.getId(),peliculaDos.getId(),funciones);
		
		thenSeListanTodasLasFunciones(cineUno.getId(),peliculaDos.getId(),funciones);
	}
	
	private void thenSeListanTodasLasFunciones(Long cine, Long pelicula,List<Funcion> funciones) {
		verify(repositorioFuncion,times(1)).getFuncionesDeUnCine(cine,pelicula);
		assertThat(funciones).isNotEmpty();
	}

	private void whenSeListanTodasLasFunciones(Long cine, Long pelicula,List<Funcion> funciones) {
		
		when(repositorioFuncion.getFuncionesDeUnCine(cine,pelicula)).thenReturn(funciones);
		this.servicioFuncion.getFuncionesDeUnCine(cine, pelicula);		
	}
	
	private Cine givenCine(String string) {
		Cine cine = new Cine();
		cine.setId(new Random().nextLong());
		cine.setNombreCine(string);
		return cine;
	}
	
	private Pelicula givenPelicula(String titulo) {
		Pelicula pelicula = new Pelicula();
		pelicula.setId(new Random().nextLong());
		pelicula.setTitulo(titulo);
		return pelicula;
	}
	
	private Sala givenSala(Cine cine,String string) {
		Sala sala = new Sala();
		sala.setId(new Random().nextLong());
		sala.setCine(cine);
		sala.setNombreSala(string);
		return sala;
	}
	
	private Funcion givenFuncion(Sala sala,Cine cine) {
		Funcion funcion = new Funcion();
		funcion.setId(new Random().nextLong());
		funcion.setSala(sala);
		funcion.setCine(cine);
		return funcion;
	}
	
	

}
