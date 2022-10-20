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
import ar.edu.unlam.tallerweb1.domain.cine.Asiento;
import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.cine.Sala;
import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.funcion.RepositorioFuncion;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;


public class RepositorioFuncionDeberia extends SpringTest{
	
	@Autowired
    private RepositorioFuncion repositorioFuncion;
	
	public static Date fecha = new Date();
	
	@Test
	@Transactional
	@Rollback
	public void obtenerCantidadDeAsientosOcupados() {
		
		Cine cineUno = givenCine("cineUno");
		Sala salaUno = givenSala(cineUno,"salaUno");
		Pelicula pelicula1 = givenPelicula("Indiana Jones");
		
		Funcion funcion = givenFuncion(salaUno,fecha,pelicula1);
		
		Asiento asientoOcupado1 = givenAsientoOcupado();
		Asiento asientoOcupado2 = givenAsientoOcupado();
		Asiento asientoNoOcupado3 = givenAsientoNoOcupado();
		
		Entrada entrada1 = givenEntrada(funcion);
		Entrada entrada2 = givenEntrada(funcion);
		Entrada entrada3 = givenEntrada(funcion);
		
		entrada3.setAsiento(asientoNoOcupado3);
		asientoNoOcupado3.setEntrada(entrada3);
		
		entrada1.setAsiento(asientoOcupado1);
		asientoOcupado1.setEntrada(entrada1);
		
		entrada2.setAsiento(asientoOcupado2);
		asientoOcupado2.setEntrada(entrada2);
		
		session().save(entrada1);
		session().save(asientoOcupado1);
		session().save(entrada2);
		session().save(asientoOcupado2);
		session().save(entrada3);
		session().save(asientoNoOcupado3);
		
		
		
		Integer cantidadOcupados = whenSeObtieneLaCantidadDeAsientosOcupados(funcion);
		
		thenSeObtieneLaCantidadDeAsientosOcupados(cantidadOcupados);
		
	}
	
	
	private void thenSeObtieneLaCantidadDeAsientosOcupados(Integer cantidadOcupados) {
		assertThat(cantidadOcupados).isEqualTo(2);
		
	}


	private Integer whenSeObtieneLaCantidadDeAsientosOcupados(Funcion funcion) {

		return this.repositorioFuncion.getCantidadAsientosOcupados(funcion.getId());
	}


	@Test
	@Transactional
	@Rollback
	public void listarTodosLosCinesDondeSePuedeVerUnaPelicula() {
		
		 Cine cineUno = givenCine("cineUno");
		 Cine cineDos = givenCine("cineDos");
		 Cine cineTres = givenCine("tres");
		 Sala salaUno = givenSala(cineUno,"salaUno");
		 Sala salaDos = givenSala(cineUno,"salaDos");
		 Sala salaTres = givenSala(cineDos,"salaA");
		 Sala salaCuatro = givenSala(cineTres,"salaB");
		 Pelicula pelicula1 = givenPelicula("Indiana Jones");
		 Pelicula pelicula2 = givenPelicula("Back to the Future");
		 givenFuncion(salaUno,fecha,pelicula1);
		 givenFuncion(salaDos,fecha,pelicula2);
		 givenFuncion(salaTres,fecha,pelicula1);
		 givenFuncion(salaCuatro,fecha,pelicula2);
		 
		 List<Funcion> cines = whenSeListanTodosLosCinesDondeSePuedeVerUnaPelicula(pelicula2.getId());
		 
		 thenSeListanTodosLosCinesDondeSePuedeVerUnaPelicula(cines);
	}
	
	 private void thenSeListanTodosLosCinesDondeSePuedeVerUnaPelicula(List<Funcion> cines) {
		assertThat(cines.size()).isEqualTo(2);
		assertThat(cines.get(0).getSala().getCine().getNombreCine()).isEqualTo("cineUno");
		assertThat(cines.get(1).getSala().getCine().getNombreCine()).isEqualTo("tres");
		
	}

	private List<Funcion> whenSeListanTodosLosCinesDondeSePuedeVerUnaPelicula(Long id) {
		
		return this.repositorioFuncion.getCinesDeUnaPelicula(id);
	}

	 @Test
	 @Transactional
	 @Rollback
	 public void listarTodasLasFuncionesDeUnCineDeterminado() {
		 
		 givenHorario();
		 
		 Cine cineUno = givenCine("cineUno");
		 Cine cineDos = givenCine("cineDos");
		 Sala salaUno = givenSala(cineUno,"salaUno");
		 Sala salaDos = givenSala(cineDos,"salaDos");
		 Pelicula pelicula1 = givenPelicula("Indiana Jones");
		 Pelicula pelicula2 = givenPelicula("Back to the Future");
		 givenFuncion(salaUno,fecha,pelicula1);
		 givenFuncion(salaDos,fecha,pelicula2);
			 
	     List<Funcion> funciones = whenSeListanTodasLasFuncionesDeEsaPelicula(cineUno.getId(),pelicula1.getId());
	     
	     thenSeListanTodasLasFunciones(funciones);
	
	 }
	 
	 private List<Funcion> whenSeListanTodasLasFuncionesDeEsaPelicula(Long cine,Long pelicula) {

		return this.repositorioFuncion.getFuncionesDeUnCine(cine,pelicula);
	}

	private void thenSeListanTodasLasFunciones(List<Funcion> funciones) {
		assertThat(funciones.size()).isEqualTo(1);
		assertThat(funciones.get(0).getPelicula().getTitulo()).isEqualTo("Indiana Jones");
		
	}
	
	private Date givenHorario() {
		String fechaDada = "28-05-2029";
		
		SimpleDateFormat formato = new SimpleDateFormat ("dd-MM-yyyy");
	     

	        try {
				fecha= formato.parse(fechaDada);

		
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
	        return fecha;
	}

	private Cine givenCine(String string) {
			Cine cine = new Cine();
			cine.setId(new Random().nextLong());
			cine.setNombreCine(string);
			session().save(cine);
			return cine;
		}
	
	private Sala givenSala(Cine cine,String string) {
		Sala sala = new Sala();
		sala.setId(new Random().nextLong());
		sala.setCine(cine);
		sala.setNombreSala(string);
		session().save(sala);
		return sala;
	}
	
	private Pelicula givenPelicula(String titulo) {
		Pelicula pelicula = new Pelicula();
		pelicula.setId(new Random().nextLong());
		pelicula.setTitulo(titulo);
		session().save(pelicula);
		return pelicula;
	}
	
	private Funcion givenFuncion(Sala sala,Date fecha,Pelicula pelicula) {
		Funcion funcion = new Funcion();
		funcion.setSala(sala);
		funcion.setId(new Random().nextLong());
		funcion.setFecha(fecha);
		funcion.setPelicula(pelicula);
		session().save(funcion);
		return funcion;
	}
	
	private Asiento givenAsientoOcupado() {
		Asiento asiento = new Asiento();
		asiento.setOcupado(true);
		return asiento;
	}
	
	private Asiento givenAsientoNoOcupado() {
		Asiento asiento = new Asiento();
		asiento.setOcupado(false);
		return asiento;
	}
	
	private Entrada givenEntrada(Funcion funcion) {
		Entrada entrada = new Entrada();
		entrada.setFuncion(funcion);
		return entrada;
	}
	
	
	
	

}
