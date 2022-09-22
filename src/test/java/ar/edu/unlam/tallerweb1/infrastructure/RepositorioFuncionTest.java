package ar.edu.unlam.tallerweb1.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.funcion.RepositorioFuncion;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;

public class RepositorioFuncionTest extends SpringTest{
	
	@Autowired
    private RepositorioFuncion repositorioFuncion;
	
	 @Test
	 @Transactional
	 @Rollback
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

		 
		 session().save(C1);
	     session().save(C2);
	     session().save(P1);
	     session().save(P2);
	     session().save(F1);
	     session().save(F2);
	     session().save(F3);
	     

	     
	     List<Funcion> funciones = whenSeListanTodasLasFunciones(C1.getId(),P1.getId());
	     
	     thenSeListanTodasLasFunciones(funciones);
	
	 }
	 
	 private List<Funcion> whenSeListanTodasLasFunciones(Long cine,Long pelicula) {

		return this.repositorioFuncion.getFuncionesDeUnCine(cine,pelicula);
	}

	private void thenSeListanTodasLasFunciones(List<Funcion> funciones) {
		assertThat(funciones.size()).isEqualTo(2);
		assertThat(funciones.get(1).getPelicula().getTitulo()).isEqualTo("Indiana Jones");
		
	}

	private Cine givenCine(String string) {
			Cine cine = new Cine();
			cine.setId(new Random().nextLong());
			cine.setNombre(string);
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
