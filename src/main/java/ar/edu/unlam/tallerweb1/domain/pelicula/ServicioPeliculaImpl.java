package ar.edu.unlam.tallerweb1.domain.pelicula;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServicioPeliculaImpl implements ServicioPelicula {

	@Override
	public List<Pelicula> obtenerPeliculas() {
		// TODO Auto-generated method stub
		return this.obtenerPeliculas();
	}
}
