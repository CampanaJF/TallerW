package ar.edu.unlam.tallerweb1.domain.funcion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.domain.cine.Asiento;
import ar.edu.unlam.tallerweb1.exceptions.FuncionSinAsientosDisponiblesException;
import ar.edu.unlam.tallerweb1.exceptions.NoSeEncontraronFuncionesException;

@Service("servicioFuncion")
@Transactional
public class ServicioFuncionImpl implements ServicioFuncion{
	
	private RepositorioFuncion repositorioFuncion;

	@Autowired
	public ServicioFuncionImpl(RepositorioFuncion repositorioFuncion) {
		this.repositorioFuncion=repositorioFuncion;
	}

	@Override
	public Funcion getFuncion(Long funcionId) {

		return this.repositorioFuncion.getFuncion(funcionId);
	}

	@Override
	public List<Funcion> obtenerLasFuncionesDeLosProximosTresDias(Long cine, Long pelicula) throws NoSeEncontraronFuncionesException{
		
		List<Funcion> funciones = this.repositorioFuncion.getFuncionesDeUnCine(cine,pelicula);
		
		List<Funcion> siguientesFunciones = new ArrayList<>();
		
		for (Funcion funcion : funciones) {
			
			if(validarFechaFuncion(funcion)) {
				formatFechaFuncion(funcion);
				siguientesFunciones.add(funcion);
			}
		}
		
		validarFuncionesEncontradas(siguientesFunciones);
		
		return siguientesFunciones;
	}

	@Override
	public void validarFuncionesEncontradas(List<Funcion> siguientesFunciones) {
		if(siguientesFunciones.isEmpty())
			throw new NoSeEncontraronFuncionesException();
	}
	
	@Override
	public List<Asiento> obtenerAsientosDeLaFuncion(Long funcion) {
		
		return this.repositorioFuncion.getTodosLosAsientos(funcion);

	}

	@Override
	public void formatFechaFuncion(Funcion funcion) {	
		
		if(validarFormatoExistente(funcion)) {
			
		SimpleDateFormat formato = new SimpleDateFormat ("dd-MM-yyyy");
		
		funcion.setFechaStr(formato.format(funcion.getFecha()));
		
		this.repositorioFuncion.update(funcion);
		
		}	
		
	}

	@Override
	public Date getFechaLimiteDeFunciones() {
	
		Date dt = new Date();
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 3);
		dt = c.getTime();
		
		return dt;
	}

	@Override
	public Boolean validarFechaFuncion(Funcion funcion) {
		
		return fechaDentroDeLosProximosTresDias(funcion);

	}

	private boolean fechaDentroDeLosProximosTresDias(Funcion funcion) {
		Date cuatroDiasDespues = getFechaLimiteDeFunciones();
		Date hoy = new Date();
		return funcion.getFecha().after(hoy)&&funcion.getFecha().before(cuatroDiasDespues);
	}

	@Override
	public Boolean validarAsientosDisponibles(Funcion funcion) throws FuncionSinAsientosDisponiblesException {
		Funcion funcionEncontrada = getFuncion(funcion.getId());
		if(funcionEncontrada.getSala().getAsientosTotales()-asientosOcupados(funcionEncontrada)>0)
			return true;
		
		return false;
	}

	private Integer asientosOcupados(Funcion funcion) {
		return this.repositorioFuncion.getCantidadAsientosOcupados(funcion.getId());
	}

	@Override
	public Boolean validarFormatoExistente(Funcion funcion) {
		Funcion funcionEncontrada = getFuncion(funcion.getId());
		if(funcionEncontrada.getFechaStr()==null) 
			return true;
		
		return false;
	}

	

}
