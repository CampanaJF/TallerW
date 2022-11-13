package ar.edu.unlam.tallerweb1.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.historial.Historial;
import ar.edu.unlam.tallerweb1.domain.historial.RepositorioHistorial;
import ar.edu.unlam.tallerweb1.domain.pelicula.Etiqueta;
import ar.edu.unlam.tallerweb1.domain.pelicula.EtiquetaPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;


public class RepositorioHistorialDeberia extends SpringTest{

	@Autowired
    private RepositorioHistorial repositorioHistorial;

	
	@Test
	@Transactional
	@Rollback
	public void guardarNuevasEntradasAlHistorial() {
		
		Usuario usuario = givenUsuario();
		
		Etiqueta etiqueta = givenEtiqueta();
		
		Historial historialNuevo = new Historial();
		
		historialNuevo.setEtiqueta(etiqueta);
		historialNuevo.setUsuario(usuario);
		
		whenSeGuardaElNuevoHistorial(historialNuevo);
		
		thenSeGuardoElHistorial(usuario);
	}
	
	
	private void thenSeGuardoElHistorial(Usuario usuario) {
		assertThat(historialUsuario(usuario)).isNotEmpty();
		assertThat(historialUsuario(usuario)).hasSize(1);
	}

	private void whenSeGuardaElNuevoHistorial(Historial historialNuevo) {
		this.repositorioHistorial.guardarEnElHistorial(historialNuevo);	
	}


	private void thenSeObtienenLasPeliculas(List<EtiquetaPelicula> peliculas) {
		assertThat(peliculas.get(0).getPelicula().getTitulo()).isEqualTo("segunda");
		assertThat(peliculas.get(1).getPelicula().getTitulo()).isEqualTo("tercera");
		
	}

	private List<EtiquetaPelicula> whenSeObtienenPeliculas(Etiqueta etiqueta) {
		return this.repositorioHistorial.obtenerPeliculasDeLaEtiqueta(etiqueta);
		
	}


	
	private void thenSeActualizoElHistorial(Usuario usuario,List<Etiqueta> etiquetasNuevas) {
		assertThat(repositorioHistorial.obtenerHistorial(usuario).size()).isEqualTo(6);
	//	assertThat(repositorioHistorial.obtenerHistorial(usuario).containsAll(etiquetasNuevas)).isTrue();
		
	}

	private void thenSeAgregoAlHistorial(Usuario usuario) {
		assertThat(repositorioHistorial.obtenerHistorial(usuario).size()).isEqualTo(3);
		
	}

	
	private List<Historial> historialUsuario(Usuario usuario){
		return this.repositorioHistorial.obtenerHistorial(usuario);
	}
	
	public EtiquetaPelicula givenEtiquetaPelicula(Etiqueta etiqueta, Pelicula pelicula) {
		
		EtiquetaPelicula etiquetaPelicula = new EtiquetaPelicula();
		
		etiquetaPelicula.setEtiqueta(etiqueta);
		etiquetaPelicula.setPelicula(pelicula);
		
		session().save(etiquetaPelicula);
		
		return etiquetaPelicula;
		
	}
	

	public List<Etiqueta> givenEtiquetas(Integer cantidad){
		
		List<Etiqueta> etiquetas = new ArrayList<>();
		
		for (int i = 0; i < cantidad; i++) {
			etiquetas.add(givenEtiqueta());	
		}
	
		return etiquetas;	
	}
	
	public Etiqueta givenEtiqueta () {
		
		Etiqueta etiqueta = new Etiqueta();

		session().save(etiqueta);
		return etiqueta;
	}
	
	public Usuario givenUsuario() {
		
		Usuario usuario = new Usuario();
		
		session().save(usuario);
		return usuario;
	}
	
	public Pelicula givenPelicula(String titulo) {
		
		Pelicula pelicula = new Pelicula();
		
		pelicula.setTitulo(titulo);
		session().save(pelicula);
		return pelicula;
	}
}
