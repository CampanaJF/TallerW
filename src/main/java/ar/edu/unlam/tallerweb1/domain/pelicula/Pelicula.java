package ar.edu.unlam.tallerweb1.domain.pelicula;

import ar.edu.unlam.tallerweb1.domain.clasificacionPelicula.ClasificacionPelicula;
import ar.edu.unlam.tallerweb1.domain.genero.Genero;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
    public class Pelicula {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique=true)
        private String titulo;

        private String sinopsis;

        private String poster;

        private String director;

        private String protagonista;
        
        private Integer calificacion;
         
        @ManyToOne
        private Genero genero;
        
        private Integer duracion;

        @ManyToOne
        private ClasificacionPelicula clasificacionPelicula;

        private Date fechaEstreno;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getSinopsis() {
            return sinopsis;
        }

        public void setSinopsis(String sinopsis) {
            this.sinopsis = sinopsis;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getProtegonista() {
            return protagonista;
        }

        public Integer getCalificacion() {
			return calificacion;
		}

		public void setCalificacion(Integer calificacion) {
			this.calificacion = calificacion;
		}

		public void setProtegonista(String protegonista) {
            this.protagonista = protegonista;
        }


        public Genero getGenero() {
            return genero;
        }

        public void setGenero(Genero genero) {
            this.genero = genero;
        }

        public Integer getDuracion() {
            return duracion;
        }
        
        public String getDuracionEnHoras() {
        	Integer conversorAHoras=60;
        	Integer horas=duracion/conversorAHoras;
        	Integer minutos=duracion-horas*conversorAHoras;
        	
        	return (horas+"h" +minutos+"m"); 
        	
        }
        public void setDuracion(Integer duracion) {
            this.duracion = duracion;
        }

        public ClasificacionPelicula getClasificacionPelicula() {
            return clasificacionPelicula;
        }

        public void setClasificacionPelicula(ClasificacionPelicula clasificacionPelicula) {
            this.clasificacionPelicula = clasificacionPelicula;
        }

        public Date getFechaEstreno() {
            return fechaEstreno;
        }

        public void setFechaEstreno(Date fechaEstreno) {
            this.fechaEstreno = fechaEstreno;
        }
}
