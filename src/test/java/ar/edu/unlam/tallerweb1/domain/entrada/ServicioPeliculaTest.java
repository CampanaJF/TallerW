package ar.edu.unlam.tallerweb1.domain.entrada;

import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.RepositorioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPeliculaImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ServicioPeliculaTest {

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
    	when(repoPelicula.getPeliculas(filtro)).thenReturn(peliculas);
    	servicioPelicula=new ServicioPeliculaImpl(repoPelicula);
    	
    	//Ejecucion
    	List<Pelicula>listadoPeliculas=servicioPelicula.obtenerPeliculas(filtro);
    	assertEquals(1, listadoPeliculas.size());
    	assertNotNull(listadoPeliculas);
    }

}
