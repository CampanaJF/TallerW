package ar.edu.unlam.tallerweb1.domain.pelicula;
import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.RepositorioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.ServicioPeliculaImpl;
import ar.edu.unlam.tallerweb1.domain.pelicula.dto.PeliculaConEtiquetaDTO;
import ar.edu.unlam.tallerweb1.domain.usuario.GeneroUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioPeliculaImpl;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Java6Assertions.assertThat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
public class ServicioPeliculaTest {


    public static final String PELICULA_TITULO ="Back to the future";

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
    	Filtro filtro=new Filtro(1L,1L,"Director");
    	RepositorioPelicula repoPelicula=mock(RepositorioPelicula.class);
    	List<EtiquetaPelicula>peliculas=new ArrayList<>();
    	EtiquetaPelicula etiquetaPelicula=new EtiquetaPelicula();
    	Etiqueta etiqueta=new Etiqueta();
    	Pelicula pelicula=new Pelicula();
    	pelicula.setId(1L);
    	etiquetaPelicula.setEtiqueta(etiqueta);
    	etiquetaPelicula.setPelicula(pelicula);
    	peliculas.add(etiquetaPelicula);
    	when(repoPelicula.getPeliculasFiltro(filtro)).thenReturn(peliculas);
    	servicioPelicula=new ServicioPeliculaImpl(repoPelicula);
    	
