var map;
var service;
var infowindow;

console.log(data)

function initMap() {
 
 navigator.geolocation.getCurrentPosition(function(pos) {

   lat = pos.coords.latitude;
   lon = pos.coords.longitude;
	
   var myLatlng = new google.maps.LatLng(lat, lon);

   var mapOptions = {
     center: myLatlng,
     zoom: 11,
   };
   const ubicacionActual = { lat: lat, lng: lon };
   const image="https://upload.wikimedia.org/wikipedia/commons/b/bc/House_image_icon.png";
   map = new google.maps.Map(document.getElementById("map"),  mapOptions);
	crearMarcador(ubicacionActual,"Mi posición",image)
	
   // Creamos el infowindow
   infowindow = new google.maps.InfoWindow();

   // Creamos el servicio PlaceService y enviamos la petición.
		for(var i = 0; i < data.length; i++){
			var latLong={ lat: data[i].cine.latitud, lng:  data[i].cine.longitud }
			var nombre=data[i].cine.nombreCine
			crearMarcador(latLong,nombre,null);
		}

 });
 
 function crearMarcador(latLong,nombre,image)
 {
	
	if(image!=null){
		   var marker = new google.maps.Marker({
     map: map,
     position: latLong,
     title:nombre,
     icon:{
	 url:image,
	 scaledSize: new google.maps.Size(35,35),
	 origin : new google.maps.Point(0,0),
	 anchor: new google.maps.Point(0,32)
	 }
     
   });
	}else{
		
	var marker = new google.maps.Marker({
     map: map,
     position: latLong,
     title:nombre,
     
   });
		
	}
	
   // Creamos un marcador


 // Asignamos el evento click del marcador
   google.maps.event.addListener(marker, 'click', function() {
     infowindow.setContent(nombre);
     infowindow.open(map, this);
   });
   }
 
 

  /*
 
  var ubicacionPredeterminada= new google.maps.LatLng(-34.669804627948, -58.56286420843934);

  map = new google.maps.Map(document.getElementById('map'), {
      center: ubicacionPredeterminada,
      zoom: 15
    });
  

 marker= new google.maps.Marker({
    position: ubicacionPredeterminada,
    map
  });
  
  var request = {
    location: (-34.66959283533036,-58.561410453188266),
    radius: '500',
    type: ['cafe']
  };

  service = new google.maps.places.PlacesService(map);
   service.nearbySearch(request, function(results, status) {
     if (status === google.maps.places.PlacesServiceStatus.OK) {
       for (var i = 0; i < results.length; i++) {
         crearMarcador(results[i]);
       }
     }
   });
}

function callback(results, status) {
  if (status == google.maps.places.PlacesServiceStatus.OK) {
    for (var i = 0; i < results.length; i++) {
      createMarker(results[i]);
    }
  }
*/
}




