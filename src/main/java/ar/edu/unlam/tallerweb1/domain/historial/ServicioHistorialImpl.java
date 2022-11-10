package ar.edu.unlam.tallerweb1.domain.historial;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.domain.pelicula.Etiqueta;
import ar.edu.unlam.tallerweb1.domain.pelicula.EtiquetaPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
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
	public void agregarAlHistorial(Usuario usuario, Pelicula pelicula) {
		
		List<Etiqueta> etiquetasNuevas = obtenerEtiquetasNoRepetidas(usuario,obtenerEtiquetasDePelicula(pelicula));
		
		if(historialLleno(usuario))
			crearHistorial(usuario, etiquetasNuevas);
		else
			actualizarHistorial(usuario, etiquetasNuevas);
		
	}

	@Override
	public List<Etiqueta> obtenerEtiquetasNoRepetidas(Usuario usuario, List<Etiqueta> etiquetasNuevas) {
			
		etiquetasNuevas.removeAll(obtenerEtiquetasDelHistorial(usuario));
		
		return etiquetasNuevas;
	}
	
	@Override
	public List <EtiquetaPelicula> obtenerPeliculasDeLasEtiquetasDelUsuario(Usuario usuario,Integer indice) {
		
		List<Etiqueta> etiquetasDelUsuario = obtenerEtiquetasDelHistorial(usuario);

		return obtener4PeliculasDeLasEtiqueta(etiquetasDelUsuario.get(indice));
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

	@Override
	public List<Etiqueta> obtenerEtiquetasDelHistorial(Usuario usuario) {
		
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


	private void actualizarHistorial(Usuario usuario, List<Etiqueta> etiquetas) {
		this.repositorioHistorial.actualizarHistorial(usuario, etiquetas);
	}

	private void crearHistorial(Usuario usuario, List<Etiqueta> etiquetas) {
		this.repositorioHistorial.agregarAlHistorial(usuario, etiquetas);
	}
	
	private int obtenerIndice() {
		
		Random r = new Random();
		int low = 0;
		int high = 5;
		int result = r.nextInt(high-low) + low;
		
		return result;
	}



	
	
	

	
	
}
