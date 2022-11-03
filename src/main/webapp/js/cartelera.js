var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
  return new bootstrap.Tooltip(tooltipTriggerEl)
})
//AOS.init();
function getContextPath() {
   return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}


function limpiarFiltros(){
	
	window.location.href=getContextPath()+"/cartelera";
}
const filtrosSeleccionados={
   genero:'',
   clasificacion:'',
   orden:''
}

function seleccionarFiltro(capsula,filtro){
	const capsulaActivada=document.querySelector(`#${filtro} .activada`);
	if(capsulaActivada){
		capsulaActivada.style.backgroundColor="#D81438";
		capsulaActivada.classList.remove("activada")
	}
	capsula.style.backgroundColor='#c94f4f';
	capsula.classList.add("activada");
	if(filtro ==='filtroGenero'){
		let genero=document.getElementById("generoFiltro");
	genero.value=capsula.id
		
	}
	if(filtro==='clasificacion'){
		let clasificacion=document.getElementById("clasificacionFiltro");
		clasificacion.value=capsula.id
		
	}


}


function seleccionarOrden(capsula,filtro){
  const capsulaActivada=document.querySelector(`#${filtro} .activada`);
	let orden=document.getElementById("ordenFiltro");
	
	if(capsulaActivada){
		capsulaActivada.style.backgroundColor="#D81438";
		capsulaActivada.classList.remove("activada")
	}
	capsula.style.backgroundColor='#c94f4f';
	capsula.classList.add("activada");


orden.value=capsula.id


  
}

document.querySelector('jsuites-rating').addEventListener('onchange', function(e) {
    //document.getElementById('console').innerHTML = 'New value: ' + this.value;
});


