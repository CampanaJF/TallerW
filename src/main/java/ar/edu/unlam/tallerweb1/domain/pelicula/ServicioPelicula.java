package ar.edu.unlam.tallerweb1.domain.pelicula;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
public interface ServicioPelicula {
    List<Pelicula> getPeliculas();
	public List<Pelicula> obtenerPeliculas(Filtro filtro);
	List<Pelicula> buscarPeliculas(String titulo);
}
