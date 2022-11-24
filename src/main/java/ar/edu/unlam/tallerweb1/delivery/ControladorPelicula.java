package ar.edu.unlam.tallerweb1.delivery;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.cine.ServicioCine;
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


import java.util.List;


@Controller
public class ControladorPelicula extends ControladorBase{
	
	private ServicioPelicula servicioPelicula;
	private ServicioCine servicioCine;
	@Autowired

	public ControladorPelicula(ServicioPelicula servicioPelicula,ServicioUsuario servicioUsuario,ServicioCine servicioCine) {
		super(servicioUsuario);
		this.servicioPelicula = servicioPelicula;
		this.servicioCine=servicioCine;

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
		String listadoDeCines=this.servicioCine.getCinesUbicacion(pelicula);
		ModelMap model = new ModelMap();
		model.put("pelicula",peliculaBuscada);
		model.put("similares", this.servicioPelicula.obtenerPeliculasSimilaresPorGenero(peliculaBuscada.getGenero(),peliculaBuscada));
        model.put("promedio",this.servicioPelicula.obtenerPromedioValoracionesPorPelicula(peliculaBuscada));
		model.put("votos",this.servicioPelicula.obtenerCalificacionesDeUnaPelicula(peliculaBuscada).size());
		model.put("usuario",obtenerUsuarioLogueado(request));
		model.put("listadoDeCines", listadoDeCines);

		return new ModelAndView("ver-pelicula",model);
	}
	@RequestMapping(path="/calificar-pelicula", method = RequestMethod.GET)
	public ModelAndView calificarpelicula(@RequestParam Long pelicula,HttpServletRequest request){
		ModelMap model = new ModelMap();
		model.put("pelicula",this.servicioPelicula.buscarPeliculaPorId(pelicula));
		model.put("usuario",obtenerUsuarioLogueado(request));
		model.put("datosValoracion",new DatosValoracion());
		return new ModelAndView("calificar-pelicula",model);
	}
	@RequestMapping(path="/guardar-calificacion", method=RequestMethod.POST)
	public ModelAndView guardarCalificacion(@ModelAttribute("datosValoracion")DatosValoracion  datosValoracion,
											HttpServletRequest request){

		Pelicula pelicula = this.servicioPelicula.buscarPeliculaPorId(datosValoracion.getPelicula().getId());

		this.servicioPelicula.guardarValoracionPelicula(datosValoracion.getPuntos(), pelicula, datosValoracion.getComentarios(), datosValoracion.getUsuario());
		ModelMap model = new ModelMap();
		model.put("comentario", datosValoracion.getComentarios());
		model.put("usuario", obtenerUsuarioLogueado(request));
		model.put("pelicula",pelicula);
		model.put("puntos", datosValoracion.getPuntos());

		return new ModelAndView("calificacionPelicula-exitosa",model);
	}
	@RequestMapping(path="/ver-opiniones", method=RequestMethod.GET)
	public ModelAndView verOpiniones(@RequestParam Long pelicula,HttpServletRequest request){
		Pelicula peliculaBuscada = this.servicioPelicula.buscarPeliculaPorId(pelicula);
		List<Valoracion> valoraciones = this.servicioPelicula.obtenerCalificacionesDeUnaPelicula(peliculaBuscada);
		ModelMap model = new ModelMap();
		model.put("pelicula",peliculaBuscada);
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
