package ar.edu.unlam.tallerweb1.domain.pelicula;

import java.util.*;

import javax.transaction.Transactional;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.usuario.GeneroUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.PeliculaConEtiquetaDTO;


@Service
@Transactional
public class ServicioPeliculaImpl implements ServicioPelicula {

	private RepositorioPelicula repositorioPelicula;

	@Autowired
	public ServicioPeliculaImpl(RepositorioPelicula repositorioPelicula) {
		this.repositorioPelicula = repositorioPelicula;
	}

	@Override
	public List<PeliculaConEtiquetaDTO> obtenerPeliculas(Filtro filtro) {
		List<EtiquetaPelicula> etiquetasPeliculas = repositorioPelicula.getPeliculasFiltro(filtro);
		List<PeliculaConEtiquetaDTO> resultado = mapeoPeliculaConEtiquetaDTO(etiquetasPeliculas);

		return resultado;
	}

	private List<PeliculaConEtiquetaDTO> mapeoPeliculaConEtiquetaDTO(List<EtiquetaPelicula> etiquetasPeliculas) {
		Long idActual = 0L;
		Pelicula peliculaActual = new Pelicula();
		List<Etiqueta> etiquetas = new ArrayList<>();
		List<PeliculaConEtiquetaDTO> resultado = new ArrayList<>();
		for (EtiquetaPelicula etiquetaPelicula : etiquetasPeliculas) {
			if (!etiquetaPelicula.getPelicula().getId().equals(idActual)) {
				if (idActual != 0) {
					PeliculaConEtiquetaDTO peliculaDTO = new PeliculaConEtiquetaDTO();
					peliculaDTO.setPelicula(peliculaActual);
					agregarEtiquetas(peliculaDTO, etiquetas);
					resultado.add(peliculaDTO);
					etiquetas.clear();
				}
				peliculaActual = crearPelicula(etiquetaPelicula.getPelicula());
				idActual = peliculaActual.getId();

			}

			etiquetas.add(crearEtiqueta(etiquetaPelicula.getEtiqueta()));

		}
		if (!etiquetasPeliculas.isEmpty()) {
			PeliculaConEtiquetaDTO peliculaDTO = new PeliculaConEtiquetaDTO();
			peliculaDTO.setPelicula(peliculaActual);
			agregarEtiquetas(peliculaDTO, etiquetas);
			resultado.add(peliculaDTO);
		}
		return resultado;
	}

	private void agregarEtiquetas(PeliculaConEtiquetaDTO pelicula, List<Etiqueta> etiquetas) {
		List<Etiqueta> etiquetasActual = new ArrayList<>();
		etiquetasActual.addAll(etiquetas);
		pelicula.setEtiquetas(etiquetasActual);
	}

	private Pelicula crearPelicula(Pelicula pelicula) {
		Pelicula peliculaCopia = new Pelicula();

		peliculaCopia.setCalificacion(pelicula.getCalificacion());
		peliculaCopia.setDuracion(pelicula.getDuracion());
		peliculaCopia.setClasificacionPelicula(pelicula.getClasificacionPelicula());
		peliculaCopia.setDirector(pelicula.getDirector());
		peliculaCopia.setFechaEstreno(pelicula.getFechaEstreno());
		peliculaCopia.setGenero(pelicula.getGenero());
		peliculaCopia.setId(pelicula.getId());
		peliculaCopia.setPoster(pelicula.getPoster());
		peliculaCopia.setProtagonista(pelicula.getProtagonista());
		peliculaCopia.setSinopsis(pelicula.getSinopsis());
		peliculaCopia.setTitulo(pelicula.getTitulo());

		return peliculaCopia;
	}

	private Etiqueta crearEtiqueta(Etiqueta etiqueta) {
		Etiqueta etiquetaCopia = new Etiqueta();
		etiquetaCopia.setDescripcion(etiqueta.getDescripcion());
		etiquetaCopia.setId(etiqueta.getId());


		return etiquetaCopia;
	}


	@Override
	public List<Pelicula> getPeliculas() {
		return this.repositorioPelicula.getPeliculas();
	}

	@Override
	public List<Pelicula> buscarPeliculas(String titulo) {

		return this.repositorioPelicula.buscarPeliculas(titulo);
	}


