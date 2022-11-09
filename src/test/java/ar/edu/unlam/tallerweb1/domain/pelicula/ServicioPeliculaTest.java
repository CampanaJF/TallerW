package ar.edu.unlam.tallerweb1.domain.pelicula;
import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.RepositorioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPeliculaImpl;
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.PeliculaConEtiquetaDTO;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioPeliculaImpl;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Java6Assertions.assertThat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
public class ServicioPeliculaTest {


    public static final String PELICULA_TITULO ="Back to the future";

	@Inject
	private ServicioPelicula servicioPelicula;
	@Autowired
	private RepositorioPelicula repositorioPelicula;


    @Before
    public void init(){
        repositorioPelicula = mock(RepositorioPelicula.class);
        servicioPelicula = new ServicioPeliculaImpl(repositorioPelicula);
    }

//Aca lo unico que me interesa es que el servicio devuelva peliculas
    @Test
    public void listarPeliculas(){
    	
    	//Preparacion
    	Filtro filtro=new Filtro(1L,1L,"Director");
    	RepositorioPelicula repoPelicula=mock(RepositorioPelicula.class);
    	List<EtiquetaPelicula>peliculas=new ArrayList<>();
    	EtiquetaPelicula etiquetaPelicula=new EtiquetaPelicula();
    	Etiqueta etiqueta=new Etiqueta();
    	Pelicula pelicula=new Pelicula();
    	pelicula.setId(1L);
    	etiquetaPelicula.setEtiqueta(etiqueta);
    	etiquetaPelicula.setPelicula(pelicula);
    	peliculas.add(etiquetaPelicula);
    	when(repoPelicula.getPeliculasFiltro(filtro)).thenReturn(peliculas);
    	servicioPelicula=new ServicioPeliculaImpl(repoPelicula);
    	
    	//Ejecucion
    	List<PeliculaConEtiquetaDTO>listadoPeliculas=servicioPelicula.obtenerPeliculas(filtro);
    	assertEquals(1, listadoPeliculas.size());
    	assertNotNull(listadoPeliculas);
    }

 @Test
    public void queSePuedaBuscarUnaPeliculaPorSuTitulo(){

        givenQueExistenPeliculas();
        List<Pelicula> peliculas=whenConsultoPorLaPelicula();
        thenSeEncontroLaPelicula(peliculas);
    }
    @Test
    public void alRealizarUnaBusquedaNoEncuentroLaPelicula(){
        givenNoExisteLaPelicula();
        List<Pelicula> peliculaList= whenBuscoLaPelicula ();
        thenNoEncuentroPeliculas(peliculaList);
    }

    private void givenQueExistenPeliculas(){
        List<Pelicula> peliculaList = new LinkedList<>();

        Pelicula pelicula1 = new Pelicula();
        pelicula1.setTitulo(PELICULA_TITULO);

        peliculaList.add(pelicula1);

        when(this.repositorioPelicula.buscarPeliculas(PELICULA_TITULO)).thenReturn(peliculaList);
    }

    private List<Pelicula> whenConsultoPorLaPelicula(){
        return this.servicioPelicula.buscarPeliculas(PELICULA_TITULO);
    }
    private  void thenSeEncontroLaPelicula(List<Pelicula> peliculaList){
        assertThat(peliculaList).isNotNull();
        verify(repositorioPelicula,times(1)).buscarPeliculas(PELICULA_TITULO);
    }
    private void givenNoExisteLaPelicula(){
        when(this.repositorioPelicula.buscarPeliculas(PELICULA_TITULO)).thenReturn(null);
    }
    private List<Pelicula> whenBuscoLaPelicula (){
        return servicioPelicula.buscarPeliculas(PELICULA_TITULO);
    }
    private void thenNoEncuentroPeliculas(List<Pelicula>peliculas){
       assertThat(peliculas).isNull();
    }
    
    
    @Test
    public void consultarQueDevuelvaLosEstrenosDelMes() {
    	
    	givenPeliculasEstrenosDelMes();
    	List<PeliculaConEtiquetaDTO>peliculasEstrenos=whenConsultoEstrenoDelMes();
    	thenObtengoEstrenoDelMes(peliculasEstrenos);
    }
    
