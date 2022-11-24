package ar.edu.unlam.tallerweb1.domain.genero;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.usuario.GeneroUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.GeneroNoElegidoException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ServicioGeneroTest {
    private final HttpServletRequest mockRequest= mock(HttpServletRequest.class);

    @Autowired
    private RepositorioGenero repositorioGenero;

    @Autowired
    private ServicioGenero servicioGenero;

    @Before
    public void init(){

        repositorioGenero = mock(RepositorioGenero.class);
        servicioGenero = new ServicioGeneroImpl(repositorioGenero);
    }
    @Test
    public void testQueAlPedirTodosLosGenerosDevuelvaLaListaCompleta(){
        List<Genero> listaGeneros = new ArrayList<>();
        when(repositorioGenero.getGeneros()).thenReturn(listaGeneros);
        List<Genero> listaGenerosRecuperados = servicioGenero.listarGeneros();
        verify(repositorioGenero,times(1)).getGeneros();
        assertEquals(listaGeneros,listaGenerosRecuperados);
    }
    @Test
    public void verificarQueDevuelvaLosGenerosElegidosPorUnUsuario(){
        Usuario usuarioExistente = givenExisteUnUsuario();
        givenExisteUnGenero();
        List<Genero> generosLista = givenListaDeGeneros(2);
        givenExistenGenerosDeUnUsuario(usuarioExistente, generosLista);
        List<GeneroUsuario> generosDeUnUsuarioLista = whenObtengoGenerosElegidosPor(usuarioExistente);
        thenObtengoLosGenerosElegidosPorElUsuario(generosDeUnUsuarioLista, 2);
    }
    @Test
    public void verificarQueNoDevuelvaGenerosSiNoEligioNinguno(){
        Usuario usuarioExistente = givenExisteUnUsuario();
        givenExisteUnGenero();
        List<Genero> generosLista = givenListaDeGeneros(0);
        givenExistenGenerosDeUnUsuario(usuarioExistente, generosLista);
        List<GeneroUsuario> generosDeUnUsuarioLista = whenObtengoGenerosElegidosPor(usuarioExistente);
        thenObtengoLosGenerosElegidosPorElUsuario(generosDeUnUsuarioLista, 0);
    }
    @Test(expected = GeneroNoElegidoException.class)
    public void queLanceExcepcionSiNoSeGuardaronGenerosCorrectamente(){
        Usuario usuarioExistente = givenExisteUnUsuario();
        whenGuardoGenerosDelUsuario(usuarioExistente);
    }

    private void whenGuardoGenerosDelUsuario(Usuario usuarioExistente) {
        this.servicioGenero.guardarGeneroElegidoPorUsuario(null,usuarioExistente);
    }

    private void thenObtengoLosGenerosElegidosPorElUsuario(List<GeneroUsuario> generosElegidosLista, int cantidadEsperada) {
        assertThat(generosElegidosLista).isNotNull();
        assertThat(generosElegidosLista).hasSize(cantidadEsperada);
        verify(repositorioGenero, times(1)).obtenerGenerosElegidosPorUsuario(anyObject());
    }

    private List<GeneroUsuario> whenObtengoGenerosElegidosPor(Usuario usuarioExistente) {
        return this.servicioGenero.obtenerGenerosElegidosPorUsuario(usuarioExistente);
    }
    private List<Genero> givenListaDeGeneros(int cantidadElegida) {
        List<Genero> generosElegidos = new ArrayList<>();

        for (int i=0 ; i<cantidadElegida ; i++) {
            generosElegidos.add(givenExisteUnGenero());
        }
        return generosElegidos;
    }
    private void givenExistenGenerosDeUnUsuario(Usuario usuarioExistente, List<Genero>generosElegidosLista){
        List<GeneroUsuario> generoUsuarioList = new ArrayList<>();

        GeneroUsuario generoUsuario= new GeneroUsuario();
        for (Genero genero: generosElegidosLista) {
            generoUsuario.setId(new Random().nextLong());
            generoUsuario.setUsuario(usuarioExistente);
            generoUsuario.setGenero(genero);
            generoUsuarioList.add(generoUsuario);
        }

        when(repositorioGenero.obtenerGenerosElegidosPorUsuario(usuarioExistente)).thenReturn(generoUsuarioList);
    }
    private Genero givenExisteUnGenero() {
        Genero genero = new Genero();
        genero.setId(new Random().nextLong());
        return genero;
    }

    private Usuario givenExisteUnUsuario() {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setId(new Random().nextLong());
        return nuevoUsuario;
    }



}