	@Override
	public List<PeliculaConEtiquetaDTO> obtenerPeliculaEstrenos() {
		List<EtiquetaPelicula> etiquetasPeliculas = this.repositorioPelicula.getEstrenosDelMes();
		List<PeliculaConEtiquetaDTO> resultado = mapeoPeliculaConEtiquetaDTO(etiquetasPeliculas);
		List<PeliculaConEtiquetaDTO> auxiliar = new ArrayList<>();
		for (int i = 0; i < resultado.size(); i++) {
			if (i < 4) {
				auxiliar.add(resultado.get(i));
			}
		}
		return auxiliar;


	}


	@Override
	public Pelicula buscarPeliculaPorId(Long id) {
		return this.repositorioPelicula.buscarPeliculaPorId(id);
	}

	@Override
	public List<Pelicula> obtenerPeliculasSimilaresPorGenero(Genero genero, Pelicula pelicula) {
		return this.repositorioPelicula.obtenerPeliculasSimilaresPorGenero(genero, pelicula);
	}

	@Override
	public void guardarValoracionPelicula(int puntos, Pelicula pelicula, String comentario, Usuario usuario) {
		Valoracion nuevaValoracion = new Valoracion();
		nuevaValoracion.setPuntos(puntos);
		nuevaValoracion.setPelicula(pelicula);
		nuevaValoracion.setComentario(comentario);
		nuevaValoracion.setUsuario(usuario);

		this.repositorioPelicula.guardarValoracionPelicula(nuevaValoracion);
	}

	@Override
	public List<Valoracion> obtenerCalificacionesDeUnaPelicula(Pelicula pelicula) {
		return this.repositorioPelicula.listarValoracionesPorPelicula(pelicula);
	}

	@Override
	public double obtenerPromedioValoracionesPorPelicula(Pelicula peliculaBuscada) {
		Long suma = 0L;
		double promedioValoracion = 0L;
		List<Valoracion> valoraciones = obtenerCalificacionesDeUnaPelicula(peliculaBuscada);
		int cantidadValoraciones = valoraciones.size();

		if (cantidadValoraciones != 0) {
			for (Valoracion val : valoraciones) {
				suma = suma + val.getPuntos();
			}
			promedioValoracion = Math.round(((double) suma / valoraciones.size()) * 10.0) / 10.0;
		}

		return promedioValoracion;
	}


	@Override
	public List<PeliculaConEtiquetaDTO> obtenerProximosEstrenos() {
		List<EtiquetaPelicula> etiquetasPeliculas = this.repositorioPelicula.getProximosEstrenos();
		List<PeliculaConEtiquetaDTO> resultado = mapeoPeliculaConEtiquetaDTO(etiquetasPeliculas);
		List<PeliculaConEtiquetaDTO> auxiliar = new ArrayList<>();
		for (int i = 0; i < resultado.size(); i++) {
			if (i < 4) {
				auxiliar.add(resultado.get(i));
			}
		}
		return auxiliar;

	}

	@Override
	public List<GeneroUsuario> obtenerGenerosElegidosPorUsuario(Usuario usuario) {
		return repositorioPelicula.obtenerGenerosElegidosPorUsuario(usuario);
	}

	@Override
	public List<EtiquetaPelicula> obtenerPeliculasPorGeneroElegido(Usuario usuario) {

		List<GeneroUsuario> generosElegidos = obtenerGenerosElegidosPorUsuario(usuario);
		List<EtiquetaPelicula> peliculas = new ArrayList<>();

		for (GeneroUsuario genero : generosElegidos) {
			peliculas.addAll(this.repositorioPelicula.obtenerPeliculasPor(genero.getGenero()));
		}
		return peliculas;
	}
	@Override
	public List<PeliculaConEtiquetaDTO> obtenerPeliculasEnBaseAGeneroElegido(Usuario usuario) {
		List<EtiquetaPelicula> etiquetasPeliculas = obtenerPeliculasPorGeneroElegido(usuario);
		Collections.shuffle(etiquetasPeliculas);
		return mapeoPeliculaConEtiquetaDTO(etiquetasPeliculas.subList(0,3));
	}


}