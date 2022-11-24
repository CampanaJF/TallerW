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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.domain.entrada.ServicioEntrada;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations= "classpath:applicationContext.xml")
public class ControladorPendientesDeberia {

	private final ServicioEntrada servicioEntrada = mock(ServicioEntrada.class);
	private final ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	
	private final RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
	
	private final ControladorPendientes controladorPendientes = new ControladorPendientes(servicioEntrada,servicioUsuario);
	
	private final HttpServletRequest mockRequest = mock(HttpServletRequest.class);
	private final HttpSession mockSession = mock(HttpSession.class);
	
	private ModelAndView mav = new ModelAndView();
	
	
	@Test
	public void permitirComprarUnaEntradaQueFueDesocupada() {

		whenSeCompraLaEntradaDesocupada(5L);
		
		thenSeComproLaEntrada();
	}
	
	@Test
	public void permitirCancelarUnaReserva() {
		
		whenSeCancelarUnaReserva(5L);
		
		thenSeCanceloLaReserva();
	}
	
	private void thenSeCanceloLaReserva() {
		assertThat(mav.getViewName()).isEqualTo("redirect:/mis-entradas");
		
	}

	private void whenSeCancelarUnaReserva(Long entrada) {
		mav = this.controladorPendientes.cancelarReserva(entrada,mockRequest,redirectAttributes);	
	}
	
	private void thenSeComproLaEntrada() {
		assertThat(mav.getViewName()).isEqualTo("entrada");
	}

	private void whenSeCompraLaEntradaDesocupada(Long entrada) {
		mocksSessionRequests();
		
		mav = this.controladorPendientes.entradasPendientes(entrada,mockRequest);	
	}
	
	
	
	
	private void mocksSessionRequests() {
	    when(mockRequest.getSession()).thenReturn(mockSession);
	    when(mockRequest.getSession().getAttribute("ID")).thenReturn(1L);

	 }
}

