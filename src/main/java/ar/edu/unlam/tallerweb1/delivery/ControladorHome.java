package ar.edu.unlam.tallerweb1.delivery;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.PeliculaConEtiquetaDTO;
import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;

@Controller
public class ControladorHome {


	private ServicioSession servicioSession;
	ServicioPelicula servicioPelicula;
     private ServicioUsuario servicioUsuario;
	@Autowired
	public ControladorHome(ServicioSession servicioSession,ServicioPelicula servicioPelicula,ServicioUsuario servicioUsuario){
		this.servicioSession = servicioSession;
		this.servicioPelicula=servicioPelicula;
		this.servicioUsuario=servicioUsuario;
	}
	private Usuario obtenerUsuarioLogueado(HttpServletRequest request) {
		return this.servicioUsuario.getUsuario((Long)request.getSession().getAttribute("ID"));
	}
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome(HttpServletRequest request,@ModelAttribute("error") String mensaje) {
		
		ModelMap model = new ModelMap();
		Long userId = this.servicioSession.getUserId(request);
		 
		//model.put("error", mensaje);
		model.put("usuario", obtenerUsuarioLogueado(request));

        List<PeliculaConEtiquetaDTO> peliculasGeneroElegido = servicioPelicula.obtenerPeliculasEnBaseAGeneroElegido(obtenerUsuarioLogueado(request));
		List<PeliculaConEtiquetaDTO>peliculasEstrenos=servicioPelicula.obtenerPeliculaEstrenos();
		List<PeliculaConEtiquetaDTO>proximosEstrenos=servicioPelicula.obtenerProximosEstrenos();
		
		model.put("peliculasEstrenos", peliculasEstrenos);
		model.put("proximosEstrenos", proximosEstrenos);
		model.put("peliculasGeneroElegido", peliculasGeneroElegido);
		return new ModelAndView("home",model);
	}

}
