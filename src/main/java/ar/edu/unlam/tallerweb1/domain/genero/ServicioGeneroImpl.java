package ar.edu.unlam.tallerweb1.domain.genero;

import ar.edu.unlam.tallerweb1.domain.usuario.Usuario;
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
    public void guardarGeneroElegidoPorUsuario(Long id, Usuario usuario) {
        Genero genero = new Genero();
        genero.setId(id);
        genero.setUsuario(usuario);
        repositorioGenero.guardarGeneroElegidoPorUsuario(genero);
    }


}
