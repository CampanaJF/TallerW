package ar.edu.unlam.tallerweb1.domain.entrada;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("servicioEntrada")
@Transactional
public class ServicioEntradaImpl implements ServicioEntrada {
	
	private RepositorioEntrada repositorioEntrada;
	
	@Autowired
	public ServicioEntradaImpl(RepositorioEntrada repositorioEntrada) {
		this.repositorioEntrada = repositorioEntrada;
	}

	@Override
	public Entrada comprarEntrada(Entrada entrada) {
	
	
		return this.repositorioEntrada.comprarEntrada(entrada);
		
	}

	@Override
	public Entrada getEntrada(Long uId,Long fId) {
		
		return this.repositorioEntrada.getEntrada(uId,fId);
		
	}

	@Override
	public List<Entrada> getEntradas(Long uId) {
		
		return this.repositorioEntrada.getEntradas(uId);
		
	}

}
