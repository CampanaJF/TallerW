package ar.edu.unlam.tallerweb1.domain.entrada;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.delivery.DatosEntrada;
import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

public class ServicioEntradaTest {
	
	private RepositorioEntrada repositorioEntrada = mock(RepositorioEntrada.class);
	
	private ServicioEntradaImpl servicioEntrada = new ServicioEntradaImpl(repositorioEntrada);
	
	@Test
	public void queSePuedaComprarUnaEntrada() {
		
		Usuario U1 = givenUsuario(3L,"A");
    	
    	Pelicula P1 = givenPelicula("Indiana Jones");
    	
    	Cine S1 = givenCine("Sala 5");
    	
    	Funcion F1 = givenFuncion(P1,S1);
    	
    	DatosEntrada DE = givenDatosEntrada(F1,U1);
    		
    	Entrada entrada = whenSeCompraLaEntrada(DE);
		
		thenSeComproLaEntrada(entrada,F1);
	}
	
	private void thenSeComproLaEntrada(Entrada entrada,Funcion funcion) {
		assertThat(entrada.getFuncion()).isEqualTo(funcion);

		
	}

	private Entrada whenSeCompraLaEntrada(DatosEntrada DE) {
		
		return this.servicioEntrada.comprarEntrada(DE);
		
	}
	
	@Test
	public void queSePuedanComprarMultiplesEntradasParaUnaFuncion() {
		
		Usuario U1 = givenUsuario(2L,"A");
		Usuario U2 = givenUsuario(3L,"B");
    	
    	Pelicula P1 = givenPelicula("Indiana Jones");
    	
    	Cine S1 = givenCine("CINE 5");
    	
    	Funcion F1 = givenFuncion(P1,S1);
    	
    	DatosEntrada DE1 = givenDatosEntrada(F1,U1);
    	DatosEntrada DE2 = givenDatosEntrada(F1,U1);
    	DatosEntrada DE3 = givenDatosEntrada(F1,U2);
    	
		Entrada E1 = whenSeCompranLasEntradas(DE1);
    	Entrada E2 = whenSeCompranLasEntradas(DE2);
    	Entrada E3 = whenSeCompranLasEntradas(DE3);
    				
		thenSeCompraronLasEntradas(E1,E2,E3);
	}
	
	private Entrada whenSeCompranLasEntradas(DatosEntrada DE) {
		Entrada entrada =this.servicioEntrada.comprarEntrada(DE);

		return entrada;
	}

	private void thenSeCompraronLasEntradas(Entrada e1, Entrada e2, Entrada e3) {
		assertThat(e1.getFuncion().getCine().getNombreCine()).isEqualTo("CINE 5");
		assertThat(e2.getUsuario().getNombre()).isEqualTo("A");
		assertThat(e3.getUsuario().getNombre()).isEqualTo("B");

	}

	@Test
	public void queSePuedanVerLasEntradasQueSeCompraronPorUnUsuario() {
		
		Usuario U1 = givenUsuario(1L,"A");
    	
    	Pelicula P1 = givenPelicula("Indiana Jones");
    	
    	Cine S1 = givenCine("Sala 5");
    	
    	Funcion F1 = givenFuncion(P1,S1);
    	
    	Entrada E1 = givenEntrada(U1,F1);

    			
		whenSeConsultanLasEntradas(U1);
		
		thenSeObtienenLasEntradas(U1);
	}

	private void thenSeObtienenLasEntradas(Usuario u1) {
		verify(repositorioEntrada,times(1)).getEntradas(u1.getId());	
	}

	private void whenSeConsultanLasEntradas(Usuario u1) {
		this.servicioEntrada.getEntradas(u1.getId());	
	}

	private Entrada givenEntrada(Usuario U1,Funcion F1) {
		Entrada entrada = new Entrada();
		entrada.setFuncion(F1);
		entrada.setUsuario(U1);
		entrada.setPelicula(F1.getPelicula().getTitulo());
		return entrada;
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
		DatosEntrada DE = new DatosEntrada();
		DE.setFuncion(funcion);
		DE.setUsuario(usuario);
		return DE;
	}

	private Funcion givenFuncion(Pelicula pelicula, Cine cine) {
		Funcion funcion = new Funcion();
		funcion.setPelicula(pelicula);
		funcion.setCine(cine);
		return funcion;
	}

	public Usuario givenUsuario(Long id,String nombre) {
    	Usuario usuario = new Usuario ();
    	usuario.setId(id);
    	usuario.setNombre(nombre);
    	return usuario;
    }

}
