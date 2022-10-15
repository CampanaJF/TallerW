
//AOS.init();
function getContextPath() {
   return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}

function eliminarFiltro(capsula){
filtro={
   genero:'',
   clasificacion:'',
   orden:''
}

$.ajax({
            url: getContextPath()+'/eliminarFiltros',
            type: 'POST',
            data: filtro,
            contentType: "application/x-www-form-urlencoded",
             success: function (data) {
        //aqui redireccionas
    },
    error: function (request, status, error) {
     
    }
	});
               
            

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

document.querySelector('jsuites-rating').addEventListener('onchange', function(e) {
    //document.getElementById('console').innerHTML = 'New value: ' + this.value;
});


