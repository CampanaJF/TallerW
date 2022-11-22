package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.entrada.Entrada;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

public class DatosReserva {
	
	private Entrada entrada;
	
	private Usuario usuario;

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
