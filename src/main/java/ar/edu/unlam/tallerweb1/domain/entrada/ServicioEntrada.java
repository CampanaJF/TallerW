package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;


public interface ServicioEntrada {

	void comprar(Funcion funcion,Usuario usuario,Integer cantidad);
	
	void comprarMultiplesEntradas(Funcion funcion,Usuario usuario,Integer cantidad);
	
	void comprarUnaEntrada(Funcion funcion,Usuario usuario);
	
	void validarEntrada(Funcion funcion,Usuario usuario,Integer cantidad);
	
	Entrada getEntrada(Long entrada);
	
	List<Entrada> getEntradasCompradasDelUsuario(Long usuario,Long funcion);
	
	Long cantidadDeAsientosDisponiblesDeLaFuncion(Long funcion);
}
