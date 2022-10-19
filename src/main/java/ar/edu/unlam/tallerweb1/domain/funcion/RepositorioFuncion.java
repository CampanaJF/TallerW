package ar.edu.unlam.tallerweb1.domain.funcion;

import java.util.List;


public interface RepositorioFuncion {

	List<Funcion> getFuncionesDeUnCine(Long sala,Long pelicula);

	Funcion getFuncion(Long funcionId);

	void update(Funcion funcion);

	List<Funcion> getCinesDeUnaPelicula(Long pelicula);
	
}
