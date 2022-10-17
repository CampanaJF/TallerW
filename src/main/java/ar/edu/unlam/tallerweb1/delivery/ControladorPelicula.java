package ar.edu.unlam.tallerweb1.delivery;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;

import ar.edu.unlam.tallerweb1.domain.pelicula.Valoracion;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;


@Controller
public class ControladorPelicula {
	
	private final ServicioSession servicioSession;
	private ServicioPelicula servicioPelicula;
	private ServicioUsuario servicioUsuario;
	@Autowired
	public ControladorPelicula(ServicioSession servicioSession, ServicioPelicula servicioPelicula, ServicioUsuario servicioUsuario) {
		this.servicioSession=servicioSession;
		this.servicioPelicula = servicioPelicula;
		this.servicioUsuario=servicioUsuario;
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
	private Usuario obtenerUsuarioLogueado(HttpServletRequest request) {
		return this.servicioUsuario.getUsuario((Long)request.getSession().getAttribute("ID"));
	}
    @RequestMapping(path = "/busqueda", method = RequestMethod.GET)
	public ModelAndView buscar(@RequestParam(value="titulo")String titulo,HttpServletRequest request) {

		ModelMap model = new ModelMap();
		model.put("usuario",obtenerUsuarioLogueado(request));
		model.put("peliculas", this.servicioPelicula.buscarPeliculas(titulo));
		return new ModelAndView("pelicula-buscada",model);
	}

	@RequestMapping(path="/ver-pelicula", method = RequestMethod.GET)
	public ModelAndView verPelicula(@RequestParam Long pelicula,HttpServletRequest request){

		Pelicula peliculaBuscada = this.servicioPelicula.buscarPeliculaPorId(pelicula);
		List<Pelicula> peliculasSimilares = this.servicioPelicula.obtenerPeliculasSimilaresPorGenero(peliculaBuscada.getGenero(),peliculaBuscada);

		ModelMap model = new ModelMap();
		model.put("pelicula",peliculaBuscada);
		model.put("similares",peliculasSimilares);
		//model.put("datosValoracion",new DatosValoracion());
		model.put("usuario",obtenerUsuarioLogueado(request));
       //model.put("promedioValoraciones", this.servicioPelicula.obtenerPromedioValoracionesPorPelicula(peliculaBuscada));

		return new ModelAndView("ver-pelicula",model);
	}
	@RequestMapping(path="/guardar-calificacion", method=RequestMethod.POST)
	public ModelAndView guardarCalificacion(@ModelAttribute DatosValoracion datosValoracion,
											final RedirectAttributes redirectAttributes){

		this.servicioPelicula.guardarValoracionPelicula(datosValoracion.getValoracion().getEstrellas(), datosValoracion.getValoracion().getPelicula());

		redirectAttributes.addFlashAttribute("mensaje","Su puntuacion ha sido registrada");

		return new ModelAndView("redirect:/ver-pelicula");
	}
}
