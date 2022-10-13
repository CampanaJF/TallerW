package ar.edu.unlam.tallerweb1.domain.pelicula;

import java.util.List;

import ar.edu.unlam.tallerweb1.domain.helper.Filtro;

public interface RepositorioPelicula {

	List<Pelicula> getPeliculasFiltro(Filtro filtro);
	
	List<Pelicula> getPeliculas();
	
    List<Pelicula> buscarPeliculas(String titulo);

	List<Pelicula> getEstrenosDelMes();
}
