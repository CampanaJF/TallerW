package ar.edu.unlam.tallerweb1.domain.genero;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.usuario.GeneroUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    /*@Test
    public void queDevuelvaLosGenerosElegidosDelUsuario(){
          Usuario usuario = dadoQueHayUnUsuario();
          Genero genero = dadoQueHayUnGenero();
          Genero genero2 = dadoQueHayUnGenero();
          Genero genero3 = dadoQueHayUnGenero();
          dadoQueUnUsuarioEligioGeneros(usuario,genero);
          dadoQueUnUsuarioEligioGeneros(usuario,genero2);
          dadoQueUnUsuarioEligioGeneros(usuario,genero3);

          List<Genero> listaGenerosElegidos  = cuandoObtengoLosGenerosElegidos(usuario);

          entoncesObtengoGenerosDelUsuario(listaGenerosElegidos,3,usuario);
    }

    private List<Genero> cuandoObtengoLosGenerosElegidos(Usuario usuario) {
        List<Genero> generoList = new ArrayList<>();
        when(repositorioGenero.obtenerGenerosElegidosPorUsuario(usuario)).thenReturn(generoList);
        return  generoList;
    }

    private void entoncesObtengoGenerosDelUsuario(List<Genero> listaGenerosElegidos, int cantidadEsperada,Usuario usuario) {
        verify(repositorioGenero,times(1)).obtenerGenerosElegidosPorUsuario(usuario);
        Assertions.assertThat(listaGenerosElegidos).isNotEmpty();
        Assertions.assertThat(listaGenerosElegidos.size()).isEqualTo(cantidadEsperada);
    }

    private void dadoQueUnUsuarioEligioGeneros(Usuario usuario, Genero genero) {
        GeneroUsuario generoUsuario = new GeneroUsuario();
        generoUsuario.setUsuario(usuario);
        generoUsuario.setGenero(genero);
    }

    private Genero dadoQueHayUnGenero() {
        Genero genero = new Genero();
        return genero;
    }

    private Usuario dadoQueHayUnUsuario(){
        Usuario usuario = new Usuario();
        return usuario;
    }*/

}
