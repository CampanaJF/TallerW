package ar.edu.unlam.tallerweb1.domain.clasificacionPelicula;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;

@Service
@Transactional
public class ServicioClasificacionImpl implements ServicioClasificacion {

	@Autowired
	private RepositorioClasificacion repositorioClasificacion;
	
	
	
	@Override
	public List<ClasificacionPelicula> listarClasificacion() {
		// TODO Auto-generated method stub
		return repositorioClasificacion.getClasificaciones();
	}



	@Override
	public String getDescripcionClasificacionById(Long id) {
		ClasificacionPelicula clasificacion=repositorioClasificacion.getDescripcionById(id);
		return clasificacion.getDescripcion();
	}

}
