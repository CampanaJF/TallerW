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

import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.cine.CinePelicula;
import ar.edu.unlam.tallerweb1.domain.cine.ServicioCine;
import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.entrada.ServicioEntrada;
import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;

@Controller
public class ControladorEntrada {
	
	private final ServicioSession servicioSession;
	private final ServicioEntrada servicioEntrada;
	private final ServicioCine servicioCine;
	
	@Autowired
	public ControladorEntrada(ServicioSession servicioSession,ServicioEntrada servicioEntrada,ServicioCine servicioCine) {
		this.servicioSession=servicioSession;
		this.servicioEntrada=servicioEntrada;
		this.servicioCine=servicioCine;
	}
	
	@RequestMapping(path = "/comprar-entrada", method = RequestMethod.GET)
	public ModelAndView prepararEntrada(HttpServletRequest request) {
		
		Long sess = servicioSession.getUserId(request);
		ModelMap model = new ModelMap();
		
		List<CinePelicula> cines = this.servicioCine.getCines(1L);
		
		model.put("datosEntrada", new Entrada());
		model.put("usuario", sess);
		model.put("cines", cines);
		
		return new ModelAndView ("comprar-entrada",model);
	}
	
	@RequestMapping(path = "/procesar-compra-entrada", method = RequestMethod.POST)
	public ModelAndView comprarEntrada(@ModelAttribute("datosEntrada") Entrada datosEntrada,HttpServletRequest request) {
		
		Long sess = this.servicioSession.getUserId(request);
				
		Entrada entradaComprada = this.servicioEntrada.comprarEntrada(datosEntrada);
		
		ModelMap model = new ModelMap();
		
		model.put("usuario", sess);
		model.put("entrada", entradaComprada);
		
		return new ModelAndView("entrada",model);
	}

}
