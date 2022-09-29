package ar.edu.unlam.tallerweb1.domain.pelicula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioPeliculaImpl implements ServicioPelicula{

    private RepositorioPelicula repositorioPelicula;

    @Autowired
    public ServicioPeliculaImpl(RepositorioPelicula repositorioPelicula){
        this.repositorioPelicula=repositorioPelicula;
    }

    @Override
    public List<Pelicula> getPeliculas() {
        return this.repositorioPelicula.getPeliculas();
    }

    @Override
    public List<Pelicula> buscarPeliculas(String titulo){

       return this.repositorioPelicula.buscarPeliculas(titulo);
    }
}
