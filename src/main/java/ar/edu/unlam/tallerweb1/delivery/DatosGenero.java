package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

import java.util.List;

public class DatosGenero {

    private List<Long> generos;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public List<Long> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Long> generos) {
        this.generos = generos;
    }
}
