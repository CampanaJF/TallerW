package ar.edu.unlam.tallerweb1.domain.funcion;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ar.edu.unlam.tallerweb1.domain.cine.Asiento;

public interface ServicioFuncion {


	Funcion getFuncion(Long funcionId);
	
	List<Funcion> obtenerLasFuncionesDeLosProximosTresDias(Long cine,Long pelicula);
	
	HashMap<Integer,List<Asiento>> obtenerAsientosDeLaFuncion(Long funcion);
	
	void formatFechaFuncion(Funcion funcion);
	
	Date getFechaLimiteDeFunciones();
	
	Boolean validarFechaFuncion(Funcion funcion);
	
	Boolean validarAsientosDisponibles(Funcion funcion);
	
	

}
