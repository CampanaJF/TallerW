package ar.edu.unlam.tallerweb1.delivery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
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
	private final ServicioPelicula servicioPelicula = mock(ServicioPelicula.class);
	private final ControladorPelicula controladorPelicula = new ControladorPelicula(servicioSession,servicioPelicula);

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


/*	@Test
	public void alBuscarUnaPeliculaPorSuTituloLaEncuentro(){
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
	}*/
	@Test
	public void queSePuedaRealizarUnaBusquedaDePeliculasExitosamente(){
		givenQueLaPeliculaExiste();
		ModelAndView mav =whenBuscoPelicula();
		thenEncuentroPeliculas(mav);
	}
	private void givenQueLaPeliculaExiste(){
		List<Pelicula> peliculaList = new LinkedList<>();
		when(this.servicioPelicula.buscarPeliculas(PELICULA_TITULO)).thenReturn(peliculaList);
	}
	private ModelAndView whenBuscoPelicula() {
		return this.controladorPelicula.buscar(PELICULA_TITULO, mockRequest);
	}
	private void thenEncuentroPeliculas(ModelAndView mav){
		assertThat(mav.getViewName()).isEqualTo("pelicula-buscada");
	}

	@Test
	public void queAlRealizarUnaBusquedaDePeliculaNoSeEncuetreDisponible(){
		givenQueLaPeliculaNoExiste();
		ModelAndView mav = whenBuscoPelicula();
		thenNoEncuentroPeliculas(mav);
	}

	private void givenQueLaPeliculaNoExiste(){
		when(servicioPelicula.buscarPeliculas(PELICULA_TITULO)).thenReturn(null);
	}
	private void thenNoEncuentroPeliculas(ModelAndView mav){
		assertThat(mav.getViewName()).isEqualTo("pelicula-buscada");
	}


}
