package ar.edu.unlam.tallerweb1.domain.funcion;

import java.util.Date;
import java.util.List;

public interface ServicioFuncion {


	Funcion getFuncion(Long funcionId);
	
	List<Funcion> obtenerLasFuncionesDeLosProximosTresDias(Long cine,Long pelicula);
	
	void formatFechaFuncion(Funcion funcion);
	
	Date getFechaLimiteDeFunciones();
	
	Boolean validarFechaFuncion(Funcion funcion);
	
	Boolean validarAsientosDisponibles(Funcion funcion);

}
