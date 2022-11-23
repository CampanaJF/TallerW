package ar.edu.unlam.tallerweb1.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

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
import ar.edu.unlam.tallerweb1.domain.entrada.EntradaPendiente;
import ar.edu.unlam.tallerweb1.domain.entrada.RepositorioEntrada;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;


public class RepositorioEntradaDeberia extends SpringTest {
	
	@Autowired
    private RepositorioEntrada repositorioEntrada;
	
	
	@Test
	@Transactional
	@Rollback
	public void obtenerLasPendientesActivasDeUnUsuario() {
		
		Usuario usuario = givenUsuario("Okarin");
		
		Funcion funcion = givenFuncion();
		givenEntradasPendientesActivas(usuario,funcion);
		
	
		List<EntradaPendiente> entradas = whenSeBuscanLasPendientesActivasDelUsuario(usuario);
		
		thenSeObtuvieronLasPendientes(entradas);	
	}

	@Test
	@Transactional
	@Rollback
	public void obtenerLasPendientesDeUnaEntradaParaActualizarlas() {
		
		Funcion funcion = givenFuncion();
		Entrada entrada = givenEntrada(funcion);
		givenEntradasPendientes(funcion);
		
	
		List<EntradaPendiente> entradas = whenSeBuscanLasPendientes(entrada);
		
		thenSeObtuvieronLasPendientes(entradas);	
	}
	


	@Test
	@Transactional
	@Rollback
	public void obtenerLaEntradaAcomprarDeLasPendientes() {
		Funcion funcion = givenFuncion();
		givenEntrada(funcion);
		givenEntradaPendiente(funcion);
		
		List<Entrada> entradas = whenSeBuscanLasEntradasDeEsaFuncion(funcion);
		
		thenSeObtuvieronLasEntradas(entradas);	
	}
	
