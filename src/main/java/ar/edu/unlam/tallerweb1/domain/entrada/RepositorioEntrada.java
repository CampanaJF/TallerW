package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

public interface RepositorioEntrada {

	void comprarEntrada(Entrada entrada);
	
	Entrada getEntrada(Long entrada);
	
	Entrada getUltimaEntradaDeUsuario(Long usuario,Long funcion);

	List<Entrada> getUltimaEntradaDeUsuarioList(Long id, Long id2);
}
