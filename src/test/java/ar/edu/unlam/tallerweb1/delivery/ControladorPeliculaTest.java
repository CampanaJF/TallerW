package ar.edu.unlam.tallerweb1.delivery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.tallerweb1.domain.cine.ServicioCine;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;




public class ControladorPeliculaTest {

	private final ServicioSession servicioSession = mock(ServicioSession.class);

	private final ServicioPelicula servicioPelicula = mock(ServicioPelicula.class);
	private final ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	private final ServicioCine servicioCine = mock(ServicioCine.class);
	private final ControladorPelicula controladorPelicula = new ControladorPelicula(servicioSession,servicioPelicula,servicioUsuario,servicioCine);

	private final HttpServletRequest mockRequest = mock(HttpServletRequest.class);
	private final HttpSession mockSession = mock(HttpSession.class);
    public static final String PELICULA_TITULO ="Back to the future";

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
	public void queSePuedaRealizarUnaBusquedaDePeliculasExitosamente(){
		givenQueLaPeliculaExiste();
		ModelAndView mav =whenBuscoPelicula();
		thenEncuentroPeliculas(mav);
	}
	@Test
	public void queAlRealizarUnaBusquedaDePeliculaNoSeEncuetreDisponible(){
		givenQueLaPeliculaNoExiste();
		ModelAndView mav = whenBuscoPelicula();
		thenNoEncuentroPeliculas(mav);
	}

	private void givenQueLaPeliculaExiste(){
		List<Pelicula> peliculaList = new LinkedList<>();
		when(this.servicioPelicula.buscarPeliculas(PELICULA_TITULO)).thenReturn(peliculaList);
	}
	private ModelAndView whenBuscoPelicula() {
         mocksSessionRequests();
		return this.controladorPelicula.buscar(PELICULA_TITULO,mockRequest);
	}
	private void thenEncuentroPeliculas(ModelAndView mav){
		assertThat(mav.getViewName()).isEqualTo("pelicula-buscada");
	}



	private void givenQueLaPeliculaNoExiste(){
		when(servicioPelicula.buscarPeliculas(PELICULA_TITULO)).thenReturn(null);
	}
	private void thenNoEncuentroPeliculas(ModelAndView mav){
		assertThat(mav.getViewName()).isEqualTo("pelicula-buscada");
	}

	@Test
	public void queAlCalificarUnaPeliculaLeMuestreVistaExitosa(){
		 ModelAndView mav = whenIrAGuardarCalificacion();
		 thenObtengoLaVista(mav);
	}
    @Test
	public void queMuestreLaVistaDeOpinionesDeUnaPelicula(){
		 ModelAndView mav = whenIrAVerOpiniones();
		 thenObtengoLaVistaDeOpiniones(mav);
	}

	private void thenObtengoLaVistaDeOpiniones(ModelAndView mav) {
		 assertThat(mav.getViewName()).isEqualTo("ver-opiniones");
	}

	private ModelAndView whenIrAVerOpiniones() {
		 mocksSessionRequests();
	 return this.controladorPelicula.verOpiniones(2L,mockRequest);
	 }

	private void thenObtengoLaVista(ModelAndView mav) {
		 assertThat(mav.getViewName()).isEqualTo("calificacionPelicula-exitosa");
	}

	private ModelAndView whenIrAGuardarCalificacion() {
		 mocksSessionRequests();
		 return this.controladorPelicula.guardarCalificacion(4,2L,"buena",mockRequest);
	}

}
