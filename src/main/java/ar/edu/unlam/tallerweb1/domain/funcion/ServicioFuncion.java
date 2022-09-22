package ar.edu.unlam.tallerweb1.domain.funcion;

import java.util.List;

public interface ServicioFuncion {

	List <Funcion> getFuncionesDeUnCine(Long cine,Long pelicula);

}
