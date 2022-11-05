package ar.edu.unlam.tallerweb1.domain.usuario;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ar.edu.unlam.tallerweb1.domain.pelicula.Etiqueta;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique=true)
	private String email;
	
	@Column(unique=true)
	private String nombre;
	
	private String password;
	
	private String rol;
	
	@OneToMany
	private List<Etiqueta>etiquetasVisitadas;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean activo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public List<Etiqueta> getEtiquetasVisitadas() {
		return etiquetasVisitadas;
	}
	public void setEtiquetasVisitadas(List<Etiqueta> etiquetasVisitadas) {
		this.etiquetasVisitadas = etiquetasVisitadas;
	}
	public boolean activo() {
		return activo;
    }

    public void activar() {
		activo = true;
    }
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