    @Test
    public void consultarLosProximosEstrenos(){
    	givenProximosEstrenos();
    	List<PeliculaConEtiquetaDTO>proximosEstrenos=whenConsultoProximosEstrenos();
    	thenObtengoProximosEstrenos(proximosEstrenos);
    	
    }
	private void thenObtengoProximosEstrenos(List<PeliculaConEtiquetaDTO> proximosEstrenos) {
		assertEquals(2,proximosEstrenos.size());
		
	}

	private List<PeliculaConEtiquetaDTO> whenConsultoProximosEstrenos() {
		// TODO Auto-generated method stub
		return servicioPelicula.obtenerProximosEstrenos();
	}

	private void thenObtengoEstrenoDelMes(List<PeliculaConEtiquetaDTO>peliculasEstrenos) {
		assertEquals(2,peliculasEstrenos.size());
		
	}

	private List<PeliculaConEtiquetaDTO> whenConsultoEstrenoDelMes() {
		return servicioPelicula.obtenerPeliculaEstrenos();
		
	}

	private void givenPeliculasEstrenosDelMes() {
		List<EtiquetaPelicula> peliculasEstrenos = obtenerPeliculasEstrenos();
		when(repositorioPelicula.getEstrenosDelMes()).thenReturn(peliculasEstrenos);
		
	}

	private List<EtiquetaPelicula> obtenerPeliculasEstrenos() {
		EtiquetaPelicula etiqueta1=new EtiquetaPelicula();
		EtiquetaPelicula etiqueta2=new EtiquetaPelicula();
		Etiqueta etiqueta=new Etiqueta();
		
		Pelicula pelicula =new Pelicula();
		Pelicula pelicula2=new Pelicula();
		pelicula.setId(1L);
		pelicula2.setId(2L);
		
		etiqueta1.setEtiqueta(etiqueta);
		etiqueta2.setEtiqueta(etiqueta);
		
		etiqueta1.setPelicula(pelicula);
		etiqueta2.setPelicula(pelicula2);
		
		List<EtiquetaPelicula>peliculasEstrenos=new ArrayList<>();
		peliculasEstrenos.add(etiqueta1);
		peliculasEstrenos.add(etiqueta2);
		return peliculasEstrenos;
	}
	
	private void givenProximosEstrenos() {
		
	
		List<EtiquetaPelicula>proximasEstrenos=obtenerPeliculasEstrenos();
		
		when(repositorioPelicula.getProximosEstrenos()).thenReturn(proximasEstrenos);
		
	}
    
  /*  @Test
    public void queSePuedaObtenerUnPromedioDeValoracionesDeUnaPelicula(){
        Pelicula pelicula = new Pelicula();
        givenExistePelicula(pelicula);
        Valoracion valoracion1 = new Valoracion(4,pelicula,"Buena",null);
        Valoracion valoracion2 = new Valoracion(5,pelicula,"Excelente",null);
        Valoracion valoracion3 = new Valoracion(4,pelicula,"Me gusto",null);
        givenExistenValoracionesDeUnaPelicula(valoracion1);
        givenExistenValoracionesDeUnaPelicula(valoracion2);
        givenExistenValoracionesDeUnaPelicula(valoracion3);
        Long promedio = whenObtengoPromedioDeValoracionesPorPelicula(pelicula);
        thenObtengoPromedioDeValoracionesDeUnaPelicula(promedio,4L);
    }

    private void thenObtengoPromedioDeValoracionesDeUnaPelicula(Long promedio, Long promedioEsperado) {
        assertThat(promedio).isEqualTo(promedioEsperado);
    }

    private double whenObtengoPromedioDeValoracionesPorPelicula(Pelicula pelicula){
        return this.servicioPelicula.obtenerPromedioValoracionesPorPelicula(pelicula);
    }
    private void  givenExistenValoracionesDeUnaPelicula(Valoracion valoracion){
        List<Valoracion> valoracionList = new ArrayList<>();
        valoracionList.add(valoracion);
        when(this.repositorioPelicula.listarValoracionesPorPelicula(valoracion.getPelicula())).thenReturn(valoracionList);
    }
    private void givenExistePelicula(Pelicula pelicula){
        List<Pelicula> peliculaList = new ArrayList<>();
        peliculaList.add(pelicula);
        when(this.repositorioPelicula.getPeliculas()).thenReturn(peliculaList);
    }
*/
}
