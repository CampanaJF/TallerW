package ar.edu.unlam.tallerweb1.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.historial.RepositorioHistorial;
import ar.edu.unlam.tallerweb1.domain.pelicula.Etiqueta;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;


public class RepositorioHistorialDeberia extends SpringTest{

	@Autowired
    private RepositorioHistorial repositorioHistorial;
	
	// Controlar la cantidad de etiquetas maxima que puede tener un usuario almacenadas
	// recomendacion de peliculas basado en lo que se vio en el pasado (usar etiquetas)
	// se debe establecer cuando el cliente compra entradas
	
	@Test
	@Transactional
	@Rollback
	public void agregarAlHistorialLasEtiquetasDeLaPelicula() {
		
		Usuario usuario = givenUsuario();
		List <Etiqueta> etiquetasDeLaPelicula = givenEtiquetas(3);
		
		whenAgregaAlHistorial(usuario,etiquetasDeLaPelicula);
		
		thenSeAgregoAlHistorial(usuario);
		
	}
	
	private void thenSeAgregoAlHistorial(Usuario usuario) {
		assertThat(repositorioHistorial.obtenerHistorial(usuario).size()).isEqualTo(3);
		
	}

	private void whenAgregaAlHistorial(Usuario usuario, List<Etiqueta> etiquetasDeLaPelicula) {
		repositorioHistorial.agregarAlHistorial(usuario,etiquetasDeLaPelicula);
		
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
}
