package ar.edu.unlam.tallerweb1.delivery;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.clasificacionPelicula.ClasificacionPelicula;
import ar.edu.unlam.tallerweb1.domain.clasificacionPelicula.ServicioClasificacion;
import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.genero.ServicioGenero;
import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
import ar.edu.unlam.tallerweb1.domain.pelicula.EtiquetaPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.PeliculaConEtiquetaDTO;
import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;

@Controller
public class ControladorCartelera{

	
	ServicioGenero servicioGenero;
	ServicioClasificacion servicioClasificacion;
	ServicioPelicula servicioPelicula;
	ServicioSession servicioSession;
	
	@Autowired
	public ControladorCartelera(ServicioGenero servicioGenero,ServicioClasificacion servicioClasificacion,
			ServicioPelicula servicioPelicula,ServicioSession servicioSession) {

        this.servicioGenero=servicioGenero;
		this.servicioClasificacion=servicioClasificacion;
		this.servicioPelicula=servicioPelicula;
		this.servicioSession=servicioSession;
	}
	
	@RequestMapping(path="/cartelera")
	
    public ModelAndView irACartelera(HttpServletRequest request,@RequestParam(value ="genero",required=false) Long genero,
    		@RequestParam(value ="clasificacion",required=false) Long clasificacion,
    		@RequestParam(value ="orden",required=false) String orden){
		
    ModelMap model=new ModelMap();
	Long userId = this.servicioSession.getUserId(request);
	 
	//model.put("error", mensaje);
	
    Filtro filtro=new Filtro(genero,clasificacion,orden);
    List<Genero> listaGeneros = this.servicioGenero.listarGeneros();
    List<ClasificacionPelicula>listaClasificacion=this.servicioClasificacion.listarClasificacion();
    List<PeliculaConEtiquetaDTO>peliculas=this.servicioPelicula.obtenerPeliculas(filtro);
    List<String>filtrosSeleccionados=new ArrayList<>();
    
    if(genero!=null) filtrosSeleccionados.add(this.servicioGenero.getDescripcionGeneroById(genero));
    if(clasificacion!=null)filtrosSeleccionados.add(this.servicioClasificacion.getDescripcionClasificacionById(clasificacion));
    if(orden!=null && orden!="") filtrosSeleccionados.add(orden);
    
    model.put("usuario", userId);
    model.put("generos",listaGeneros);
    model.put("clasificaciones",listaClasificacion);
    model.put("peliculasDTO",peliculas);
    model.put("filtrosSeleccionados", filtrosSeleccionados);

    
    return new ModelAndView("cartelera",model);
}
	

}
