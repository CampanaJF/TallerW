package ar.edu.unlam.tallerweb1.delivery;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.domain.entrada.EntradaPendiente;
import ar.edu.unlam.tallerweb1.domain.mail.ServicioMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.domain.entrada.ServicioEntrada;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

import java.util.List;

@Controller
public class ControladorPendientes extends ControladorBase{
	
	private final ServicioEntrada servicioEntrada;
	private final ServicioMail servicioMail;
	@Autowired
	public ControladorPendientes(ServicioEntrada servicioEntrada, ServicioUsuario servicioUsuario, ServicioMail servicioMail) {
		super(servicioUsuario);

		this.servicioEntrada = servicioEntrada;
		this.servicioMail=servicioMail;
	}
	
	//Que no haya mas de una notificacion para la misma funcion por usuario
	//Que si no hay nadie a quien notificar simplementa se vuelva a poder comprar regularmente
	//
	
	@RequestMapping(path = "/entrada-cancelar", method = RequestMethod.GET)
	public ModelAndView cancelarReserva(@RequestParam("entrada") Long entrada,HttpServletRequest request,
										final RedirectAttributes redirectAttributes) {
		
		cancelarReserva(entrada);
		redirectAttributes.addFlashAttribute("mensaje","¡Se Canceló la reserva exitosamente! <i class=\"bi bi-emoji-frown\"></i>");
		List<EntradaPendiente> pendientesAEnviarMail = this.servicioEntrada.getPendientesParaEnviarMail(entrada);
		for (EntradaPendiente pendientes: pendientesAEnviarMail) {
			this.servicioMail.enviarMail(pendientes.getUsuario().getEmail(),
					servicioMail.getAsuntoEntradasDisponibles(),
					servicioMail.getMensajeEntradasDisponibles(pendientes.getUsuario().getNombre(),
							                                 pendientes.getFuncion().getPelicula().getTitulo()));
		}
		return new ModelAndView("redirect:/mis-entradas");
	}
	
	@RequestMapping(path="/mis-notificaciones", method = RequestMethod.GET)
	public ModelAndView misNotificaciones(HttpServletRequest request) {
		
		Usuario usuario = obtenerUsuarioLogueado(request);
		
		ModelMap model = new ModelMap();
		
		model.put("notificaciones",this.servicioEntrada.obtenerEntradasCanceladas(usuario.getId()));
		
		return new ModelAndView("mis-notificaciones",model);
	}
	

	@RequestMapping(path="/entradas-pendientes", method = RequestMethod.GET)
	public ModelAndView entradasPendientes(@RequestParam("funcion") Long funcion, HttpServletRequest request) {
				
		ModelMap model = new ModelMap();
		
		
		model.put("entradasCanceladas",this.servicioEntrada.obtenerEntradasCanceladas(funcion));
		model.put("usuario", obtenerUsuarioLogueado(request));
		model.addAttribute("datosReserva", new DatosReserva());
		return new ModelAndView("entrada",model);
	}
	
	@RequestMapping(path="/comprar-pendientes", method = RequestMethod.POST)
	public ModelAndView comprarEntradaPendiente(@ModelAttribute("datosReserva") DatosReserva datosReserva,
												HttpServletRequest request) {
		
		this.servicioEntrada.comprarPendiente(datosReserva.getEntrada(), datosReserva.getUsuario());
		
		return new ModelAndView("redirect:/home");
	}
	
	@SuppressWarnings("unused")
	private ModelAndView validarUsuario(Usuario usuarioLogueado,final RedirectAttributes redirectAttributes) {
		
		if(null==usuarioLogueado) { 
			redirectAttributes.addFlashAttribute("mensaje","!Ingrese Antes de seguir!");
			return new ModelAndView("redirect:/login");
		}
		
		return null;
	}
	
	@SuppressWarnings("unused")
	private ModelAndView validarUsuario(Usuario usuarioLogueado) {
		
		if(null==usuarioLogueado) { 
			return new ModelAndView("redirect:/login");
		}
		
		return null;
	}
	
	private void cancelarReserva(Long entrada) {
		this.servicioEntrada.cancelarReserva(entrada);
	}
	

}
