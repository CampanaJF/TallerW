package ar.edu.unlam.tallerweb1.delivery;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
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
import ar.edu.unlam.tallerweb1.domain.historial.ServicioHistorial;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

import java.util.List;

@Controller
public class ControladorPendientes extends ControladorBase{
	
	private final ServicioEntrada servicioEntrada;
	private final ServicioMail servicioMail;
	private final ServicioHistorial servicioHistorial;
	
	@Autowired
	public ControladorPendientes(ServicioEntrada servicioEntrada, ServicioUsuario servicioUsuario,
								 ServicioHistorial servicioHistorial, ServicioMail servicioMail) {
		super(servicioUsuario);

		this.servicioEntrada = servicioEntrada;
		this.servicioMail=servicioMail;
		this.servicioHistorial = servicioHistorial;
	}
	
	//Que no haya mas de una notificacion para la misma funcion por usuario
	//Que si no hay nadie a quien notificar simplementa se vuelva a poder comprar regularmente
	//
	
	@RequestMapping(path = "/entrada-cancelar", method = RequestMethod.GET)
	public ModelAndView cancelarReserva(@RequestParam("entrada") Long entrada,HttpServletRequest request,
										final RedirectAttributes redirectAttributes) {
		
		cancelarReserva(entrada);
		
		redirectAttributes.addFlashAttribute("mensaje","!Se Cancelo la reserva exitosamente!");
		
		enviarMailsPendientes(entrada);
		
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
	public ModelAndView entradasPendientes(@RequestParam("funcion") Long funcion, HttpServletRequest request,
											final RedirectAttributes redirectAttributes) {
		
		Usuario usuario = obtenerUsuarioLogueado(request);
		
		//validarUsuario(usuario,redirectAttributes);
		
		if(null==usuario) { 
			redirectAttributes.addFlashAttribute("mensaje","!Ingrese Antes de seguir!");
			return new ModelAndView("redirect:/login");
		}
		
		ModelMap model = new ModelMap();
		
		model.put("entradasCanceladas",this.servicioEntrada.obtenerEntradasCanceladas(funcion));
		model.put("usuario", usuario);
		model.addAttribute("datosReserva", new DatosReserva());
		return new ModelAndView("entrada",model);
	}
	
	@RequestMapping(path="/comprar-pendientes", method = RequestMethod.POST)
	public ModelAndView comprarEntradaPendiente(@ModelAttribute("datosReserva") DatosReserva datosReserva,
												HttpServletRequest request) {
		
		comprarPendiente(datosReserva);
		guardarEnElHistorial(obtenerEntrada(datosReserva));
		
		return new ModelAndView("redirect:/mis-entradas");
	}
	
	private Entrada obtenerEntrada(DatosReserva datosReserva) {
		return this.servicioEntrada.obtenerEntrada(datosReserva.getEntrada().getId());
	}

	private void guardarEnElHistorial(Entrada entrada) {
		this.servicioHistorial.guardarEnElHistorial(entrada.getUsuario(),entrada.getFuncion().getPelicula());
	}

	
	private void cancelarReserva(Long entrada) {
		this.servicioEntrada.cancelarReserva(entrada);
	}
	
	public void enviarMailsPendientes(Long entrada) {
		
		List<EntradaPendiente> pendientesAEnviarMail = this.servicioEntrada.getPendientesParaEnviarMail(entrada);
		
		for (EntradaPendiente pendientes: pendientesAEnviarMail) {
			enviarMail(pendientes);
		}
	}
	
	private void comprarPendiente(DatosReserva datosReserva) {
		this.servicioEntrada.comprarPendiente(datosReserva.getEntrada(), datosReserva.getUsuario());
	}
	
	private void enviarMail(EntradaPendiente pendiente) {
		
		this.servicioMail.enviarMail(obtenerMail(pendiente),obtenerAsunto(),obtenerMensaje(pendiente));
		
	}

	private String obtenerMensaje(EntradaPendiente pendiente) {
		return this.servicioMail.getMensajeEntradasDisponiblesHTML(pendiente.getUsuario().getNombre(),
				                                 			  pendiente.getFuncion().getPelicula().getTitulo(),
				                                 			  pendiente.getFuncion().getId());
		
	}

	private String obtenerAsunto() {
		return servicioMail.getAsuntoEntradasDisponibles();
	}

	private String obtenerMail(EntradaPendiente pendiente) {
		return pendiente.getUsuario().getEmail();
	}
	

}
