package ar.edu.unlam.tallerweb1.domain.genero;

import ar.edu.unlam.tallerweb1.delivery.DatosGenero;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.GeneroUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

import java.util.List;

public interface ServicioGenero {

    List<Genero> listarGeneros();
    String getDescripcionGeneroById(Long id);

    List<Genero> obtenerDescrpcionesGeneroPorId(Long idGenero);
    void guardarGeneroElegidoPorUsuario(List<Long> generos, Usuario usuario);
    List<GeneroUsuario> obtenerGenerosElegidosPorUsuario(Usuario usuarioExistente);

}
