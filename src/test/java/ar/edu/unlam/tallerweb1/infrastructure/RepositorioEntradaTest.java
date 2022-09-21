package ar.edu.unlam.tallerweb1.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.entrada.RepositorioEntrada;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;


public class RepositorioEntradaTest extends SpringTest {
	
	@Autowired
    private RepositorioEntrada repositorioEntrada;
	
	@Test
    @Transactional
    @Rollback
    public void queSePuedaComprarUnaEntrada() {
    	
    	Usuario U1 = givenUsuario("A");
    	
    	Pelicula P1 = givenPelicula("Indiana Jones");
    	
    	Cine S1 = givenCine("Cinee");
    	
    	Funcion F1 = givenFuncion(P1,S1);
    	
    	Entrada E1 = givenEntrada(U1,F1);
    
    	session().save(U1);
    	session().save(P1);
    	session().save(S1);
    	session().save(F1);
    	session().save(E1);

    	whenSeCompraUnaEntrada(E1);
    	
    	assertThat(repositorioEntrada.getEntrada(E1.getId(),F1.getId())).isNotNull();
    	assertThat(repositorioEntrada.getEntrada(E1.getId(),F1.getId()).getPelicula()).isEqualTo("Indiana Jones");
 	
    }
	
	private void whenSeCompraUnaEntrada(Entrada E1) {
		this.repositorioEntrada.comprarEntrada(E1);
		
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
		cine.setNombre(nombreCine);
		return cine;
	}

	private Pelicula givenPelicula(String titulo) {
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo(titulo);
		return pelicula;
	}

	private Funcion givenFuncion(Pelicula pelicula, Cine cine) {
		Funcion funcion = new Funcion();
		funcion.setPelicula(pelicula);
		funcion.setCine(cine);
		return funcion;
	}

	public Usuario givenUsuario(String nombre) {
    	Usuario usuario = new Usuario ();
    	usuario.setNombre(nombre);
    	return usuario;
    }

}
