package ar.edu.unlam.tallerweb1.domain.pelicula;
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
    
    
    
    
    
}