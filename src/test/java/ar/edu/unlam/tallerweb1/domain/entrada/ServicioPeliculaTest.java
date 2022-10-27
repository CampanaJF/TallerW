package ar.edu.unlam.tallerweb1.domain.entrada;
import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.RepositorioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPeliculaImpl;

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
    	Filtro filtro=new Filtro(1L,1L,"Genero");
    	RepositorioPelicula repoPelicula=mock(RepositorioPelicula.class);
    	List<Pelicula>peliculas=new ArrayList<>();
    	Pelicula pelicula=new Pelicula();
    	peliculas.add(pelicula);
    	when(repoPelicula.getPeliculasFiltro(filtro)).thenReturn(peliculas);
    	servicioPelicula=new ServicioPeliculaImpl(repoPelicula);
    	
    	//Ejecucion
    	List<Pelicula>listadoPeliculas=servicioPelicula.obtenerPeliculas(filtro);
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


    @Test
    public void meDebeDevolverUnaListaDePeliculasSimilaresPorGenero(){
        //dado que existe pelicula

        //dado que existe genero
        givenExisteGeneroDePelicula("Aventuras");
        givenQueExistenPeliculasConGenero("Aventuras");
        //when listo peliculas similares
        List<Pelicula> similares = whenListoPeliculasSimilaresPorGenero("Aventuras");
        //then obtengo peliculas similares
        thenObtengoListaDePeliculasSimilaresPorGenero(similares,"Aventuras");
    }
    @Test
    public void meDebeDevolverCeroCuandoConsultoUnaListaDePeliculasSimilaresPorGenero(){
        givenExisteGeneroDePelicula("Aventuras");
        givenQueExistenPeliculasConGenero("Accion");
        //when listo peliculas similares
        List<Pelicula> similares = whenListoPeliculasSimilaresPorGenero("Aventuras");
        //then obtengo peliculas similares
        thenObtengoCeroEnListaDePeliculasSimilaresPorGenero(similares,0);
    }

    private void thenObtengoCeroEnListaDePeliculasSimilaresPorGenero(List<Pelicula> similares, int cantidad) {
        assertThat(similares).hasSize(cantidad);
    }

    private void thenObtengoListaDePeliculasSimilaresPorGenero(List<Pelicula> similares, String genero) {
        assertThat(similares).isNotNull();
        verify(repositorioPelicula,times(1)).obtenerPeliculasSimilaresPorGenero(genero);
    }

    private List<Pelicula> whenListoPeliculasSimilaresPorGenero(String descripcion) {
        return this.servicioPelicula.obtenerPeliculasSimilaresPorGenero(descripcion);
    }
    private void givenQueExistenPeliculasConGenero(String descripcion){
        List<Pelicula> peliculaList = new LinkedList<>();
         Pelicula pelicula = new Pelicula();
         Genero genero = new Genero();
         genero.setDescripcion(descripcion);
         pelicula.setGenero(genero);
         peliculaList.add(pelicula);
         when(this.repositorioPelicula.getPeliculas()).thenReturn(peliculaList);
    }
    private void givenExisteGeneroDePelicula(String descripcion) {
        List<Pelicula> peliculaList = new LinkedList<>();

        Pelicula p = new Pelicula();
        Genero genero = new Genero();
        genero.setDescripcion(descripcion);
        p.setGenero(genero);
        peliculaList.add(p);
         when(this.repositorioPelicula.buscarPeliculaPorGenero(genero)).thenReturn(peliculaList);
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
    	List<Pelicula>peliculasEstrenos=whenConsultoEstrenoDelMes();
    	thenObtengoEstrenoDelMes(peliculasEstrenos);
    }
    
    @Test
    public void consultarLosProximosEstrenos(){
    	givenProximosEstrenos();
    	List<Pelicula>proximosEstrenos=whenConsultoProximosEstrenos();
    	thenObtengoProximosEstrenos(proximosEstrenos);
    	
    }
	private void thenObtengoProximosEstrenos(List<Pelicula> proximosEstrenos) {
		assertEquals(2,proximosEstrenos.size());
		
	}

	private List<Pelicula> whenConsultoProximosEstrenos() {
		// TODO Auto-generated method stub
		return servicioPelicula.obtenerProximosEstrenos();
	}

	private void thenObtengoEstrenoDelMes(List<Pelicula>peliculasEstrenos) {
		assertEquals(2,peliculasEstrenos.size());
		
	}

	private List<Pelicula> whenConsultoEstrenoDelMes() {
		return servicioPelicula.obtenerPeliculaEstrenos();
		
	}

	private void givenPeliculasEstrenosDelMes() {
		Pelicula pelicula=new Pelicula();
		Pelicula pelicula2=new Pelicula();
	
		List<Pelicula>peliculasEstrenos=new ArrayList<>();
		peliculasEstrenos.add(pelicula);
		peliculasEstrenos.add(pelicula2);
		when(repositorioPelicula.getEstrenosDelMes()).thenReturn(peliculasEstrenos);
		
	}
	
	private void givenProximosEstrenos() {
		Pelicula pelicula=new Pelicula();
		Pelicula pelicula2=new Pelicula();
	
		List<Pelicula>proximasEstrenos=new ArrayList<>();
		proximasEstrenos.add(pelicula);
		proximasEstrenos.add(pelicula2);
		when(repositorioPelicula.getProximosEstrenos()).thenReturn(proximasEstrenos);
		
	}
    
    
    
    
    
}