    	//Ejecucion
    	List<PeliculaConEtiquetaDTO>listadoPeliculas=servicioPelicula.obtenerPeliculas(filtro);
    	assertEquals(1, listadoPeliculas.size());
    	assertNotNull(listadoPeliculas);
    }

 @Test
    public void queSePuedaBuscarUnaPeliculaPorSuTitulo(){

        givenQueExistenPeliculas();
        List<Pelicula> peliculas=whenConsultoPorLaPelicula();
        thenSeEncontroLaPelicula(peliculas);
    }
    @Test
    public void alRealizarUnaBusquedaNoEncuentroLaPelicula(){
        givenNoExisteLaPelicula();
        List<Pelicula> peliculaList= whenBuscoLaPelicula ();
        thenNoEncuentroPeliculas(peliculaList);
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
    private void givenNoExisteLaPelicula(){
        when(this.repositorioPelicula.buscarPeliculas(PELICULA_TITULO)).thenReturn(null);
    }
    private List<Pelicula> whenBuscoLaPelicula (){
        return servicioPelicula.buscarPeliculas(PELICULA_TITULO);
    }
    private void thenNoEncuentroPeliculas(List<Pelicula>peliculas){
       assertThat(peliculas).isNull();
    }
    
    
    @Test
    public void consultarQueDevuelvaLosEstrenosDelMes() {
    	
    	givenPeliculasEstrenosDelMes();
    	List<PeliculaConEtiquetaDTO>peliculasEstrenos=whenConsultoEstrenoDelMes();
    	thenObtengoEstrenoDelMes(peliculasEstrenos);
    }
    
    @Test
    public void consultarLosProximosEstrenos(){
    	givenProximosEstrenos();
    	List<PeliculaConEtiquetaDTO>proximosEstrenos=whenConsultoProximosEstrenos();
    	thenObtengoProximosEstrenos(proximosEstrenos);
    	
    }
	private void thenObtengoProximosEstrenos(List<PeliculaConEtiquetaDTO> proximosEstrenos) {
		assertEquals(2,proximosEstrenos.size());
		
	}

	private List<PeliculaConEtiquetaDTO> whenConsultoProximosEstrenos() {
		// TODO Auto-generated method stub
		return servicioPelicula.obtenerProximosEstrenos();
	}

	private void thenObtengoEstrenoDelMes(List<PeliculaConEtiquetaDTO>peliculasEstrenos) {
		assertEquals(2,peliculasEstrenos.size());
		
	}

	private List<PeliculaConEtiquetaDTO> whenConsultoEstrenoDelMes() {
		return servicioPelicula.obtenerPeliculaEstrenos();
		
	}

	private void givenPeliculasEstrenosDelMes() {
		List<EtiquetaPelicula> peliculasEstrenos = obtenerPeliculasEstrenos();
		when(repositorioPelicula.getEstrenosDelMes()).thenReturn(peliculasEstrenos);
		
	}

	private List<EtiquetaPelicula> obtenerPeliculasEstrenos() {
		EtiquetaPelicula etiqueta1=new EtiquetaPelicula();
		EtiquetaPelicula etiqueta2=new EtiquetaPelicula();
		Etiqueta etiqueta=new Etiqueta();
		
		Pelicula pelicula =new Pelicula();
		Pelicula pelicula2=new Pelicula();
		pelicula.setId(1L);
		pelicula2.setId(2L);
		
		etiqueta1.setEtiqueta(etiqueta);
		etiqueta2.setEtiqueta(etiqueta);
		
		etiqueta1.setPelicula(pelicula);
		etiqueta2.setPelicula(pelicula2);
		
		List<EtiquetaPelicula>peliculasEstrenos=new ArrayList<>();
		peliculasEstrenos.add(etiqueta1);
		peliculasEstrenos.add(etiqueta2);
		return peliculasEstrenos;
	}
	
	private void givenProximosEstrenos() {
		
	
		List<EtiquetaPelicula>proximasEstrenos=obtenerPeliculasEstrenos();
		
		when(repositorioPelicula.getProximosEstrenos()).thenReturn(proximasEstrenos);
		
	}

	@Test
	public void verificarQueDevuelvaPeliculasDeUnGeneroElegido(){
		Usuario usuarioExistente = givenExisteUnUsuario();
		Genero generoElegido = dadoQueHayUnGenero("Accion");
		Genero otroGenero = dadoQueHayUnGenero("Terror");
		dadoQueExistePelicula("Top gun",generoElegido);
		dadoQueExistePelicula("Bienvenido al infierno",otroGenero);
		GeneroUsuario generoUsuario = dadoQueUnUsuarioEligeUnGenero(usuarioExistente,generoElegido);
		List<Pelicula> peliculasDeUnGenero= cuandoConsultoPorPeliculasDeEseGeneroElegido(generoUsuario.getGenero());
		entoncesObtengoPeliculasDeEseGenero(peliculasDeUnGenero,1);
	}
	@Test
	public void verificaQueNoDevuelvaPeliculasSiNoEligioGeneros(){
		Usuario usuarioExistente = givenExisteUnUsuario();
		Genero genero = dadoQueHayUnGenero("Accion");
		dadoQueExistePelicula("Top gun",genero);
		GeneroUsuario generoUsuario = dadoQueUnUsuarioEligeUnGenero(usuarioExistente,null);
		List<Pelicula> peliculasDeUnGenero= cuandoConsultoPorPeliculasDeEseGeneroElegido(generoUsuario.getGenero());
		entoncesObtengoPeliculasDeEseGenero(peliculasDeUnGenero,0);
	}
	@Test
	public void verificaQueSiEligio2GenerosQueDevuelvaPeliculasDeAmbosGeneros(){
		Usuario usuarioExistente = givenExisteUnUsuario();
		List<Genero>generosElegidoLista = dadoQueHayUnaListaDeGeneros();
		dadoQueHayPeliculasDeVariosGeneros(generosElegidoLista);
		List<GeneroUsuario> generoUsuario = dadoQueUnUsuarioEligeVariosGeneros(usuarioExistente, generosElegidoLista);
		List<Pelicula> peliculas = cuandoConsultoPorPeliculasDeVariosGenerosElegido(generoUsuario);
		entoncesObtengoPeliculasDeVariosGenero(peliculas);
	}

	private void entoncesObtengoPeliculasDeVariosGenero(List<Pelicula> peliculas ) {
		verify(repositorioPelicula, atLeastOnce()).obtenerPeliculasPorGeneroElegidoPorUsuario(anyObject());
		assertThat(peliculas).isNotNull();
		assertThat(peliculas.size()).isEqualTo(4);
	}
	private List<Pelicula> cuandoConsultoPorPeliculasDeVariosGenerosElegido(List<GeneroUsuario> generosUsuario) {
		List<Pelicula> peliculasLista  = new ArrayList<>();
		for (GeneroUsuario genero: generosUsuario) {
			peliculasLista.addAll(this.servicioPelicula.obtenerPeliculasPorGenero(genero.getGenero()));
		}
		return peliculasLista;
	}

	private List<GeneroUsuario> dadoQueUnUsuarioEligeVariosGeneros(Usuario usuario, List<Genero> generosElegidoLista) {
		List<GeneroUsuario> generos = new ArrayList<>();

		for (Genero genero: generosElegidoLista) {
			GeneroUsuario generoUsuario = new GeneroUsuario();
			generoUsuario.setUsuario(usuario);
			generoUsuario.setGenero(genero);
			generos.add(generoUsuario);

		}
		when(repositorioPelicula.obtenerGenerosElegidosPorUsuario(usuario)).thenReturn(generos);
		return generos;
	}
	private List<Pelicula> dadoQueHayPeliculasDeVariosGeneros(List<Genero> generosElegidoLista) {
		List<Pelicula> peliculas = new ArrayList<>();

		for (Genero genero: generosElegidoLista) {
			Pelicula pelicula= new Pelicula();
			pelicula.setId(new Random().nextLong());
			pelicula.setGenero(genero);
			peliculas.add(pelicula);
			when(repositorioPelicula.obtenerPeliculasPorGeneroElegidoPorUsuario(genero)).thenReturn(peliculas);
		}
		return peliculas;
	}
	private List<Genero> dadoQueHayUnaListaDeGeneros() {
		List<Genero> generoLista = new ArrayList<>();


		Genero generoElegido = new Genero();
		generoElegido.setId(new Random().nextLong());
		generoLista.add(generoElegido);

		Genero otroGeneroElegido = new Genero();
		otroGeneroElegido.setId(new Random().nextLong());
		generoLista.add(otroGeneroElegido);
		when(repositorioPelicula.obtenerGenerosDePelicula()).thenReturn(generoLista);
		return generoLista;
	}

	private List<Pelicula> cuandoConsultoPorPeliculasDeEseGeneroElegido(Genero genero) {
		return servicioPelicula.obtenerPeliculasPorGenero(genero);
	}

	private void entoncesObtengoPeliculasDeEseGenero(List<Pelicula> peliculasDeUnGenero, int cantidadEsperada) {
		assertThat(peliculasDeUnGenero).hasSize(cantidadEsperada);

	}

	private void dadoQueExistePelicula(String titulo, Genero genero) {
		List<Pelicula> peliculasLista = new ArrayList<>();
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo(titulo);
		pelicula.setGenero(genero);
		peliculasLista.add(pelicula);
		when(repositorioPelicula.obtenerPeliculasPorGeneroElegidoPorUsuario(genero)).thenReturn(peliculasLista);
	}

	private GeneroUsuario dadoQueUnUsuarioEligeUnGenero(Usuario usuarioExistente, Genero genero) {
		GeneroUsuario generoUsuario= new GeneroUsuario();
		generoUsuario.setUsuario(usuarioExistente);
		generoUsuario.setGenero(genero);
		return generoUsuario;
	}

	private Genero dadoQueHayUnGenero(String descripcion) {
		Genero genero= new Genero();
		genero.setDescripcion(descripcion);
		return genero;
	}
	private Usuario givenExisteUnUsuario() {
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setId(new Random().nextLong());
		return nuevoUsuario;
	}
}
