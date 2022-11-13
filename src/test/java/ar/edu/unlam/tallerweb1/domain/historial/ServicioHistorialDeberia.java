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
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.PeliculaConEtiquetaDTO;
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.ServicioRandomizer;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;


public class ServicioHistorialDeberia {
	
	private RepositorioHistorial repositorioHistorial = mock(RepositorioHistorial.class);
	
	private ServicioRandomizer servicioRandomizer = mock(ServicioRandomizer.class);
	
	private ServicioHistorialImpl servicioHistorial = new ServicioHistorialImpl(repositorioHistorial,servicioRandomizer);
	
	@Test
	public void validarSiUnaEtiquetaEstaRepetida() {
		
		Etiqueta repetida1 = givenEtiqueta();
		Etiqueta noRepetida = givenEtiqueta();
		
		List<Etiqueta> etiquetas = givenEtiquetas(2);
		etiquetas.add(repetida1);
		etiquetas.add(noRepetida);
		
		Boolean res = whenSeValidaConUnaRepetida(etiquetas,repetida1);
		
		thenSeValidoComoFalse(res);
		
	}
	
	private void thenSeValidoComoFalse(Boolean res) {
		assertThat(res).isFalse();
		
	}

	private Boolean whenSeValidaConUnaRepetida(List<Etiqueta> etiquetas, Etiqueta repetida1) {
		return this.servicioHistorial.validarRepetida(etiquetas, repetida1);
	}

	@Test
	public void actualizarElHistorialDelUsuarioConDosEtiquetasRepetidas() {
		
		Usuario usuario = givenUsuario();
		Etiqueta repetida1 = givenEtiqueta();
		Etiqueta repetida2 = givenEtiqueta();
		Pelicula pelicula = givenPelicula();
		List<Etiqueta> etiquetasNuevas = givenEtiquetas(1);
		etiquetasNuevas.add(repetida1);
		etiquetasNuevas.add(repetida2);
		
		List<EtiquetaPelicula> etiquetasPelicula = givenEtiquetaPelicula(etiquetasNuevas,pelicula);
		
		List<Etiqueta> etiquetasHistorial = givenEtiquetas(4);
		etiquetasHistorial.add(repetida1);
		etiquetasHistorial.add(repetida2);
		List <Historial> historial = givenHistorialUsuario(usuario,etiquetasHistorial); 
		
		whenSeActualizaElHistorialDelUsuarioConDosRepetidas(usuario,pelicula,etiquetasPelicula,historial);
		
		thenSeActualizoElHistorial(usuario,pelicula);
		
	}
	
	private void thenSeActualizoElHistorial(Usuario usuario,Pelicula pelicula) {
		verify(this.repositorioHistorial,times(1)).obtenerEtiquetasDePelicula(pelicula);
		verify(this.repositorioHistorial,times(1)).obtenerHistorial(usuario);
		verify(this.repositorioHistorial,times(1)).actualizarHistorial(any(Historial.class));	
	}

	private void whenSeActualizaElHistorialDelUsuarioConDosRepetidas(Usuario usuario, Pelicula pelicula,
														List<EtiquetaPelicula> etiquetasPelicula, List<Historial> historial) {
		
		when(this.repositorioHistorial.obtenerEtiquetasDePelicula(pelicula)).thenReturn(etiquetasPelicula);
		when(this.repositorioHistorial.obtenerHistorial(usuario)).thenReturn(historial);
		this.servicioHistorial.guardarEnElHistorial(usuario, pelicula);
		
	}

	@Test
	public void obtenerLaListaDeEtiquetasRepetidas() {
		
		Usuario usuario = givenUsuario();
		Etiqueta repetida = givenEtiqueta();
		List<Etiqueta> etiquetas = givenEtiquetas(2);
		etiquetas.add(repetida);
		
		List<Etiqueta> etiquetasHistorial = givenEtiquetas(5);
		etiquetasHistorial.add(repetida);
		List <Historial> historial = givenHistorialUsuario(usuario,etiquetasHistorial); 
		
		List <Etiqueta> repetidasEncontradas = whenSeObtienenLasRepetidas(etiquetas,historial);
		
		thenSeObtuvieronLasRepetidas(repetidasEncontradas,repetida);
		
	}
	
