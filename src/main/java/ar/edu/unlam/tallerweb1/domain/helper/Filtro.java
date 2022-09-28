package ar.edu.unlam.tallerweb1.domain.helper;

public class Filtro {
private Long genero;
private Long clasificacion;
private String orden;
	
	public Filtro(Long genero, Long clasificacion, String orden) {
		this.genero=genero;
		this.clasificacion=clasificacion;
		this.orden=orden;
	}

	public Long getGenero() {
		return genero;
	}

	public void setGenero(Long genero) {
		this.genero = genero;
	}

	public Long getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Long clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

}
