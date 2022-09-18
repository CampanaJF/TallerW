package ar.edu.unlam.tallerweb1.infrastructure;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.entrada.RepositorioEntrada;
import ar.edu.unlam.tallerweb1.domain.entrada.ServicioEntrada;

@Service("servicioEntrada")
@Transactional
public class ServicioEntradaImpl implements ServicioEntrada {
	
	private RepositorioEntrada repositorioEntrada;
	
	@Autowired
	public ServicioEntradaImpl(RepositorioEntrada repositorioEntrada) {
		this.repositorioEntrada = repositorioEntrada;
	}

	@Override
	public void comprarEntrada(Long idUsuario, Long idFuncion) {
		Entrada nueva = new Entrada();
		
		//nueva.setFuncion(funcion);
		//nueva.setUsuario(usuario);
		//nueva.setPelicula(funcion.getPelicula().getTitulo());
		//nueva.setSala(funcion.getSala().getNombre());
		//nueva.setAsiento();
		
		this.repositorioEntrada.comprarEntrada(nueva);
		
	}

	@Override
	public Entrada getEntrada(Long id) {
		
		return this.repositorioEntrada.getEntrada(id);
		
	}

}
