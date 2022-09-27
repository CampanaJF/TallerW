package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

public interface RepositorioEntrada {

	Entrada comprarEntrada(Entrada entrada);
	
	Entrada getEntrada(Long entrada);

	List<Entrada> getEntradas(Long uId);
}
