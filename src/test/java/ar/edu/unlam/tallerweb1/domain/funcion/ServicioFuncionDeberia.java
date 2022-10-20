package ar.edu.unlam.tallerweb1.domain.funcion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.cine.Sala;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;

public class ServicioFuncionDeberia {
	
	private RepositorioFuncion repositorioFuncion= mock(RepositorioFuncion.class);
	
	private ServicioFuncionImpl servicioFuncion = new ServicioFuncionImpl(repositorioFuncion);
	
	public static Date fecha = new Date();
	
	public static Cine cine = new Cine();
	
	public static Pelicula pelicula = new Pelicula();
	
	public static Pelicula peliculaDos = new Pelicula();
	
	public static Sala sala = new Sala();
	
	// testear el formato de fecha tambien
	

	@Test
	public void listarTodasLasFuncionesDeUnCineParaLosSiguientesTresDias() {
		
		 cine = givenCine("Cine++");
		 pelicula = givenPelicula("Indiana Jones");
		 sala = givenSala(cine,"Sala A");
		 sala.setAsientosTotales(5);
			 
		 Funcion funcionUno = givenFuncionConHorario(givenFechaInvalida(),pelicula,sala);
		 Funcion funcionDos = givenFuncionConHorario(givenFechaInvalida(),pelicula,sala);
		 Funcion funcionTres = givenFuncionConHorario(givenFechaInvalida(),pelicula,sala);
		 Funcion funcionAlfa = givenFuncionConHorario(givenFechaValida(),pelicula,sala);
		 Funcion funcionBeta = givenFuncionConHorario(givenFechaValida(),pelicula,sala);
		 Funcion funcionCuatro = givenFuncionConHorario(givenFechaInvalida(),pelicula,sala);
		 Funcion funcionDelta = givenFuncionConHorario(givenFechaInvalida(),pelicula,sala);
		 Funcion funcionEpsilon = givenFuncionConHorario(givenFechaValida(),pelicula,sala);
		 Funcion funcionCinco = givenFuncionConHorario(givenFechaValida(),pelicula,sala);
		 
		 List<Funcion> funciones = new ArrayList<Funcion>();

			funciones.add(funcionUno);
			funciones.add(funcionDos);
			funciones.add(funcionTres);
			funciones.add(funcionAlfa);
			funciones.add(funcionBeta);
			funciones.add(funcionCuatro);
			funciones.add(funcionDelta);
			funciones.add(funcionEpsilon);
			funciones.add(funcionCinco);


		List <Funcion> funcionesObtenidas = whenSeListanTodasLasFuncionesDeLosSiguientesTresDias(cine.getId(),pelicula.getId(),funciones);
		
		thenSeListanTodasLasFuncionesDeLosSiguientesTresDias(cine.getId(),pelicula.getId(),funcionesObtenidas);
	}
	
	private void thenSeListanTodasLasFuncionesDeLosSiguientesTresDias(Long cine, Long pelicula, List<Funcion> funcionesObtenidas) {
		verify(repositorioFuncion,times(1)).getFuncionesDeUnCine(cine,pelicula);
		assertThat(funcionesObtenidas).isNotEmpty();
		assertThat(funcionesObtenidas.size()).isEqualTo(4);
		
	}

	private List<Funcion> whenSeListanTodasLasFuncionesDeLosSiguientesTresDias(Long cine, Long pelicula, List<Funcion> funciones) {
		
		for (int i = 0; i < funciones.size(); i++) {
			when(repositorioFuncion.getCantidadAsientosOcupados(funciones.get(i).getId())).thenReturn(1);		
		}
		
		when(repositorioFuncion.getFuncionesDeUnCine(cine,pelicula)).thenReturn(funciones);
		return this.servicioFuncion.obtenerLasFuncionesDeLosProximosTresDias(cine, pelicula);	
		
	}
	
	@Test
	public void validarSiLasFuncionesTienenAsientosDisponibles() {
		
		 cine = givenCine("Cine++");
		 pelicula = givenPelicula("Indiana Jones");
		 sala = givenSala(cine,"Sala A");
		 sala.setAsientosTotales(5);
			 
		 Funcion funcionUno = givenFuncionConHorario(givenFechaInvalida(),pelicula,sala);

		 
		 Boolean tieneAsientosDisponibles = whenSeValidaSiTieneAsientosDisponibles(funcionUno);

		 thenSeValidoSiTeniaAsientosDisponibles(tieneAsientosDisponibles);

	}
	
	

	private void thenSeValidoSiTeniaAsientosDisponibles(Boolean tieneAsientosDisponibles) {
		assertThat(tieneAsientosDisponibles).isFalse();
		
	}

	private Boolean whenSeValidaSiTieneAsientosDisponibles(Funcion funcionUno) {
		when(repositorioFuncion.getCantidadAsientosOcupados(funcionUno.getId())).thenReturn(5);
		return this.servicioFuncion.validarAsientosDisponibles(funcionUno);
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
	
	private Funcion givenFuncionConHorario(Date fechaFuncion,Pelicula pelicula,Sala sala) {
		
		Funcion funcion = new Funcion();
		
		funcion.setFecha(fechaFuncion);
		funcion.setPelicula(pelicula);
		funcion.setPrecio(599.99);
		funcion.setSala(sala);
		
		return funcion;
	}
	
	public Date givenFechaValida() {
		
		Date dt = new Date();
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 2);
		dt = c.getTime();
		
		return dt;
	}
	
	public Date givenFechaInvalida() {
		
		Date dt = new Date();
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 4);
		dt = c.getTime();
		
		return dt;
	}
	
//	private Date givenFecha(String fechaIngresada) {
//		String fechaDada = fechaIngresada;
//		
//		SimpleDateFormat formato = new SimpleDateFormat ("dd-MM-yyyy");
//	     
//	        try {
//				fecha= formato.parse(fechaDada);
//			}catch (ParseException e) {
//				e.printStackTrace();
//			}
//		
//	        return fecha;
//	}
	
//	private Funcion givenFuncion(Sala sala) {
//		Funcion funcion = new Funcion();
//		funcion.setId(new Random().nextLong());
//		funcion.setSala(sala);
//		return funcion;
//	}
	
	

}
