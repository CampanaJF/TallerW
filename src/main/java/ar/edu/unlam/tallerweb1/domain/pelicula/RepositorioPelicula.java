package ar.edu.unlam.tallerweb1.domain.pelicula;

import java.util.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.PeliculaConEtiquetaDTO;
import ar.edu.unlam.tallerweb1.domain.usuario.GeneroUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

public interface RepositorioPelicula {

	List<EtiquetaPelicula> getPeliculasFiltro(Filtro filtro);
	
	List<Pelicula> getPeliculas();
	
    List<Pelicula> buscarPeliculas(String titulo);


	List<EtiquetaPelicula> getEstrenosDelMes();

    Pelicula buscarPeliculaPorId(Long id);

    List<Pelicula> buscarPeliculasPorActor(String protagonista);

    void guardarValoracionPelicula(int puntos, Pelicula pelicula, String comentario, Usuario usuario);

    List<Valoracion> listarValoracionesPorPelicula(Pelicula pelicula);


    List<Pelicula> buscarPeliculaPorGenero(Genero genero);



    List<Pelicula> obtenerPeliculasSimilaresPorGenero(Genero genero, Pelicula pelicula);



    List<EtiquetaPelicula> getProximosEstrenos();

	List<EtiquetaPelicula> obtenerPeliculasConEtiquetas(List<String> historialDeEtiquetas);

    List<GeneroUsuario> obtenerGenerosElegidosPorUsuario(Usuario usuario);
    List<EtiquetaPelicula> obtenerPeliculasPor(Genero genero);

}