	@Test
	@Transactional
	@Rollback
	public void obtenerEntradasPendientes() {
		
		Funcion funcion = givenFuncion();
		Funcion funcionB = givenFuncion();
		Entrada entrada = givenEntrada(funcion);
		
		givenEntradasPendientes(funcion);
		givenEntradasPendientes(funcionB);
		
		List<EntradaPendiente> entradasPendientes = whenSeObtienenLasPendientes(entrada);
		
		thenSeObtuvieronLasPendientes(entradasPendientes);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void obtenerLaCantidadDeAsientosVaciosDeLaFuncion() {
		
		Funcion funcion = givenFuncion();
		
		Integer vacios = 5;
		
		givenAsientosVaciosYEntradas(funcion,vacios);
		
		givenAsientosOcupadosYEntradas(funcion,15);
		
		Integer obtenido = whenSeObtienenLaCantidadDeVacios(funcion);
		
		thenSeObtieneLaCantidadDeVacios(obtenido,vacios);
	}
	
	@Test
	@Transactional
	@Rollback
	public void obtenerTodasLasEntradasCompradasParaUnaFuncionPorUnUsuario() {
	
		Usuario usuario1 = givenUsuario("Jojo");
		
		Pelicula indianaJones = givenPelicula("Indiana Jones");
		Pelicula kungFuPanda = givenPelicula("Kung Fu Panda");
		
		Cine cineUno = givenCine("CineUno");
    	
    	Sala salaUno = givenSala(cineUno,"salaUno");
    	
    	Funcion funcion1= givenFuncion(kungFuPanda,salaUno);
    	Funcion funcion3 = givenFuncion(indianaJones,salaUno);
    	
    	givenEntradas(usuario1,funcion1,15L);
    	givenEntradas(usuario1,funcion3,5L);
    	
    	List<Entrada> entradasObtenidas = whenSeObtienenTodasLasEntradasDeUnUsuarioParaUnaDeterminaFuncion(usuario1,funcion3);
    	
    	thenSeObtienenTodasLasEntradasDeUnUsuarioParaUnaDeterminadaFuncion(entradasObtenidas);
	}
	
	@Test
	@Transactional
	@Rollback
	public void obtenerLaUltimaEntradaCompradaPorUnUsuarioParaUnaFuncion() {
		Usuario usuario1 = givenUsuario("Jojo");
		Usuario usuario2 = givenUsuario("Dio");
		
		Pelicula indianaJones = givenPelicula("Indiana Jones");
    	
    	Cine cineUno = givenCine("CineUno");
    	
    	Sala salaUno = givenSala(cineUno,"salaUno");
    	
    	Funcion funcion = givenFuncion(indianaJones,salaUno);
    	
    	givenEntrada(usuario1,funcion);
    	givenEntrada(usuario2,funcion);
    	Entrada entrada3 = givenEntrada(usuario2,funcion);
    	givenEntrada(usuario1,funcion);
    	
    	Entrada obtenida = whenSeObtieneLaUltimaEntradaCompradaDeUnUsuarioParaUnaFuncionDeterminada(usuario2,funcion);
    	
    	thenSeObtieneLaUltimaEntradaCompradaDeUnUsuario(obtenida,entrada3);	
	}
	
	@Test
    @Transactional
    @Rollback
    public void comprarUnaEntrada() {
		
    	Usuario usuario = givenUsuario("A");
    	  	
    	Funcion funcion = givenFuncion();
    	
    	Entrada entrada = givenAsientoVacioYEntrada(funcion);
    	  	
    	whenSeCompraUnaEntrada(funcion,usuario,entrada.getAsiento());
    	
    	thenSeComproLaEntrada(entrada);
    }
	
	private List<EntradaPendiente> whenSeBuscanLasPendientesActivasDelUsuario(Usuario usuario) {
		return this.repositorioEntrada.getPendientesActivasDelUsuario(usuario);
	}
	
	private List<EntradaPendiente> whenSeBuscanLasPendientes(Entrada entrada) {
		return this.repositorioEntrada.getPendientes(entrada.getId());
	}
	
	private void thenSeObtuvieronLasEntradas(List<Entrada> entradas) {
		assertThat(entradas.size()).isEqualTo(1);
		assertThat(entradas.get(0).getUsuario()).isNull();;	
	}

	private List<Entrada> whenSeBuscanLasEntradasDeEsaFuncion(Funcion funcion) {
		return this.repositorioEntrada.getEntradasCanceladas(funcion.getId());
	}
	
	private void thenSeObtuvieronLasPendientes(List<EntradaPendiente> entradasPendientes) {
		assertThat(entradasPendientes.size()).isEqualTo(5);	
	}

	private List<EntradaPendiente> whenSeObtienenLasPendientes(Entrada entrada) {
		return this.repositorioEntrada.getPendientes(entrada.getId());
	}
	
	private Integer whenSeObtienenLaCantidadDeVacios(Funcion funcion) {
		return this.repositorioEntrada.getCantidadAsientosVacios(funcion.getId());	
	}

	private void thenSeObtieneLaCantidadDeVacios(Integer obtenido,Integer vacios) {
		assertThat(obtenido).isEqualTo(vacios);	
	}

	private List<Entrada> whenSeObtienenTodasLasEntradasDeUnUsuarioParaUnaDeterminaFuncion(Usuario usuario,Funcion funcion){
		return this.repositorioEntrada.getEntradasCompradasDelUsuario(usuario.getId(),funcion.getId());	
	}

	private void thenSeObtienenTodasLasEntradasDeUnUsuarioParaUnaDeterminadaFuncion(List<Entrada> entradasObtenidas) {
		assertThat(entradasObtenidas.size()).isEqualTo(5);
		assertThat(entradasObtenidas.get(0).getFuncion().getPelicula().getTitulo()).isEqualTo("Indiana Jones");
	}

	private void thenSeObtieneLaUltimaEntradaCompradaDeUnUsuario(Entrada entradaObtenida,Entrada entradaOriginal) {
		assertThat(entradaObtenida.getId().equals(entradaOriginal.getId()));
		assertThat(entradaObtenida.getUsuario().equals(entradaOriginal.getUsuario()));
		assertThat(entradaObtenida.equals(entradaOriginal));		
	}

	private Entrada whenSeObtieneLaUltimaEntradaCompradaDeUnUsuarioParaUnaFuncionDeterminada(Usuario usuario,Funcion funcion){
		return this.repositorioEntrada.getEntradasCompradasDelUsuario(usuario.getId(),funcion.getId()).get(0);	
	}

	private void thenSeComproLaEntrada(Entrada entrada) {
		assertThat(entrada.getUsuario()).isNotNull();
		assertThat(entrada.getAsiento().getOcupado()).isTrue();	
	}

	private void whenSeCompraUnaEntrada(Funcion funcion,Usuario usuario,Asiento asiento) {
		this.repositorioEntrada.comprarEntrada(funcion,usuario,asiento);	
	}
	
	private void givenEntradas(Usuario U1,Funcion F1,Long cantidad){
		
		while(cantidad>0){
			givenEntrada(U1,F1);
			cantidad--;
		}
		
	}
	
	private void givenEntradasPendientesActivas(Usuario usuario,Funcion funcion) {
		
		for (int i = 0; i < 5; i++) {
			givenEntradaPendienteActiva(usuario,funcion);
			
		}
		
	}
	
	private void givenEntradasPendientes(Funcion funcion) {

		for (int i = 0; i < 5; i++) {
			givenEntradaPendiente(funcion);
			
		}
		
	}
	
	private EntradaPendiente givenEntradaPendienteActiva(Usuario usuario,Funcion funcion) {
		EntradaPendiente entradaPendiente = new EntradaPendiente();
		
		entradaPendiente.setActiva(true);
		entradaPendiente.setFuncion(funcion);
		entradaPendiente.setUsuario(usuario);
		
		session().save(entradaPendiente);
		
		return entradaPendiente;
		
	}
	
	private EntradaPendiente givenEntradaPendiente(Funcion funcion) {
		EntradaPendiente entradaPendiente = new EntradaPendiente();
		
		entradaPendiente.setFuncion(funcion);
		
		session().save(entradaPendiente);
		
		return entradaPendiente;	
	}
	
	private Entrada givenAsientoVacioYEntrada(Funcion funcion) {
				
			Entrada entrada = new Entrada();
			Asiento asiento = new Asiento();
			
			entrada.setAsiento(asiento);
			entrada.setFuncion(funcion);
			asiento.setEntrada(entrada);
			asiento.setOcupado(false);
			
			session().save(entrada);
			session().save(asiento);
			
			return entrada;				
	}
	
	private void givenAsientosVaciosYEntradas(Funcion funcion,Integer cantidad) {
		
		for (int i = 0; i < cantidad; i++) {
			
			Entrada entrada = new Entrada();
			Asiento asiento = new Asiento();
			
			entrada.setAsiento(asiento);
			entrada.setFuncion(funcion);
			asiento.setEntrada(entrada);
			asiento.setOcupado(false);
			
			session().save(entrada);
			session().save(asiento);			
		}	
	}
	
	private void givenAsientosOcupadosYEntradas(Funcion funcion,Integer cantidad) {
		
		for (int i = 0; i < cantidad; i++) {
			
			Entrada entrada = new Entrada();
			Asiento asiento = new Asiento();
			
			entrada.setAsiento(asiento);
			entrada.setFuncion(funcion);
			asiento.setEntrada(entrada);
			asiento.setOcupado(true);
			
			session().save(entrada);
			session().save(asiento);			
		}
	}
	
	private Entrada givenEntrada(Usuario U1,Funcion F1) {
		Entrada entrada = new Entrada();
		entrada.setFuncion(F1);
		entrada.setUsuario(U1);
		entrada.setId(new Random().nextLong());
		session().save(entrada);
		return entrada;
	}
	
	private Entrada givenEntrada(Funcion F1) {
		Entrada entrada = new Entrada();
		entrada.setFuncion(F1);
		entrada.setId(new Random().nextLong());
		session().save(entrada);
		return entrada;
	}

	private Cine givenCine(String nombreCine) {
		Cine cine = new Cine();
		cine.setNombreCine(nombreCine);
		session().save(cine);
		return cine;
	}

	private Pelicula givenPelicula(String titulo) {
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo(titulo);
		session().save(pelicula);
		return pelicula;
	}
	
	private Funcion givenFuncion() {
		Funcion funcion = new Funcion();

		session().save(funcion);
		return funcion;
	}
	
	private Funcion givenFuncion(Pelicula pelicula, Sala sala) {
		Funcion funcion = new Funcion();
		funcion.setPelicula(pelicula);
		funcion.setSala(sala);
		session().save(funcion);
		return funcion;
	}
	
	private Sala givenSala(Cine cine,String string) {
		Sala sala = new Sala();
		sala.setId(new Random().nextLong());
		sala.setCine(cine);
		sala.setNombreSala(string);
		session().save(sala);
		return sala;
	}

	public Usuario givenUsuario(String nombre) {
    	Usuario usuario = new Usuario ();
    	usuario.setNombre(nombre);
    	session().save(usuario);
    	return usuario;
    }

}
