package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.domain.cine.Asiento;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.DatosEntradaInvalidaException;
import ar.edu.unlam.tallerweb1.exceptions.ErrorDeAsientoException;



@Service("servicioEntrada")
@Transactional
public class ServicioEntradaImpl implements ServicioEntrada {
	
	private RepositorioEntrada repositorioEntrada;

	
	@Autowired
	public ServicioEntradaImpl(RepositorioEntrada repositorioEntrada) {
		this.repositorioEntrada = repositorioEntrada;
	}

	@Override
	public void comprar(Funcion funcion,Usuario usuario,List<Asiento> asientos) {
		
		validarEntrada(funcion,usuario,asientos);
			
		if(asientos.size()>1) {
			comprarMultiplesEntradas(funcion,usuario,asientos);
		}
		else {
			comprarUnaEntrada(funcion,usuario,asientos.get(0));
		}
			
	}
	
	@Override
	public void comprarMultiplesEntradas(Funcion funcion,Usuario usuario,List<Asiento> asientos) {
			
		for (int i = 0; i < asientos.size(); i++) {
			
			comprarUnaEntrada(funcion,usuario,asientos.get(i));
			
		}
		
	}

	@Override
	public void comprarUnaEntrada(Funcion funcion,Usuario usuario,Asiento asiento) {
	
		this.repositorioEntrada.comprarEntrada(funcion,usuario,asiento);
		
	}
	
	@Override
	public Entrada getEntrada(Long entrada) {
		
		return this.repositorioEntrada.getEntrada(entrada);
		
	}

	@Override
	public List<Entrada> getEntradasCompradasDelUsuario(Long usuario,Long funcion) {
		
		return this.repositorioEntrada.getEntradasCompradasDelUsuario(usuario,funcion);
		
	}
	
	//Buscar el usuario en el repo,  cual repo?
	//Diversidad de excepciones
	@Override
	public void validarEntrada(Funcion funcion,Usuario usuario,List<Asiento> asientos)throws DatosEntradaInvalidaException {
		
		if(usuario==null) {
			throw new DatosEntradaInvalidaException();
		}
		
		if(funcion==null) {
			throw new DatosEntradaInvalidaException();
		}
		
		validarAsiento(funcion,asientos);
		
	}
	
	@Override
	public void validarAsiento(Funcion funcion, List<Asiento> asientos) throws ErrorDeAsientoException{
		
		if(asientos.size()==0||asientos==null) {
			throw new ErrorDeAsientoException();
		}
		
		if(this.repositorioEntrada.getCantidadAsientosOcupados(funcion.getId())<asientos.size()) {
			throw new ErrorDeAsientoException();
		}
		
	}

	

	



}
