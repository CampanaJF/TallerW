package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.exceptions.*;
import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

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
public class ControladorUsuario {

	private ServicioUsuario servicioUsuario;
	private ServicioSession servicioSession;

	@Autowired
	public ControladorUsuario(ServicioUsuario servicioUsuario,ServicioSession servicioSession){
		this.servicioUsuario = servicioUsuario;
		this.servicioSession = servicioSession;
	}


	@RequestMapping("/login")
	public ModelAndView irALogin(HttpServletRequest request,final RedirectAttributes redirectAttributes) {

		ModelMap model = new ModelMap();
		
		if(null!=obtenerUsuarioLogueado(request)) {
			redirectAttributes.addFlashAttribute("mensaje","Ya esta Logueado!"); //permite redirect con model
			return new ModelAndView("redirect:/home");
		}

		model.put("datosUsuario", new Usuario());

		return new ModelAndView("login", model);
	}

	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("datosUsuario") DatosUsuario datosUsuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		Usuario usuarioBuscado = servicioUsuario.loginUsuario(datosUsuario.getEmail(), datosUsuario.getPassword());
		
		if (usuarioBuscado != null) {
			servicioSession.setUserId(usuarioBuscado.getId(), request);
			return new ModelAndView("redirect:/cartelera");
		} else {
			model.put("error", "Usuario o clave incorrecta!");
		}
		return new ModelAndView("login", model);
	}
	
	@RequestMapping("/registrarme")
	public ModelAndView registro(HttpServletRequest request, final RedirectAttributes redirectAttributes) {

		ModelMap model = new ModelMap();
		
		if(null!=obtenerUsuarioLogueado(request)) {
			redirectAttributes.addFlashAttribute("mensaje","Ya esta Logueado!");
			return new ModelAndView("redirect:/home");
		}
		
		model.put("datosUsuario", new DatosUsuario());
		
		return new ModelAndView("registro-usuario", model);
	}
	
	@RequestMapping(path="/procesarRegistro",method=RequestMethod.POST)
	public ModelAndView procesarRegistro(
			@ModelAttribute("datosUsuario") DatosUsuario datosUsuario, final RedirectAttributes redirectAttributes) {
		
		String mensaje="Se Registro Exitosamente!";
			
		try {
            servicioUsuario.registrarUsuario(datosUsuario.getEmail(),datosUsuario.getPassword(),
            								 datosUsuario.getPasswordRe(),datosUsuario.getNombre());
        } catch (EmailEnUsoException eeue) {
        	mensaje="El Email ya esta en uso!";
        	
        } catch (PasswordsDiferentesException pde) {
        	mensaje="Las contraseñas son diferentes!";
        	
        } catch (PasswordLenghtException ple) {
        	mensaje="La contraseña debe tener almenos 12 caracteres!";
        }
		
		if(mensaje!="Se Registro Exitosamente!") {
			redirectAttributes.addFlashAttribute("mensaje",mensaje);
			return new ModelAndView("redirect:/registrarme");
		}

		redirectAttributes.addFlashAttribute("mensaje",mensaje);
		return new ModelAndView("redirect:/cartelera");
			
	}
	
	@RequestMapping(path = "/perfil", method = RequestMethod.GET)
	public ModelAndView verPerfil(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		
		ModelMap model = new ModelMap();
		
		if(null==obtenerUsuarioLogueado(request)) {
			redirectAttributes.addFlashAttribute("mensaje","No puede ver su perfil sin estar logueado!");
			return new ModelAndView("redirect:/home");
		}
		
		Usuario encontrado = obtenerUsuarioLogueado(request);
		
		model.put("datosUsuario", encontrado);
		
		return new ModelAndView("perfil-usuario", model);
	}
	
	
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ModelAndView guardarUsuario(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ModelAndView("redirect:/home");
    }
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/login");
	}
	
	private Usuario obtenerUsuarioLogueado(HttpServletRequest request) {
		return this.servicioUsuario.getUsuario((Long)request.getSession().getAttribute("ID"));
	}


	
	
	
}
