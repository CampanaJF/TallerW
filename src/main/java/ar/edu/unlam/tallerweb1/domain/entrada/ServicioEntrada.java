package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

import ar.edu.unlam.tallerweb1.domain.cine.Asiento;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;


public interface ServicioEntrada {

void comprar(Funcion funcion,Usuario usuario,List<Long> asientos);
	
	void comprarMultiplesEntradas(Funcion funcion,Usuario usuario,List<Asiento> asiento);
	
	void comprarUnaEntrada(Funcion funcion,Usuario usuario,List<Asiento> asiento);
	
	void validarEntrada(Funcion funcion,Usuario usuario,List<Asiento> asiento);
	
	void validarAsiento(Funcion funcion,List<Asiento> asiento);
	
	void validarIdAsientos(List<Asiento> asiento);
	
	void validarCantidadDeAsientosDisponibles(Funcion funcion,List<Asiento> asiento);
	
	void validarAsientosIngresados(List<Asiento> asiento);
	
	void validarFuncionIngresada(Funcion funcion);
	
	void validarUsuarioIngresado(Usuario usuario);
	
	List<Asiento> getAsientos(List<Long> asientos);
	
	Entrada getEntrada(Long entrada);
	
	List<Entrada> obtenerEntradasVigentes(Long usuario,Long funcion);

	List<Entrada> obtenerEntradasVigentes(Usuario usuarioLogueado);

	void cancelarReserva(Long entrada);

	void agregarAPendientes(Funcion funcion, Usuario usuario);

	void actualizarPendientes(Long entrada);
	
}