package ar.edu.unlam.tallerweb1.domain.pelicula;

import java.util.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.helper.Filtro;

public interface RepositorioPelicula {

	List<Pelicula> getPeliculasFiltro(Filtro filtro);
	
	List<Pelicula> getPeliculas();
	
    List<Pelicula> buscarPeliculas(String titulo);


	List<Pelicula> getEstrenosDelMes();

    Pelicula buscarPeliculaPorId(Long id);


    List<Pelicula> obtenerPeliculasSimilaresPorGenero(String genero);

    List<Pelicula> buscarPeliculasPorActor(String protagonista);

    void guardarValoracionPelicula(int estrellas, Pelicula pelicula);

    List<Valoracion> listarValoracionesPorPelicula(Pelicula pelicula);


    List<Pelicula> buscarPeliculaPorGenero(Genero genero);
    List<Pelicula> getProximosEstrenos();
}
