package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

import ar.edu.unlam.tallerweb1.delivery.DatosEntrada;

public interface ServicioEntrada {

	void comprarEntrada(DatosEntrada datosEntrada);
	
	Entrada getEntrada(Long entrada);
	
	Entrada getUltimaEntradaDeUsuario(Long usuario,Long funcion);

	List<Entrada> getUltimaEntradaDeUsuarioList(Long uId,Long funcion);
}
