package ar.edu.unlam.tallerweb1.domain.genero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
}
