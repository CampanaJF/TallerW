package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.domain.cine.Asiento;
import ar.edu.unlam.tallerweb1.domain.funcion.Funcion;
import ar.edu.unlam.tallerweb1.domain.helper.FuncionEntradas;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.AsientoSinIdException;
import ar.edu.unlam.tallerweb1.exceptions.DatosEntradaInvalidaException;
import ar.edu.unlam.tallerweb1.exceptions.ErrorDeAsientoException;



@Service("servicioEntrada")
@Transactional
public class ServicioEntradaImpl implements ServicioEntrada {
	
	private RepositorioEntrada repositorioEntrada;

	
	@Autowired
	public ServicioEntradaImpl(RepositorioEntrada repositorioEntrada) {
		this.repositorioEntrada = repositorioEntrada;
	}

	@Override
	public void comprar(Funcion funcion,Usuario usuario,List<Long> asientos) {
		
		List<Asiento> asientosEncontrados = getAsientos(asientos);
		
		validarEntrada(funcion,usuario,asientosEncontrados);
			
		if(asientos.size()>1) 
			comprarMultiplesEntradas(funcion,usuario,asientosEncontrados);	
		else 
			comprarUnaEntrada(funcion,usuario,asientosEncontrados);
		
			
	}
	
	@Override
	public void comprarMultiplesEntradas(Funcion funcion,Usuario usuario,List<Asiento> asientos) {
			
		for (Asiento asiento : asientos) {
			
			comprarEntrada(funcion, usuario, asiento);
		}
		
	}

	@Override
	public void comprarUnaEntrada(Funcion funcion,Usuario usuario,List<Asiento> asiento) {
	
		comprarEntrada(funcion,usuario,asiento.get(0));
		
	}
	
	@Override
	public Entrada obtenerEntrada(Long entrada) {
		
		return this.repositorioEntrada.getEntrada(entrada);
		
	}

	// cambiar a funcionEntradas
	@Override
	public List<Entrada> obtenerEntradasVigentes(Long usuario,Long funcion) {
		
		return obtenerVigentes(obtenerLasEntradasDelUsuarioParaLaFuncion(usuario, funcion));
		
	}

	@Override
	public List<Entrada> obtenerEntradasVigentes(Usuario usuarioLogueado) {
		
		return obtenerVigentes(obtenerTodasLasEntradasDelUsuario(usuarioLogueado));
	}

	@Override
	public void validarEntrada(Funcion funcion,Usuario usuario,List<Asiento> asientos)throws DatosEntradaInvalidaException {
		
		validarUsuarioIngresado(usuario);

		validarFuncionIngresada(funcion);
		
		validarAsiento(funcion,asientos);
		
	}
	
	@Override
	public void validarAsiento(Funcion funcion, List<Asiento> asientos){
		
		validarAsientosIngresados(asientos);
		
		validarIdAsientos(asientos);	
		
		validarCantidadDeAsientosDisponibles(funcion,asientos);
				
	}

	@Override
	public void validarFuncionIngresada(Funcion funcion) {
		
		if(funcion==null) 
			throw new DatosEntradaInvalidaException();
		
	}

	@Override
	public void validarUsuarioIngresado(Usuario usuario) {
		
		if(usuario==null) 
			throw new DatosEntradaInvalidaException();
		
	}
	
	@Override
	public void validarAsientosIngresados(List<Asiento> asientos) throws ErrorDeAsientoException {
		
		if(asientos==null||asientos.size()==0) 
			throw new ErrorDeAsientoException();
		
	}
	
	@Override
	public void validarCantidadDeAsientosDisponibles(Funcion funcion, List<Asiento> asientos) throws ErrorDeAsientoException {
		
		if(this.repositorioEntrada.getCantidadAsientosVacios(funcion.getId())<asientos.size()) 
			throw new ErrorDeAsientoException();	
	}

	@Override
	public void validarIdAsientos(List<Asiento> asientos)throws AsientoSinIdException {
		
		for (Asiento asiento : asientos) {
			if(asiento.getId()==null)
				throw new AsientoSinIdException();
		}
		
	}

	@Override
	public List<Asiento> getAsientos(List<Long> asientos) {
		List<Asiento> asientosSeleccionados = new ArrayList<>();
		
		for (Long asiento : asientos) {
			asientosSeleccionados.add(this.repositorioEntrada.getAsiento(asiento));
		}
		return asientosSeleccionados;
	}
	
	private List<Entrada> obtenerTodasLasEntradasDelUsuario(Usuario usuarioLogueado) {
		return this.repositorioEntrada.getEntradasCompradasDelUsuario(usuarioLogueado);
	}
	
	private List<Entrada> obtenerLasEntradasDelUsuarioParaLaFuncion(Long usuario, Long funcion) {
		return this.repositorioEntrada.getEntradasCompradasDelUsuario(usuario,funcion);
	}
	
	private List<Entrada> obtenerVigentes(List<Entrada> entradasDelUsuario) {
		
		List<Entrada> entradasVigentes = new ArrayList<>();
		
		for (Entrada entrada : entradasDelUsuario) {
			
			if (validarEntradaVigente(entrada)) 
				entradasVigentes.add(entrada);	
		}
		
		return entradasVigentes;
	}
	
