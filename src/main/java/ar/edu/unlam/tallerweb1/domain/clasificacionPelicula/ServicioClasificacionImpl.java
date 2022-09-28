package ar.edu.unlam.tallerweb1.domain.clasificacionPelicula;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServicioClasificacionImpl implements ServicioClasificacion {

	@Autowired
	private RepositorioClasificacion repositorioCalificacion;
	
	
	
	@Override
	public List<ClasificacionPelicula> listarClasificacion() {
		// TODO Auto-generated method stub
		return repositorioCalificacion.getClasificaciones();
	}

}
