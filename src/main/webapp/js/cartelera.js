
//AOS.init();

function eliminarFiltro(capsula){
capsula.parentNode.remove();
}




const filtrosSeleccionados={
   genero:'',
   clasificacion:'',
   orden:''
}


function seleccionarGenero(capsula){
 
let genero=document.getElementById("generoFiltro");
genero.value=capsula.id

  
}
function seleccionarClasificacion(capsula){

let clasificacion=document.getElementById("clasificacionFiltro");
clasificacion.value=capsula.id

}

function seleccionarOrden(capsula){
  
let orden=document.getElementById("ordenFiltro");
orden.value=capsula.id

  

}
