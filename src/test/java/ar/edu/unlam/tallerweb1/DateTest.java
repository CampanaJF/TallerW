package ar.edu.unlam.tallerweb1;

import static org.junit.Assert.*;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class DateTest {
	
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
	
	@Test
	public void YEAAAAAAAAAAAAAA() {
		
		Random r = new Random();
		int low = 0;
		int high = 6;
		int result = r.nextInt(high-low) + low;
		int result1 = r.nextInt(high-low) + low;
		int result2 = r.nextInt(high-low) + low;
		int result3 = r.nextInt(high-low) + low;
		int result4 = r.nextInt(high-low) + low;
		int result5 = r.nextInt(high-low) + low;
		int result6 = r.nextInt(high-low) + low;
		int result7 = r.nextInt(high-low) + low;
		int result8 = r.nextInt(high-low) + low;
		int result9 = r.nextInt(high-low) + low;
		int result10 = r.nextInt(high-low) + low;
		
		System.out.println(result + " " + result1 + " " + result2 + " " + result9 + " " +
		result3 + " " + result4 + " " + result10 + " " + result5 + " " + result6 + " " + result7 + " " + result8 + " ");

//		Date date = new Date();
//	
//	
//		Date dt = new Date();
//		Calendar c = Calendar.getInstance(); 
//		c.setTime(dt); 
//		c.add(Calendar.DATE, 4);
//		dt = c.getTime();
//		
//		System.out.println("HOY: "+ date +" "+"Futuro: " + dt );
		

		  
				
	}
	


	

}