package ar.edu.unlam.tallerweb1.domain.pelicula;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.domain.helper.Filtro;

@Service
@Transactional
public class ServicioPeliculaImpl implements ServicioPelicula {

	RepositorioPelicula repositorioPelicula;
	@Autowired
	public ServicioPeliculaImpl(RepositorioPelicula repositorioPelicula) {
		this.repositorioPelicula=repositorioPelicula;
	}
	@Override
	public List<Pelicula> obtenerPeliculas(Filtro filtro) {
	
		return repositorioPelicula.getPeliculas(filtro);
	}
	
}
