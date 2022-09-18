package ar.edu.unlam.tallerweb1.domain.entrada;

import static org.mockito.Mockito.mock;

import ar.edu.unlam.tallerweb1.infrastructure.ServicioEntradaImpl;

public class ServicioEntradaTest {
	
	private RepositorioEntrada repositorioEntrada = mock(RepositorioEntrada.class);
	
	private ServicioEntradaImpl servicioEntrada = new ServicioEntradaImpl(repositorioEntrada);

}
