package ar.edu.unlam.tallerweb1.delivery;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.clasificacionPelicula.ClasificacionPelicula;
import ar.edu.unlam.tallerweb1.domain.clasificacionPelicula.ServicioClasificacion;
import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.genero.ServicioGenero;
import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.RepositorioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPeliculaImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class ControladorCarteleraTest {

	ServicioGenero servicioGenero;
	ServicioClasificacion servicioClasificacion;
	ServicioPelicula servicioPelicula;
	
	@Before
    public void init(){
        servicioGenero = mock(ServicioGenero.class);
        servicioPelicula = mock(ServicioPelicula.class);
        servicioClasificacion = mock(ServicioClasificacion.class);
	}
	@Autowired
	private ControladorCartelera controlador;
	
	@Test
	public void verificaQueElNombreDeLaVistaSeaCorrecto() {
		Filtro filtro=new Filtro(null,null,null);
		List<Genero> listaGeneros=new ArrayList<>();
		when(this.servicioGenero.listarGeneros()).thenReturn(listaGeneros);
		List<ClasificacionPelicula> listaClasificacion=new ArrayList<>();
		when(this.servicioClasificacion.listarClasificacion()).thenReturn(listaClasificacion);
		List<Pelicula> listaPelicula=new ArrayList<>();
		when(this.servicioPelicula.obtenerPeliculas(filtro)).thenReturn(listaPelicula);
		controlador=new ControladorCartelera(servicioGenero,servicioClasificacion,servicioPelicula);
		ModelAndView modelo=controlador.irACartelera(null, null, null);
		
		assertThat(modelo.getViewName()).isEqualTo("cartelera");
		assertThat(modelo.getModel()).containsKey("generos");
	}

}
