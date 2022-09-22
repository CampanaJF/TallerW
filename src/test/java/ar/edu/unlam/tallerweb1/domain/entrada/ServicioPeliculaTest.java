package ar.edu.unlam.tallerweb1.domain.entrada;

import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
public class ServicioPeliculaTest {



//Aca lo unico que me interesa es que el servicio devuelva peliculas
    @Test
    public void listarPeliculas(){
    //Preparacion
    List<Pelicula> peliculas=new ArrayList<Pelicula>();

  
    ServicioPelicula servicioCartelera=mock(ServicioPelicula.class);
    Pelicula pelicula=new Pelicula();
    pelicula.setTitulo("Dragon Ball");
    //Agregar genero,calificacion,duracion 
    
    
    
    peliculas.add(pelicula);
    when(servicioCartelera.obtenerPeliculas()).thenReturn(peliculas);


    //Ejecucion
    List<Pelicula>peliculasDelServicio=servicioCartelera.obtenerPeliculas();

    //Necesito obtener peliculas
    assertTrue(peliculasDelServicio.size()==peliculas.size());
    //Validacion



}

}
