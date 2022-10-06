package ar.edu.unlam.tallerweb1.domain.funcion;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Random;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;

public class ServicioFuncionTest {
	
	private RepositorioFuncion repositorioFuncion= mock(RepositorioFuncion.class);
	
	private ServicioFuncionImpl servicioFuncion = new ServicioFuncionImpl(repositorioFuncion);
	
	@Test
	public void queSeListenTodasLasFuncionesDeUnCineParaUnaDeterminadaPelicula() {
		
		 Cine C1 = givenCine("1");
		 Cine C2 = givenCine("2");
		 Funcion F1 = givenFuncion(C1,12);
		 Funcion F2 = givenFuncion(C2,17);
		 Funcion F3 = givenFuncion(C1,18);
		 Pelicula P1 = givenPelicula("Indiana Jones");
		 Pelicula P2 = givenPelicula("Back to the Future");
		 F1.setPelicula(P1);
		 F3.setPelicula(P1);
		 F2.setPelicula(P2);
	
		
		List<Funcion> funciones = whenSeListanTodasLasFunciones(C2.getId(),P2.getId());
		
		thenSeListanTodasLasFunciones(C2.getId(),P2.getId());
	}
	
	private void thenSeListanTodasLasFunciones(Long cine, Long pelicula) {
		verify(repositorioFuncion,times(1)).getFuncionesDeUnCine(cine,pelicula);

	}

	private List<Funcion> whenSeListanTodasLasFunciones(Long cine, Long pelicula) {
		return this.servicioFuncion.getFuncionesDeUnCine(cine, pelicula);
		
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
	
	private Funcion givenFuncion(Cine cine,Integer horario) {
		Funcion funcion = new Funcion();
		funcion.setCine(cine);
		funcion.setId(new Random().nextLong());
		funcion.setHorario(horario);
		return funcion;
	}
	
	

}
