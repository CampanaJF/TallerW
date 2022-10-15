package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

public interface RepositorioEntrada {

	void comprarEntrada(Entrada entrada);
	
	Entrada getEntrada(Long entrada);
	
	List<Entrada> getEntradasCompradasDelUsuario(Long id, Long id2);
}
