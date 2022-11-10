package ar.edu.unlam.tallerweb1.delivery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.clasificacionPelicula.ServicioClasificacion;
import ar.edu.unlam.tallerweb1.domain.genero.ServicioGenero;
import ar.edu.unlam.tallerweb1.domain.historial.ServicioHistorial;
import ar.edu.unlam.tallerweb1.domain.pelicula.Etiqueta;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.PeliculaConEtiquetaDTO;
import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

public class ControladorHomeTest {

	ServicioPelicula servicioPelicula;
	ServicioUsuario servicioUsuario;
	ServicioHistorial servicioHistorial;
	HttpServletRequest mockRequest;
	HttpSession mockSession;

	@Before
    public void init(){
		servicioUsuario=mock(ServicioUsuario.class);
        servicioPelicula = mock(ServicioPelicula.class);
        servicioHistorial = mock(ServicioHistorial.class);
        servicioUsuario = mock(ServicioUsuario.class);
        mockRequest = mock(HttpServletRequest.class);
    	mockSession = mock(HttpSession.class);

	}
	@Autowired
	private ControladorHome controlador;
	
	@Test
	public void cuandoCargueElHomeQueSeCarguePeliculasEstrenos() {
		List<PeliculaConEtiquetaDTO>peliculasEstrenos=givenCargarHome();
		ModelAndView mav=whenConsultaPeliculasEstrenos(peliculasEstrenos);
		thenDevuelvePeliculasEstrenos(mav);
	}

	private void thenDevuelvePeliculasEstrenos(ModelAndView mav) {
		assertThat(mav.getModel()).containsKey("peliculasEstrenos");
		assertThat(mav.getModel().get("peliculasEstrenos")).asList();
		
	}

	private ModelAndView whenConsultaPeliculasEstrenos(List<PeliculaConEtiquetaDTO>peliculasEstrenos) {
		
		ModelAndView mav=controlador.irAHome(mockRequest,"");
		return mav;
	}

	private List<PeliculaConEtiquetaDTO> givenCargarHome() {
		controlador=new ControladorHome(servicioUsuario,servicioPelicula,servicioHistorial);
		Usuario usuario = givenUsuario();
		PeliculaConEtiquetaDTO pelicula=new PeliculaConEtiquetaDTO();
		PeliculaConEtiquetaDTO pelicula2=new PeliculaConEtiquetaDTO();
		List<PeliculaConEtiquetaDTO> peliculasEstrenos=new ArrayList<>();
		peliculasEstrenos.add(pelicula);
		peliculasEstrenos.add(pelicula2);
		mocksSessionRequests();
		when(servicioHistorial.obtenerEtiquetasDelHistorial(usuario)).thenReturn(givenEtiquetas(3));
		when(servicioPelicula.obtenerPeliculaEstrenos()).thenReturn(peliculasEstrenos);
		when(servicioUsuario.getUsuario(5L)).thenReturn(usuario);
		return peliculasEstrenos;
		
	}
	
	private Usuario givenUsuario() {
		Usuario usuario = new Usuario();
		
		usuario.setId(5L);
		
		return usuario;
	}
	
	private void mocksSessionRequests() {
	    when(mockRequest.getSession()).thenReturn(mockSession);
	    when(mockRequest.getSession().getAttribute("ID")).thenReturn(5L);

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
	etiqueta.setId(new Random().nextLong());
	
	return etiqueta;
	}


}
