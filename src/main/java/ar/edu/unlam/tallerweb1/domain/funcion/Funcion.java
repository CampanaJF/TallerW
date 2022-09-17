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
	
	@Column(columnDefinition = "boolean default false")
	private Boolean subtitulos;
	
	@Enumerated(EnumType.STRING)
	private Lenguaje lenguaje;
	
	// horario tbd private Date fecha;

}
