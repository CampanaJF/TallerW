package ar.edu.unlam.tallerweb1.domain.entrada;

public interface RepositorioEntrada {

	void comprarEntrada(Entrada entrada);
	
	Entrada getEntrada(Long id);
}
