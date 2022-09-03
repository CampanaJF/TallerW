package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.*;
import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorLogin {

	private ServicioLogin servicioLogin;
	private ServicioSession servicioSession;

	@Autowired
	public ControladorLogin(ServicioLogin servicioLogin,ServicioSession servicioSession){
		this.servicioLogin = servicioLogin;
		this.servicioSession = servicioSession;
	}


	@RequestMapping("/login")
	public ModelAndView irALogin(HttpServletRequest request,final RedirectAttributes redirectAttributes) {

		ModelMap model = new ModelMap();
		Long sess = servicioSession.getUserId(request);
		
		if(null!=sess) {
			redirectAttributes.addFlashAttribute("mensaje","Ya esta Logueado!"); //permite redirect con model
			return new ModelAndView("redirect:/home");
		}

		model.put("datosUsuario", new Usuario());

		return new ModelAndView("login", model);
	}

	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("datosUsuario") Usuario datosUsuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		Usuario usuarioBuscado = servicioLogin.loginUsuario(datosUsuario.getEmail(), datosUsuario.getPassword());
		if (usuarioBuscado != null) {
			servicioSession.setUserId(usuarioBuscado.getId(), request);
			return new ModelAndView("redirect:/home");
		} else {
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}
	
	@RequestMapping("/registrarme")
	public ModelAndView registro(HttpServletRequest request, final RedirectAttributes redirectAttributes) {

		Long sess = servicioSession.getUserId(request);
		ModelMap model = new ModelMap();
		
		if(null!=sess) {
			redirectAttributes.addFlashAttribute("mensaje","Ya esta Logueado!");
			return new ModelAndView("redirect:/home");
		}
		
		model.put("datosUsuario", new Usuario());
		
		return new ModelAndView("registro-usuario", model);
	}
	
	@RequestMapping(path="/procesarRegistro",method=RequestMethod.POST)
	public ModelAndView procesarRegistro(
			@ModelAttribute("datosUsuario") Usuario datosUsuario, final RedirectAttributes redirectAttributes) {
		
		String mensaje="Se Registro Exitosamente";
			
		try {
            servicioLogin.registrarUsuario(datosUsuario);
        } catch (EmailEnUsoException eeue) {
        	mensaje="El Email ya esta en uso";
        	
        } catch (PasswordsDiferentesException pde) {
        	mensaje="Las contraseñas son diferentes";
        	
        } catch (PasswordLenghtException ple) {
        	mensaje="La contraseña debe tener almenos 12 caracteres";
        }
		
		if(mensaje!="Se Registro Exitosamente") {
			redirectAttributes.addFlashAttribute("mensaje",mensaje);
			return new ModelAndView("redirect:/registrarme");
		}

		redirectAttributes.addFlashAttribute("mensaje",mensaje);
		return new ModelAndView("redirect:/home");
			
	}
	
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ModelAndView guardarUsuario(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ModelAndView("redirect:/home");
    }
	
	

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView goHome(HttpServletRequest request,@ModelAttribute("error") String mensaje) {
		
		ModelMap model = new ModelMap();
		Long userId = this.servicioSession.getUserId(request);
		 
		model.put("error", mensaje);
		
		return new ModelAndView("home",model);
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/login");
	}
	
	
}
