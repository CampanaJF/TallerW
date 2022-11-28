package ar.edu.unlam.tallerweb1.delivery;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import ar.edu.unlam.tallerweb1.domain.cine.Asiento;
import ar.edu.unlam.tallerweb1.domain.cine.CinePelicula;
import ar.edu.unlam.tallerweb1.domain.cine.ServicioCine;
import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.entrada.EntradaPendiente;
import ar.edu.unlam.tallerweb1.domain.entrada.ServicioEntrada;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.funcion.ServicioFuncion;
import ar.edu.unlam.tallerweb1.domain.historial.ServicioHistorial;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.DatosEntradaInvalidaException;
import ar.edu.unlam.tallerweb1.exceptions.NoSeEncontraronFuncionesException;


@Controller
public class ControladorEntrada extends ControladorBase{
	

	private final ServicioEntrada servicioEntrada;
	private final ServicioFuncion servicioFuncion;
	private final ServicioCine servicioCine;
	private final ServicioHistorial servicioHistorial;
	private final ServicioMail servicioMail;
	@Autowired
	public ControladorEntrada(ServicioEntrada servicioEntrada, ServicioUsuario servicioUsuario,
							  ServicioFuncion servicioFuncion, ServicioCine servicioCine
			                  , ServicioHistorial servicioHistorial, ServicioMail servicioMail) {
		super(servicioUsuario);
		this.servicioEntrada = servicioEntrada;
		this.servicioFuncion = servicioFuncion;
		this.servicioCine = servicioCine;
		this.servicioHistorial = servicioHistorial;
		this.servicioMail=servicioMail;
	}
	
	/*TO DO 
	 * 		- Agregar validaciones en el html para los formularios
	 *      - try catch para casos donde no hay funciones (mejorar)
	 *      - Arreglar la forma de validar al usuario
	 *      
	 *      - Hacer una clase que devuelva un modelo que contenga el usuario loguedo y sus notificaciones (?

	 *      - Que al terminar de comprar la entrada se muestren los datos de la misma en un PDF
			- Que al terminar de comprar la entrada se envie un recibo al correo del comprador
			
			- Notificacion de asiento disponible (funcion que tiene el asiento)
	 *      - Al apretar la notificacion te manda a la vista de entradas con la opcion de comprar las que esten libres de esa funcion
	 *      
	 *      Las notificaciones deben tener id,usuario,string de descripcion y funcion
		    se podria hacer una clase abstracta
		    al apretar la notifiacion te dirige a la lista de entradas disponibles para comprar
			
	*/
	

	
	@RequestMapping(path = "/entrada-pelicula", method = RequestMethod.GET)
	public ModelAndView entradaPelicula(HttpServletRequest request,@RequestParam("peliculaId") Long peliculaId) {
		
				
		List<CinePelicula> cines = obtenerCines(peliculaId);
		//Se utilizo este servicio para no depender de la lista de cines
		// que puede venir null y genera excepcion
		ModelMap model = new ModelMap();
		
		Usuario usuario = obtenerUsuarioLogueado(request);
		
		if(usuario!=null&&hayNotificaciones(usuario)) {
			
			List<EntradaPendiente> notificaciones = obtenerNotifcaciones(usuario);
			
			model.put("notificaciones", notificaciones);
			
			model.put("cantidadNotificaciones", obtenerCantidadNotificaciones(notificaciones));
			
		}
		
		model.put("usuario", usuario);
		model.put("cines", obtenerCines(peliculaId));
		model.put("pelicula",cines.get(0).getPelicula());

		model.addAttribute("datosCine", new DatosCine());
		
		return new ModelAndView ("entrada-pelicula",model);
	}

