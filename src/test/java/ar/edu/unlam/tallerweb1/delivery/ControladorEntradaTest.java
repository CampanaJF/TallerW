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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.cine.CinePelicula;
import ar.edu.unlam.tallerweb1.domain.cine.Sala;
import ar.edu.unlam.tallerweb1.domain.cine.ServicioCine;
import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.entrada.ServicioEntrada;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.funcion.ServicioFuncion;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations= "classpath:applicationContext.xml")
public class ControladorEntradaTest {
	

	private final ServicioEntrada servicioEntrada = mock(ServicioEntrada.class);
	private final ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	private final ServicioFuncion servicioFuncion = mock(ServicioFuncion.class);
	private final ServicioCine servicioCine = mock(ServicioCine.class);
	
	private final RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
	
	private final ControladorEntrada controladorEntrada = new ControladorEntrada(servicioEntrada,servicioUsuario,
																					servicioFuncion,servicioCine);

	private final HttpServletRequest mockRequest = mock(HttpServletRequest.class);
	private final HttpSession mockSession = mock(HttpSession.class);

	
	private ModelAndView mav = new ModelAndView();
	
	
	@Test
	public void queSeHayaCompradoLaEntradaExitosamente() {
		
		Cine cine = givenCine("CINEEEEEE");
		Pelicula pelicula = givenPelicula("peli",1L);
		Sala sala = givenSala(cine,"LA SALA");
		Funcion funcion = givenFuncion(sala,pelicula);
		Usuario usuario = givenUsuario(1L,"Nombre");
		
		DatosEntrada datosEntrada = givenDatosEntrada(funcion,usuario);
		
		Entrada entrada = givenEntrada(usuario,funcion);
		
		whenSeSeleccionaLaFuncionDeseada(entrada,datosEntrada);
		
		thenSeCompraLaEntradaParaEsaFuncion();
			
	}
	
	private void thenSeCompraLaEntradaParaEsaFuncion() {
		assertThat(mav.getViewName()).isEqualTo("entrada");
		assertThat(mav.getModel().get("entrada")).isNotNull();
		
	}

	private void whenSeSeleccionaLaFuncionDeseada(Entrada entrada,DatosEntrada DE) {
		mocksSessionRequests();
		
		when(servicioUsuario.getUsuario(1L)).thenReturn(new Usuario());
		when(this.servicioEntrada.getUltimaEntradaDeUsuario(DE.getUsuario().getId(),DE.getFuncion().getId())).thenReturn(entrada);
		mav = this.controladorEntrada.entradaCompra(DE,mockRequest,redirectAttributes);	
	}

	@Test
	public void queSeHayaElegidoUnCineYPuedaElegirElHorarioYFormatoParaComprarUnaEntradaDePelicula() {
		
		Cine cine = givenCine("CINEEE");
		Sala sala = givenSala(cine,"LA SALA");
		Pelicula pelicula = givenPelicula("peli",1L);
		
		DatosCine datosCine= new DatosCine();
		
		datosCine.setCine(cine.getId());
		datosCine.setPelicula(pelicula.getId());
		
		List <Funcion> funciones =  givenFuncionesParaEseCineYEsaPelicula(sala,pelicula);
		
		whenSeQuiereElegirElFormatoYHorario(datosCine,funciones);
		
		thenSeEligenElFormatoYHorario(funciones);
	}

	private void thenSeEligenElFormatoYHorario(List<Funcion> funciones) {
		 assertThat(mav.getViewName()).isEqualTo("entrada-preparacion");
		 assertThat(mav.getModel().get("funciones")).isNotNull();
		
	}

	private void whenSeQuiereElegirElFormatoYHorario(DatosCine CD,List<Funcion> funciones) {
		mocksSessionRequests();
		
		when(servicioUsuario.getUsuario(1L)).thenReturn(new Usuario());
		when(servicioFuncion.getFuncionesDeUnCine(CD.getCine(),CD.getPelicula())).thenReturn(funciones);
		mav = this.controladorEntrada.entradaPreparacion(CD,mockRequest);
		
	}
	
	private List<Funcion> givenFuncionesParaEseCineYEsaPelicula(Sala sala,Pelicula pelicula){
		
		Funcion funcion1 = givenFuncion(sala,pelicula);
		Funcion funcion2 = givenFuncion(sala,pelicula);
		Funcion funcion3 = givenFuncion(sala,pelicula);
		
		List <Funcion> funciones = new ArrayList<Funcion>();
		funciones.add(funcion1);
		funciones.add(funcion2);
		funciones.add(funcion3);
		
		return funciones;
	}
	
	@Test
	public void queNoSePuedaComprarUnaEntradaSiNoSeEstaLogueado(){
				
		DatosEntrada datosEntrada = new DatosEntrada();
				
    	whenSeIntentaComprarUnaEntradaSinEstarLogueado(datosEntrada);
    	
    	thenSeRedireccionaARegistro();
	
	}

	private void whenSeIntentaComprarUnaEntradaSinEstarLogueado(DatosEntrada datosEntrada) {
		mocksSessionRequests();
		
		when(servicioUsuario.getUsuario(1L)).thenReturn(null);
		mav = this.controladorEntrada.entradaCompra(datosEntrada,mockRequest,redirectAttributes);	
		
	}

	private void thenSeRedireccionaARegistro() {
		assertThat(mav.getViewName()).isEqualTo("redirect:/registrarme");
		
	}

	@Test
	public void queSePuedaElegirElCineParaComprarUnaEntradaDePelicula(){
		
		List <CinePelicula> cines = givenCinesConPeliculas();
		
    	whenSeEligeQuiereElegirElCine(cines);
    	
    	thenSePuedeElegirElCine(cines);
	
	}
	
	private List<CinePelicula> givenCinesConPeliculas(){
		
		Cine cine1 = givenCine("1");
		Cine cine2 = givenCine("2");
		Cine cine3 = givenCine("3");
		
		Pelicula pelicula1 = givenPelicula("Pelicula 1",1L);
		Pelicula pelicula2 = givenPelicula("Pelicula 2 Electric Boogaloo",2L);
		
		List <CinePelicula> cines = new ArrayList<CinePelicula>();
		CinePelicula one = new CinePelicula();
		CinePelicula two = new CinePelicula();
		CinePelicula three = new CinePelicula(); 
		
		one.setCine(cine1);
		one.setPelicula(pelicula1);
		two.setCine(cine2);
		two.setPelicula(pelicula2);
		three.setCine(cine3);
		three.setPelicula(pelicula1);
		
		
		cines.add(one);
		cines.add(two);
		cines.add(three);
		
		return cines;
	}
	
	private void whenSeEligeQuiereElegirElCine(List<CinePelicula> cines) {
		mocksSessionRequests();
		
		when(servicioUsuario.getUsuario(1L)).thenReturn(new Usuario());
		when(servicioCine.getCines(1L)).thenReturn(cines);
		mav = this.controladorEntrada.entradaPelicula(mockRequest,1L);
		
		
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
		cine.setNombreCine(string);
		
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
	
	private Sala givenSala(Cine cine,String string) {
		Sala sala = new Sala();
		sala.setId(new Random().nextLong());
		sala.setCine(cine);
		sala.setNombreSala(string);
		return sala;
	}
	
	private Funcion givenFuncion(Sala sala,Pelicula pelicula) {
		Funcion funcion = new Funcion();
		funcion.setSala(sala);
		funcion.setPelicula(pelicula);
		funcion.setId(new Random().nextLong());
		
		return funcion;
		
	}

}
