package ar.edu.unlam.tallerweb1.delivery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;



@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations= "classpath:applicationContext.xml")
public class ControladorPeliculaTest {

	private final ServicioSession servicioSession = mock(ServicioSession.class);
	
	private final ControladorPelicula controladorPelicula = new ControladorPelicula(servicioSession);

	private final HttpServletRequest mockRequest = mock(HttpServletRequest.class);
	private final HttpSession mockSession = mock(HttpSession.class);

	
	private ModelAndView mav = new ModelAndView();
	
	
	@Test
	public void queSePuedaComprarUnTicketDePelicula(){
		
    	whenSeQuiereComprarUnTicket();
    	
    	thenSePuedeComprarUnTicket();
	
	}
	
	private void whenSeQuiereComprarUnTicket() {
		mocksSessionRequests();
		
		mav = this.controladorPelicula.comprarTicket(mockRequest);
		
	}

	private void thenSePuedeComprarUnTicket() {
		assertThat(mav.getViewName()).isEqualTo("comprar-ticket");
		
	}

	@Test
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
	
	


}
