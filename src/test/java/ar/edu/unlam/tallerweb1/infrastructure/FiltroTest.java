package ar.edu.unlam.tallerweb1.infrastructure;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.clasificacionPelicula.ClasificacionPelicula;
import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.helper.Filtro;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.pelicula.RepositorioPelicula;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FiltroTest extends SpringTest{
	
	@Autowired
	private RepositorioPelicula repositorioPelicula;
	
	
	@Test
    @Transactional
    @Rollback
public void verificarFiltroPorGenero(){
	givenQueHayPeliculasPorGenero();
	List<Pelicula>peliculasObtenidasPorGeneroAccion=whenBuscoPeliculasPorGeneroAccion();
	List<Pelicula>peliculasObtenidasPorGeneroTerror=whenBuscoPeliculasPorGeneroTerror();
	thenObtengoPeliculasPorGeneroAccion(peliculasObtenidasPorGeneroAccion);
	thenObtengoPeliculasPorGeneroTerror(peliculasObtenidasPorGeneroTerror);
	
}

	@Test
    @Transactional
    @Rollback
public void verificaFiltroPorClasificacion(){
	givenQueHayPeliculasPorClasificacion();
	List<Pelicula>peliculasObtenidasPorClasificacionATP=whenBuscoPeliculasPorClasificacionATP();
	List<Pelicula>peliculasObtenidasPorClasificacionMas16=whenBuscoPeliculasPorClasificacionMas16();
	thenObtengoPeliculasPorClasificacionATP(peliculasObtenidasPorClasificacionATP);
	thenObtengoPeliculasPorClasificacionMas16(peliculasObtenidasPorClasificacionMas16);

}
	

	@Test
    @Transactional
    @Rollback
public void verificaFiltroCombinado(){
	givenQueHayPeliculas();
	List<Pelicula>peliculasObtenidasPorClasificacionYGenero=whenBuscoPeliculasPorClasificacionYGenero();
	thenObtengoPeliculasPorClasificacionYGenero(peliculasObtenidasPorClasificacionYGenero);
	
	
}
	
	@Test
	 @Transactional
	 @Rollback
	public void verificarQueTraigaPeliculasOrdenadasPorDirector(){
		givenHayPeliculasPorDirector();
		List<Pelicula>peliculasObtenidasPorDirector=whenBuscoPeliculasPorDirector();
		thenObtengoPeliculasPorDirector(peliculasObtenidasPorDirector);
	}
	
	
	@Test
	 @Transactional
	 @Rollback
	public void verificarQueTraigaPeliculasOrdenadasPorTitulo(){
		givenHayPeliculasPorTitulo();
		List<Pelicula>peliculasObtenidasPorTitulo=whenBuscoPeliculasPorTitulo();
		thenObtengoPeliculasPorTitulo(peliculasObtenidasPorTitulo);
	}
	
	
	@Test
	 @Transactional
	 @Rollback
	public void verificarQueTraigaPeliculasOrdenadasPorCalificacion(){
		givenHayPeliculasPorCalificacion();
		List<Pelicula>peliculasObtenidasPorCalificacion=whenBuscoPeliculasPorCalificacion();
		thenObtengoPeliculasPorCalificacion(peliculasObtenidasPorCalificacion);
	}
	
	
	
private void thenObtengoPeliculasPorCalificacion(List<Pelicula> peliculasObtenidasPorCalificacion) {
	assertEquals(4,peliculasObtenidasPorCalificacion.size());
	assertEquals(5,peliculasObtenidasPorCalificacion.get(0).getCalificacion(),0.1f);
		
	}

private List<Pelicula> whenBuscoPeliculasPorCalificacion() {
	Filtro filtro=new Filtro(null,null,"Calificacion");
	List<Pelicula>peliculasObtenidasPorCalificacion=repositorioPelicula.getPeliculasFiltro(filtro);
	return peliculasObtenidasPorCalificacion;
	}

private void givenHayPeliculasPorCalificacion() {
	Pelicula p1=new Pelicula();
	Pelicula p2=new Pelicula();
	Pelicula p3=new Pelicula();
	Pelicula p4=new Pelicula();
	
	p1.setFechaEstreno(new Date("2022/10/10"));
	p2.setFechaEstreno(new Date("2022/10/10"));
	p3.setFechaEstreno(new Date("2022/10/10"));
	p4.setFechaEstreno(new Date("2022/10/10"));
	
	p1.setCalificacion(5);
	p2.setCalificacion(2);
	p3.setCalificacion(1);
	p4.setCalificacion(4);
	 
	session().save(p1);
	session().save(p2);
	session().save(p3);
	session().save(p4);
		
	}

private void thenObtengoPeliculasPorTitulo(List<Pelicula> peliculasObtenidasPorTitulo) {
	assertEquals(4,peliculasObtenidasPorTitulo.size());
	assertEquals("30 noches con mi ex",peliculasObtenidasPorTitulo.get(0).getTitulo());
		
	}

private List<Pelicula> whenBuscoPeliculasPorTitulo() {
	Filtro filtro=new Filtro(null,null,"Titulo");
	List<Pelicula>peliculasObtenidasPorTitulo=repositorioPelicula.getPeliculasFiltro(filtro);
	return peliculasObtenidasPorTitulo;
	}

private void givenHayPeliculasPorTitulo() {
	Pelicula p1=new Pelicula();
	Pelicula p2=new Pelicula();
	Pelicula p3=new Pelicula();
	Pelicula p4=new Pelicula();
	
	p1.setFechaEstreno(new Date("2022/10/10"));
	p2.setFechaEstreno(new Date("2022/10/10"));
	p3.setFechaEstreno(new Date("2022/10/10"));
	p4.setFechaEstreno(new Date("2022/10/10"));
	
	p1.setTitulo("Bienvenidos al infierno");
	p2.setTitulo("30 noches con mi ex");
	p3.setTitulo("El paraiso");
	p4.setTitulo("Nop");
		
	session().save(p1);
	session().save(p2);
	session().save(p3);
	session().save(p4);
	}

private void thenObtengoPeliculasPorDirector(List<Pelicula> peliculasObtenidasPorDirector) {
	assertEquals(4,peliculasObtenidasPorDirector.size());
	assertEquals("Adrian Suar",peliculasObtenidasPorDirector.get(0).getDirector());
		
	}

private List<Pelicula> whenBuscoPeliculasPorDirector() {
	Filtro filtro=new Filtro(null,null,"Director");
	List<Pelicula>peliculasObtenidasPorDirector=repositorioPelicula.getPeliculasFiltro(filtro);
	return peliculasObtenidasPorDirector;
	}

private void givenHayPeliculasPorDirector() {
	Pelicula p1=new Pelicula();
	Pelicula p2=new Pelicula();
	Pelicula p3=new Pelicula();
	Pelicula p4=new Pelicula();
	
	p1.setFechaEstreno(new Date("2022/10/10"));
	p2.setFechaEstreno(new Date("2022/10/10"));
	p3.setFechaEstreno(new Date("2022/10/10"));
	p4.setFechaEstreno(new Date("2022/10/10"));
	
	p1.setDirector("Adrian Suar");
	p2.setDirector("Lucas Combina");
	p3.setDirector("Julio Chavez");
	p4.setDirector("Tetsuro Kodama");
			
	session().save(p1);
	session().save(p2);
	session().save(p3);
	session().save(p4);
	
}

private void givenQueHayPeliculas() {
	Genero genero=new Genero();
	Genero genero1=new Genero();
	genero.setDescripcion("Accion");
	genero1.setDescripcion("Terror");
	
	session().save(genero);
	session().save(genero1);

	ClasificacionPelicula clasif=new ClasificacionPelicula();
	ClasificacionPelicula clasif2=new ClasificacionPelicula();
	
	clasif.setDescripcion("ATP");
	clasif2.setDescripcion("+16");
	session().save(clasif);
	session().save(clasif2);
	
	
	Pelicula pelicula=new Pelicula();
	Pelicula pelicula1=new Pelicula();
	Pelicula pelicula2=new Pelicula();
	

	pelicula.setTitulo("Jack en la caja maldita 2");
	pelicula1.setTitulo("Dragon ball super");
	pelicula2.setTitulo("Thor");
	
	pelicula.setFechaEstreno(new Date("2022/10/10"));
	pelicula1.setFechaEstreno(new Date("2022/10/10"));
	pelicula2.setFechaEstreno(new Date("2022/10/10"));
	
	pelicula.setGenero(genero); //Genero Accion
	pelicula1.setGenero(genero1);//Genero Terror
	pelicula2.setGenero(genero);//Genero Accion
	
	pelicula.setClasificacionPelicula(clasif);
	pelicula1.setClasificacionPelicula(clasif2);
	pelicula2.setClasificacionPelicula(clasif2); //+16
	
	session().save(pelicula1);
	session().save(pelicula);
	session().save(pelicula2);
	

	}

private List<Pelicula> whenBuscoPeliculasPorClasificacionYGenero() {
	Filtro filtro=new Filtro(1L,2L,null);
	List<Pelicula>peliculasObtenidasPorClasificacionYGenero=repositorioPelicula.getPeliculasFiltro(filtro);
	return peliculasObtenidasPorClasificacionYGenero;
	}

private void thenObtengoPeliculasPorClasificacionYGenero(List<Pelicula> peliculasObtenidasPorClasificacionYGenero) {
	assertEquals(1,peliculasObtenidasPorClasificacionYGenero.size());
	assertEquals("Thor",peliculasObtenidasPorClasificacionYGenero.get(0).getTitulo());
		
	}

private void thenObtengoPeliculasPorClasificacionMas16(List<Pelicula> peliculasObtenidasPorClasificacionMas16) {
	assertEquals(1,peliculasObtenidasPorClasificacionMas16.size());
		
	}

private void thenObtengoPeliculasPorClasificacionATP(List<Pelicula> peliculasObtenidasPorClasificacionATP) {
	assertEquals(2,peliculasObtenidasPorClasificacionATP.size());
		
	}

private List<Pelicula> whenBuscoPeliculasPorClasificacionMas16() {
	Filtro filtro=new Filtro(null,4L,null);
	List<Pelicula>peliculasObtenidasPorClasificacionMas16=repositorioPelicula.getPeliculasFiltro(filtro);
	return peliculasObtenidasPorClasificacionMas16;
		
	}

private List<Pelicula> whenBuscoPeliculasPorClasificacionATP() {
	Filtro filtro=new Filtro(null,3L,null);
	List<Pelicula>peliculasObtenidasPorClasificacionATP=repositorioPelicula.getPeliculasFiltro(filtro);
	return peliculasObtenidasPorClasificacionATP;
		
	}

private void thenObtengoPeliculasPorGeneroTerror(List<Pelicula> peliculasObtenidasPorGeneroTerror) {
		// TODO Auto-generated method stub
	assertEquals(2,peliculasObtenidasPorGeneroTerror.size());
	}


private void thenObtengoPeliculasPorGeneroAccion(List<Pelicula>peliculasObtenidasPorGeneroAccion) {
	
	assertEquals(1,peliculasObtenidasPorGeneroAccion.size());
	
}

private List<Pelicula> whenBuscoPeliculasPorGeneroAccion() {
	Filtro filtro=new Filtro(3L,null,null);
	List<Pelicula>peliculasObtenidasPorGenero=repositorioPelicula.getPeliculasFiltro(filtro);
	return peliculasObtenidasPorGenero;
}

private List<Pelicula> whenBuscoPeliculasPorGeneroTerror() {
	Filtro filtro=new Filtro(4L,null,null);
	List<Pelicula>peliculasObtenidasPorGenero=repositorioPelicula.getPeliculasFiltro(filtro);
	return peliculasObtenidasPorGenero;
	}

private void givenQueHayPeliculasPorGenero() {
	Genero genero=new Genero();
	Genero genero1=new Genero();
	genero.setDescripcion("Accion");
	genero1.setDescripcion("Terror");
	
	session().save(genero);
	session().save(genero1);

	Pelicula pelicula=new Pelicula();
	Pelicula pelicula1=new Pelicula();
	Pelicula pelicula2=new Pelicula();
	

	pelicula.setTitulo("Jack en la caja maldita 2");
	pelicula1.setTitulo("Dragon ball super");
	pelicula2.setTitulo("Thor");
	
	pelicula.setGenero(genero);
	pelicula1.setGenero(genero1);
	pelicula2.setGenero(genero1);
	
	pelicula.setFechaEstreno(new Date("2022/10/10"));
	pelicula1.setFechaEstreno(new Date("2022/10/10"));
	pelicula2.setFechaEstreno(new Date("2022/10/10"));


	session().save(pelicula1);
	session().save(pelicula);
	session().save(pelicula2);
	
}

private void givenQueHayPeliculasPorClasificacion() {
	
	ClasificacionPelicula clasif=new ClasificacionPelicula();
	ClasificacionPelicula clasif2=new ClasificacionPelicula();
	
	clasif.setDescripcion("ATP");
	clasif2.setDescripcion("+16");
	session().save(clasif);
	session().save(clasif2);
	
	
	Pelicula pelicula=new Pelicula();
	Pelicula pelicula1=new Pelicula();
	Pelicula pelicula2=new Pelicula();
	

	pelicula.setTitulo("Jack en la caja maldita 2");
	pelicula1.setTitulo("Dragon ball super");
	pelicula2.setTitulo("Thor");
	
	pelicula.setFechaEstreno(new Date("2022/10/10"));
	pelicula1.setFechaEstreno(new Date("2022/10/10"));
	pelicula2.setFechaEstreno(new Date("2022/10/10"));
	
	pelicula.setClasificacionPelicula(clasif);
	pelicula1.setClasificacionPelicula(clasif2);
	pelicula2.setClasificacionPelicula(clasif);
	
	session().save(pelicula1);
	session().save(pelicula);
	session().save(pelicula2);
	
}


}
