package ar.edu.unlam.tallerweb1.domain.historial;

import java.util.ArrayList;
import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.domain.pelicula.Etiqueta;
import ar.edu.unlam.tallerweb1.domain.pelicula.EtiquetaPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.PeliculaConEtiquetaDTO;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;



@Service("servicioHistorial")
@Transactional
public class ServicioHistorialImpl implements ServicioHistorial {
	
	private RepositorioHistorial repositorioHistorial;

	@Autowired
	public ServicioHistorialImpl(RepositorioHistorial repositorioHistorial) {
		this.repositorioHistorial = repositorioHistorial;
	}
	
	@Override
	public void guardarEnElHistorial(Usuario usuario, Pelicula pelicula) {
		
		List<Etiqueta> etiquetasNuevas = obtenerEtiquetasDePelicula(pelicula);
		
		List<Historial> historialUsuario = obtenerHistorialUsuario(usuario);
		
		if(historialMaximaCapacidad(historialUsuario)) 
		guardarHistorialNuevo(usuario,etiquetasNuevas);
		else 
		actualizarHistorial(usuario,etiquetasNuevas,historialUsuario);
		
	}

	
	
	private void actualizarHistorial(Usuario usuario, List<Etiqueta> etiquetasNuevas,List<Historial> historialUsuario) {
			
		List<Historial> repetidos = new ArrayList<>();
		
		for (int i = 0; i < etiquetasNuevas.size(); i++) {
			for (int j = 0; j < historialUsuario.size(); j++) {
				
				if(historialUsuario.get(j).getEtiqueta().equals(etiquetasNuevas.get(j)))
					repetidos.add(historialUsuario.get(j));
			}
			
		}
		
	}

	private void guardarHistorialNuevo(Usuario usuario,List<Etiqueta> etiquetas) {
		
		for (Etiqueta etiqueta : etiquetas) {
			Historial nuevo = new Historial();
			
			nuevo.setEtiqueta(etiqueta);
			nuevo.setUsuario(usuario);
			
			guardarEnElHistorial(nuevo);
		}
	}


	@Override
	public void agregarAlHistorial(Usuario usuario, Pelicula pelicula) {
		
		List<Etiqueta> etiquetasNuevas = obtenerEtiquetasNoRepetidas(usuario,obtenerEtiquetasDePelicula(pelicula));
		
		if(historialLleno(usuario))
			crearHistorial(usuario, etiquetasNuevas);
		else
			actualizarHistorialUsuario(usuario, etiquetasNuevas);
		
	}

	@Override
	public List<Etiqueta> obtenerEtiquetasNoRepetidas(Usuario usuario, List<Etiqueta> etiquetasNuevas) {
			
		etiquetasNuevas.removeAll(obtenerEtiquetasDelUsuario(usuario));
		
		return etiquetasNuevas;
	}
	
	@Override
	public List<Etiqueta> obtenerEtiquetasDelUsuario(Usuario usuario) {
		
		List<Historial> historialDelUsuario = obtenerHistorialUsuario(usuario);
		
		List<Etiqueta> etiquetas = new ArrayList<>();
		
		for (Historial historial : historialDelUsuario) {
			etiquetas.add(historial.getEtiqueta());	
		}
	
		return etiquetas;	
	}
	
	@Override
	public List<Etiqueta> obtenerEtiquetasDePelicula(Pelicula pelicula) {
		
		List<EtiquetaPelicula> etiquetasDeLaPelicula = obtenerEtiquetasPelicula(pelicula);
		
		List<Etiqueta> etiquetas = new ArrayList<>();
		
		for (EtiquetaPelicula etiquetaPelicula : etiquetasDeLaPelicula) {
			etiquetas.add(etiquetaPelicula.getEtiqueta());
		}
		
		return etiquetas;
	}
	
	@Override
	public List <PeliculaConEtiquetaDTO> obtenerPeliculasDeLasEtiquetasDelUsuario(Usuario usuario,Integer indice) {
		
		List<Etiqueta> etiquetasDelUsuario = obtenerEtiquetasDelUsuario(usuario);

	
		return mapeoHistorial( obtener4PeliculasDeLasEtiqueta(etiquetasDelUsuario.get(indice)),
																etiquetasDelUsuario.get(indice).getDescripcion());

	}
	


	private List<EtiquetaPelicula> obtener4PeliculasDeLasEtiqueta(Etiqueta etiqueta) {
		
		List<EtiquetaPelicula> encontradas = obtenerPeliculasDeLaEtiqueta(etiqueta);
		List<EtiquetaPelicula> resultado = new ArrayList<>();
		
		for (EtiquetaPelicula etiquetaPelicula : encontradas) {
			if(resultado.size()<4)
				resultado.add(etiquetaPelicula);
		}
		
		return resultado;
	}
	
	public List<PeliculaConEtiquetaDTO> mapeoHistorial(List<EtiquetaPelicula> etiquetasPeliculas,String descripcion){
		
		List<PeliculaConEtiquetaDTO> resultado = new ArrayList<>();
		Long idPelicula = 0L;
		
		for (EtiquetaPelicula etiquetaPelicula : etiquetasPeliculas) {
			if(idPelicula!=etiquetaPelicula.getPelicula().getId()) {
			PeliculaConEtiquetaDTO peliculaDTO=new PeliculaConEtiquetaDTO();
			

			peliculaDTO.setDescripcionEtiqueta(descripcion);
			peliculaDTO.setPelicula(etiquetaPelicula.getPelicula());
			peliculaDTO.setEtiquetas(obtenerEtiquetasDePelicula(etiquetaPelicula.getPelicula()));
			
			idPelicula = etiquetaPelicula.getPelicula().getId();
			resultado.add(peliculaDTO);	
			}
		
		}
		
		return resultado;
	}
	
	
	@Override
	public Boolean historialLleno(Usuario usuario) {
		
		if(obtenerHistorialUsuario(usuario).size()<6)
			return true;
		
		return false;
	}
	
	private List<EtiquetaPelicula> obtenerPeliculasDeLaEtiqueta(Etiqueta etiqueta) {
		return this.repositorioHistorial.obtenerPeliculasDeLaEtiqueta(etiqueta);
	}

	private List<EtiquetaPelicula> obtenerEtiquetasPelicula(Pelicula pelicula) {
		return this.repositorioHistorial.obtenerEtiquetasDePelicula(pelicula);
	}

	private List<Historial> obtenerHistorialUsuario(Usuario usuario) {
		return this.repositorioHistorial.obtenerHistorial(usuario);
	}


	private void actualizarHistorialUsuario(Usuario usuario, List<Etiqueta> etiquetas) {
		this.repositorioHistorial.actualizarHistorial(usuario, etiquetas);
	}

	private void crearHistorial(Usuario usuario, List<Etiqueta> etiquetas) {
		this.repositorioHistorial.agregarAlHistorial(usuario, etiquetas);
	}
	
	private void guardarEnElHistorial(Historial nuevo) {
		this.repositorioHistorial.guardarEnElHistorial(nuevo);
	}
	
	private boolean historialMaximaCapacidad(List<Historial> historialUsuario) {
		return historialUsuario.size()<=3;
	}


	

}
