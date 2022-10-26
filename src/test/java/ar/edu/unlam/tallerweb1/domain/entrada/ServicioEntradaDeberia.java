package ar.edu.unlam.tallerweb1.domain.entrada;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.domain.cine.Asiento;
import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.cine.Sala;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.ErrorDeAsientoException;
import ar.edu.unlam.tallerweb1.exceptions.DatosEntradaInvalidaException;

public class ServicioEntradaDeberia {
	
	private RepositorioEntrada repositorioEntrada = mock(RepositorioEntrada.class);

	
	private ServicioEntradaImpl servicioEntrada = new ServicioEntradaImpl(repositorioEntrada);
	

	@Test
	public void poderComprarSoloUnaEntrada() {
		
		Usuario usuario = givenUsuario(1L,"Okarin");
		
		Funcion funcion = givenFuncion();
		
		Integer cantidadDeAsientos = 1;
		
		List<Asiento> asientos = givenAsientosVacios(cantidadDeAsientos);
				
		whenSeCompranEntradas(funcion,usuario,asientos);
		
		thenSeComproUnaEntrada(funcion,usuario,asientos.get(0));
			
	}


	@Test
	public void poderComprarMultiplesEntradas() {
		
		Usuario usuario = givenUsuario(1L,"Okarin");
		
		Funcion funcion = givenFuncion();
		
		Integer cantidadDeAsientos = 5;
				
		List<Asiento> asientos = givenAsientosVacios(cantidadDeAsientos);
				
		whenSeCompranEntradas(funcion,usuario,asientos);
		
		thenSeCompranMultiplesEntradas(funcion,usuario,asientos);
			
	}


	@Test(expected = DatosEntradaInvalidaException.class)
	public void impedirComprarEntradasSiNoHayUsuarioValido() {
		
		Usuario usuario = null;
		
		Funcion funcion = givenFuncion();
		
		List<Asiento> asientos = givenAsientosVacios(1);
		
		whenSeCompranEntradas(funcion,usuario,asientos);
				
	}
	
	@Test(expected = DatosEntradaInvalidaException.class)
	public void impedirComprarEntradasSiLosDatosDeLaFuncionSonInvalidos() {
		
		Usuario usuario = givenUsuario(1L,"Okarin");
		
		Integer cantidadDeEntradas = 0;
		
		Funcion funcion = null;
		
		List<Asiento> asientos = givenAsientosVacios(cantidadDeEntradas);
		
		whenSeCompranEntradasSinFuncion(funcion,usuario,asientos);
				
	}

	@Test(expected = ErrorDeAsientoException.class)
	public void impedirComprarEntradasSiNoSeIngresaronAsientos() {
		
		Usuario usuario = givenUsuario(1L,"Okarin");
		
		Funcion funcion = givenFuncion();
		
		List<Asiento> asientos = givenAsientosVacios(0);
		
		whenSeCompranEntradas(funcion,usuario,asientos);
				
	}
	
	@Test(expected = ErrorDeAsientoException.class)
	public void impedirComprarEntradasSiNoHaySuficientesAsientos() {
		
		Usuario usuario = givenUsuario(1L,"Okarin");
		
		Funcion funcion = givenFuncion();
		
		List<Asiento> asientos = givenAsientosVacios(5);
		
		whenSeCompranEntradasSinSuficientesAsientos(funcion,usuario,asientos);
				
	}
	
	private void whenSeCompranEntradas(Funcion funcion,Usuario usuario,List<Asiento> asiento) {
		when(this.repositorioEntrada.getCantidadAsientosVacios(funcion.getId())).thenReturn(asiento.size());
		this.servicioEntrada.comprar(funcion,usuario,asiento);
		
	}
	
	
	private void whenSeCompranEntradasSinFuncion(Funcion funcion, Usuario usuario, List<Asiento> asientos) {
		this.servicioEntrada.comprar(funcion,usuario,asientos);
		
	}
	
	private void whenSeCompranEntradasSinSuficientesAsientos(Funcion funcion, Usuario usuario, List<Asiento> asientos) {
		when(this.repositorioEntrada.getCantidadAsientosVacios(funcion.getId())).thenReturn(3);
		this.servicioEntrada.comprar(funcion,usuario,asientos);
		
	}

	private void thenSeComproUnaEntrada(Funcion funcion,Usuario usuario,Asiento asiento) {
		verify(this.repositorioEntrada,times(1)).comprarEntrada(funcion,usuario,asiento);
	}
	

	private void thenSeCompranMultiplesEntradas(Funcion funcion,Usuario usuario,List<Asiento> asientos) {
		
		for (int i = 0; i < asientos.size(); i++) {
			verify(this.repositorioEntrada,times(1)).comprarEntrada(funcion,usuario,asientos.get(i));	
		}
		
		
	}
	
	private List<Asiento> givenAsientosVacios(Integer cantidad){
		
		List<Asiento> asientos = new ArrayList<Asiento>();
		
		for (int i = 0; i < cantidad; i++) {
			
			Asiento asiento = givenAsientoVacio();
			
			asientos.add(asiento);		
		}
		
		return asientos;
	}
	
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
		
	private Asiento givenAsientoVacio() {
		Asiento asiento = new Asiento();
		
		asiento.setId(new Random().nextLong());
		asiento.setOcupado(true);
		
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
