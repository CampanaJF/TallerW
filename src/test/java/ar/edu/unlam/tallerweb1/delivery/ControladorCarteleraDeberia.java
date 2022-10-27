package ar.edu.unlam.tallerweb1.delivery;

import static org.mockito.Mockito.mock;

import org.assertj.core.api.MapAssert;
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
import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class ControladorCarteleraDeberia {

	ServicioGenero servicioGenero;
	ServicioClasificacion servicioClasificacion;
	ServicioPelicula servicioPelicula;
	ServicioSession servicioSession;
	ControladorCartelera controlador;
	@Before
    public void init(){
        servicioGenero = mock(ServicioGenero.class);
        servicioPelicula = mock(ServicioPelicula.class);
        servicioClasificacion = mock(ServicioClasificacion.class);
		servicioSession=mock(ServicioSession.class);
        controlador=new ControladorCartelera(servicioGenero,servicioClasificacion,servicioPelicula,servicioSession);
	}
	
	
	
	@Test
	public void verificaQueElNombreDeLaVistaSeaCorrecto() {
		givenSeObtieneListaDeGeneros();
		givenSeObtieneListaClasificacion();
		givenSeObtieneListaPeliculas(noFilter());
		ModelAndView modelo = whenIrACartelera();
		
		thenElNombreDeLaVistaSeaCorrecta(modelo);
		thenLaVistaContengaListaGenero(modelo);
	}

	private Filtro noFilter() {
		return new Filtro(null,null,null);
	}

	private MapAssert<String, Object> thenLaVistaContengaListaGenero(ModelAndView modelo) {
		return assertThat(modelo.getModel()).containsKey("generos");
	}

	private void thenElNombreDeLaVistaSeaCorrecta(ModelAndView modelo) {
		assertThat(modelo.getViewName()).isEqualTo("cartelera");
	}

	private ModelAndView whenIrACartelera() {
		
		ModelAndView modelo=controlador.irACartelera(null, null, null, null);
		return modelo;
	}

	private void givenSeObtieneListaPeliculas(Filtro filtro) {
		List<Pelicula> listaPelicula=new ArrayList<>();
		when(this.servicioPelicula.obtenerPeliculas(filtro)).thenReturn(listaPelicula);
	}

	private void givenSeObtieneListaClasificacion() {
		List<ClasificacionPelicula> listaClasificacion=new ArrayList<>();
		when(this.servicioClasificacion.listarClasificacion()).thenReturn(listaClasificacion);
	}

	private void givenSeObtieneListaDeGeneros() {
		List<Genero> listaGeneros=new ArrayList<>();
		when(this.servicioGenero.listarGeneros()).thenReturn(listaGeneros);
	}

}
