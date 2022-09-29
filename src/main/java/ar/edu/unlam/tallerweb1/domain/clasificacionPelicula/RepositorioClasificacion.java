package ar.edu.unlam.tallerweb1.domain.clasificacionPelicula;

import java.util.List;

public interface RepositorioClasificacion {

	public List<ClasificacionPelicula> getClasificaciones();
	
	ClasificacionPelicula getDescripcionById(Long id);
	
}
