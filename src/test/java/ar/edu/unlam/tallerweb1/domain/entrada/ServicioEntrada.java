package ar.edu.unlam.tallerweb1.domain.entrada;

public interface ServicioEntrada {

	void comprarEntrada(Long Usuario,Long Funcion);
	
	Entrada getEntrada(Long id);
}
