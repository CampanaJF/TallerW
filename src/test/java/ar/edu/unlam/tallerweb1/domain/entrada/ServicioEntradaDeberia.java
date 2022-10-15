package ar.edu.unlam.tallerweb1.domain.entrada;

import static org.mockito.Mockito.*;

import java.util.Random;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.delivery.DatosEntrada;
import ar.edu.unlam.tallerweb1.domain.cine.Asiento;
import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.cine.Sala;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.AsientoNoDisponibleException;
import ar.edu.unlam.tallerweb1.exceptions.DatosEntradaInvalidaException;

public class ServicioEntradaDeberia {
	
	private RepositorioEntrada repositorioEntrada = mock(RepositorioEntrada.class);

	
	private ServicioEntradaImpl servicioEntrada = new ServicioEntradaImpl(repositorioEntrada);
	
	@Test
	public void poderComprarSoloUnaEntrada() {
		
		Usuario usuario = givenUsuario(1L,"Okarin");
		
		Funcion funcion = givenFuncion();
		
		Integer cantidadDeEntradas = 1;
		
		DatosEntrada datosEntrada = givenDatosEntrada(funcion,usuario,cantidadDeEntradas);
		
		whenSeCompraUnaEntrada(datosEntrada);
		
		thenSeComproUnaEntrada();
			
	}
	
	private void whenSeCompraUnaEntrada(DatosEntrada datosEntrada) {
		this.servicioEntrada.comprar(datosEntrada.getFuncion(),datosEntrada.getUsuario(),datosEntrada.getCantidad());
		
	}

	private void thenSeComproUnaEntrada() {
		verify(this.repositorioEntrada,times(1)).comprarEntrada(anyObject());
	}
	
	@Test
	public void poderComprarMultiplesEntradas() {
		
		Usuario usuario = givenUsuario(1L,"Okarin");
		
		Funcion funcion = givenFuncion();
		
		Integer cantidadDeEntradas = 5;
		
		DatosEntrada datosEntrada = givenDatosEntrada(funcion,usuario,cantidadDeEntradas);
			
		whenSeCompranMultiplesEntradas(datosEntrada);
		
		thenSeCompranMultiplesEntradas();
			
	}
	
	private void whenSeCompranMultiplesEntradas(DatosEntrada datosEntrada) {
		this.servicioEntrada.comprar(datosEntrada.getFuncion(),datosEntrada.getUsuario(),datosEntrada.getCantidad());
		
	}

	private void thenSeCompranMultiplesEntradas() {
		verify(this.repositorioEntrada,times(5)).comprarEntrada(anyObject());
		
	}

	@Test(expected = DatosEntradaInvalidaException.class)
	public void impedirComprarEntradasSiNoSeEspecificaLaCantidad() {
		
		Usuario usuario = givenUsuario(1L,"Okarin");
		
		Funcion funcion = givenFuncion();
		
		Integer cantidadDeEntradas = 0;
		
		DatosEntrada datosEntrada = givenDatosEntrada(funcion,usuario,cantidadDeEntradas);
		
		whenSeCompraUnaEntrada(datosEntrada);
				
	}
	
