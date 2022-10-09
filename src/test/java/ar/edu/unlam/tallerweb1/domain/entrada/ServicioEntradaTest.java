package ar.edu.unlam.tallerweb1.domain.entrada;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Random;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.delivery.DatosEntrada;
import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.cine.Sala;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

public class ServicioEntradaTest {
	
	private RepositorioEntrada repositorioEntrada = mock(RepositorioEntrada.class);
	
	private ServicioEntradaImpl servicioEntrada = new ServicioEntradaImpl(repositorioEntrada);
	
	@Test
	public void queSePuedaComprarUnaEntrada() {
		
		Usuario usuario = givenUsuario(3L,"Dio");
    	
    	Pelicula pelicula = givenPelicula("Indiana Jones");
    	
    	Cine cine = givenCine("El Cine");
    	
    	Sala sala = givenSala(cine,"Sala 5");
    	
    	Funcion funcion = givenFuncion(pelicula,sala);
    	
    	DatosEntrada datosEntrada = givenDatosEntrada(funcion,usuario);
    	
    		
    	Entrada entrada = whenSeCompraLaEntrada(datosEntrada);
		
		thenSeComproLaEntrada(entrada,funcion);
	}
	
	private void thenSeComproLaEntrada(Entrada entrada,Funcion funcion) {
		assertThat(entrada.getFuncion()).isEqualTo(funcion);

		
	}

	
	@Test
	public void queSePuedanComprarMultiplesEntradasParaUnaFuncion() {
		
		Usuario usuario1 = givenUsuario(2L,"Usuarin");
		Usuario usuario2 = givenUsuario(3L,"Okarin");
    	
    	Pelicula pelicula = givenPelicula("Indiana Jones");
    	
    	Cine cine = givenCine("Cineee");
    	
    	Sala sala = givenSala(cine,"Sala 5");
    	
    	Funcion funcion = givenFuncion(pelicula,sala);
    	
    	DatosEntrada datosEntrada1 = givenDatosEntrada(funcion,usuario1);
    	DatosEntrada datosEntrada2 = givenDatosEntrada(funcion,usuario1);
    	DatosEntrada datosEntrada3 = givenDatosEntrada(funcion,usuario2);
    	
		Entrada entrada1 = whenSeCompraLaEntrada(datosEntrada1);
    	Entrada entrada2 = whenSeCompraLaEntrada(datosEntrada2);
    	Entrada entrada3 = whenSeCompraLaEntrada(datosEntrada3);
    				
		thenSeCompraronLasEntradas(entrada1,entrada2,entrada3);
	}
	
	private Entrada whenSeCompraLaEntrada(DatosEntrada DE) {
		Entrada entrada = new Entrada ();
		entrada.setFuncion(DE.getFuncion());
		entrada.setUsuario(DE.getUsuario());
		return entrada;
	}

	private void thenSeCompraronLasEntradas(Entrada entrada1, Entrada entrada2, Entrada entrada3) {
		assertThat(entrada1.getFuncion().getSala().getCine().getNombreCine()).isEqualTo("Cineee");
		assertThat(entrada2.getUsuario().getNombre()).isEqualTo("Usuarin");
		assertThat(entrada3.getUsuario().getNombre()).isEqualTo("Okarin");

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
	
	private DatosEntrada givenDatosEntrada(Funcion funcion, Usuario usuario) {
		DatosEntrada datosEntrada = new DatosEntrada();
		datosEntrada.setFuncion(funcion);
		datosEntrada.setUsuario(usuario);
		return datosEntrada;
	}

	private Funcion givenFuncion(Pelicula pelicula, Sala sala) {
		Funcion funcion = new Funcion();
		funcion.setId(new Random().nextLong());
		funcion.setPelicula(pelicula);
		funcion.setSala(sala);
		return funcion;
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
