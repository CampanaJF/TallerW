<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<%@include file="head.jsp"%>
	
	<link href="css/ticket.css" rel="stylesheet">
	
	<title>CineClub</title>
	


	<%@include file="header.jsp"%>
	
<body class="bgColor text-white">  


		<c:if test="${not empty mensaje}">
	    <div class="p-1 alertbg">
	        <h5 class="text-center text-white">${mensaje}</h5> 	
	    </div>
	    </c:if>

<c:if test="${not empty entrada}">				  
<div class="container">
  <h1 class="upcomming">Entrada/s para ${entrada.funcion.pelicula.titulo}-${entrada.funcion.horario}</h1>
  
  	<div class="item">
  
		<div class="item-right">
		  <h4 class="num">${entrada.asiento.numero} ${entrada.asiento.ubicacion}</h4>
		  <p class="day">${entrada.funcion.sala.nombreSala}</p>
		  <span class="up-border"></span>
		  <span class="down-border"></span>
		</div>
		
		<div class="item-left">
		  <p class="event">${entrada.funcion.sala.cine.nombreCine}</p>
		  <h2 class="title">${entrada.funcion.pelicula.titulo}</h2>
		  
		  <div class="sce">
			<p>${entrada.funcion.formato.tipo}<br/>${entrada.funcion.horario}</p>
		  </div>
		  <div class="fix"></div>
		  <div class="loc">
			<p>${entrada.funcion.sala.nombreSala}<br/>Asiento ${entrada.asiento.ubicacion} ${entrada.asiento.numero}<br/> ${entrada.funcion.precio} $</p>
		  </div>
		  <div class="fix"></div>
		  <button class="cancel">Imprimir</button>
		</div> 
		
	  </div>
	   	 
</div>
</c:if>	
	    
	    
<c:if test="${not empty entradas}">				  
<div class="container">
  <h1 class="upcomming">Entrada/s</h1>
  
   <c:forEach items="${entradas}" var="entrada">
  
  	<div class="item">
  
		<div class="item-right">
		  <h4 class="num">${entrada.asiento.numero} ${entrada.asiento.ubicacion}</h4>
		  <p class="day">${entrada.funcion.sala.nombreSala}</p>
		  <span class="up-border"></span>
		  <span class="down-border"></span>
		</div>
		
		<div class="item-left">
		  <p class="event">${entrada.funcion.sala.cine.nombreCine}</p>
		  <h2 class="title">${entrada.funcion.pelicula.titulo}</h2>
		  
		  <div class="sce">
			<p>${entrada.funcion.formato.tipo}<br/>${entrada.funcion.horario}</p>
		  </div>
		  <div class="fix"></div>
		  <div class="loc">
			<p>${entrada.funcion.sala.nombreSala}<br/>Asiento ${entrada.asiento.ubicacion} ${entrada.asiento.numero}<br/> ${entrada.funcion.precio} $</p>
		  </div>
		  <div class="fix"></div>
		  <button class="cancel" data-bs-toggle="modal" data-bs-target="#exampleModal">Cancelar Reserva</button>
		</div> 
		
	  </div>
	  
	  <!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title text-center" id="exampleModalLabel">Cancelar la Reserva</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <p>¿Esta Seguro que desea cancelar la reserva?</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Volver</button>
		        <button type="button" class="btn btn-primary">Cancerlar Reserva</button>
		      </div>
		    </div>
		  </div>
		</div>
	   
	 </c:forEach>  
	
	 
</div>
</c:if>	




	
	    

	<%@include file="footer.jsp"%>
</body>
</html>