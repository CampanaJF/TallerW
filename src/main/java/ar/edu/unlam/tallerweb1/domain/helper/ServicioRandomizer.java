package ar.edu.unlam.tallerweb1.domain.helper;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service("servicioRandomizer")
public class ServicioRandomizer {

	public int obtenerIndice(Integer indiceMax) {

		Random r = new Random();
		int low = 0;
		int high = indiceMax;
		int resultado = r.nextInt(high - low);

		return resultado;
	}

	public int obtenerIndice(Integer indiceMax, Integer indiceAnterior) {

		Integer resultado = obtenerIndice(indiceMax);

		while (resultado == indiceAnterior) 
			resultado = obtenerIndice(indiceMax);
		

		return resultado;
	}

}
