package ar.edu.unlam.tallerweb1.domain.pelicula;

import javax.persistence.*;

@Entity
public class Valoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int estrellas;

    @ManyToOne
    private Pelicula pelicula;

    public Valoracion(int estrellas, Pelicula pelicula){
        this.estrellas=estrellas;
        this.pelicula=pelicula;
    }
    public Valoracion(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
