package ar.edu.unlam.tallerweb1.domain.genero;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface RepositorioGenero {
    List<Genero> getGeneros();

	Genero getDescripcionById(Long id);
}
