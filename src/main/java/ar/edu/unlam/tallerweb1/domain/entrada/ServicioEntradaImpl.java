package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.delivery.DatosEntrada;



@Service("servicioEntrada")
@Transactional
public class ServicioEntradaImpl implements ServicioEntrada {
	
	private RepositorioEntrada repositorioEntrada;
	
	@Autowired
	public ServicioEntradaImpl(RepositorioEntrada repositorioEntrada) {
		this.repositorioEntrada = repositorioEntrada;
	}

	@Override
	public Entrada comprarEntrada(DatosEntrada datosEntrada) {
	
		Entrada entrada = new Entrada();
		
		entrada.setFuncion(datosEntrada.getFuncion());
		entrada.setUsuario(datosEntrada.getUsuario());
	
		this.repositorioEntrada.comprarEntrada(entrada);
		
		return entrada;
		
	}

	@Override
	public Entrada getEntrada(Long entrada) {
		
		return this.repositorioEntrada.getEntrada(entrada);
		
	}

	@Override
	public List<Entrada> getEntradas(Long uId) {
		
		return this.repositorioEntrada.getEntradas(uId);
		
	}

}
