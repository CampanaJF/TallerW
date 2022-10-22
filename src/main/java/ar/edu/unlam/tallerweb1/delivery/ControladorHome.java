package ar.edu.unlam.tallerweb1.delivery;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;

@Controller
public class ControladorHome {


	private ServicioSession servicioSession;
	ServicioPelicula servicioPelicula;

	@Autowired
	public ControladorHome(ServicioSession servicioSession,ServicioPelicula servicioPelicula){
		this.servicioSession = servicioSession;
		this.servicioPelicula=servicioPelicula;
	}

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome(HttpServletRequest request,@ModelAttribute("error") String mensaje) {
		
		ModelMap model = new ModelMap();
		Long userId = this.servicioSession.getUserId(request);
		 
		//model.put("error", mensaje);
		model.put("usuario", userId);
	
		List<Pelicula>peliculasEstrenos=servicioPelicula.obtenerPeliculaEstrenos();
		List<Pelicula>proximosEstrenos=servicioPelicula.obtenerProximosEstrenos();
		
		model.put("peliculasEstrenos", peliculasEstrenos);
		model.put("proximosEstrenos", proximosEstrenos);
		
		return new ModelAndView("home",model);
	}

}
