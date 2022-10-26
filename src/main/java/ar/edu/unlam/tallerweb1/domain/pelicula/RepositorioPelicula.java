package ar.edu.unlam.tallerweb1.domain.pelicula;

import java.util.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

public interface RepositorioPelicula {

	List<Pelicula> getPeliculasFiltro(Filtro filtro);
	
	List<Pelicula> getPeliculas();
	
    List<Pelicula> buscarPeliculas(String titulo);


	List<Pelicula> getEstrenosDelMes();

    Pelicula buscarPeliculaPorId(Long id);

    List<Pelicula> buscarPeliculasPorActor(String protagonista);

    void guardarValoracionPelicula(int puntos, Pelicula pelicula, String comentario, Usuario usuario);

    List<Valoracion> listarValoracionesPorPelicula(Pelicula pelicula);


    List<Pelicula> buscarPeliculaPorGenero(Genero genero);



    List<Pelicula> obtenerPeliculasSimilaresPorGenero(Genero genero, Pelicula pelicula);



    List<Pelicula> getProximosEstrenos();

}
