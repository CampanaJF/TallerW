package ar.edu.unlam.tallerweb1.domain.historial;

import java.util.List;

import ar.edu.unlam.tallerweb1.domain.pelicula.Etiqueta;
import ar.edu.unlam.tallerweb1.domain.pelicula.EtiquetaPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

public interface RepositorioHistorial {

	List<Historial> obtenerHistorial(Usuario usuario);

	void agregarAlHistorial(Usuario usuario, List<Etiqueta> etiquetasDeLaPelicula);

	void actualizarHistorial(Usuario usuario, List<Etiqueta> nuevasEtiquetas);
	
	List<EtiquetaPelicula> obtenerEtiquetasDePelicula(Pelicula pelicula);

	List<EtiquetaPelicula> obtenerPeliculasDeLaEtiqueta(Etiqueta etiqueta);

}