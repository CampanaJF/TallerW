package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.genero.RepositorioGenero;
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
        dadoQueExistenGenerosQueEligioUnUsuario(1L,"Anime",usuario);
        dadoQueExistenGenerosQueEligioUnUsuario(3L,"Suspenso",usuario);
        dadoQueExistenGenerosQueEligioUnUsuario(4L,"Comedia",usuario);
         //cuando consulto por generos que eligio un usuario

        //entonces obtengo cantidad de generos por usuario
    }
    private Usuario dadoQueExisteUnUsuario(){
        Usuario usuario = new Usuario();
        session().save(usuario);
        return usuario;
    }
    private void dadoQueExistenGenerosQueEligioUnUsuario(Long generoId,String descripcion, Usuario usuario){
        Genero genero1 = new Genero();
        genero1.setId(generoId);
        genero1.setDescripcion(descripcion);
        genero1.setUsuario(usuario);
        session().save(genero1);
    }
}
