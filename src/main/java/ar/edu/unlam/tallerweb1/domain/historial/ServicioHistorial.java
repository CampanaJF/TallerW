package ar.edu.unlam.tallerweb1.domain.historial;

import java.util.List;

import ar.edu.unlam.tallerweb1.domain.pelicula.Etiqueta;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.PeliculaConEtiquetaDTO;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

public interface ServicioHistorial {
	
	void guardarEnElHistorial(Usuario usuario, Pelicula pelicula);

	List<Etiqueta> obtenerEtiquetasNoRepetidas(Usuario usuario,List<Etiqueta> etiquetasNuevas);
	
	List<Etiqueta> obtenerEtiquetasDelUsuario(Usuario usuario);
	
	List<Etiqueta> obtenerEtiquetasDePelicula(Pelicula pelicula);
	
	void agregarAlHistorial(Usuario usuario, Pelicula pelicula);
	
	Boolean historialLleno(Usuario usuario);
	
	List<PeliculaConEtiquetaDTO> obtenerPeliculasDeLasEtiquetasDelUsuario(Usuario usuario,Integer indice);


}
