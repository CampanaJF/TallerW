package ar.edu.unlam.tallerweb1.domain.pelicula;

import java.util.List;

public interface ServicioPelicula {

    List<Pelicula> getPeliculas();
    List<Pelicula> buscarPeliculas(String titulo);
}
