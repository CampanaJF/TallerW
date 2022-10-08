package ar.edu.unlam.tallerweb1.delivery;

import java.util.List;

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

import ar.edu.unlam.tallerweb1.domain.cine.CinePelicula;
import ar.edu.unlam.tallerweb1.domain.cine.ServicioCine;
import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.entrada.ServicioEntrada;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.funcion.ServicioFuncion;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

@Controller
public class ControladorEntrada {
	

	private final ServicioEntrada servicioEntrada;
	private final ServicioUsuario servicioUsuario;
	private final ServicioFuncion servicioFuncion;
	private final ServicioCine servicioCine;
	
	@Autowired
	public ControladorEntrada(ServicioEntrada servicioEntrada,ServicioUsuario servicioUsuario,
							  ServicioFuncion servicioFuncion,ServicioCine servicioCine) {
		this.servicioEntrada = servicioEntrada;
		this.servicioUsuario = servicioUsuario;
		this.servicioFuncion = servicioFuncion;
		this.servicioCine = servicioCine;
	}
	
	/*TO DO - Agregar Control de sesion para que se deba estar logueado para comprar la entrada sino explota
			- Que al terminar de comprar la entrada se muestren los datos de la misma en un PDF
			- Que al terminar de comprar la entrada se envie un recibo al correo del comprador
			
	*/
	
	@RequestMapping(path = "/entrada-pelicula", method = RequestMethod.GET)
	public ModelAndView entradaPelicula(HttpServletRequest request,@RequestParam("peliculaId") Long peliculaId,
										final RedirectAttributes redirectAttributes) {
		
		ModelMap model = new ModelMap();
		
		Usuario usuarioLogueado = obtenerUsuarioLogueado(request);
		
		if(null==usuarioLogueado) {
			redirectAttributes.addFlashAttribute("mensaje","!Registrese Para Comprar sus entradas!");
			return new ModelAndView("redirect:/registrarme");
		}
		
		List<CinePelicula> cines = this.servicioCine.getCines(peliculaId);
		
		model.put("usuario", usuarioLogueado);
		model.put("cines", cines);
		model.put("pelicula",cines.get(0).getPelicula());
		
		model.addAttribute("datosCine", new DatosCine());
		
		return new ModelAndView ("entrada-pelicula",model);
	}
	
	@RequestMapping(path = "/entrada-preparacion", method = RequestMethod.POST)
	public ModelAndView entradaPreparacion(@ModelAttribute("datosCine") DatosCine datos,
									   	   HttpServletRequest request) {
		
		ModelMap model = new ModelMap();
		
		model.put("usuario", obtenerUsuarioLogueado(request));
		model.put("funciones", obtenerFuncionesPor(datos));
		
		model.addAttribute("datosEntrada", new DatosEntrada());
		
		
		return new ModelAndView("entrada-preparacion",model);
	}
	
	@RequestMapping(path = "/entrada-compra", method = RequestMethod.POST)
	public ModelAndView entradaCompra(@ModelAttribute("datosEntrada") DatosEntrada datosEntrada,
									 HttpServletRequest request) {

		this.servicioEntrada.comprarEntrada(datosEntrada); 
		
		Entrada entradaComprada = this.servicioEntrada.getEntrada(datosEntrada.getUsuario().getId(),
																  datosEntrada.getFuncion().getId());
		
		ModelMap model = new ModelMap();
		
		model.put("usuario", obtenerUsuarioLogueado(request));
		model.put("entrada", entradaComprada);
		model.put("mensaje", "Entrada Comprada Exitosamente");
		
		return new ModelAndView("entrada",model);
	}
	
	@RequestMapping(path = "/ver-entrada", method = RequestMethod.GET)
	public ModelAndView verEntrada(@RequestParam Long entrada,HttpServletRequest request) {
		
		ModelMap model = new ModelMap();
		
		Entrada entradaEncontrada = this.servicioEntrada.getEntrada(entrada);

	
		model.put("usuario", obtenerUsuarioLogueado(request));
		model.put("entrada", entradaEncontrada);

		return new ModelAndView("entrada",model);
	}
	
	@RequestMapping(path = "/mis-entradas", method = RequestMethod.GET)
	public ModelAndView misEntradas(HttpServletRequest request) {
		
		ModelMap model = new ModelMap();
			
		List<Entrada> entradas = this.servicioEntrada.getEntradas(obtenerUsuarioLogueado(request).getId());

		if(entradas.isEmpty()) {
			String mensaje = "No Has Comprado Nignuna Entrada";
			
			model.put("usuario", obtenerUsuarioLogueado(request));
			model.put("mensaje",mensaje);
			
			return new ModelAndView("mis-entradas",model);
		}
		
		model.put("usuario", obtenerUsuarioLogueado(request));
		model.put("entradas", entradas);

		
		return new ModelAndView("mis-entradas",model);
	}
	
	private List<Funcion> obtenerFuncionesPor(DatosCine datos) {
		return this.servicioFuncion.getFuncionesDeUnCine(datos.getCine(),datos.getPelicula());
	}

	private Usuario obtenerUsuarioLogueado(HttpServletRequest request) {
		return this.servicioUsuario.getUsuario((Long)request.getSession().getAttribute("ID"));
	}
	

}
