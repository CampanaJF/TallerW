package ar.edu.unlam.tallerweb1.domain.pelicula;

import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;

import javax.persistence.*;

@Entity
public class Valoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int puntos;
    private String comentario;

    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Pelicula pelicula;

    public Valoracion(){

    }

    public Valoracion(int puntos, Pelicula pelicula, String comentario,Usuario usuario) {
        this.puntos = puntos;
        this.pelicula = pelicula;
        this.comentario=comentario;
        this.usuario=usuario;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
