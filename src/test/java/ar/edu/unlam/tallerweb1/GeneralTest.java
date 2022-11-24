package ar.edu.unlam.tallerweb1;

import static org.junit.Assert.*;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.domain.historial.Historial;

public class GeneralTest {
	
//	@Test
//	public void WW() {
//		
//		SimpleDateFormat formato = new SimpleDateFormat ("dd-MM-yyyy HH:mm");
//
//		Date date = new Date();
//		
//		try {
//	    	date=formato.parse("18-06-2022 13:00");
//
//	    }
//	    catch (ParseException e){
//	    	System.out.println("Oh nyo");
//	    }
//
//		System.out.println(date.getHours() + " " + date.getMinutes());
//		  
//				
//	}
	
// Usar la fecha de creacion de la etiqueta para determinar cual borrar	
	
	@Test
	public void ArrayHistorialTest() {

		List<Integer> nuevos = new ArrayList<>();
		List<Integer> nuevosNoRep = new ArrayList<>();
		List<Integer> historial = new ArrayList<>();
		List<Integer> borrables = new ArrayList<>();
		List<Integer> repetidos = new ArrayList<>();
		
		for (int i = 1; i < 7; i++) {
			historial.add(i);
			
		}
		
		nuevosNoRep = nuevos;
		borrables = historial;
		
		nuevos.add(6);
		nuevos.add(7);
		nuevos.add(8);
		

		for (int i = 0; i < nuevos.size(); i++) {
			for (int j = 0; j < historial.size(); j++) {
				if(historial.get(j).equals(nuevos.get(i))) 
					repetidos.add(historial.get(j));	
					
			}
			
		}
		
		for (int i = 0; i < repetidos.size(); i++) {
			borrables.remove(repetidos.get(i));
			
		}
		
		nuevosNoRep.removeAll(repetidos);
	
//		for (int i = 0; i < nuevos.size(); i++) {
//			for (int j = 0; j < historial.size(); j++) {
//				if(borrables.contains(historial.get(j))&&!(repetidos.contains(nuevos.get(i)))) {
//					historial.get(j)=nuevos.get(i);
//				}	
//					
//			}
//			
//		}
		// se tiene que hacer un metodo que haga esto para cada uno de los nuevos individualmente, el for los triplicaria
//		for (int i = 0; i < nuevos.size(); i++) {
//			for (int j = 0; j < historial.size(); j++) {
//				if(!(repetidos.contains(nuevos.get(i)))) {
//					historial.get(j)=nuevos.get(i);
//				}	
//					
//			}
//			
//		}

		for (int i = 0; i < historial.size(); i++) {
			System.out.println("Historial: " +historial.get(i));
		}
		
		for (int i = 0; i < nuevos.size(); i++) {
			System.out.println("Nuevos: " + nuevos.get(i));
		}
		
		for (int i = 0; i < repetidos.size(); i++) {
			System.out.println("Repetidos: " + repetidos.get(i));	
		}

		for (int i = 0; i < borrables.size(); i++) {
			System.out.println("Borrables: " +borrables.get(i));
		}
		
		for (int i = 0; i < nuevosNoRep.size(); i++) {
			System.out.println("NuevosNoRepetidos: " +nuevosNoRep.get(i));
			
		}
		
		if(historial.contains(1)) {
			System.out.println("yYYYYYYYY");
		}
		
		if(historial.contains(6)) {
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAA");
		}
		
		
	
	}
	
//	@Test
//	public void YEAAAAAAAAAAAAAA() {
//		
//		Random r = new Random();
//		int low = 0;
//		int high = 6;
//		int result = r.nextInt(high-low) + low;
//		int result1 = r.nextInt(high-low) + low;
//		int result2 = r.nextInt(high-low) + low;
//		int result3 = r.nextInt(high-low) + low;
//		int result4 = r.nextInt(high-low) + low;
//		int result5 = r.nextInt(high-low) + low;
//		int result6 = r.nextInt(high-low) + low;
//		int result7 = r.nextInt(high-low) + low;
//		int result8 = r.nextInt(high-low) + low;
//		int result9 = r.nextInt(high-low) + low;
//		int result10 = r.nextInt(high-low) + low;
//		
//		System.out.println(result + " " + result1 + " " + result2 + " " + result9 + " " +
//		result3 + " " + result4 + " " + result10 + " " + result5 + " " + result6 + " " + result7 + " " + result8 + " ");
//
////		Date date = new Date();
////	
////	
////		Date dt = new Date();
////		Calendar c = Calendar.getInstance(); 
////		c.setTime(dt); 
////		c.add(Calendar.DATE, 4);
////		dt = c.getTime();
////		
////		System.out.println("HOY: "+ date +" "+"Futuro: " + dt );
//		
//
//		  
//				
//	}
	


	

}