package ar.edu.unlam.tallerweb1.domain.funcion;

import java.util.List;

import ar.edu.unlam.tallerweb1.domain.cine.Asiento;


public interface RepositorioFuncion {

	List<Funcion> getFuncionesDeUnCine(Long sala,Long pelicula);

	Funcion getFuncion(Long funcionId);

	void update(Funcion funcion);

	List<Funcion> getCinesDeUnaPelicula(Long pelicula);
	
	Integer getCantidadAsientosOcupados(Long funcion);

	List<Asiento> getTodosLosAsientos(Long funcion);
	
}
