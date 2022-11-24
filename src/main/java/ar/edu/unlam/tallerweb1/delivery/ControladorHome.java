package ar.edu.unlam.tallerweb1.delivery;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import net.bytebuddy.asm.Advice.This;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.entrada.EntradaPendiente;
import ar.edu.unlam.tallerweb1.domain.entrada.ServicioEntrada;
import ar.edu.unlam.tallerweb1.domain.helper.ServicioRandomizer;
import ar.edu.unlam.tallerweb1.domain.historial.ServicioHistorial;
import ar.edu.unlam.tallerweb1.domain.pelicula.Etiqueta;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.PeliculaConEtiquetaDTO;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;


@Controller
public class ControladorHome extends ControladorBase{


    private ServicioUsuario servicioUsuario;
    private ServicioPelicula servicioPelicula;
    private ServicioHistorial servicioHistorial;
	private ServicioRandomizer servicioRandomizer;
	private ServicioEntrada servicioEntrada;
	
	@Autowired
	public ControladorHome(ServicioUsuario servicioUsuario,ServicioPelicula servicioPelicula,
							ServicioHistorial servicioHistorial,ServicioRandomizer servicioRandomizer,ServicioEntrada servicioEntrada){
		super(servicioUsuario);
		this.servicioPelicula = servicioPelicula;
		this.servicioHistorial = servicioHistorial;
		this.servicioRandomizer = servicioRandomizer;
		this.servicioEntrada = servicioEntrada;
	}
  
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome(HttpServletRequest request,@ModelAttribute("error") String mensaje) {
		
		ModelMap model = new ModelMap();
	    Usuario usuario = obtenerUsuarioLogueado(request);
	   

		model.put("usuario",usuario);
		if (elUsuarioEligioGeneros(usuario)){
			model.put("peliculasGeneroElegido",servicioPelicula.obtenerPeliculasConEtiquetaDTOEnBaseAGeneroElegido(usuario));
		}

		
		model.put("peliculasEstrenos", servicioPelicula.obtenerPeliculaEstrenos());
		model.put("proximosEstrenos", servicioPelicula.obtenerProximosEstrenos());

			
		if(usuario!=null&&hayNotificaciones(usuario)) {
			
			List<EntradaPendiente> notificaciones = obtenerNotifcaciones(usuario);
			
			model.put("notificaciones", notificaciones);
			
			model.put("cantidadNotificaciones", obtenerCantidadNotificaciones(notificaciones));
			
		}
			
		if(usuario!=null&&validarHistorialExistente(usuario)) {
				
			Integer primerIndice = obtenerIndice(indiceMax(usuario));
				
			List<PeliculaConEtiquetaDTO> peliculasHistorialA = obtenerPeliculasDelHistorial(usuario,primerIndice);
			model.put("historialA", peliculasHistorialA);
				
			List<PeliculaConEtiquetaDTO> peliculasHistorialB = obtenerPeliculasDelHistorial(usuario,
																					obtenerIndice(indiceMax(usuario),primerIndice));
			model.put("historialB", peliculasHistorialB);
		}
				
		

		
		return new ModelAndView("home",model);
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

	private int indiceMax(Usuario usuario) {
		return obtenerEtiquetasDelHistorial(usuario).size();
	}

	private int obtenerIndice(Integer indiceMax, Integer primerIndice) {
		return this.servicioRandomizer.obtenerIndice(indiceMax,primerIndice);
	}

	private int obtenerIndice(Integer indiceMax) {
		return this.servicioRandomizer.obtenerIndice(indiceMax);
	}

	private List<PeliculaConEtiquetaDTO> obtenerPeliculasDelHistorial(Usuario usuario, Integer indice) {
		return servicioHistorial.obtenerPeliculasDeLasEtiquetasDelUsuario(usuario,indice);
	}

	private List<Etiqueta> obtenerEtiquetasDelHistorial(Usuario usuario) {
		return this.servicioHistorial.obtenerEtiquetasDelUsuario(usuario);
	}
	
	private Boolean validarHistorialExistente(Usuario usuario) {
		
		if(this.servicioHistorial.obtenerEtiquetasDelUsuario(usuario).size()<3) 
			return false;
		
		return true;
		
	}
	
	private boolean elUsuarioEligioGeneros(Usuario usuario){
		return servicioPelicula.obtenerPeliculasConEtiquetaDTOEnBaseAGeneroElegido(usuario).size() > 0;
	}


}
