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
		dadoQueExisteUnaPelicula();
		ModelAndView mav = cuandoBuscoUnaPeliculaPorSuNombre("Back to the future");
		entoncesMeMuestraLaVista(mav, "pelicula-buscada");

	}

	private void entoncesMeMuestraLaVista(ModelAndView mav, String vistaEsperada) {
	assertThat(mav.getViewName()).isEqualTo(vistaEsperada);
	}

	private ModelAndView cuandoBuscoUnaPeliculaPorSuNombre(String titulo){
		return this.controladorPelicula.buscar(titulo, mockRequest);
	}
	private void dadoQueExisteUnaPelicula(){
        List<Pelicula> peliculas = new LinkedList<>();
		Pelicula pelicula1 = new Pelicula();
		pelicula1.setTitulo("Back to the future");
		peliculas.add(pelicula1);

		when(this.servicioPelicula.getPeliculas()).thenReturn(peliculas);
	}

}
