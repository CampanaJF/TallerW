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
	
	@ManyToOne
	private Asiento asiento;
	
	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	public Long getAsientosTotales() {
		return asientosTotales;
	}

	public void setAsientosTotales(Long asientosTotales) {
		this.asientosTotales = asientosTotales;
	}

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

	public Cine getCine() {
		return cine;
	}

	public void setCine(Cine cine) {
		this.cine = cine;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}
	
	
	
}
