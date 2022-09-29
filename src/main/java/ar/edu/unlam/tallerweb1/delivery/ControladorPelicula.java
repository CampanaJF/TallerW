package ar.edu.unlam.tallerweb1.delivery;



import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;

import java.util.List;


@Controller
public class ControladorPelicula {
	
	private final ServicioSession servicioSession;
	private ServicioPelicula servicioPelicula;
	@Autowired
	public ControladorPelicula(ServicioSession servicioSession,ServicioPelicula servicioPelicula) {
		this.servicioSession=servicioSession;
		this.servicioPelicula = servicioPelicula;
	}
	
	@RequestMapping(path = "/pelicula", method = RequestMethod.GET)
	public ModelAndView verPelicula(HttpServletRequest request) { //@RequestParam("peliculaId") Long peliculaId
		
		Long userId = this.servicioSession.getUserId(request);
		
		String pelicula = "pelicula.exe";
				
		ModelMap model = new ModelMap();
			
		if (userId==null) {
			model.put("pelicula", pelicula);
			
			return new ModelAndView("pelicula",model);
        }
				
		model.put("usuario", userId);
		model.put("pelicula", pelicula);
		
		return new ModelAndView("pelicula",model);
	}

    @RequestMapping(path = "/busqueda", method = RequestMethod.GET)
	public ModelAndView buscar(@RequestParam(value="titulo")String titulo, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		Long userId = this.servicioSession.getUserId(request);
		List<Pelicula> peliculaList = this.servicioPelicula.buscarPeliculas(titulo);

		model.put("usuario",userId);
		model.put("peliculas", peliculaList);
		return new ModelAndView("pelicula-buscada",model);
	}
}
