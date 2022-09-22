package ar.edu.unlam.tallerweb1.delivery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.entrada.ServicioEntrada;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations= "classpath:applicationContext.xml")
public class ControladorEntradaTest {
	
	private final ServicioSession servicioSession = mock(ServicioSession.class);
	private final ServicioEntrada servicioEntrada = mock(ServicioEntrada.class);
	
	private final ControladorEntrada controladorEntrada = new ControladorEntrada(servicioSession,servicioEntrada);

	private final HttpServletRequest mockRequest = mock(HttpServletRequest.class);
	private final HttpSession mockSession = mock(HttpSession.class);

	
	private ModelAndView mav = new ModelAndView();
	
	@Test
	public void queSeCompreLaEntrada() {
		
		Usuario U1 = givenUsuario(1L,"Nombre");
		Funcion F1 = new Funcion();
		F1.setId(1L);
		Entrada entrada = givenEntrada(U1,F1);
		
		
		whenSeCompraUnaEntrada(entrada);
		
		thenSeComproLaEntrada(entrada);
	}
	
	private void whenSeCompraUnaEntrada(Entrada entrada) {
		mocksSessionRequests();
		
		mav = this.controladorEntrada.comprarEntrada(entrada,mockRequest);
		when(servicioEntrada.getEntrada(entrada.getUsuario().getId(),entrada.getFuncion().getId())).thenReturn(entrada);
		
		
	}

	private void thenSeComproLaEntrada(Entrada entrada) {
		assertThat(mav.getViewName()).isEqualTo("entrada");

		
	}

	@Test
	public void queSePuedaComprarUnTicketDePelicula(){
		
    	whenSeQuiereComprarUnaEntrada();
    	
    	thenSePuedeComprarUnaEntrada();
	
	}
	
	private void whenSeQuiereComprarUnaEntrada() {
		mocksSessionRequests();
		
		mav = this.controladorEntrada.prepararEntrada(mockRequest);
		
	}

	private void thenSePuedeComprarUnaEntrada() {
		assertThat(mav.getViewName()).isEqualTo("comprar-entrada");

	}
	
	private void mocksSessionRequests() {
	    when(mockRequest.getSession()).thenReturn(mockSession);
	    when(mockRequest.getSession().getAttribute("ID")).thenReturn(1L);

	 }
	
	private Entrada givenEntrada(Usuario U1,Funcion F1) {
		Entrada entrada = new Entrada();
		entrada.setFuncion(F1);
		entrada.setUsuario(U1);
		return entrada;
	}

	public Usuario givenUsuario(Long id,String nombre) {
    	Usuario usuario = new Usuario ();
    	usuario.setId(id);
    	usuario.setNombre(nombre);
    	return usuario;
    }

}
