package ar.edu.unlam.tallerweb1.domain.pelicula;

import java.util.List;

import javax.transaction.Transactional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import ar.edu.unlam.tallerweb1.domain.helper.Filtro;


@Service
@Transactional
public class ServicioPeliculaImpl implements ServicioPelicula {

	 private RepositorioPelicula repositorioPelicula;

	@Autowired
	public ServicioPeliculaImpl(RepositorioPelicula repositorioPelicula) {
		this.repositorioPelicula=repositorioPelicula;
	}
	@Override
	public List<Pelicula> obtenerPeliculas(Filtro filtro) {
	
		return repositorioPelicula.getPeliculasFiltro(filtro);
	}
	@Override
    public List<Pelicula> getPeliculas() {
        return this.repositorioPelicula.getPeliculas();
    }

    @Override
    public List<Pelicula> buscarPeliculas(String titulo){

       return this.repositorioPelicula.buscarPeliculas(titulo);
    }
    
	@Override
	public List<Pelicula> obtenerPeliculaEstrenos() {
		
		return this.repositorioPelicula.getEstrenosDelMes();
	}
	
}