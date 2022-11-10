package ar.edu.unlam.tallerweb1.domain.historial;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.domain.pelicula.Etiqueta;
import ar.edu.unlam.tallerweb1.domain.pelicula.EtiquetaPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;


public class ServicioHistorialDeberia {
	
	private RepositorioHistorial repositorioHistorial = mock(RepositorioHistorial.class);
	
	private ServicioHistorialImpl servicioHistorial = new ServicioHistorialImpl(repositorioHistorial);
	
	@Test
	public void agregarEtiquetasAlHistorialDelUsuario() {
		Usuario usuario = givenUsuario();
		Pelicula pelicula = givenPelicula();
		
		List<Etiqueta> etiquetas = givenEtiquetas(3);
		
		List<EtiquetaPelicula> etiquetasPelicula = givenEtiquetaPelicula(etiquetas,pelicula);
		
		whenSeAgreganEtiquetasAlHistorial(usuario,pelicula,etiquetasPelicula);
		
		thenSeAgregaronEtiquetasAlHistorial(usuario,etiquetas);
	}



	private void thenSeAgregaronEtiquetasAlHistorial(Usuario usuario,List<Etiqueta> etiquetas) {
		verify(this.repositorioHistorial,times(1)).agregarAlHistorial(usuario,etiquetas);
		
	}

	private void whenSeAgreganEtiquetasAlHistorial(Usuario usuario,Pelicula pelicula,List<EtiquetaPelicula> etiquetasPelicula) {
		when(this.repositorioHistorial.obtenerEtiquetasDePelicula(pelicula)).thenReturn(etiquetasPelicula);
		this.servicioHistorial.agregarAlHistorial(usuario,pelicula);
		
	}

	@Test
	public void obtenerLasEtiquetasDelHistorialDeUnUsuario() {
		Usuario usuario = givenUsuario();
		
		List<Etiqueta> etiquetasPreExistentes = givenEtiquetas(3);
		
		List<Historial> historialUsuario = givenHistorialUsuario(usuario,etiquetasPreExistentes);
		
		List<Etiqueta> obtenidas = whenSeObtienenLasEtiquetasDelHistorial(usuario,historialUsuario);
		
		thenSeObtuvieronLasEtiquetas(obtenidas,usuario);
	}
	
	private void thenSeObtuvieronLasEtiquetas(List<Etiqueta> obtenidas,Usuario usuario) {
		verify(this.repositorioHistorial,times(1)).obtenerHistorial(usuario);
		assertThat(obtenidas.size()).isEqualTo(3);
		
	}

	private List<Etiqueta> whenSeObtienenLasEtiquetasDelHistorial(Usuario usuario, List<Historial> historialUsuario) {
		when(this.repositorioHistorial.obtenerHistorial(usuario)).thenReturn(historialUsuario);
		return this.servicioHistorial.obtenerEtiquetasDelHistorial(usuario);
	}


	@Test
	public void crearLaListaDeEtiquetasNoRepetidasParaActualizar() {
		
		Usuario usuario = givenUsuario();
		
		List<Etiqueta> etiquetasPreExistentes = givenEtiquetas(6);
		
		List<Historial> historialUsuario = givenHistorialUsuario(usuario,etiquetasPreExistentes);
		
		List<Etiqueta> nuevasEtiquetas = etiquetasPreExistentes;
		nuevasEtiquetas.add(givenEtiqueta());
		
		List<Etiqueta> obtenidas = whenSeIngresanNuevasEtiquetas(usuario,historialUsuario,nuevasEtiquetas);
		
		thenSeCreaLaListaDeEtiquetasNoRepetidas(usuario,obtenidas);
		
	}
	
	private void thenSeCreaLaListaDeEtiquetasNoRepetidas(Usuario usuario,List<Etiqueta> obtenidas) {
		
		verify(this.repositorioHistorial,times(1)).obtenerHistorial(usuario);
		assertThat(obtenidas.size()).isEqualTo(1);

		
	}

	private List<Etiqueta> whenSeIngresanNuevasEtiquetas(Usuario usuario,List<Historial> historial,
														List<Etiqueta> nuevasEtiquetas) {
		
		when(this.repositorioHistorial.obtenerHistorial(usuario)).thenReturn(historial);
		return this.servicioHistorial.obtenerEtiquetasNoRepetidas(usuario,nuevasEtiquetas);
		
	}
	


	private List<EtiquetaPelicula> givenEtiquetaPelicula(List<Etiqueta> etiquetas, Pelicula pelicula) {
		List<EtiquetaPelicula> etiquetasPelicula = new ArrayList<>();
		
		for (Etiqueta etiqueta : etiquetas) {
			EtiquetaPelicula etiquetaPelicula = new EtiquetaPelicula(); 
			etiquetaPelicula.setEtiqueta(etiqueta);
			etiquetaPelicula.setPelicula(pelicula);
			etiquetasPelicula.add(etiquetaPelicula);
		}
		
		return etiquetasPelicula ;
	}

	public List<Etiqueta> givenEtiquetas(Integer cantidad){
		
		List<Etiqueta> etiquetas = new ArrayList<>();
		
		for (int i = 0; i < cantidad; i++) {
			etiquetas.add(givenEtiqueta());	
		}
	
		return etiquetas;	
	}
	
	public Etiqueta givenEtiquetaConDescripcion (String descripcion) {
		
		Etiqueta etiqueta = new Etiqueta();
		etiqueta.setId(new Random().nextLong());
		etiqueta.setDescripcion(descripcion);
		
		return etiqueta;
	}
	
	public List<Historial> givenHistorialUsuario(Usuario usuario,List<Etiqueta> preExistentes) {
		
		List<Historial> historialUsuario = new ArrayList<>();
		
		for (int i = 0; i < preExistentes.size(); i++) {
			
			Historial historial = new Historial();
			historial.setEtiqueta(preExistentes.get(i));	
			historial.setUsuario(usuario);
			
			historialUsuario.add(historial);
		}
		
	
		return historialUsuario;
	}
	
	
	private Pelicula givenPelicula() {
		Pelicula pelicula = new Pelicula();
		pelicula.setId(new Random().nextLong());
		return pelicula;
	}
	
	public Etiqueta givenEtiqueta () {
		
		Etiqueta etiqueta = new Etiqueta();
		etiqueta.setId(new Random().nextLong());
		
		return etiqueta;
	}
	
	public Historial givenHistorial() {
		
		Historial historial = new Historial();
		historial.setId(new Random().nextLong());
		
		return historial;
	}
	
	public Usuario givenUsuario() {
		
		Usuario usuario = new Usuario();
		usuario.setId(new Random().nextLong());

		return usuario;
	}

}
