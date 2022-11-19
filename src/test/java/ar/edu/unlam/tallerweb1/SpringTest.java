package ar.edu.unlam.tallerweb1;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.edu.unlam.tallerweb1.domain.clasificacionPelicula.ClasificacionPelicula;
import ar.edu.unlam.tallerweb1.domain.genero.Genero;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;

// Se indica que los test que hereden de esta clase corran con el runner de junit para spring.
@RunWith(SpringJUnit4ClassRunner.class)
// Se indica
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
// Clase base para los test que se pretende que se corran dentro del contexto de spring
public abstract class SpringTest {

    // Tiene inyectado el session factory para que los test que hereden de este tengan acceso al mismo
    @Autowired
    private SessionFactory sessionFactory;

    // Metodo para obtener una sesion de base de datos
    protected Session session() {
        return this.sessionFactory.getCurrentSession();
    }
    protected Genero crearGenero(String genero) {
		Genero genero1 = new Genero();
		genero1.setDescripcion(genero);
		session().save(genero1);
		return genero1;
		
	}
    protected ClasificacionPelicula crearClasificacion(String clasificacionPelicula) {
		ClasificacionPelicula clasificacion = new ClasificacionPelicula();	
		clasificacion.setDescripcion(clasificacionPelicula);
		session().save(clasificacion);
		return clasificacion;
	}

	
	protected Pelicula crearPelicula(String titulo,Date fechaEstreno,String director,Integer calificacion) {
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo(titulo);
		pelicula.setFechaEstreno(fechaEstreno);
		pelicula.setDirector(director);
		pelicula.setCalificacion(calificacion);
		pelicula.setEnCartelera(0);
		session().save(pelicula);
		return pelicula;
	}
}
