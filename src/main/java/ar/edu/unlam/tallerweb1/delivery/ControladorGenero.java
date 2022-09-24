package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.genero.ServicioGenero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorGenero {

    private ServicioGenero servicioGenero;
    @Autowired
    public ControladorGenero(ServicioGenero servicioGenero) {
        this.servicioGenero=servicioGenero;
    }

    @RequestMapping(path = "/listarGeneros")
    public ModelAndView listarGeneros() {
        List<Genero> listaGeneros = this.servicioGenero.listarGeneros();
        ModelMap modelMap = new ModelMap();
        modelMap.put("generos",listaGeneros);
        return new ModelAndView("cartelera",modelMap);
    }
}
