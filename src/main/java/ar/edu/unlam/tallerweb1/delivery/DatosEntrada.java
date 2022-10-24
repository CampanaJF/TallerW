package ar.edu.unlam.tallerweb1.delivery;

import java.util.List;

import ar.edu.unlam.tallerweb1.domain.cine.Asiento;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

public class DatosEntrada {
	private Funcion funcion;
    private Usuario usuario;
    private List<Asiento> asientos;

    
	public Funcion getFuncion() {
		return funcion;
	}
	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Asiento> getAsientos() {
		return asientos;
	}
	public void setAsientos(List<Asiento> asientos) {
		this.asientos = asientos;
	}

    
    
}
