package ar.edu.unlam.tallerweb1.domain.pelicula;

import javax.persistence.*;

@Entity
public class Valoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int puntos;

    @ManyToOne
    private Pelicula pelicula;


    public Valoracion(){

    }

    public Valoracion(int puntos, Pelicula pelicula) {
        this.puntos = puntos;
        this.pelicula = pelicula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
