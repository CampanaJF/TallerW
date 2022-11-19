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

import ar.edu.unlam.tallerweb1.domain.helper.ServicioRandomizer;
import ar.edu.unlam.tallerweb1.domain.historial.ServicioHistorial;
import ar.edu.unlam.tallerweb1.domain.pelicula.Etiqueta;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.PeliculaConEtiquetaDTO;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;


@Controller
public class ControladorHome {


    private ServicioUsuario servicioUsuario;
    private ServicioPelicula servicioPelicula;
    private ServicioHistorial servicioHistorial;
	  private ServicioRandomizer servicioRandomizer;
	
	@Autowired
	public ControladorHome(ServicioUsuario servicioUsuario,ServicioPelicula servicioPelicula,
							ServicioHistorial servicioHistorial,ServicioRandomizer servicioRandomizer){
		this.servicioUsuario = servicioUsuario;
		this.servicioPelicula = servicioPelicula;
		this.servicioHistorial = servicioHistorial;
		this.servicioRandomizer = servicioRandomizer;
	}
  
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome(HttpServletRequest request,@ModelAttribute("error") String mensaje) {
		
		ModelMap model = new ModelMap();
	    Usuario usuario = servicioUsuario.getUsuario((Long)request.getSession().getAttribute("ID"));
	   

		if(usuario!=null&&validarHistorialExistente(usuario)) {		
			Integer primerIndice = obtenerIndice(indiceMax(usuario));

			List<PeliculaConEtiquetaDTO> peliculasHistorialA = obtenerPeliculasDelHistorial(usuario,primerIndice);
			model.put("historialA", peliculasHistorialA);
			
			List<PeliculaConEtiquetaDTO> peliculasHistorialB = obtenerPeliculasDelHistorial(usuario,
																					obtenerIndice(indiceMax(usuario),primerIndice));
			model.put("historialB", peliculasHistorialB);
			  
			model.put("usuario", usuario);
			List<PeliculaConEtiquetaDTO> peliculasGeneroElegido = servicioPelicula.obtenerPeliculasEnBaseAGeneroElegido(usuario);
			
			model.put("peliculasGeneroElegido", peliculasGeneroElegido);
			
		}else {
			 model.put("usuario", usuario);
		}
		
		
		List<PeliculaConEtiquetaDTO>peliculasEstrenos=servicioPelicula.obtenerPeliculaEstrenos();
		List<PeliculaConEtiquetaDTO>proximosEstrenos=servicioPelicula.obtenerProximosEstrenos();
		
		
		model.put("peliculasEstrenos", peliculasEstrenos);
		model.put("proximosEstrenos", proximosEstrenos);
		
		return new ModelAndView("home",model);
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
	
	


}
