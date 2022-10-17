package ar.edu.unlam.tallerweb1.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.cine.Cine;
import ar.edu.unlam.tallerweb1.domain.cine.RepositorioCine;


public class RepositorioCineTest extends SpringTest {

	 @Autowired
	    private RepositorioCine repositorioCine;
	 
	 @Test
	 @Transactional
	 @Rollback
	 public void queSeListenTodosLosCines() {
		 
		 Cine C1 = givenCine("1");
		 Cine C2 = givenCine("2");
		 Cine C3 = givenCine("3");
		 Cine C4 = givenCine("4");
		 
		 session().save(C1);
	     session().save(C2);
	     session().save(C3);
	     session().save(C4);
	     
	     List<Cine> cines = whenSeListanTodosLosCines();
	     
	     thenSeListanTodosLosCines(cines);
	
	 }

	private void thenSeListanTodosLosCines(List<Cine> cines) {
		assertThat(cines.size()).isEqualTo(4);
		assertThat(cines.get(2).getNombreCine()).isEqualTo("3");
		
	}

	private List<Cine> whenSeListanTodosLosCines() {

		return this.repositorioCine.getCines();
	}

	private Cine givenCine(String string) {
		Cine cine = new Cine();
		cine.setId(new Random().nextLong());
		cine.setNombreCine(string);
		return cine;
	}
	 
	 
}
