package ar.edu.unlam.tallerweb1.domain.pelicula;

import java.util.List;

import javax.transaction.Transactional;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
	public void guardarValoracionPelicula(int puntos, Pelicula pelicula, String comentario, Usuario usuario) {
		this.repositorioPelicula.guardarValoracionPelicula(puntos,pelicula,comentario,usuario);
	}

	@Override
	public List<Valoracion> obtenerCalificacionesDeUnaPelicula(Pelicula pelicula) {
		return this.repositorioPelicula.listarValoracionesPorPelicula(pelicula);
	}
	@Override
	public double obtenerPromedioValoracionesPorPelicula(Pelicula peliculaBuscada) {
		Long suma=0L;
		double promedioValoracion=0L;
		List<Valoracion> valoraciones= obtenerCalificacionesDeUnaPelicula(peliculaBuscada);
		int cantidadValoraciones = valoraciones.size();

		if(cantidadValoraciones!=0){
			for (Valoracion val:valoraciones) {
				suma=suma+val.getPuntos();
			}
			promedioValoracion=(double)suma/valoraciones.size();
		}

		return promedioValoracion;
	}


	@Override
	public List<Pelicula> obtenerProximosEstrenos() {
		// TODO Auto-generated method stub
		return this.repositorioPelicula.getProximosEstrenos();
	}

}