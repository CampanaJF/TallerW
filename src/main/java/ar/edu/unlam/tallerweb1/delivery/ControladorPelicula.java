package ar.edu.unlam.tallerweb1.delivery;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;

import ar.edu.unlam.tallerweb1.domain.pelicula.Valoracion;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;


@Controller
public class ControladorPelicula extends ControladorBase{
	
	private final ServicioSession servicioSession;
	private ServicioPelicula servicioPelicula;
	private ServicioUsuario servicioUsuario;
	@Autowired
	public ControladorPelicula(ServicioSession servicioSession, ServicioPelicula servicioPelicula, ServicioUsuario servicioUsuario) {
		super(servicioUsuario);
		this.servicioPelicula=servicioPelicula;
		this.servicioSession=servicioSession;
	}
	@Override
	public Usuario obtenerUsuarioLogueado(HttpServletRequest request){
		return super.obtenerUsuarioLogueado(request);
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
		ModelMap model = new ModelMap();
		model.put("pelicula",peliculaBuscada);
		model.put("similares", this.servicioPelicula.obtenerPeliculasSimilaresPorGenero(peliculaBuscada.getGenero(),peliculaBuscada));
        model.put("promedio",this.servicioPelicula.obtenerPromedioValoracionesPorPelicula(peliculaBuscada));
		model.put("votos",this.servicioPelicula.obtenerCalificacionesDeUnaPelicula(peliculaBuscada).size());
		model.put("usuario",obtenerUsuarioLogueado(request));

		return new ModelAndView("ver-pelicula",model);
	}
	@RequestMapping(path="/calificar-pelicula", method = RequestMethod.GET)
	public ModelAndView calificarpelicula(@RequestParam Long pelicula,HttpServletRequest request){
		Pelicula peliculaBuscada = this.servicioPelicula.buscarPeliculaPorId(pelicula);

		ModelMap model = new ModelMap();
		model.put("pelicula",peliculaBuscada);
		model.put("usuario",obtenerUsuarioLogueado(request));
		return new ModelAndView("calificar-pelicula",model);
	}
	@RequestMapping(path="/guardar-calificacion", method=RequestMethod.GET)
	public ModelAndView guardarCalificacion(@RequestParam(value="puntos") int puntos,
											@RequestParam(value = "peliculaId") Long peliculaId,
											@RequestParam(value = "comentario") String comentario,
											HttpServletRequest request){

		Pelicula pelicula = this.servicioPelicula.buscarPeliculaPorId(peliculaId);
		Usuario usuario = obtenerUsuarioLogueado(request);
		this.servicioPelicula.guardarValoracionPelicula(puntos,pelicula,comentario,usuario);
        ModelMap model = new ModelMap();
        model.put("votos", this.servicioPelicula.obtenerCalificacionesDeUnaPelicula(pelicula).size());
		model.put("comentario", comentario);
		model.put("mensaje","¡Tu calificación ha sido guardada!");
		model.put("usuario",usuario);
		model.put("pelicula",pelicula);
		model.put("puntos", puntos);

		return new ModelAndView("calificacionPelicula-exitosa",model);
	}
	@RequestMapping(path="/ver-opiniones", method=RequestMethod.GET)
	public ModelAndView verOpiniones(@RequestParam Long pelicula,HttpServletRequest request){
		Pelicula pelicula1 = this.servicioPelicula.buscarPeliculaPorId(pelicula);
		List<Valoracion> valoraciones = this.servicioPelicula.obtenerCalificacionesDeUnaPelicula(pelicula1);
		ModelMap model = new ModelMap();
		model.put("pelicula",pelicula1);
		model.put("valoraciones",valoraciones);
		model.put("usuario",obtenerUsuarioLogueado(request));
		if(noHayValoraciones(valoraciones))
                 model.put("sinvaloracion","Todavia no se han hecho reseñas");

		return  new ModelAndView("ver-opiniones",model);
	}

	private boolean noHayValoraciones(List<Valoracion> valoraciones) {
		return valoraciones.size() == 0;
	}
}
