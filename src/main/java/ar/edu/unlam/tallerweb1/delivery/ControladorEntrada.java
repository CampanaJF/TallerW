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

import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.cine.CinePelicula;
import ar.edu.unlam.tallerweb1.domain.cine.ServicioCine;
import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.entrada.ServicioEntrada;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.funcion.ServicioFuncion;
import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

@Controller
public class ControladorEntrada {
	
	private final ServicioSession servicioSession;
	private final ServicioEntrada servicioEntrada;
	private final ServicioUsuario servicioUsuario;
	private final ServicioFuncion servicioFuncion;
	private final ServicioCine servicioCine;
	
	@Autowired
	public ControladorEntrada(ServicioSession servicioSession,ServicioEntrada servicioEntrada,ServicioUsuario servicioUsuario,
							  ServicioFuncion servicioFuncion,ServicioCine servicioCine) {
		this.servicioSession = servicioSession;
		this.servicioEntrada = servicioEntrada;
		this.servicioUsuario = servicioUsuario;
		this.servicioFuncion = servicioFuncion;
		this.servicioCine = servicioCine;
	}
	
	@RequestMapping(path = "/entrada-pelicula", method = RequestMethod.GET)
	public ModelAndView entradaPelicula(HttpServletRequest request) {
		
		Long sess = servicioSession.getUserId(request);
		ModelMap model = new ModelMap();
		
		List<CinePelicula> cines = this.servicioCine.getCines(1L);
		
		model.put("usuario", sess);
		model.put("cines", cines);
		
		return new ModelAndView ("entrada-pelicula",model);
	}
	
	@RequestMapping(path = "/entrada-preparacion", method = RequestMethod.POST)
	public ModelAndView entradaPreparacion(@RequestParam("peliculaId") Long peliculaId,
										   @RequestParam("cineId") Long cineId,
									   	   HttpServletRequest request) {
		
		Long sess = this.servicioSession.getUserId(request);
		
		Usuario usuarioModel = this.servicioUsuario.getUsuario(sess);
				
		List <Funcion> funciones= this.servicioFuncion.getFuncionesDeUnCine(cineId,peliculaId);
		
		ModelMap model = new ModelMap();
		
		model.put("usuarioModel", usuarioModel);
		model.put("usuario", sess);
		model.put("funciones", funciones);
		
		
		return new ModelAndView("entrada-preparacion",model);
	}
	
	@RequestMapping(path = "/entrada-compra", method = RequestMethod.POST)
	public ModelAndView entradaCompra(@ModelAttribute("datosEntrada") DatosEntrada datosEntrada,HttpServletRequest request) {
		
		Long sess = this.servicioSession.getUserId(request);

		Entrada entradaComprada = this.servicioEntrada.comprarEntrada(datosEntrada);
		
		ModelMap model = new ModelMap();
		
		model.put("usuario", sess);
		model.put("entrada", entradaComprada);
		
		return new ModelAndView("entrada",model);
	}

}
