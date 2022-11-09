package ar.edu.unlam.tallerweb1.domain.genero;

import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

import java.util.List;

public interface ServicioGenero {

    List<Genero> listarGeneros();
    String getDescripcionGeneroById(Long id);

    List<Genero> obtenerDescrpcionesGeneroPorId(Long idGenero);


    void guardarGeneroElegidoPorUsuario(Long id, Usuario usuario);
}
