package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.genero.ServicioGenero;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.GeneroNoElegidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorGenero {
    private ServicioUsuario servicioUsuario;
    private ServicioGenero servicioGenero;

    @Autowired
    public ControladorGenero(ServicioUsuario servicioUsuario, ServicioGenero servicioGenero){
        this.servicioGenero=servicioGenero;
        this.servicioUsuario=servicioUsuario;
    }

    private Usuario obtenerUsuarioLogueado(HttpServletRequest request) {
        return this.servicioUsuario.getUsuario((Long)request.getSession().getAttribute("ID"));
    }

    @RequestMapping(path = "/elegir-gustos", method = RequestMethod.GET)
    public ModelAndView elegirGustos(HttpServletRequest request){
        ModelMap model = new ModelMap();
        model.put("usuario",obtenerUsuarioLogueado(request));
        model.put("datosGenero", new DatosGenero());
        model.put("generos", this.servicioGenero.listarGeneros());
        return new ModelAndView("elegir-gustos-cinematograficos",model);
    }

    @RequestMapping(path = "/procesar-elegir-gustos", method = RequestMethod.POST)
    public ModelAndView procesarElegirGustos(@ModelAttribute(value = "datosGenero")DatosGenero  datosGenero,
                                             final RedirectAttributes redirectAttributes){
        try {
            this.servicioGenero.guardarGeneroElegidoPorUsuario(datosGenero.getGeneros(), datosGenero.getUsuario());
        }catch (GeneroNoElegidoException exception ){
            redirectAttributes.addFlashAttribute("mensaje","Elegi al menos un genero para continuar.");
            return new ModelAndView("redirect:/elegir-gustos");
        }
       return new ModelAndView("redirect:/home");
    }

}