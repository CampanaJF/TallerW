package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

import ar.edu.unlam.tallerweb1.delivery.DatosEntrada;

public interface ServicioEntrada {

	Entrada comprarEntrada(DatosEntrada datosEntrada);
	
	Entrada getEntrada(Long entrada);

	List<Entrada> getEntradas(Long uId);
}
