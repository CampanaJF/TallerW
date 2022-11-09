package ar.edu.unlam.tallerweb1.domain.pelicula;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

import java.util.List;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.PeliculaConEtiquetaDTO;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

public interface ServicioPelicula {
    List<Pelicula> getPeliculas();
	public List<PeliculaConEtiquetaDTO> obtenerPeliculas(Filtro filtro);
	List<Pelicula> buscarPeliculas(String titulo);

	List<PeliculaConEtiquetaDTO> obtenerPeliculaEstrenos();

	Pelicula buscarPeliculaPorId(Long id);

	List<Pelicula> obtenerPeliculasSimilaresPorGenero(Genero genero,Pelicula pelicula);

    void guardarValoracionPelicula(int puntos, Pelicula pelicula, String comentario, Usuario usuario);

    Long obtenerPromedioValoracionesPorPelicula(Pelicula peliculaBuscada);


	List<Valoracion> obtenerCalificacionesDeUnaPelicula(Pelicula pelicula);

	//List<Valoracion> obtenerValoracionesPorPelicula(Pelicula buscada);
	List<PeliculaConEtiquetaDTO> obtenerProximosEstrenos();
	


}
