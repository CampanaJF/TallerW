
AOS.init();

function eliminarFiltro(capsula){
console.log(capsula)
capsula.parentNode.remove();
}





function seleccionarGenero(capsula){
console.log(capsula.style.backgroundColor)
   // let capsula=document.getElementById("capsula");
   if(capsula.style.backgroundColor=='rgb(216, 20, 56)'){
    capsula.style.backgroundColor='grey';
   } else{
    capsula.style.backgroundColor='rgb(216, 20, 56)';
   }


}

