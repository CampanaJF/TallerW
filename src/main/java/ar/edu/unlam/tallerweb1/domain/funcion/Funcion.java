package ar.edu.unlam.tallerweb1.domain.funcion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.sala.Sala;

@Entity
public class Funcion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Pelicula pelicula;
	
	@ManyToOne
	private Sala sala;
	
	@ManyToOne
	private Formato formato;
	
	private Integer horario;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean subtitulos;
	
	@Enumerated(EnumType.STRING)
	private Lenguaje lenguaje;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	public Formato getFormato() {
		return formato;
	}

	public void setFormato(Formato formato) {
		this.formato = formato;
	}

	public Integer getHorario() {
		return horario;
	}

	public void setHorario(Integer horario) {
		this.horario = horario;
	}

	public Boolean getSubtitulos() {
		return subtitulos;
	}

	public void setSubtitulos(Boolean subtitulos) {
		this.subtitulos = subtitulos;
	}

	public Lenguaje getLenguaje() {
		return lenguaje;
	}

	public void setLenguaje(Lenguaje lenguaje) {
		this.lenguaje = lenguaje;
	}
	
	// horario tbd private Date fecha;
	
	

}
