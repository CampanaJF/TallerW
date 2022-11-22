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

import ar.edu.unlam.tallerweb1.domain.cine.Asiento;
import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.cine.CinePelicula;
import ar.edu.unlam.tallerweb1.domain.cine.Sala;
import ar.edu.unlam.tallerweb1.domain.cine.ServicioCine;
import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.entrada.ServicioEntrada;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.funcion.ServicioFuncion;
import ar.edu.unlam.tallerweb1.domain.historial.ServicioHistorial;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations= "classpath:applicationContext.xml")
public class ControladorEntradaDeberia {
	

	private final ServicioEntrada servicioEntrada = mock(ServicioEntrada.class);
	private final ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	private final ServicioFuncion servicioFuncion = mock(ServicioFuncion.class);
	private final ServicioCine servicioCine = mock(ServicioCine.class);
	private final ServicioHistorial servicioHistorial = mock(ServicioHistorial.class);
	
	private final RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
	
	private final ControladorEntrada controladorEntrada = new ControladorEntrada(servicioEntrada,servicioUsuario,
																				 servicioFuncion,servicioCine,servicioHistorial);

	private final HttpServletRequest mockRequest = mock(HttpServletRequest.class);
	private final HttpSession mockSession = mock(HttpSession.class);

	
	private ModelAndView mav = new ModelAndView();

	@Test
	public void permitirComprarUnaEntradaQueFueDesocupada() {

		whenSeCompraLaEntradaDesocupada(5L);
		
		thenSeComproLaEntrada();
	}

	@Test
	public void permitirVerTodasLasEntradasAunVigentes() {
		
		whenSeQuierenVerLasEntradasVigentesDeUnUsuario(1L,givenEntradas(5),givenUsuario());
		
		thenSeListanSusEntradas();	
	}
	
	@Test
	public void permitirCancelarUnaReserva() {
		
		whenSeCancelarUnaReserva(5L);
		
		thenSeCanceloLaReserva();
	}
	
	@Test
	public void permitirElegirElCineParaComprarUnaEntrada(){
		
		List <CinePelicula> cines = givenCinesConPeliculas();
		
    	whenSeEligeQuiereElegirElCine(cines);
    	
    	thenSePuedeElegirElCine(cines);
	}
	
	@Test
	public void permitirElegirLaFuncion() {
		
		List <Funcion> funciones = new ArrayList<>();
		funciones.add(givenFuncion());
		
		whenSeEligeLaFuncion(givenDatosCine(),funciones);
		
		thenSePuedenElegirLosAsientos();
	}
	
	@Test
	public void permitirElegirAsientos() {
		
		DatosEntrada datosEntrada= givenDatosEntrada(givenFuncion(),givenUsuario());

		
		whenSeEligenAsientos(datosEntrada);
		
		thenSePuedenComprarLaEntrada();
	}
	
	@Test
	public void comprarUnaEntradaExitosamente() {
		
		Funcion funcion = givenFuncion();
		Usuario usuario = givenUsuario(1L,"Nombre");
		List<Asiento> asientos = givenAsientos(2);
		
		DatosEntrada datosEntrada = givenDatosEntrada(funcion,usuario);
		
		List<Entrada> entrada = new ArrayList<>();
		
		entrada.add(givenEntrada(usuario,funcion));
		
		whenSeSeleccionaLaFuncionDeseada(entrada,datosEntrada,asientos);
		
		thenSeCompraLaEntradaParaEsaFuncion();		
	}
	
