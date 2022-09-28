package ar.edu.unlam.tallerweb1.domain.entrada;

import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.RepositorioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPeliculaImpl;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioPeliculaImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;


public class ServicioPeliculaTest {

    private ServicioPelicula servicioPelicula;
    private RepositorioPelicula repositorioPelicula;

    @Before
    public void init(){
        this.repositorioPelicula= mock(RepositorioPelicula.class);
        this.servicioPelicula= new ServicioPeliculaImpl(this.repositorioPelicula);
    }

    @Test
    public void queSePuedaBuscarUnaPeliculaPorSuTitulo(){
        //dadp que existen peliculas
        dadoQueExistenPeliculas();
        //cuando consulta por la peli
        List<Pelicula> peliculas=cuandoConsultoPorLaPelicula("Back to the future");
        //me devuelve uno
        entoncesSeBuscoLaPelicula("Back to the future");
    }
    private void dadoQueExistenPeliculas(){
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setTitulo("Back to the future");

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setTitulo("Indiana Jones: Raiders of the Lost Ark");

        List<Pelicula> peliculaList = new LinkedList<>();
        peliculaList.add(pelicula1);
        peliculaList.add(pelicula2);

        when(this.repositorioPelicula.getPeliculas()).thenReturn(peliculaList);
    }
    private List<Pelicula> cuandoConsultoPorLaPelicula(String titulo){
        return this.servicioPelicula.buscarPeliculas(titulo);
    }
    private  void entoncesSeBuscoLaPelicula(String titulo){
        verify(repositorioPelicula,times(1)).buscarPeliculas(titulo);
    }

}
