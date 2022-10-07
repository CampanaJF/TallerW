package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

public interface RepositorioEntrada {

	void comprarEntrada(Entrada entrada);
	
	Entrada getEntrada(Long entrada);
	
	Entrada getEntrada(Long usuario,Long funcion);

	List<Entrada> getEntradas(Long uId);
}
