package ar.edu.unlam.tallerweb1.domain.pelicula;

import ar.edu.unlam.tallerweb1.domain.clasificacionPelicula.ClasificacionPelicula;
import ar.edu.unlam.tallerweb1.domain.genero.Genero;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
        
        @OneToMany
        private List<Etiqueta>etiquetas;
        
        @ManyToOne
        private ClasificacionPelicula clasificacionPelicula;

        private Date fechaEstreno;
        
        private Integer enCartelera;
        
        
        
		public Integer getEnCartelera() {
			return enCartelera;
		}

		public void setEnCartelera(Integer enCartelera) {
			this.enCartelera = enCartelera;
		}

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

        public String getProtagonista() {
            return protagonista;
        }

        public Integer getCalificacion() {
			return calificacion;
		}

		public void setCalificacion(Integer calificacion) {
			this.calificacion = calificacion;
		}


        public void setProtagonista(String protagonista) {
            this.protagonista = protagonista;

        }

        public Genero getGenero() {
            return genero;
        }

        public void setGenero(Genero genero) {
            this.genero = genero;
        }

        public List<Etiqueta> getEtiquetas() {
			return etiquetas;
		}

		public void setEtiquetas(List<Etiqueta> etiquetas) {
			this.etiquetas = etiquetas;
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
