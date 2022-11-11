package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class ControladorBase {

    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorBase(ServicioUsuario servicioUsuario){
        this.servicioUsuario=servicioUsuario;

    }

    public Usuario obtenerUsuarioLogueado(HttpServletRequest request) {
        return this.servicioUsuario.getUsuario((Long)request.getSession().getAttribute("ID"));
    }
}
