package ar.edu.unlam.tallerweb1.domain.helper;

import java.util.List;

import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;

public class FuncionEntradas {
	
	private List<Entrada> entradas;
	
	private Funcion funcion;

	public List<Entrada> getEntradas() {
		return entradas;
	}

	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}

	public Funcion getFuncion() {
		return funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

}
