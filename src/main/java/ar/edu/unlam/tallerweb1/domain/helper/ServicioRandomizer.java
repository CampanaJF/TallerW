package ar.edu.unlam.tallerweb1.domain.helper;

import java.util.Random;

public class ServicioRandomizer {
	
	public int obtenerIndice(Integer indiceMax) {
		
		Random r = new Random();
		int low = 0;
		int high = indiceMax;
		int resultado = r.nextInt(high-low);
		
		return resultado;
	}

	
	public int obtenerIndice(Integer indiceMax,Integer indice) {
	
	Integer resultado = obtenerIndice(indiceMax);
	
	while(resultado==indice) {
		resultado = obtenerIndice(indiceMax);
	}
		
	return resultado;
	}


}