	@Test(expected = DatosEntradaInvalidaException.class)
	public void impedirComprarEntradasSiLosDatosDeLaFuncionSonInvalidos() {
		
		Usuario usuario = givenUsuario(1L,"Okarin");
		
		Integer cantidadDeEntradas = 0;
		
		DatosEntrada datosEntrada = givenDatosEntrada(null,usuario,cantidadDeEntradas);
		
		whenSeCompraUnaEntrada(datosEntrada);
				
	}
	

//	@Test(expected = AsientoNoDisponibleException.class)
//	public void queNoSePuedaComprarUnaEntradaSiYaNoHayAsientosDisponiblesParaEsaFuncion() {
//		
//		Usuario usuario = givenUsuario(1L,"Okarin");
//		
//		Funcion funcion = givenFuncionCompletaConAsientosDisponible(1L);
//		
//		Long cantidadDeEntradas = 1L;
//		
//		DatosEntrada datosEntrada = givenDatosEntrada(funcion,usuario,cantidadDeEntradas);
//		
//		whenSeCompraUnaEntrada(datosEntrada);
//		
//		whenSeCompraUnaEntrada(datosEntrada);
//			
//	}
	
//	@Test
//	public void queSeObtengaLaCantidadDeAsientosRestantesDeUnaFuncion() {
//		Funcion funcion = givenFuncionCompletaConAsientosDisponible(1L);
//		
//		Long asientosDisponibles = whenSeObtieneLaCantidadDeAsientosDisponibles(funcion);
//		
//		thenSeObtieneLaCantidadDeAsientosDisponibles(asientosDisponibles);
//	}
//	
//	private Long whenSeObtieneLaCantidadDeAsientosDisponibles(Funcion funcion) {
//		Long asientosDisponibles = this.servicioEntrada.cantidadDeAsientosDisponiblesDeLaFuncion(funcion.getId());
//		
//		return asientosDisponibles;
//	}
//
//	private void thenSeObtieneLaCantidadDeAsientosDisponibles(Long asientosDisponibles) {
//		assertThat(asientosDisponibles).isEqualTo(1L);
//		
//	}

//	private Funcion givenFuncionCompletaConAsientosDisponible(Long asientos) {
//		
//		Funcion funcion = new Funcion();
//		Sala sala = givenSala(givenCine("Cinemax"),"Sala 42");
//		sala.setAsientosTotales(asientos);
//		
//		List <Asiento> asientosFuncion = new ArrayList<>();
//		
//		while(asientos>0L) {
//			asientosFuncion.add(givenAsientoNoOcupado(sala));
//			asientos--;
//		}
//		
//		funcion.setPelicula(givenPelicula("Indiana Jones"));
//		funcion.setPrecio(599.99);
//		funcion.setSala(sala);
//		
//		return funcion;
//	}
	
	private Funcion givenFuncion() {
		
		Funcion funcion = new Funcion();
		
		funcion.setPelicula(givenPelicula("Indiana Jones"));
		funcion.setPrecio(599.99);
		funcion.setSala(givenSala(givenCine("Cinemax"),"Sala 42"));
		
		return funcion;
	}

	private Cine givenCine(String nombreCine) {
		Cine cine = new Cine();
		cine.setNombreCine(nombreCine);
		return cine;
	}

	private Pelicula givenPelicula(String titulo) {
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo(titulo);
		return pelicula;
	}
	
	private DatosEntrada givenDatosEntrada(Funcion funcion, Usuario usuario,Integer cantidad) {
		DatosEntrada datosEntrada = new DatosEntrada();
		datosEntrada.setFuncion(funcion);
		datosEntrada.setUsuario(usuario);
		datosEntrada.setCantidad(cantidad);
		return datosEntrada;
	}
	
	private Asiento givenAsientoOcupado(Sala sala) {
		Asiento asiento = new Asiento();
		asiento.setId(new Random().nextLong());
		asiento.setSala(sala);
		asiento.setOcupado(true);
		
		return asiento;
	}
	
	private Asiento givenAsientoNoOcupado(Sala sala) {
		Asiento asiento = new Asiento();
		asiento.setId(new Random().nextLong());
		asiento.setSala(sala);
		asiento.setOcupado(false);
		
		return asiento;
	}
	
	private Sala givenSala(Cine cine,String string) {
		Sala sala = new Sala();
		sala.setId(new Random().nextLong());
		sala.setCine(cine);
		sala.setNombreSala(string);
		return sala;
	}

	public Usuario givenUsuario(Long id,String nombre) {
    	Usuario usuario = new Usuario ();
    	usuario.setId(id);
    	usuario.setNombre(nombre);
    	return usuario;
    }

}
