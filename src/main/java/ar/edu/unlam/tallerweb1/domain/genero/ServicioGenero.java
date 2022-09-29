package ar.edu.unlam.tallerweb1.domain.genero;

import java.util.List;

public interface ServicioGenero {

    List<Genero> listarGeneros();
    String getDescripcionGeneroById(Long id);
}
