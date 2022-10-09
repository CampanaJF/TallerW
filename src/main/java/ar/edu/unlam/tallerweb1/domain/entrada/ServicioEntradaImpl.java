package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.delivery.DatosEntrada;



@Service("servicioEntrada")
@Transactional
public class ServicioEntradaImpl implements ServicioEntrada {
	
	private RepositorioEntrada repositorioEntrada;
	
	@Autowired
	public ServicioEntradaImpl(RepositorioEntrada repositorioEntrada) {
		this.repositorioEntrada = repositorioEntrada;
	}
	
	// TO DO
	// Agregar logica para multiples entradas
	// Validar capacidad
	// Generar Asiento/s

	@Override
	public void comprarEntrada(DatosEntrada datosEntrada) {
	
		Entrada entrada = new Entrada();
		
		entrada.setFuncion(datosEntrada.getFuncion());
		entrada.setUsuario(datosEntrada.getUsuario());
	
		this.repositorioEntrada.comprarEntrada(entrada);
			
	}

	@Override
	public Entrada getEntrada(Long entrada) {
		
		return this.repositorioEntrada.getEntrada(entrada);
		
	}

	@Override
	public List<Entrada> getUltimaEntradaDeUsuarioList(Long usuario,Long funcion) {
		
		return this.repositorioEntrada.getUltimaEntradaDeUsuarioList(usuario,funcion);
		
	}

	@Override
	public Entrada getUltimaEntradaDeUsuario(Long usuario, Long funcion) {
	
		return this.repositorioEntrada.getUltimaEntradaDeUsuario(usuario,funcion);
	}

}