	@Test
	public void elegirElCineYLaPeliculaParaLaCompraDeUnaEntrada() {
		
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
	
	@Test
	public void impedirComprarEntradasSiNoSeEstaLogueado(){
				
    	whenSeIntentaComprarUnaEntradaSinEstarLogueado(new DatosEntrada());
    	
    	thenSeRedireccionaARegistro();
	}
	
	private void thenSeComproLaEntrada() {
		assertThat(mav.getViewName()).isEqualTo("redirect:/mis-entradas");
	}

	private void whenSeCompraLaEntradaDesocupada(Long entrada) {
		mocksSessionRequests();
		
		mav = this.controladorEntrada.comprarEntradaDesocupada(entrada,mockRequest);	
	}
	
	private void thenSeListanSusEntradas() {
		assertThat(mav.getViewName()).isEqualTo("entrada");
		assertThat(mav.getModel().get("entradas")).isNotNull();	
	}

	private void whenSeQuierenVerLasEntradasVigentesDeUnUsuario(long l,List<Entrada> entradas,Usuario usuarioLogueado) {
		mocksSessionRequests();
		when(servicioUsuario.getUsuario(1L)).thenReturn(new Usuario());
		when(servicioEntrada.obtenerEntradasVigentes(usuarioLogueado)).thenReturn(entradas);;
		mav = this.controladorEntrada.verEntradasVigentes(mockRequest,null);		
	}

	private void thenSeCanceloLaReserva() {
		assertThat(mav.getViewName()).isEqualTo("redirect:/mis-entradas");
		
	}

	private void whenSeCancelarUnaReserva(Long entrada) {
		mav = this.controladorEntrada.cancelarReserva(entrada,mockRequest,redirectAttributes);	
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
	
	private void thenSePuedenElegirLosAsientos() {
		assertThat(mav.getViewName()).isEqualTo("entrada-preparacion");	
	}

	private void whenSeEligeLaFuncion(DatosCine datosCine,List<Funcion>funciones) {
		mocksSessionRequests();
		
		when(this.servicioFuncion.obtenerLasFuncionesDeLosProximosTresDias(datosCine.getCine(),datosCine.getPelicula()))
		.thenReturn(funciones);
		
		mav = this.controladorEntrada.entradaPreparacion(datosCine,mockRequest,redirectAttributes);		
	}
	
	private void thenSePuedenComprarLaEntrada() {
		assertThat(mav.getViewName()).isEqualTo("entrada-asientos");		
	}

	private void whenSeEligenAsientos(DatosEntrada datosEntrada) {
		mocksSessionRequests();
		when(this.servicioFuncion.validarAsientosDisponibles(datosEntrada.getFuncion())).thenReturn(true);
		
		mav = this.controladorEntrada.entradaAsientos(datosEntrada,mockRequest,redirectAttributes);		
	}

	private void thenSeCompraLaEntradaParaEsaFuncion() {
		assertThat(mav.getViewName()).isEqualTo("entrada");
		assertThat(mav.getModel().get("entradas")).isNotNull();	
	}

	private void whenSeSeleccionaLaFuncionDeseada(List<Entrada> entrada,DatosEntrada datosEntrada,List<Asiento> asientos) {
		mocksSessionRequests();
		
		when(servicioUsuario.getUsuario(1L)).thenReturn(new Usuario());
		when(this.servicioEntrada.obtenerEntradasVigentes(datosEntrada.getUsuario().getId(),datosEntrada.getFuncion().getId())).thenReturn(entrada);
		mav = this.controladorEntrada.entradaCompra(datosEntrada,mockRequest,redirectAttributes);	
	}

	private void thenSeEligenElFormatoYHorario(List<Funcion> funciones) {
		 assertThat(mav.getViewName()).isEqualTo("entrada-preparacion");
		 assertThat(mav.getModel().get("funciones")).isNotNull();	
	}

	private void whenSeQuiereElegirElFormatoYHorario(DatosCine CD,List<Funcion> funciones) {
		mocksSessionRequests();
		
		when(servicioUsuario.getUsuario(1L)).thenReturn(new Usuario());
		when(servicioFuncion.obtenerLasFuncionesDeLosProximosTresDias(CD.getCine(),CD.getPelicula())).thenReturn(funciones);
		mav = this.controladorEntrada.entradaPreparacion(CD,mockRequest,null);		
	}
		
	private void whenSeIntentaComprarUnaEntradaSinEstarLogueado(DatosEntrada datosEntrada) {
		mocksSessionRequests();
		
		when(servicioUsuario.getUsuario(1L)).thenReturn(null);
		mav = this.controladorEntrada.entradaCompra(datosEntrada,mockRequest,redirectAttributes);			
	}

	private void thenSeRedireccionaARegistro() {
		assertThat(mav.getViewName()).isEqualTo("redirect:/registrarme");	
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
	
	private List<Entrada> givenEntradas(Integer cantidad) {
		
		List<Entrada> entradas = new ArrayList<>();
		
		for (int i = 0; i < cantidad; i++) {
			entradas.add(givenEntrada());
			
		}
		
		return entradas;
		
	}
	
	private Entrada givenEntrada() {
		Entrada entrada = new Entrada();
		entrada.setId(new Random().nextLong());
		return entrada;
	}



	private List<Asiento> givenAsientos(Integer cantidad){
		
		List<Asiento> asientos = new ArrayList<>();
		
		for (int i = 0; i < cantidad; i++) {
			asientos.add(givenAsiento());
			
		}
		
		return asientos;
	}

	private Asiento givenAsiento() {
		Asiento asiento = new Asiento();
		asiento.setId(new Random().nextLong());
		return asiento;
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
	
	public Usuario givenUsuario() {
    	Usuario usuario = new Usuario ();
    	usuario.setId(new Random().nextLong());
 	
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
	
	private Funcion givenFuncion() {
		Funcion funcion = new Funcion();
		funcion.setId(new Random().nextLong());
		
		return funcion;
		
	}
	
	private Funcion givenFuncion(Sala sala,Pelicula pelicula) {
		Funcion funcion = new Funcion();
		funcion.setSala(sala);
		funcion.setPelicula(pelicula);
		funcion.setId(new Random().nextLong());
		
		return funcion;
		
	}
		
	private DatosCine givenDatosCine() {
		DatosCine datosCine = new DatosCine();
		
		datosCine.setCine(1L);
		datosCine.setPelicula(1L);
		return datosCine;
	}
	
	

}
