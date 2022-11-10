package ar.edu.unlam.tallerweb1.delivery;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.historial.ServicioHistorial;
import ar.edu.unlam.tallerweb1.domain.pelicula.Etiqueta;
import ar.edu.unlam.tallerweb1.domain.pelicula.EtiquetaPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.PeliculaConEtiquetaDTO;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

@Controller
public class ControladorHome {

	private ServicioUsuario servicioUsuario;
	private ServicioPelicula servicioPelicula;
	private ServicioHistorial servicioHistorial;

	@Autowired
	public ControladorHome(ServicioUsuario servicioUsuario,ServicioPelicula servicioPelicula,ServicioHistorial servicioHistorial){
		this.servicioUsuario = servicioUsuario;
		this.servicioPelicula = servicioPelicula;
		this.servicioHistorial = servicioHistorial;
	}

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome(HttpServletRequest request,@ModelAttribute("error") String mensaje) {
		
		ModelMap model = new ModelMap();
	    Usuario usuario = servicioUsuario.getUsuario((Long)request.getSession().getAttribute("ID"));
		 

		if(usuario!=null) {
			List<EtiquetaPelicula> peliculasHistorial = servicioHistorial.obtenerPeliculasDeLasEtiquetasDelUsuario(usuario);
			model.put("historial", peliculasHistorial);
		}
		
		List<PeliculaConEtiquetaDTO>peliculasEstrenos=servicioPelicula.obtenerPeliculaEstrenos();
		List<PeliculaConEtiquetaDTO>proximosEstrenos=servicioPelicula.obtenerProximosEstrenos();
		
		
		model.put("usuario", usuario);
		
		model.put("peliculasEstrenos", peliculasEstrenos);
		model.put("proximosEstrenos", proximosEstrenos);
		
		return new ModelAndView("home",model);
	}
	
	private int obtenerIndice(Usuario usuario) {
		
		Random r = new Random();
		int low = 0;
		int high = obtenerEtiquetasDelHistorial(usuario).size();
		int result = r.nextInt(high-low) + low;
		
		return result;
	}

	private List<Etiqueta> obtenerEtiquetasDelHistorial(Usuario usuario) {
		return this.servicioHistorial.obtenerEtiquetasDelHistorial(usuario);
	}
	
	


}
