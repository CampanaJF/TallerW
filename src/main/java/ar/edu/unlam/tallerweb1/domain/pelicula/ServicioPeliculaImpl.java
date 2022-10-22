package ar.edu.unlam.tallerweb1.domain.pelicula;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
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
	


	@Override
	public Pelicula buscarPeliculaPorId(Long id) {
		return this.repositorioPelicula.buscarPeliculaPorId(id);
	}

	@Override
	public List<Pelicula> obtenerPeliculasSimilaresPorGenero(Genero genero, Pelicula pelicula) {
		return this.repositorioPelicula.obtenerPeliculasSimilaresPorGenero(genero,pelicula);
	}

	@Override
	public void guardarValoracionPelicula(int estrellas, Pelicula pelicula) {
       this.repositorioPelicula.guardarValoracionPelicula(estrellas,pelicula);
	}

	@Override
	public Long obtenerPromedioValoracionesPorPelicula(Pelicula peliculaBuscada) {
		Long suma=0L;
		Long promedioValoracion=0L;
		List<Valoracion> valoraciones= this.repositorioPelicula.listarValoracionesPorPelicula(peliculaBuscada);
		int cantidadValoraciones = valoraciones.size();

		if(cantidadValoraciones!=0){
			for (Valoracion val:valoraciones) {
				suma=suma+val.getEstrellas();
			}
			promedioValoracion=suma/valoraciones.size();
		}

		return promedioValoracion;
	}

	@Override
	public List<Valoracion> obtenerValoracionesPorPelicula(Pelicula buscada) {
		return this.repositorioPelicula.listarValoracionesPorPelicula(buscada);
	}
	@Override
	public List<Pelicula> obtenerProximosEstrenos() {
		// TODO Auto-generated method stub
		return this.repositorioPelicula.getProximosEstrenos();
	}


}