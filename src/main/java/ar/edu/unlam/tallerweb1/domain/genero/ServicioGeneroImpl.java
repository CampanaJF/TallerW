package ar.edu.unlam.tallerweb1.domain.genero;

import ar.edu.unlam.tallerweb1.delivery.DatosGenero;
import ar.edu.unlam.tallerweb1.domain.pelicula.Pelicula;
import ar.edu.unlam.tallerweb1.domain.usuario.GeneroUsuario;
import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.AsientoSinIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class ServicioGeneroImpl implements ServicioGenero{


    private RepositorioGenero repositorioGenero;

    @Autowired
    public ServicioGeneroImpl(RepositorioGenero repositorioGenero) {
        this.repositorioGenero=repositorioGenero;
    }

    public ServicioGeneroImpl(){

    }

    @Override
    public List<Genero> listarGeneros() {
        return repositorioGenero.getGeneros();
    }

	@Override
	public String getDescripcionGeneroById(Long id) {
		Genero genero=repositorioGenero.getDescripcionById(id);
		return genero.getDescripcion();
	}

    @Override
    public List<Genero> obtenerDescrpcionesGeneroPorId(Long idGenero) {
        return repositorioGenero.obtenerDescrpcionesGeneroPorId(idGenero);
    }

    @Override
    public void guardarGeneroElegidoPorUsuario(List<Long> genero, Usuario usuario) {

        List<Genero> generos = getGeneros(genero);

       for (Genero genero1: generos) {
             GeneroUsuario generoUsuario1 = new GeneroUsuario();
             generoUsuario1.setUsuario(usuario);
             generoUsuario1.setGenero(genero1);
           repositorioGenero.guardarGeneroElegidoPorUsuario(generoUsuario1);
        }
    }

    private List<Genero> getGeneros(List<Long>generos){
        List<Genero> generosElegidos = new ArrayList<>();

        for (Long genero : generos) {
            generosElegidos.add(this.repositorioGenero.getGenero(genero));
        }
        return generosElegidos;
    }
}
