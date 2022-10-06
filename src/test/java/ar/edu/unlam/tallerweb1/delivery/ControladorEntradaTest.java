package ar.edu.unlam.tallerweb1.delivery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.cine.CinePelicula;
import ar.edu.unlam.tallerweb1.domain.cine.ServicioCine;
import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.entrada.ServicioEntrada;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.funcion.ServicioFuncion;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.session.ServicioSession;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations= "classpath:applicationContext.xml")
public class ControladorEntradaTest {
	
	private final ServicioSession servicioSession = mock(ServicioSession.class);
	private final ServicioEntrada servicioEntrada = mock(ServicioEntrada.class);
	private final ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	private final ServicioFuncion servicioFuncion = mock(ServicioFuncion.class);
	private final ServicioCine servicioCine = mock(ServicioCine.class);
	
	private final ControladorEntrada controladorEntrada = new ControladorEntrada(servicioSession,servicioEntrada,servicioUsuario,
																					servicioFuncion,servicioCine);

	private final HttpServletRequest mockRequest = mock(HttpServletRequest.class);
	private final HttpSession mockSession = mock(HttpSession.class);

	
	private ModelAndView mav = new ModelAndView();
	
//	@Test
//	public void queSeCompreLaEntrada() {
//		
//		Usuario U1 = givenUsuario(1L,"Nombre");
//		Funcion F1 = new Funcion();
//		F1.setId(1L);
//		Entrada entrada = givenEntrada(U1,F1);
//		
//		
//		whenSeCompraUnaEntrada(entrada);
//		
//		thenSeComproLaEntrada(entrada);
//	}
//	
//	private void whenSeCompraUnaEntrada(Entrada entrada) {
//		mocksSessionRequests();
//		
//		when(servicioEntrada.getEntrada(entrada.getUsuario().getId(),entrada.getFuncion().getId())).thenReturn(entrada);
//		mav = this.controladorEntrada.comprarEntrada(entrada,mockRequest);
//		
//		
//		
//	}
//
//	private void thenSeComproLaEntrada(Entrada entrada) {
//		assertThat(mav.getViewName()).isEqualTo("entrada");
//
//		
//	}
	
	@Test
	public void queSeHayaCompradoLaEntradaExitosamente() {
		
		Cine C1 = givenCine("1");
		Pelicula P1 = givenPelicula("peli",1L);
		Funcion funcion = givenFuncion(C1,P1);
		Usuario usuario = givenUsuario(1L,"Nombre");
		
		DatosEntrada DE = givenDatosEntrada(funcion,usuario);
		
		Entrada entrada = givenEntrada(usuario,funcion);
		
		whenSeSeleccionaLaFuncionDeseada(funcion,usuario,entrada,DE);
		
		thenSeCompraLaEntradaParaEsaFuncion();
			
	}
	
	private void thenSeCompraLaEntradaParaEsaFuncion() {
		assertThat(mav.getViewName()).isEqualTo("entrada");
		assertThat(mav.getModel().get("usuario")).isNotNull();
		assertThat(mav.getModel().get("entrada")).isNotNull();
		
	}

	private void whenSeSeleccionaLaFuncionDeseada(Funcion funcion, Usuario usuario, Entrada entrada,DatosEntrada DE) {
		mocksSessionRequests();
		
		when(this.servicioEntrada.comprarEntrada(DE)).thenReturn(entrada);
		when(this.servicioEntrada.getEntrada(entrada.getId())).thenReturn(entrada);
		mav = this.controladorEntrada.entradaCompra(DE,mockRequest);	
	}

	@Test
	public void queSeHayaElegidoUnCineYPuedaElegirElHorarioYFormatoParaComprarUnaEntradaDePelicula() {
		
		Cine C1 = givenCine("1");
		Pelicula P1 = givenPelicula("peli",1L);
		
		DatosCine CD= new DatosCine();
		
		CD.setCine(C1.getId());
		CD.setPelicula(P1.getId());
		
		List <Funcion> funciones =  givenFuncionesParaEseCineYEsaPelicula(C1,P1);
		
		whenSeQuiereElegirElFormatoYHorario(CD,funciones);
		
		thenSeEligenElFormatoYHorario(funciones);
	}

