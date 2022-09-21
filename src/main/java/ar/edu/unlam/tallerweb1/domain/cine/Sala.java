package ar.edu.unlam.tallerweb1.domain.cine;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String nombreSala;
	
	@ManyToOne
	private Cine cine;
	
	private Long asientosTotales;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAsientos() {
		return asientosTotales;
	}

	public void setAsientos(Long asientos) {
		this.asientosTotales = asientos;
	}

	public String getNombre() {
		return nombreSala;
	}

	public void setNombre(String nombre) {
		this.nombreSala = nombre;
	}

	public Cine getCine() {
		return cine;
	}

	public void setCine(Cine cine) {
		this.cine = cine;
	}
	
	
	
}
