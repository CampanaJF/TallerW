package ar.edu.unlam.tallerweb1.domain.pelicula;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

import java.util.List;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
public interface ServicioPelicula {
    List<Pelicula> getPeliculas();
	public List<Pelicula> obtenerPeliculas(Filtro filtro);
	List<Pelicula> buscarPeliculas(String titulo);

	List<Pelicula> obtenerPeliculaEstrenos();

	Pelicula buscarPeliculaPorId(Long id);

	List<Pelicula> obtenerPeliculasSimilaresPorGenero(Genero genero,Pelicula pelicula);

    void guardarValoracionPelicula(int estrellas, Pelicula pelicula);

    Long obtenerPromedioValoracionesPorPelicula(Pelicula peliculaBuscada);

	List<Valoracion> obtenerValoracionesPorPelicula(Pelicula buscada);
	List<Pelicula> obtenerProximosEstrenos();

}
