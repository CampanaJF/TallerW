package ar.edu.unlam.tallerweb1.delivery;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCartelera {

@RequestMapping(path="/cartelera")

    public ModelAndView irACartelera(){
    ModelMap model=new ModelMap();
    
    return new ModelAndView("cartelera",model);
}


}
