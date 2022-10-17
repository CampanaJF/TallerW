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
import ar.edu.unlam.tallerweb1.domain.cine.Sala;
import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
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
	
	private List<Entrada> whenSeObtienenTodasLasEntradasDeUnUsuarioParaUnaDeterminaFuncion(Usuario usuario,Funcion funcion){
		return this.repositorioEntrada.getEntradasCompradasDelUsuario(usuario.getId(),funcion.getId());
		
	}

	private void thenSeObtienenTodasLasEntradasDeUnUsuarioParaUnaDeterminadaFuncion(List<Entrada> entradasObtenidas) {
		assertThat(entradasObtenidas.size()).isEqualTo(5);
		assertThat(entradasObtenidas.get(0).getFuncion().getPelicula().getTitulo()).isEqualTo("Indiana Jones");
	
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
	
	private void thenSeObtieneLaUltimaEntradaCompradaDeUnUsuario(Entrada entradaObtenida,Entrada entradaOriginal) {
		assertThat(entradaObtenida.getId().equals(entradaOriginal.getId()));
		assertThat(entradaObtenida.getUsuario().equals(entradaOriginal.getUsuario()));
		assertThat(entradaObtenida.equals(entradaOriginal));		
	}

	private Entrada whenSeObtieneLaUltimaEntradaCompradaDeUnUsuarioParaUnaFuncionDeterminada(Usuario usuario,Funcion funcion){
		return this.repositorioEntrada.getEntradasCompradasDelUsuario(usuario.getId(),funcion.getId()).get(0);
		
	}

	@Test
    @Transactional
    @Rollback
    public void crearUnaEntrada() {
    	
    	Usuario usuario1 = givenUsuario("A");
    	
    	Pelicula indianaJones = givenPelicula("Indiana Jones");
    	
    	Cine cineUno = givenCine("CineUno");
    	
    	Sala salaUno = givenSala(cineUno,"salaUno");
    	
    	Funcion funcion = givenFuncion(indianaJones,salaUno);
    	
    	Entrada entrada = givenEntrada(usuario1,funcion);
    
    	whenSeCompraUnaEntrada(entrada);
    	
    	assertThat(repositorioEntrada.getEntrada(entrada.getId())).isNotNull();
    	assertThat(repositorioEntrada.getEntrada(entrada.getId()).getFuncion()
    											 .getPelicula().getTitulo()).isEqualTo("Indiana Jones");
 	
    }
	
	private void whenSeCompraUnaEntrada(Entrada E1) {
		this.repositorioEntrada.comprarEntrada(E1);
		
	}
	
	private void givenEntradas(Usuario U1,Funcion F1,Long cantidad){
		
		while(cantidad>0){
			givenEntrada(U1,F1);
			cantidad--;
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
