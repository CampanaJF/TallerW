package ar.edu.unlam.tallerweb1.domain.entrada;

import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.RepositorioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPeliculaImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;


public class ServicioPeliculaTest {

    private ServicioPelicula servicioPelicula;
    private RepositorioPelicula repositorioPelicula;
    public static final String PELICULA_TITULO ="Back to the future";

    @Before
    public void init(){
        this.repositorioPelicula= mock(RepositorioPelicula.class);
        this.servicioPelicula= new ServicioPeliculaImpl(this.repositorioPelicula);
    }

    @Test
    public void queSePuedaBuscarUnaPeliculaPorSuTitulo(){
        //dadp que existen peliculas
        givenQueExistenPeliculas();
        //cuando consulta por la peli
        List<Pelicula> peliculas=whenConsultoPorLaPelicula();
        //me devuelve uno
        thenSeEncontroLaPelicula(peliculas);
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
    @Test
    public void alRealizarUnaBusquedaNoEncuentroLaPelicula(){
        //dado que la peli no existe
        givenNoExisteLaPelicula();
        //when busco la peli
        List<Pelicula> peliculaList= whenBuscoLaPelicula ();
        //then no la encuentro
        thenNoEncuentroPeliculas(peliculaList);
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
}