	private void thenSeEligenElFormatoYHorario(List<Funcion> funciones) {
		 assertThat(mav.getViewName()).isEqualTo("entrada-preparacion");
		 assertThat(mav.getModel().get("funciones")).isNotNull();
		
	}

	private void whenSeQuiereElegirElFormatoYHorario(DatosCine CD,List<Funcion> funciones) {
		mocksSessionRequests();
		
		when(servicioFuncion.getFuncionesDeUnCine(CD.getCine(),CD.getPelicula())).thenReturn(funciones);
		mav = this.controladorEntrada.entradaPreparacion(CD,mockRequest);
		
	}
	
	private List<Funcion> givenFuncionesParaEseCineYEsaPelicula(Cine cine,Pelicula pelicula){
		
		Funcion F1 = givenFuncion(cine,pelicula);
		Funcion F2 = givenFuncion(cine,pelicula);
		Funcion F3 = givenFuncion(cine,pelicula);
		
		List <Funcion> funciones = new ArrayList();
		funciones.add(F1);
		funciones.add(F2);
		funciones.add(F3);
		
		return funciones;
	}

	@Test
	public void queSePuedaElegirElCineParaComprarUnaEntradaDePelicula(){
		
		List <CinePelicula> cines = givenCinesConPeliculas();
		
    	whenSeEligeQuiereElegirElCine(cines);
    	
    	thenSePuedeElegirElCine(cines);
	
	}
	
	private List<CinePelicula> givenCinesConPeliculas(){
		
		Cine C1 = givenCine("1");
		Cine C2 = givenCine("2");
		Cine C3 = givenCine("3");
		
		Pelicula P1 = givenPelicula("P",1L);
		Pelicula P2 = givenPelicula("P",2L);
		
		List <CinePelicula> cines = new ArrayList();
		CinePelicula one = new CinePelicula();
		CinePelicula two = new CinePelicula();
		CinePelicula three = new CinePelicula(); 
		
		one.setCine(C1);
		one.setPelicula(P1);
		two.setCine(C2);
		two.setPelicula(P2);
		three.setCine(C3);
		three.setPelicula(P1);
		
		
		cines.add(one);
		cines.add(two);
		cines.add(three);
		
		return cines;
	}
	
	private void whenSeEligeQuiereElegirElCine(List<CinePelicula> cines) {
		mocksSessionRequests();
		
		when(servicioCine.getCines(1L)).thenReturn(cines);
		mav = this.controladorEntrada.entradaPelicula(mockRequest);
		
		
	}

	private void thenSePuedeElegirElCine(List<CinePelicula> cines) {
		assertThat(mav.getViewName()).isEqualTo("entrada-pelicula");


	}
	
	private void mocksSessionRequests() {
	    when(mockRequest.getSession()).thenReturn(mockSession);
	    when(mockRequest.getSession().getAttribute("ID")).thenReturn(1L);

	 }
	
	private DatosEntrada givenDatosEntrada(Funcion funcion, Usuario usuario) {
		DatosEntrada DE = new DatosEntrada();
		DE.setFuncion(funcion);
		DE.setUsuario(usuario);
		return DE;
	}
	
	private Cine givenCine(String string) {
		Cine cine = new Cine();
		cine.setId(new Random().nextLong());
		cine.setNombre(string);
		
		return cine;
	}
	
	private Entrada givenEntrada(Usuario U1,Funcion F1) {
		Entrada entrada = new Entrada();
		entrada.setFuncion(F1);
		entrada.setUsuario(U1);
		
		return entrada;
	}

	public Usuario givenUsuario(Long id,String nombre) {
    	Usuario usuario = new Usuario ();
    	usuario.setId(id);
    	usuario.setNombre(nombre);
    	
    	return usuario;
    }
	
	private Pelicula givenPelicula(String titulo,Long id) {
		Pelicula pelicula = new Pelicula();
		pelicula.setId(id);
		pelicula.setTitulo(titulo);
		
		return pelicula;
	}
	
	private Funcion givenFuncion(Cine cine,Pelicula pelicula) {
		Funcion funcion = new Funcion();
		funcion.setCine(cine);
		funcion.setPelicula(pelicula);
		funcion.setHorario(new Random().nextInt());
		funcion.setId(new Random().nextLong());
		
		return funcion;
		
	}

}
