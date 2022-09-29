package ar.edu.unlam.tallerweb1.domain.genero;

import ar.edu.unlam.tallerweb1.SpringTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServicioGeneroTest {
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
}
