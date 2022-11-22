package ar.edu.unlam.tallerweb1.delivery;

import javax.servlet.http.HttpServletRequest;

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

@Controller
public class ControladorPendientes {
	
	private final ServicioEntrada servicioEntrada;
	private final ServicioUsuario servicioUsuario;

	@Autowired
	public ControladorPendientes(ServicioEntrada servicioEntrada,ServicioUsuario servicioUsuario) {
		
		this.servicioEntrada = servicioEntrada;
		this.servicioUsuario = servicioUsuario;
	}
	
	@RequestMapping(path = "/entrada-cancelar", method = RequestMethod.GET)
	public ModelAndView cancelarReserva(@RequestParam("entrada") Long entrada,HttpServletRequest request,
										final RedirectAttributes redirectAttributes) {
		
		cancelarReserva(entrada);
		redirectAttributes.addFlashAttribute("mensaje","!Se Cancelo la reserva exitosamente!");
		
		return new ModelAndView("redirect:/mis-entradas");
	}
	

	@RequestMapping(path="/entradas-pendientes", method = RequestMethod.GET)
	public ModelAndView entradasPendientes(@RequestParam("funcion") Long funcion, HttpServletRequest request) {
				
		ModelMap model = new ModelMap();
		
		// cambiar la vista para que reciba esto
		model.put("entradasCanceladas",this.servicioEntrada.obtenerEntradasCanceladas(funcion));
		model.put("usuario", obtenerUsuarioLogueado(request));
		model.addAttribute("datosReserva", new DatosReserva());
		return new ModelAndView("entrada",model);
	}
	
	@RequestMapping(path="/comprar-pendientes", method = RequestMethod.POST)
	public ModelAndView comprarEntradaPendiente(@ModelAttribute("datosReserva") DatosReserva datosReserva,
												HttpServletRequest request) {
		
		this.servicioEntrada.comprarPendiente(datosReserva.getEntrada(), datosReserva.getUsuario());
		
		return new ModelAndView("redirect:/mis-entradas");
	}
	
//	private ModelAndView validarUsuario(Usuario usuarioLogueado,final RedirectAttributes redirectAttributes) {
//		
//		if(null==usuarioLogueado) { 
//			redirectAttributes.addFlashAttribute("mensaje","!Ingrese Antes de seguir!");
//			return new ModelAndView("redirect:/login");
//		}
//		
//		return null;
//	}
	
	private void cancelarReserva(Long entrada) {
		this.servicioEntrada.cancelarReserva(entrada);
	}
	
	private Usuario obtenerUsuarioLogueado(HttpServletRequest request) {
		return this.servicioUsuario.getUsuario((Long)request.getSession().getAttribute("ID"));
	}
}
