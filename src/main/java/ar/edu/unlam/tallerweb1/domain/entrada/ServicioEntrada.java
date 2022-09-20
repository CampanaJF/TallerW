package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

public interface ServicioEntrada {

	void comprarEntrada(Entrada entrada);
	
	Entrada getEntrada(Long uId,Long fId);

	List<Entrada> getEntradas(Long uId);
}
