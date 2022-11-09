package ar.edu.unlam.tallerweb1.delivery;

import java.util.ArrayList;
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

import ar.edu.unlam.tallerweb1.domain.cine.Asiento;
import ar.edu.unlam.tallerweb1.domain.cine.CinePelicula;
import ar.edu.unlam.tallerweb1.domain.cine.ServicioCine;
import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.entrada.ServicioEntrada;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.funcion.ServicioFuncion;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.DatosEntradaInvalidaException;
import ar.edu.unlam.tallerweb1.exceptions.NoSeEncontraronFuncionesException;

@Controller
public class ControladorEntrada {
	

	private final ServicioEntrada servicioEntrada;
	private final ServicioUsuario servicioUsuario;
	private final ServicioFuncion servicioFuncion;
	private final ServicioCine servicioCine;
	private final ServicioPelicula servicioPelicula;
	
	@Autowired
	public ControladorEntrada(ServicioEntrada servicioEntrada,ServicioUsuario servicioUsuario,
							  ServicioFuncion servicioFuncion,ServicioCine servicioCine,ServicioPelicula servicioPelicula) {
		
		this.servicioEntrada = servicioEntrada;
		this.servicioUsuario = servicioUsuario;
		this.servicioFuncion = servicioFuncion;
		this.servicioCine = servicioCine;
		this.servicioPelicula=servicioPelicula;
	}
	
	/*TO DO 
	 * 		- Agregar validaciones en el html para los formularios
	 *      - try catch para casos donde no hay funciones (mejorar)
	 *      - Arreglar la forma de validar al usuario
	 *      - Manejar las etiquetas a almacenar desde la compra de la entrada.
	 *      
	 *      - Que al terminar de comprar la entrada se muestren los datos de la misma en un PDF
			- Que al terminar de comprar la entrada se envie un recibo al correo del comprador
	 *      
			
	*/
	
	@RequestMapping(path = "/entrada-pelicula", method = RequestMethod.GET)
	public ModelAndView entradaPelicula(HttpServletRequest request,@RequestParam("peliculaId") Long peliculaId) {
		
				
		List<CinePelicula> cines = this.servicioCine.getCines(peliculaId);
		//Se utilizo este servicio para no depender de la lista de cines
		// que puede venir null y genera excepcion
		ModelMap model = new ModelMap();
		
		model.put("usuario", obtenerUsuarioLogueado(request));
		model.put("cines", this.servicioCine.getCines(peliculaId));
		model.put("pelicula",cines.get(0).getPelicula());

		model.addAttribute("datosCine", new DatosCine());
		
		return new ModelAndView ("entrada-pelicula",model);
	}
	
	@RequestMapping(path = "/entrada-preparacion", method = RequestMethod.POST)
	public ModelAndView entradaPreparacion(@ModelAttribute("datosCine") DatosCine datos,
									   	   HttpServletRequest request,final RedirectAttributes redirectAttributes) {
			
		try {
			obtenerFuncionesPor(datos);
		}catch(NoSeEncontraronFuncionesException q){
			redirectAttributes.addFlashAttribute("mensaje","No existen funciones para esta pelicula y este cine");
			return new ModelAndView("redirect:/home");	
		}
		
		ModelMap model = new ModelMap();
		
		model.put("usuario", obtenerUsuarioLogueado(request));
		model.put("funciones",  obtenerFuncionesPor(datos));
		
		model.addAttribute("datosEntrada", new DatosEntrada());
		
		return new ModelAndView("entrada-preparacion",model);
	}
	
	@RequestMapping(path = "/entrada-asientos",method = RequestMethod.POST)
	public ModelAndView entradaAsientos(@ModelAttribute("datosEntrada") DatosEntrada datosEntrada,
			 HttpServletRequest request,final RedirectAttributes redirectAttributes) {
		
		ModelMap model = new ModelMap();
		
		model.put("usuario", obtenerUsuarioLogueado(request));
		model.put("funcion", obtenerFuncion(datosEntrada.getFuncion()));
		model.put("asientos", obtenerAsientosDeLaFuncion(datosEntrada.getFuncion().getId()));
		model.addAttribute("datosEntrada", datosEntrada);
		
		return new ModelAndView("entrada-asientos",model);
	}
		
	@RequestMapping(path = "/entrada-compra", method = RequestMethod.POST)
	public ModelAndView entradaCompra(@ModelAttribute("datosEntrada") DatosEntrada datosEntrada,
									 HttpServletRequest request,final RedirectAttributes redirectAttributes) {

		
		ModelAndView usuarioLogueado = validarUsuarioLogueado(request, redirectAttributes);
		if(usuarioLogueado!=null) 
			return usuarioLogueado;
		
		try {
			comprarEntrada(datosEntrada); 
		}catch(DatosEntradaInvalidaException q) {
			redirectAttributes.addFlashAttribute("mensaje","Debe comprar por lo menos una entrada y seleccionar una funcion!");
			return new ModelAndView("redirect:/home");	
		}
		
		List <Entrada> entradaComprada = this.servicioEntrada.getEntradasCompradasDelUsuario(datosEntrada.getUsuario().getId(),
																  							datosEntrada.getFuncion().getId());
		ModelMap model = new ModelMap();
		model.put("usuario", obtenerUsuarioLogueado(request));
		model.put("entradas", entradaComprada);
		model.put("mensaje", "Entrada Comprada Exitosamente");
		
		return new ModelAndView("entrada",model);
	}
	
	
	
	@RequestMapping(path = "/ver-entrada", method = RequestMethod.GET)
	public ModelAndView verEntrada(@RequestParam Long entrada,HttpServletRequest request,
									final RedirectAttributes redirectAttributes) {
		
		ModelMap model = new ModelMap();
		
		Usuario usuarioLogueado = obtenerUsuarioLogueado(request);
		
		if(null==usuarioLogueado) { 
			redirectAttributes.addFlashAttribute("mensaje","!Registrese Para Comprar sus entradas!");
			return new ModelAndView("redirect:/registrarme");
		}
		
		Entrada entradaEncontrada = this.servicioEntrada.getEntrada(entrada);

		model.put("usuario", usuarioLogueado);
		model.put("entrada", entradaEncontrada);

		return new ModelAndView("entrada",model);
	}

	private ModelAndView validarUsuarioLogueado(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		Usuario usuarioLogueado = obtenerUsuarioLogueado(request);
		
		if(null==usuarioLogueado) {
			redirectAttributes.addFlashAttribute("mensaje","Registrese Para Comprar sus entradas!");
			return new ModelAndView("redirect:/registrarme");
		}
		
		return null;
	}
	
	private List<Asiento> obtenerAsientosDeLaFuncion(Long funcion){
		
		return this.servicioFuncion.obtenerAsientosDeLaFuncion(funcion);
	}
	
	private List<Funcion> obtenerFuncionesPor(DatosCine datos) {
		return this.servicioFuncion.obtenerLasFuncionesDeLosProximosTresDias(datos.getCine(),datos.getPelicula());
	}
	
	private Funcion obtenerFuncion(Funcion funcion) {
		return this.servicioFuncion.getFuncion(funcion.getId());
	}

	private Usuario obtenerUsuarioLogueado(HttpServletRequest request) {
		return this.servicioUsuario.getUsuario((Long)request.getSession().getAttribute("ID"));
	}
	
	private void comprarEntrada(DatosEntrada datosEntrada) {
		this.servicioEntrada.comprar(datosEntrada.getFuncion(),datosEntrada.getUsuario(),datosEntrada.getAsientos());
	}
	
	

}
