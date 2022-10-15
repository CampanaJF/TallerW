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

		Date date = new Date();
	
	
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 4);
		dt = c.getTime();
		
		System.out.println("HOY: "+ date +" "+"Futuro: " + dt );
		

		  
				
	}
	


	

}