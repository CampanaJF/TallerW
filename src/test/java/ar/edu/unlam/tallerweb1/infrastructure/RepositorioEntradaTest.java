package ar.edu.unlam.tallerweb1.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

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


public class RepositorioEntradaTest extends SpringTest {
	
	@Autowired
    private RepositorioEntrada repositorioEntrada;
	
	@Test
    @Transactional
    @Rollback
    public void queSePuedaComprarUnaEntrada() {
    	
    	Usuario usuario1 = givenUsuario("A");
    	
    	Pelicula indianaJones = givenPelicula("Indiana Jones");
    	
    	Cine cineUno = givenCine("CineUno");
    	
    	Sala salaUno = givenSala(cineUno,"salaUno");
    	
    	Funcion funcion = givenFuncion(indianaJones,salaUno);
    	
    	Entrada entrada = givenEntrada(usuario1,funcion);
    
    	session().save(usuario1);
    	session().save(indianaJones);
    	session().save(cineUno);
    	session().save(salaUno);
    	session().save(funcion);
    	session().save(entrada);

    	whenSeCompraUnaEntrada(entrada);
    	
    	assertThat(repositorioEntrada.getEntrada(entrada.getId())).isNotNull();
    	assertThat(repositorioEntrada.getEntrada(entrada.getId()).getFuncion()
    											 .getPelicula().getTitulo()).isEqualTo("Indiana Jones");
 	
    }
	
	private void whenSeCompraUnaEntrada(Entrada E1) {
		this.repositorioEntrada.comprarEntrada(E1);
		
	}
	
	private Entrada givenEntrada(Usuario U1,Funcion F1) {
		Entrada entrada = new Entrada();
		entrada.setFuncion(F1);
		entrada.setUsuario(U1);
		entrada.setId(new Random().nextLong());
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

	private Funcion givenFuncion(Pelicula pelicula, Sala sala) {
		Funcion funcion = new Funcion();
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

	public Usuario givenUsuario(String nombre) {
    	Usuario usuario = new Usuario ();
    	usuario.setNombre(nombre);
    	return usuario;
    }

}
