package ar.edu.unlam.tallerweb1.domain.genero;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioGeneroImpl implements ServicioGenero{


    private RepositorioGenero repositorioGenero;

    public ServicioGeneroImpl(RepositorioGenero repositorioGenero) {
        this.repositorioGenero=repositorioGenero;
    }

    public ServicioGeneroImpl(){

    }

    @Override
    public List<Genero> listarGeneros() {
        return repositorioGenero.getGeneros();
    }
}
