package ar.edu.unlam.tallerweb1.domain.cine;

import java.util.List;

public interface ServicioCine {
	
	List<Cine> getCines();

	List<CinePelicula> getCines(Long pelicula);
	String getCinesUbicacion(Long idPelicula);

}
