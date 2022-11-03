package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.domain.cine.Asiento;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.AsientoSinIdException;
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
			
		if(asientos.size()>1) 
			comprarMultiplesEntradas(funcion,usuario,asientos);	
		else 
			comprarUnaEntrada(funcion,usuario,asientos);
		
			
	}
	
	@Override
	public void comprarMultiplesEntradas(Funcion funcion,Usuario usuario,List<Asiento> asientos) {
			
		for (Asiento asiento : asientos) {
			
			comprarEntrada(funcion, usuario, asiento);
		}
		
	}

	@Override
	public void comprarUnaEntrada(Funcion funcion,Usuario usuario,List<Asiento> asiento) {
	
		comprarEntrada(funcion,usuario,asiento.get(0));
		
	}
	
	@Override
	public Entrada getEntrada(Long entrada) {
		
		return this.repositorioEntrada.getEntrada(entrada);
		
	}

	@Override
	public List<Entrada> getEntradasCompradasDelUsuario(Long usuario,Long funcion) {
		
		return this.repositorioEntrada.getEntradasCompradasDelUsuario(usuario,funcion);
		
	}
	

	@Override
	public void validarEntrada(Funcion funcion,Usuario usuario,List<Asiento> asientos)throws DatosEntradaInvalidaException {
		
		if(usuario==null) 
			throw new DatosEntradaInvalidaException();
		
		
		if(funcion==null) 
			throw new DatosEntradaInvalidaException();
		
		
		validarAsiento(funcion,asientos);
		
	}
	
	private void comprarEntrada(Funcion funcion, Usuario usuario, Asiento asiento) {
		this.repositorioEntrada.comprarEntrada(funcion,usuario,asiento);
	}
	
	@Override
	public void validarAsiento(Funcion funcion, List<Asiento> asientos) throws ErrorDeAsientoException, AsientoSinIdException{
		
		if(asientos==null||asientos.size()==0) 
			throw new ErrorDeAsientoException();
		
		
		if(this.repositorioEntrada.getCantidadAsientosVacios(funcion.getId())<asientos.size()) 
			throw new ErrorDeAsientoException();
		
		
		for (Asiento asiento : asientos) {
			if(asiento.getId()==null)
				throw new AsientoSinIdException();
		}
		
		
	}

	

	



}
