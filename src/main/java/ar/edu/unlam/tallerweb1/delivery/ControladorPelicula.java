package ar.edu.unlam.tallerweb1.delivery;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;


@Controller
public class ControladorPelicula {
	
	private final ServicioSession servicioSession;
	
	@Autowired
	public ControladorPelicula(ServicioSession servicioSession) {
		this.servicioSession=servicioSession;
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
	
	@RequestMapping(path = "/resultado-busqueda", method = RequestMethod.GET)
	public ModelAndView buscarPelicula(HttpServletRequest request) {
		
		Long userId = this.servicioSession.getUserId(request);
		
		String busqueda = "busqueda.exe";
				
		ModelMap model = new ModelMap();
			
		if (userId==null) {
			model.put("busqueda", busqueda);
			
			return new ModelAndView("resultado-busqueda",model);
        }
				
		model.put("usuario", userId);
		model.put("busqueda", busqueda);
		
		return new ModelAndView("resultado-busqueda",model);
	}

	
	
	

}
