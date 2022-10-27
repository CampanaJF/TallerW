function validaCalificacion(){

    if(!document.querySelector('input[name="puntos"]:checked')) {
        alert('Por favor califique la pelicula.');
        return false;
    }

    var val = document.getElementById('comentario').value;
    if (/^\s*$/g.test(val)) {
        alert('Por favor ingrese un comentario.');
        return false;
    }
}
/*function validaComentario(){
    var val = document.getElementById('comentario').value;
    if (/^\s*$/g.test(val)) {
        alert('Por favor ingrese un comentario.');
        return false;
    }
}*/