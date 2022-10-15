package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.delivery.DatosEntrada;
import ar.edu.unlam.tallerweb1.exceptions.DatosEntradaInvalidaException;



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
	// Debe verificar la cantidad de asientos disponibles para esa funcion antes de comprar las entradas, si no hay suficientes
	// no las compra ninguna

	@Override
	public void comprar(DatosEntrada datosEntrada) {
		
		validarEntrada(datosEntrada);
		
		Long cantidadDeEntradas = datosEntrada.getCantidad();
		
		if(cantidadDeEntradas>1L) {
			comprarMultiplesEntradas(datosEntrada);
		}
		else {
			comprarUnaEntrada(datosEntrada);
		}
			
	}
	
	@Override
	public void comprarMultiplesEntradas(DatosEntrada datosEntrada) {
		
		Long cantidadDeEntradas = datosEntrada.getCantidad();
			
		while(cantidadDeEntradas>0) {
			
			comprarUnaEntrada(datosEntrada);
			cantidadDeEntradas--;
		}
		
	}

	@Override
	public void comprarUnaEntrada(DatosEntrada datosEntrada) {
		
		Entrada entrada = new Entrada();
		
		entrada.setFuncion(datosEntrada.getFuncion());
		entrada.setUsuario(datosEntrada.getUsuario());
	
		this.repositorioEntrada.comprarEntrada(entrada);
		
	}
	
	@Override
	public void validarEntrada(DatosEntrada datosEntrada) throws DatosEntradaInvalidaException {
		
		if(datosEntrada.getCantidad()<=0L||datosEntrada.getCantidad()==null) {
			throw new DatosEntradaInvalidaException();
		}
		
		if(datosEntrada.getFuncion()==null) {
			throw new DatosEntradaInvalidaException();
		}
		
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

	@Override
	public Long cantidadDeAsientosDisponiblesDeLaFuncion(Long funcion) {
		
		return null;
	}

	

	

}
