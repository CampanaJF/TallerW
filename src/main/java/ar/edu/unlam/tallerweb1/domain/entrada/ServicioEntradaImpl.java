package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
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
	public void comprar(Funcion funcion,Usuario usuario,Integer cantidad) {
		
		validarEntrada(funcion,usuario,cantidad);
			
		if(cantidad>1L) {
			comprarMultiplesEntradas(funcion,usuario,cantidad);
		}
		else {
			comprarUnaEntrada(funcion,usuario);
		}
			
	}
	
	@Override
	public void comprarMultiplesEntradas(Funcion funcion,Usuario usuario,Integer cantidad) {
			
		while(cantidad>0) {
			
			comprarUnaEntrada(funcion,usuario);
			cantidad--;
		}
		
	}

	@Override
	public void comprarUnaEntrada(Funcion funcion,Usuario usuario) {
		
		Entrada entrada = new Entrada();
		
		entrada.setFuncion(funcion);
		entrada.setUsuario(usuario);
	
		this.repositorioEntrada.comprarEntrada(entrada);
		
	}
	
	@Override
	public void validarEntrada(Funcion funcion,Usuario usuario,Integer cantidad) throws DatosEntradaInvalidaException {
		
		if(cantidad<=0L||cantidad==null) {
			throw new DatosEntradaInvalidaException();
		}
		
		if(funcion==null) {
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
