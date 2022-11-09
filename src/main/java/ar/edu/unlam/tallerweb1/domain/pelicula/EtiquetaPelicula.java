package ar.edu.unlam.tallerweb1.domain.pelicula;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class EtiquetaPelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Pelicula pelicula;
	
	@ManyToOne
	private Etiqueta etiqueta;

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

	public Etiqueta getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(Etiqueta etiqueta) {
		this.etiqueta = etiqueta;
	}





}
