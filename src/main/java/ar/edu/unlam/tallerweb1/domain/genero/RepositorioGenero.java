package ar.edu.unlam.tallerweb1.domain.genero;

import ar.edu.unlam.tallerweb1.domain.usuario.GeneroUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RepositorioGenero {
    List<Genero> getGeneros();

	Genero getDescripcionById(Long id);

    List<Genero> obtenerDescrpcionesGeneroPorId(Long idGenero);

    void guardarGeneroElegidoPorUsuario(GeneroUsuario genero);

    List<Genero> obtenerGenerosElegidosPorUsuario(Usuario generoUsuario);

    Genero getGenero(Long asiento);
}