	private List<CinePelicula> obtenerCines(Long peliculaId) {
		return this.servicioCine.getCines(peliculaId);
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
		
		if(validarAsientosFuncion(datosEntrada)) {
			agregarAPendientes(datosEntrada);
			redirectAttributes.addFlashAttribute("mensaje","No Hay asientos para esa funcion, le avisaremos si se libera uno");
			return new ModelAndView("redirect:/home");
		}
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
		
		List <Entrada> entradaComprada = obtenerEntradasVigentesDeLaFuncion(datosEntrada);
		enviarMail(entradaComprada);
		guardarEnElHistorial(datosEntrada, entradaComprada);
		
		ModelMap model = new ModelMap();
		model.put("usuario", obtenerUsuarioLogueado(request));
		model.put("entradas", entradaComprada);
		model.put("mensaje", "Entrada Comprada Exitosamente");
		
		return new ModelAndView("entrada",model);
	}

	@RequestMapping(path = "/mis-entradas", method = RequestMethod.GET)
	public ModelAndView verEntradasVigentes(HttpServletRequest request,final RedirectAttributes redirectAttributes) {
		
		Usuario usuarioLogueado = obtenerUsuarioLogueado(request);
		
		validarUsuario(usuarioLogueado,redirectAttributes);
				
		ModelMap model = new ModelMap();
		model.put("usuario", usuarioLogueado);
		model.put("entradas", this.servicioEntrada.obtenerEntradasVigentes(usuarioLogueado));
		
		return new ModelAndView("entrada",model);
	}
	
	private ModelAndView validarUsuario(Usuario usuarioLogueado,final RedirectAttributes redirectAttributes) {
		
		if(null==usuarioLogueado) { 
			redirectAttributes.addFlashAttribute("mensaje","!Ingrese Antes de seguir!");
			return new ModelAndView("redirect:/login");
		}
		
		return null;
	}

	private ModelAndView validarUsuarioLogueado(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		Usuario usuarioLogueado = obtenerUsuarioLogueado(request);
		
		if(null==usuarioLogueado) {
			redirectAttributes.addFlashAttribute("mensaje","Registrese Para Comprar sus entradas!");
			return new ModelAndView("redirect:/registrarme");
		}
		
		return null;
	}
	
	
	private void guardarEnElHistorial(DatosEntrada datosEntrada, List<Entrada> entradaComprada) {
		this.servicioHistorial.guardarEnElHistorial(datosEntrada.getUsuario(),entradaComprada.get(0).getFuncion().getPelicula());
	}

	private void enviarMail(List<Entrada> entradaComprada) {
		for (Entrada entrada : entradaComprada) {
			this.servicioMail.enviarMail(entrada.getUsuario().getEmail(),
					servicioMail.getAsuntoConfirmacionCompra(),
					servicioMail.getMensajeConfirmacionCompra(entrada.getUsuario(),
							entrada.getFuncion()));
		}
		
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


	
	private void comprarEntrada(DatosEntrada datosEntrada) {
		this.servicioEntrada.comprar(datosEntrada.getFuncion(),datosEntrada.getUsuario(),datosEntrada.getAsientos());
	}
	
	private List<Entrada> obtenerEntradasVigentesDeLaFuncion(DatosEntrada datosEntrada) {
		return this.servicioEntrada.obtenerEntradasVigentes(datosEntrada.getUsuario().getId(),
																  	datosEntrada.getFuncion().getId());
	}
	
	private boolean validarAsientosFuncion(DatosEntrada datosEntrada) {
		return !(this.servicioFuncion.validarAsientosDisponibles(datosEntrada.getFuncion()));
	}

	private void agregarAPendientes(DatosEntrada datosEntrada) {
		this.servicioEntrada.agregarAPendientes(datosEntrada.getFuncion(),datosEntrada.getUsuario());
	}
	
	private boolean hayNotificaciones(Usuario usuario) {
		return !(obtenerNotifcaciones(usuario).isEmpty());
	}


	private List<EntradaPendiente> obtenerNotifcaciones(Usuario usuario) {
		return this.servicioEntrada.obtenerPendientesActivasDelUsuario(usuario);
	}
	

	private int obtenerCantidadNotificaciones(List<EntradaPendiente> notificaciones) {
		
		int total = 0;
		
		for (EntradaPendiente entradaPendiente : notificaciones) {
			total++;
		}
		
		return total;
	}



}