	private void thenSeObtuvieronLasRepetidas(List<Etiqueta> etiquetasRepetidas,Etiqueta repetida) {
		assertThat(etiquetasRepetidas.contains(repetida));
		assertThat(etiquetasRepetidas.size()).isEqualTo(1);
		
	}

	private List <Etiqueta> whenSeObtienenLasRepetidas(List<Etiqueta> etiquetas, List<Historial> historial) {
		return this.servicioHistorial.obtenerRepetidos(etiquetas,historial);
		
	}

	@Test
	public void mapearPeliculaConEtiquetaDTO() {
		
		Pelicula pelicula = givenPelicula();
		
		List<Etiqueta> etiquetas = givenEtiquetas(3);
		
		List<EtiquetaPelicula> etiquetasPelicula = givenEtiquetaPelicula(etiquetas,pelicula);
		
		List<PeliculaConEtiquetaDTO> DTO = whenSeMapea(etiquetasPelicula);
		
		thenSeMapeoCorrectamente(DTO);
	}
	
	private void thenSeMapeoCorrectamente(List<PeliculaConEtiquetaDTO> DTO) {
		verify(this.repositorioHistorial,times(1)).obtenerEtiquetasDePelicula(DTO.get(0).getPelicula());
		assertThat(DTO.size()).isEqualTo(1);
	}

	private List<PeliculaConEtiquetaDTO> whenSeMapea(List<EtiquetaPelicula> etiquetasPelicula) {
		when(this.repositorioHistorial.obtenerEtiquetasDePelicula(etiquetasPelicula.get(0).getPelicula())).thenReturn(etiquetasPelicula);
		return this.servicioHistorial.mapeoHistorial(etiquetasPelicula,"wew");
		
	}
	
	@Test
	public void agregarMultiplesEtiquetasAlHistorialDelUsuario() {
		Usuario usuario = givenUsuario();
		Pelicula pelicula = givenPelicula();
		
		List<Etiqueta> etiquetas = givenEtiquetas(6);
		
		List<EtiquetaPelicula> etiquetasPelicula = givenEtiquetaPelicula(etiquetas,pelicula);
		
		whenSeAgreganEtiquetasAlHistorial(usuario,pelicula,etiquetasPelicula);
		
		thenSeAgregaronEtiquetasAlHistorial(usuario,etiquetas);
	}

	private void thenSeAgregaronEtiquetasAlHistorial(Usuario usuario,List<Etiqueta> etiquetas) {
		verify(this.repositorioHistorial,times(6)).guardarEnElHistorial(any(Historial.class));
		
	}

	@Test
	public void agregarUnaEtiquetaAlHistorialDelUsuario() {
		Usuario usuario = givenUsuario();
		Pelicula pelicula = givenPelicula();
		
		List<Etiqueta> etiquetas = givenEtiquetas(1);
		
		List<EtiquetaPelicula> etiquetasPelicula = givenEtiquetaPelicula(etiquetas,pelicula);
		
		whenSeAgreganEtiquetasAlHistorial(usuario,pelicula,etiquetasPelicula);
		
		thenSeAgregoUnaEtiquetaAlHistorial(usuario,etiquetas);
	}

	private void thenSeAgregoUnaEtiquetaAlHistorial(Usuario usuario,List<Etiqueta> etiquetas) {
		verify(this.repositorioHistorial,times(1)).guardarEnElHistorial(any(Historial.class));
		
	}

	private void whenSeAgreganEtiquetasAlHistorial(Usuario usuario,Pelicula pelicula,List<EtiquetaPelicula> etiquetasPelicula) {
		when(this.repositorioHistorial.obtenerEtiquetasDePelicula(pelicula)).thenReturn(etiquetasPelicula);
		this.servicioHistorial.guardarEnElHistorial(usuario,pelicula);
		
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
		return this.servicioHistorial.obtenerEtiquetasDelUsuario(usuario);
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
