package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

import ar.edu.unlam.tallerweb1.domain.cine.Asiento;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;


public interface ServicioEntrada {

	void comprar(Funcion funcion,Usuario usuario,List<Asiento> asiento);
	
	void comprarMultiplesEntradas(Funcion funcion,Usuario usuario,List<Asiento> asiento);
	
	void comprarUnaEntrada(Funcion funcion,Usuario usuario,Asiento asiento);
	
	void validarEntrada(Funcion funcion,Usuario usuario,List<Asiento> asiento);
	
	void validarAsiento(Funcion funcion,List<Asiento> asiento);
	
	Entrada getEntrada(Long entrada);
	
	List<Entrada> getEntradasCompradasDelUsuario(Long usuario,Long funcion);
	
}