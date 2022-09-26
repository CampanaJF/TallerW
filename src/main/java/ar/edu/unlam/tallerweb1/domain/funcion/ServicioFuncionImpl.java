package ar.edu.unlam.tallerweb1.domain.funcion;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("servicioFuncion")
@Transactional
public class ServicioFuncionImpl implements ServicioFuncion{
	
	private RepositorioFuncion repositorioFuncion;

	@Autowired
	public ServicioFuncionImpl(RepositorioFuncion repositorioFuncion) {
		this.repositorioFuncion=repositorioFuncion;
	}

	@Override
	public List<Funcion> getFuncionesDeUnCine(Long cine, Long pelicula) {
	
		return this.repositorioFuncion.getFuncionesDeUnCine(cine,pelicula);
	}

	@Override
	public Funcion getFuncion(Long funcionId) {

		return this.repositorioFuncion.getFuncion(funcionId);
	}

}
