package ar.edu.unlam.tallerweb1.domain.clasificacionPelicula;

import java.util.List;

public interface ServicioClasificacion {

public List<ClasificacionPelicula> listarClasificacion();
		
public String getDescripcionClasificacionById(Long id);

}
