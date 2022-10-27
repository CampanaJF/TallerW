
function validaCaracteres(){
   /* pelicula = $('#titulo').val();


     if(pelicula.length >=3){
         return true;
     }
     else{
         alert('Por favor introduce 3 caracteres como minimo.')
         return false;
     }*/
    let titulo = document.querySelector('#buscador').value;
         if(titulo.length >=3){
             return true;
         }
         else{
             alert('Por favor introduce 3 caracteres como minimo.')
             return false;
         }

    }



