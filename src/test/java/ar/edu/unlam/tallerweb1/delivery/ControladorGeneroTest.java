package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.genero.ServicioGenero;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorGeneroTest {

    private ServicioGenero servicioGenero;

    private ControladorGenero controladorGenero;

    @Before
    public void init(){
        this.servicioGenero = mock(ServicioGenero.class);
        this.controladorGenero = new ControladorGenero(this.servicioGenero);
    }
    @Test
    public void testQueAlPedirTodosLosGenerosDevuelvaLaListaCompleta(){
        List<Genero> listaGenero = new ArrayList<>();
        when(servicioGenero.listarGeneros()).thenReturn(listaGenero);
        ModelAndView modelAndView = controladorGenero.listarGeneros();
        assertThat((List<Genero>)modelAndView.getModel().get("generos")).hasSize(0);

    }

}
