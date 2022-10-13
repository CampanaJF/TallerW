package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

import ar.edu.unlam.tallerweb1.delivery.DatosEntrada;

public interface ServicioEntrada {

	void comprar(DatosEntrada datosEntrada);
	
	void comprarMultiplesEntradas(DatosEntrada datosEntrada);
	
	void comprarUnaEntrada(DatosEntrada datosEntrada);
	
	void validarEntrada(DatosEntrada datosEntrada);
	
	Entrada getEntrada(Long entrada);
	
	Entrada getUltimaEntradaDeUsuario(Long usuario,Long funcion);

	List<Entrada> getUltimaEntradaDeUsuarioList(Long usuario,Long funcion);
	
	Long cantidadDeAsientosDisponiblesDeLaFuncion(Long funcion);
}
