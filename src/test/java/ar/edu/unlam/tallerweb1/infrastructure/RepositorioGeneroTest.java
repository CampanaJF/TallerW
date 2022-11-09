package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.genero.RepositorioGenero;
import ar.edu.unlam.tallerweb1.domain.usuario.GeneroUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioGeneroTest extends SpringTest {
    @Autowired
	private RepositorioGenero repositorioGenero;

    @Test
    @Transactional
    @Rollback
    public void testQueSeDevuelvaListaDeGeneros(){
        dadoQueTenemosGenerosGuardadosEnBaseDeDatos();
        List<Genero> listaGeneros = cuandoConsultoLosGeneros();
        entoncesTengoLaListaDeGeneros(listaGeneros);
    }

    private void entoncesTengoLaListaDeGeneros(List<Genero> listaGeneros) {
        assertThat(listaGeneros).hasSize(2);
    }

    private List<Genero> cuandoConsultoLosGeneros() {
        return repositorioGenero.getGeneros();
    }

    private void dadoQueTenemosGenerosGuardadosEnBaseDeDatos() {
        Genero genero = new Genero();
        genero.setDescripcion("Drama");

        Genero genero2 = new Genero();
        genero.setDescripcion("Accion");

        session().save(genero);
        session().save(genero2);
}
    //Agregar para tipo de genero
    //busqueda por genero por genero/clasificacion por clasificacion busqueda sin filtro

    @Test
    @Transactional
    @Rollback
    public void queVerifiqueQueUnUsuarioEligioVariosGeneros(){

           Usuario usuario = dadoQueExisteUnUsuario();
           Genero genero = dadoQueExisteUnGenero("Accion");
           Genero genero2 = dadoQueExisteUnGenero("Suspenso");
            dadoQueExistenGenerosDeUnUsuario(usuario,genero);
            dadoQueExistenGenerosDeUnUsuario(usuario,genero2);
            dadoQueExistenGenerosDeUnUsuario(usuario,genero2);

           List<Genero> generosPorUsuario = cuandoConsultoPorLosGenerosDeUnUsuario(usuario);

           entoncesObtengoGenerosElegidosPorElUsuario(generosPorUsuario,3);
    }

    private void entoncesObtengoGenerosElegidosPorElUsuario(List<Genero> generosPorUsuario, int cantidadEsperada ) {
        assertThat(generosPorUsuario).isNotNull();
        assertThat(generosPorUsuario).hasSize(cantidadEsperada);
    }

    private List<Genero> cuandoConsultoPorLosGenerosDeUnUsuario(Usuario generoUsuario) {
        return repositorioGenero.obtenerGenerosElegidosPorUsuario(generoUsuario);
    }

    private Usuario dadoQueExisteUnUsuario(){
        Usuario usuario = new Usuario();
        session().save(usuario);
        return usuario;
    }
    private Genero dadoQueExisteUnGenero(String descripcion){
        Genero genero = new Genero();
        genero.setDescripcion(descripcion);
        session().save(genero);
        return  genero;
    }
    private GeneroUsuario dadoQueExistenGenerosDeUnUsuario(Usuario usuario, Genero genero){
        GeneroUsuario generoUsuario = new GeneroUsuario();
        generoUsuario.setUsuario(usuario);
        generoUsuario.setGenero(genero);
        session().save(generoUsuario);
        return generoUsuario;
    }
}
