package ar.edu.unlam.tallerweb1.domain.entrada;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	public void cancelarUnaEntrada() {
		
		Long entrada = 5L;
		
		whenSeCancelaUnaReserva(entrada);
		
		thenSeCancelo(entrada);
	}
	
	private void thenSeCancelo(Long entrada) {
		verify(this.repositorioEntrada,times(1)).cancelarReserva(entrada);
		
	}

	private void whenSeCancelaUnaReserva(Long entrada) {
		this.servicioEntrada.cancelarReserva(entrada);
		
	}

	@Test
	public void listarEntradasVigentes() {
		
		Usuario usuario = givenUsuario(1L,"Okarin");
		
		List<Entrada> entradasVigentes = givenEntradasVigentes(3);
		List<Entrada> entradasNoVigentes = givenEntradasNoVigentes(3);
		
		List<Entrada> entradas = new ArrayList<>();
		
		entradas.addAll(entradasVigentes);
		entradas.addAll(entradasNoVigentes);
		
		List<Entrada> obtenidas= whenSeListanLasEntradas(usuario,entradas);
		
		thenSeObtienenSoloLasVigentes(obtenidas,usuario);
		
	}
	
	private void thenSeObtienenSoloLasVigentes(List<Entrada> obtenidas,Usuario usuario) {
		verify(this.repositorioEntrada,times(1)).getEntradasCompradasDelUsuario(usuario);
		assertThat(obtenidas.size()).isEqualTo(3);
		
	}

	private List<Entrada> whenSeListanLasEntradas(Usuario usuario, List<Entrada> entradas) {
		when(this.repositorioEntrada.getEntradasCompradasDelUsuario(usuario)).thenReturn(entradas);
		return this.servicioEntrada.obtenerEntradasVigentes(usuario);
		
	}

	@Test
	public void poderComprarSoloUnaEntrada() {
		
		Usuario usuario = givenUsuario(1L,"Okarin");
		
		Funcion funcion = givenFuncion();
		
		Integer cantidadDeAsientos = 1;
		
		List<Asiento> asientos = givenAsientosVacios(cantidadDeAsientos);
		
		List<Long> numeros = givenNumeros(cantidadDeAsientos);
				
		whenSeCompranEntradas(funcion,usuario,numeros,asientos);
		
		thenSeComproUnaEntrada(funcion,usuario,asientos.get(0));
			
	}


	@Test
	public void poderComprarMultiplesEntradas() {
		
		Usuario usuario = givenUsuario(1L,"Okarin");
		
		Funcion funcion = givenFuncion();
		
		Integer cantidadDeAsientos = 5;
				
		List<Asiento> asientos = givenAsientosVacios(cantidadDeAsientos);
		
		List<Long> numeros = givenNumeros(cantidadDeAsientos);
				
		whenSeCompranEntradas(funcion,usuario,numeros,asientos);
		
		thenSeCompranMultiplesEntradas(funcion,usuario,asientos);
			
	}


	@Test(expected = DatosEntradaInvalidaException.class)
	public void impedirComprarEntradasSiNoHayUsuarioValido() {
		
		Usuario usuario = null;
		
		Funcion funcion = givenFuncion();
		
		List<Asiento> asientos = givenAsientosVacios(1);
		
		List<Long> numeros = givenNumeros(1);
		
		whenSeCompranEntradas(funcion,usuario,numeros,asientos);
				
	}
	
	@Test(expected = DatosEntradaInvalidaException.class)
	public void impedirComprarEntradasSiLosDatosDeLaFuncionSonInvalidos() {
		
		Usuario usuario = givenUsuario(1L,"Okarin");
		
		Integer cantidadDeEntradas = 0;
		
		Funcion funcion = null;
		
		List<Asiento> asientos = givenAsientosVacios(cantidadDeEntradas);
		
		List<Long> numeros = givenNumeros(cantidadDeEntradas);
		
		whenSeCompranEntradasSinFuncion(funcion,usuario,numeros,asientos);
				
	}

	@Test(expected = ErrorDeAsientoException.class)
	public void impedirComprarEntradasSiNoSeIngresaronAsientos() {
		
		Usuario usuario = givenUsuario(1L,"Okarin");
		
		Funcion funcion = givenFuncion();
		
		List<Asiento> asientos = givenAsientosVacios(0);
		
		List<Long> numeros = givenNumeros(0);
		
		whenSeCompranEntradas(funcion,usuario,numeros,asientos);
				
	}
	
	@Test(expected = ErrorDeAsientoException.class)
	public void impedirComprarEntradasSiNoHaySuficientesAsientos() {
		
		Usuario usuario = givenUsuario(1L,"Okarin");
		
		Funcion funcion = givenFuncion();
		
		List<Asiento> asientos = givenAsientosVacios(5);
		
		List<Long> numeros = givenNumeros(5);
		
		whenSeCompranEntradasSinSuficientesAsientos(funcion,usuario,numeros,asientos);
				
	}
	
	private void whenSeCompranEntradas(Funcion funcion, Usuario usuario, List<Long> numeros, List<Asiento> asientos) {
		getAsientos(numeros,asientos);
		when(this.repositorioEntrada.getCantidadAsientosVacios(funcion.getId())).thenReturn(numeros.size());
		this.servicioEntrada.comprar(funcion,usuario,numeros);
		
	}
	
	
	private void whenSeCompranEntradasSinFuncion(Funcion funcion, Usuario usuario, List<Long> numeros,List<Asiento> asientos) {
		getAsientos(numeros,asientos);
		this.servicioEntrada.comprar(funcion,usuario,numeros);
		
	}
	
	private void whenSeCompranEntradasSinSuficientesAsientos(Funcion funcion, Usuario usuario, List<Long> numeros,List<Asiento> asientos) {
		getAsientos(numeros,asientos);
		when(this.repositorioEntrada.getCantidadAsientosVacios(funcion.getId())).thenReturn(3);
		this.servicioEntrada.comprar(funcion,usuario,numeros);
		
	}

	private void thenSeComproUnaEntrada(Funcion funcion,Usuario usuario,Asiento asiento) {
		verify(this.repositorioEntrada,times(1)).comprarEntrada(funcion,usuario,asiento);
	}
	

	private void thenSeCompranMultiplesEntradas(Funcion funcion,Usuario usuario,List<Asiento> asientos) {
		
		for (int i = 0; i < asientos.size(); i++) {
			verify(this.repositorioEntrada,times(1)).comprarEntrada(funcion,usuario,asientos.get(i));	
		}
		
		
	}
	
	private void getAsientos(List<Long> numeros,List<Asiento> asientos){
		
		for (int i = 0; i < numeros.size(); i++) {
			when(this.repositorioEntrada.getAsiento(numeros.get(i))).thenReturn(asientos.get(i));
			
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
	
	private List<Long> givenNumeros(Integer cantidad){
	
		List<Long> numeros = new ArrayList<>();
		
		for (int i = 0; i < cantidad; i++) {
			
			
			numeros.add(new Random().nextLong());		
		}
		
		return numeros;
	}
	
	private Funcion givenFuncion() {
		
		Funcion funcion = new Funcion();
		
		funcion.setId(new Random().nextLong());
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
	
	public Date givenFechaInvalida() {

		return new Date();
	}
	
	public Date givenFechaValida() {
		
		Date dt = new Date();
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 2);
		dt = c.getTime();
		
		return dt;
	}
	
	private List<Entrada> givenEntradasVigentes(Integer cantidad){
		
		List<Entrada> entradas = new ArrayList<>();
		
		for (int i = 0; i < cantidad; i++) {
			Entrada entrada = new Entrada();
			
			entrada.setFuncion(givenFuncionVigente());
			
			entradas.add(entrada);
		}
		
		return entradas;
		
	}
	
	private List<Entrada> givenEntradasNoVigentes(Integer cantidad){
		
		List<Entrada> entradas = new ArrayList<>();
		
		for (int i = 0; i < cantidad; i++) {
			Entrada entrada = new Entrada();
			
			entrada.setFuncion(givenFuncionNoVigente());
			
			entradas.add(entrada);
		}
		
		return entradas;
		
	}

	private Funcion givenFuncionVigente() {
		Funcion funcion = new Funcion();
		
		funcion.setId(new Random().nextLong());
		funcion.setPelicula(givenPelicula("Indiana Jones"));
		funcion.setPrecio(599.99);
		funcion.setFecha(givenFechaValida());
		funcion.setSala(givenSala(givenCine("Cinemax"),"Sala 42"));
		
		return funcion;
	}
	
	private Funcion givenFuncionNoVigente() {
		Funcion funcion = new Funcion();
		
		funcion.setId(new Random().nextLong());
		funcion.setPelicula(givenPelicula("Indiana Jones"));
		funcion.setPrecio(599.99);
		funcion.setFecha(givenFechaInvalida());
		funcion.setSala(givenSala(givenCine("Cinemax"),"Sala 42"));
		
		return funcion;
	}

}
