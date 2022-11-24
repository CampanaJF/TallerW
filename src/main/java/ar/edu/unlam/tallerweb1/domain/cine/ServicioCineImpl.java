package ar.edu.unlam.tallerweb1.domain.cine;

import java.util.List;

import javax.transaction.Transactional;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service("servicioCine")
@Transactional
public class ServicioCineImpl implements ServicioCine{
	
	private RepositorioCine repositorioCine;
	
	@Autowired
	public ServicioCineImpl(RepositorioCine repositorioCine) {
		this.repositorioCine = repositorioCine;
	}

	@Override
	public List<Cine> getCines() {
	
		return this.repositorioCine.getCines();
	}
	
	@Override
	public List<CinePelicula> getCines(Long pelicula){
		
		return this.repositorioCine.getCines(pelicula);
	}

	@Override
	public String getCinesUbicacion(Long idPelicula) {
		// TODO Auto-generated method stub
		List<CinePelicula> cines=this.repositorioCine.getCines(idPelicula);
		return new Gson().toJson(cines);

	}

}
