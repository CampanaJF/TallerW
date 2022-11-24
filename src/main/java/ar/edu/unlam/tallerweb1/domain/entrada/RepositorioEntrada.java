package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

import ar.edu.unlam.tallerweb1.domain.cine.Asiento;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

public interface RepositorioEntrada {

	void comprarEntrada(Funcion funcion,Usuario usuario,Asiento asiento);
	
	Entrada getEntrada(Long entrada);
	
	Entrada getEntrada(Long funcion,Long asiento);
	
	List<Entrada> getEntradasCompradasDelUsuario(Long id, Long id2);
	
	List<Entrada> getEntradasCompradasDelUsuario(Usuario usuarioLogueado);

	Integer getCantidadAsientosVacios(Long funcion);
	
	Usuario getUsuario(Long Id);
	
	Asiento getAsiento(Long id);

	void cancelarReserva(Long entrada);

	void agregarAPendientes(EntradaPendiente entradaPendiente);

	void actualizarPendiente(EntradaPendiente entradaPendiente);
	
	void comprarPendiente(Entrada entrada);
	
	void eliminarPendiente(EntradaPendiente entradaPendiente);
	
	List<Entrada> getEntradasCanceladas(Long funcion);

	List<EntradaPendiente> getPendientesActivasDelUsuario(Usuario usuario);

	List<EntradaPendiente> getPendientes(Long entrada, Long usuario);
	
	List<EntradaPendiente> getPendientes(Long entrada);

	EntradaPendiente obtenerPendiente(Funcion funcion, Usuario usuario);

	List<EntradaPendiente> getPendientesParaEnviarMail(Long entrada);


}
