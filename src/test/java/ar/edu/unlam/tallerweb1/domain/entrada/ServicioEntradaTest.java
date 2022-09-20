package ar.edu.unlam.tallerweb1.domain.entrada;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.sala.Sala;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

public class ServicioEntradaTest {
	
	private RepositorioEntrada repositorioEntrada = mock(RepositorioEntrada.class);
	
	private ServicioEntradaImpl servicioEntrada = new ServicioEntradaImpl(repositorioEntrada);
	
	@Test
	public void queSePuedaComprarUnaEntrada() {
		
		Usuario U1 = givenUsuario(3L,"A");
    	
    	Pelicula P1 = givenPelicula("Indiana Jones");
    	
    	Sala S1 = givenSala("Sala 5");
    	
    	Funcion F1 = givenFuncion(P1,S1);
    	
    	Entrada E1 = givenEntrada(U1,F1);
    			
		whenSeCompraLaEntrada(E1);
		
		thenSeComproLaEntrada(E1);
	}
	
	private void thenSeComproLaEntrada(Entrada E1) {
		verify(repositorioEntrada,times(1)).comprarEntrada(E1);
		
	}

	private void whenSeCompraLaEntrada(Entrada E1) {
		this.servicioEntrada.comprarEntrada(E1);
		
	}
	
	@Test
	public void queSePuedanComprarMultiplesEntradasParaUnaFuncion() {
		
		Usuario U1 = givenUsuario(2L,"A");
    	
    	Pelicula P1 = givenPelicula("Indiana Jones");
    	
    	Sala S1 = givenSala("Sala 5");
    	
    	Funcion F1 = givenFuncion(P1,S1);
    	
    	Entrada E1 = givenEntrada(U1,F1);
    	Entrada E2 = givenEntrada(U1,F1);
    	Entrada E3 = givenEntrada(U1,F1);
    			
		whenSeCompranLasEntradas(E1,E2,E3);
		
		thenSeCompraronLasEntradas(E1,E2,E3);
	}
	
	private void whenSeCompranLasEntradas(Entrada e1, Entrada e2, Entrada e3) {
		this.servicioEntrada.comprarEntrada(e1);
		this.servicioEntrada.comprarEntrada(e2);
		this.servicioEntrada.comprarEntrada(e3);
		
	}

	private void thenSeCompraronLasEntradas(Entrada e1, Entrada e2, Entrada e3) {
		verify(repositorioEntrada,times(1)).comprarEntrada(e1);
		verify(repositorioEntrada,times(1)).comprarEntrada(e2);
		verify(repositorioEntrada,times(1)).comprarEntrada(e3);
		
	}

	@Test
	public void queSePuedanVerLasEntradasQueSeCompraronPorUnUsuario() {
		
		Usuario U1 = givenUsuario(1L,"A");
    	
    	Pelicula P1 = givenPelicula("Indiana Jones");
    	
    	Sala S1 = givenSala("Sala 5");
    	
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

	private Sala givenSala(String nombreSala) {
		Sala sala = new Sala();
		sala.setNombre(nombreSala);
		return sala;
	}

	private Pelicula givenPelicula(String titulo) {
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo(titulo);
		return pelicula;
	}

	private Funcion givenFuncion(Pelicula pelicula, Sala sala) {
		Funcion funcion = new Funcion();
		funcion.setPelicula(pelicula);
		funcion.setSala(sala);
		return funcion;
	}

	public Usuario givenUsuario(Long id,String nombre) {
    	Usuario usuario = new Usuario ();
    	usuario.setId(id);
    	usuario.setNombre(nombre);
    	return usuario;
    }

}
