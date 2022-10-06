package ar.edu.unlam.tallerweb1.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.cine.Sala;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.funcion.RepositorioFuncion;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;

public class RepositorioFuncionTest extends SpringTest{
	
	@Autowired
    private RepositorioFuncion repositorioFuncion;
	
	public static Date horario = new Date();
	
	 @Test
	 @Transactional
	 @Rollback
	 public void queSeListenTodasLasFuncionesDeUnCineParaUnaDeterminadaPelicula() {
		 
		 givenHorario();
		 
		 Cine cineUno = givenCine("cineUno");
		 Cine cineDos = givenCine("cineDos");
		 Sala salaUno = givenSala(cineUno,"salaUno");
		 Sala salaDos = givenSala(cineDos,"salaDos");
		 Funcion F1 = givenFuncion(salaUno,horario);
		 Funcion F2 = givenFuncion(salaDos,horario);
		 Pelicula P1 = givenPelicula("Indiana Jones");
		 Pelicula P2 = givenPelicula("Back to the Future");
		 F1.setPelicula(P1);
		 F2.setPelicula(P2);
		 
		 
		 session().save(cineUno);
	     session().save(cineDos);
	     session().save(salaUno);
	     session().save(salaDos);
	     session().save(P1);
	     session().save(P2);
	     session().save(F1);
	     session().save(F2);

	     
	      
	     List<Funcion> funciones = whenSeListanTodasLasFuncionesDeEsaPelicula(salaUno.getId(),P1.getId());
	     
	     thenSeListanTodasLasFunciones(funciones);
	
	 }
	 
	 private List<Funcion> whenSeListanTodasLasFuncionesDeEsaPelicula(Long sala,Long pelicula) {

		return this.repositorioFuncion.getFuncionesDeUnCine(sala,pelicula);
	}

	private void thenSeListanTodasLasFunciones(List<Funcion> funciones) {
		assertThat(funciones.size()).isEqualTo(1);
		assertThat(funciones.get(0).getPelicula().getTitulo()).isEqualTo("Indiana Jones");
		
	}
	
	private Date givenHorario() {
		String fecha = "28-05-2029 17:00";
		
		SimpleDateFormat formato = new SimpleDateFormat ("dd-MM-yyyy HH:mm");
	     

	        try {
				horario= formato.parse(fecha);

		
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
	        return horario;
	}

	private Cine givenCine(String string) {
			Cine cine = new Cine();
			cine.setId(new Random().nextLong());
			cine.setNombreCine(string);
			return cine;
		}
	
	private Sala givenSala(Cine cine,String string) {
		Sala sala = new Sala();
		sala.setId(new Random().nextLong());
		sala.setCine(cine);
		sala.setNombreSala(string);
		return sala;
	}
	
	private Pelicula givenPelicula(String titulo) {
		Pelicula pelicula = new Pelicula();
		pelicula.setId(new Random().nextLong());
		pelicula.setTitulo(titulo);
		return pelicula;
	}
	
	private Funcion givenFuncion(Sala sala,Date horario) {
		Funcion funcion = new Funcion();
		funcion.setSala(sala);
		funcion.setId(new Random().nextLong());
		funcion.setHorario(horario);
		return funcion;
	}

}