	private Boolean validarEntradaVigente(Entrada entrada) {
		Date hoy = new Date();
		
		if (entrada.getFuncion().getFecha().after(hoy)) 
			return true;
		
		return false;
	}
	
	private void comprarEntrada(Funcion funcion, Usuario usuario, Asiento asiento) {
		
		this.repositorioEntrada.comprarEntrada(funcion,usuario,asiento);
	}
	

	@Override
	public void cancelarReserva(Long entrada) {
		liberarEntrada(entrada);
	
		actualizarPendientes(entrada);
	}
	
	@Override
	public void comprarPendiente(Entrada entrada, Usuario usuario) {
		
		Entrada entradaPendiente = obtenerEntrada(entrada.getId());
		
		entradaPendiente.setUsuario(usuario);
		
		comprarPendiente(entradaPendiente);
		
		eliminarPendientes(entradaPendiente,usuario);
	}
	
	@Override
	public void agregarAPendientes(Funcion funcion, Usuario usuario) {
		
		if(validarPendiente(funcion,usuario)) {
		
		EntradaPendiente entradaPendiente = new EntradaPendiente();
		
		entradaPendiente.setFuncion(funcion);
		entradaPendiente.setUsuario(usuario);
		
		this.repositorioEntrada.agregarAPendientes(entradaPendiente);
		
		}
	}

	private Boolean validarPendiente(Funcion funcion, Usuario usuario) {
		
		EntradaPendiente pendientesDelUsuario = obtenerPendiente(funcion,usuario);
		
		if(pendientesDelUsuario==null)
			return true;
		
		return false;
	}

	private EntradaPendiente obtenerPendiente(Funcion funcion, Usuario usuario) {
		return this.repositorioEntrada.obtenerPendiente(funcion,usuario);
	}

	@Override
	public void actualizarPendientes(Long entrada) {
		
		List<EntradaPendiente> entradasPendientes = obtenerPendientes(entrada);
		
		if(entradasPendientes.size()==0)
			throw new ErrorDeAsientoException();
		
		for (EntradaPendiente entradaPendiente : entradasPendientes) {
			
			entradaPendiente.setActiva(true);
			entradaPendiente.setDescripcion("¡Un Asiento se desocupo, compralo ahora!");
			
			notificarPendiente(entradaPendiente);
		}
	}
	
	private void eliminarPendientes(Entrada entradaPendiente, Usuario usuario) {
		
		Integer cantidad = obtenerEntradasCanceladas(entradaPendiente.getFuncion().getId()).size();
		
		eliminarPendienteDelComprador(entradaPendiente,usuario);
		
		if(cantidad==0)
			eliminarPendientesSiNoHayMasPorVender(entradaPendiente);
	
	}
	
	public void eliminarPendienteDelComprador(Entrada entrada,Usuario usuario) {
		
		List<EntradaPendiente> aEliminar = obtenerPendientes(entrada.getId(),usuario.getId());
		
		for (EntradaPendiente entradaPendiente : aEliminar) {
			this.repositorioEntrada.eliminarPendiente(entradaPendiente);
		}
	}
	
	public void eliminarPendientesSiNoHayMasPorVender(Entrada entrada) {
		
		List<EntradaPendiente> aEliminar = obtenerPendientes(entrada.getId());
		
		for (EntradaPendiente entradaPendiente : aEliminar) {
			this.repositorioEntrada.eliminarPendiente(entradaPendiente);
		}
	}

	private void comprarPendiente(Entrada entrada) {
		this.repositorioEntrada.comprarPendiente(entrada);
	}
	
	private void notificarPendiente(EntradaPendiente entradaPendiente) {
		this.repositorioEntrada.actualizarPendiente(entradaPendiente);
	}	
	
	private void liberarEntrada(Long entrada) {
		this.repositorioEntrada.cancelarReserva(entrada);
	}

	private List<EntradaPendiente> obtenerPendientes(Long entrada) {
		return this.repositorioEntrada.getPendientes(entrada);
	}
	
	@Override
	public List<Entrada> obtenerEntradasCanceladas(Long funcion){
		return this.repositorioEntrada.getEntradasCanceladas(funcion);
	}
	
	@Override	
	public List<EntradaPendiente> obtenerPendientesActivasDelUsuario(Usuario usuario) {
		
		return this.repositorioEntrada.getPendientesActivasDelUsuario(usuario);
	}
	
	private List<EntradaPendiente> obtenerPendientes(Long entrada,Long usuario) {
		return this.repositorioEntrada.getPendientes(entrada,usuario);
	}
	
	
	//TODO utilizar un metodo para extraer las entradas de cada funcion
	@SuppressWarnings("unused")
	private List<FuncionEntradas> formatearEntradas(List<Entrada> entradas){
		
		List<FuncionEntradas> funcionEntradas = new ArrayList<>();
		
		for (Entrada entrada : entradas) {
			FuncionEntradas funcionEntrada = new FuncionEntradas();
			
			funcionEntrada.setFuncion(entrada.getFuncion());
			funcionEntrada.setEntradas(entradas);
			
		}
		
		return funcionEntradas;
	}

	

	

	


}