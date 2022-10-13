package ar.edu.unlam.tallerweb1.delivery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.clasificacionPelicula.ServicioClasificacion;
import ar.edu.unlam.tallerweb1.domain.genero.ServicioGenero;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;

public class ControladorHomeTest {

	ServicioPelicula servicioPelicula;
	HttpServletRequest mockRequest;
	HttpSession mockSession;
	ServicioSession servicioSession;
	@Before
    public void init(){
    
        servicioPelicula = mock(ServicioPelicula.class);
        mockRequest = mock(HttpServletRequest.class);
    	mockSession = mock(HttpSession.class);
    	servicioSession=mock(ServicioSession.class);
	}
	@Autowired
	private ControladorHome controlador;
	
	@Test
	public void cuandoCargueElHomeQueSeCarguePeliculasEstrenos() {
		List<Pelicula>peliculasEstrenos=givenCargarHome();
		ModelAndView mav=whenConsultaPeliculasEstrenos(peliculasEstrenos);
		thenDevuelvePeliculasEstrenos(mav);
	}

	private void thenDevuelvePeliculasEstrenos(ModelAndView mav) {
		assertThat(mav.getModel()).containsKey("peliculasEstrenos");
		assertThat(mav.getModel().get("peliculasEstrenos")).asList();
		
	}

	private ModelAndView whenConsultaPeliculasEstrenos(List<Pelicula>peliculasEstrenos) {
		
		ModelAndView mav=controlador.irAHome(mockRequest,"");
		return mav;
	}

	private List<Pelicula> givenCargarHome() {
		controlador=new ControladorHome(servicioSession,servicioPelicula);
		Pelicula pelicula=new Pelicula();
		Pelicula pelicula2=new Pelicula();
		List<Pelicula> peliculasEstrenos=new ArrayList<>();
		peliculasEstrenos.add(pelicula);
		peliculasEstrenos.add(pelicula2);
		when(servicioPelicula.obtenerPeliculaEstrenos()).thenReturn(peliculasEstrenos);
		when(servicioSession.getUserId(mockRequest)).thenReturn(1L);
		return peliculasEstrenos;
		
	}

}
