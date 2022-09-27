package ar.edu.unlam.tallerweb1.delivery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;



@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations= "classpath:applicationContext.xml")
public class ControladorPeliculaTest {

	private final ServicioSession servicioSession = mock(ServicioSession.class);
	private final ServicioPelicula servicioPelicula = mock(ServicioPelicula.class);
	private final ControladorPelicula controladorPelicula = new ControladorPelicula(servicioSession,servicioPelicula);

	private final HttpServletRequest mockRequest = mock(HttpServletRequest.class);
	private final HttpSession mockSession = mock(HttpSession.class);


	private ModelAndView mav = new ModelAndView();
	
	

/***	@Test no sirve
	public void queSePuedaBuscarUnaPelicula(){
		
    	whenSeBuscaUnaPelicula();
    	
    	thenSeVeLaPeliculaBuscada();
	
	}
	
	
	private void whenSeBuscaUnaPelicula() {
		mocksSessionRequests();
		
		mav = this.controladorPelicula.buscarPelicula(mockRequest);
		
	}
	
	private void thenSeVeLaPeliculaBuscada() {
		assertThat(mav.getViewName()).isEqualTo("resultado-busqueda");
		
	}
*/
	@Test
	public void queSePuedanVerLosDetallesDeUnaPelicula(){
		
    	whenSeIngresaAUnaPelicula();
    	
    	thenSeVenLosDetallesDeLaPelicula();
	
	}
	
	private void whenSeIngresaAUnaPelicula() {
		mocksSessionRequests();

		mav = this.controladorPelicula.verPelicula(mockRequest);
		
	}

	private void thenSeVenLosDetallesDeLaPelicula() {
		assertThat(mav.getViewName()).isEqualTo("pelicula");
		
	}
	
	/*	
	public Pelicula givenPelicula(String nombre) {
    	Pelicula pelicula = new Pelicula ();

    	return pelicula;
    } */
	
	 private void mocksSessionRequests() {
	    when(mockRequest.getSession()).thenReturn(mockSession);
	    when(mockRequest.getSession().getAttribute("ID")).thenReturn(1L);

	 }


	@Test
	public void alBuscarUnaPeliculaPorSuTituloMeDebeMostrarLaMisma(){

	// dado que existe una pelicula
		dadoQueExisteUnaPelicula();
	// cuando busco una pelicula por su nombre
		ModelAndView mav = cuandoBuscoUnaPeliculaPorSuNombre("Back to the future");
	//entonces obtengo la vista
		entoncesMeMuestraLaVista(mav, "pelicula-buscada");

	}

	private void entoncesMeMuestraLaVista(ModelAndView mav, String vistaEsperada) {
	assertThat(mav.getViewName()).isEqualTo(vistaEsperada);
	}

	private void dadoQueExisteUnaPelicula(){

         List<Pelicula> peliculas = new LinkedList<>();
			Pelicula pelicula1 = new Pelicula();
			pelicula1.setTitulo("Back to the future");
			peliculas.add(pelicula1);

		when(this.servicioPelicula.getPeliculas()).thenReturn(peliculas);

	}
	private ModelAndView cuandoBuscoUnaPeliculaPorSuNombre(String titulo){
		return this.controladorPelicula.buscar(titulo, mockRequest);
	}


	/*****Para probar vista
	@Test
	public void queSePuedaObtenerUnaListaDePeliculasYVisualizarlas(){
		dadoQueTenemosUnaListaDePeliculas( 5);
		ModelAndView mav = cuandoAccedeALaPantallaDePeliculas();
		entoncesMeMuestraLaVistaPeliculas(mav,"todas-peliculas");
		entoncesObtengoUnaListaDePeliculas(mav, 5);
	}

	private void dadoQueTenemosUnaListaDePeliculas(int cantidad){
		List<Pelicula> peliculas = new LinkedList<>();
		for (int i = 0; i <cantidad ; i++) {
			peliculas.add((new Pelicula()));
		}
		when(this.servicioPelicula.getPeliculas()).thenReturn(peliculas);
	}
	private ModelAndView cuandoAccedeALaPantallaDePeliculas(){
		return this.controladorPelicula.visualizarPeliculas();
	}
	private void entoncesMeMuestraLaVistaPeliculas(ModelAndView mav, String vistaEsperada){
		assertThat(mav.getViewName()).isEqualTo(vistaEsperada);
	}
	private void entoncesObtengoUnaListaDePeliculas(ModelAndView mav, int cantidad){
		assertThat(mav.getModel().get("peliculas")).isNotNull();
		assertThat(mav.getModel().get("peliculas")).isInstanceOf(List.class);
		List<Pelicula> peliculaList = (List<Pelicula>) mav.getModel().get("peliculas");
		assertThat(peliculaList).hasSize(cantidad);
	}
*/
}
