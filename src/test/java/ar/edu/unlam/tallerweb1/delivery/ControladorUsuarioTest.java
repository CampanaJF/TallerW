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
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations= "classpath:applicationContext.xml")
public class ControladorUsuarioTest {

	private final ServicioSession servicioSession = mock(ServicioSession.class);
	private final ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	
	private final ControladorUsuario controladorUsuario = new ControladorUsuario(servicioUsuario,servicioSession);

	private final HttpServletRequest mockRequest = mock(HttpServletRequest.class);
	private final HttpSession mockSession = mock(HttpSession.class);

	
	private ModelAndView mav = new ModelAndView();
	
	
	@Test
	public void queSePuedaEntrarAlPerfilDeUsuario(){
		
    	whenSeQuiereVerElPerfil();
    	
    	thenSePuedeVerElPerfil();
	
	}
	
	private void whenSeQuiereVerElPerfil() {
		mocksSessionRequests();
		
		mav = this.controladorUsuario.verPerfil(mockRequest, null);
		
	}

	private void thenSePuedeVerElPerfil() {
		assertThat(mav.getViewName()).isEqualTo("perfil-usuario");
		
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
