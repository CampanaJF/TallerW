<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<%@include file="head.jsp"%>
	
	<link href="css/ticket.css" rel="stylesheet">
	
	<title>CineClub</title>
	
	<body>

	<%@include file="header.jsp"%>
	
	 <body class="text-white bgColor">  


		<c:if test="${not empty mensaje}">
	    <div class="p-1 alertbg">
	        <h5 class="text-center text-white">${mensaje}</h5> 	
	    </div>
	    </c:if>
		
		<div class="container">
  <h1 class="upcomming">Entrada/s</h1>
  
  <div class="item">
  
		<div class="item-right">
		  <h4 class="num">${entrada.asiento.ubicacion}</h4>
		  <p class="day">${entrada.funcion.sala.nombreSala}</p>
		  <span class="up-border"></span>
		  <span class="down-border"></span>
		</div> <!-- end item-right -->
		
		<div class="item-left">
		  <p class="event">${entrada.funcion.sala.cine.nombreCine}</p>
		  <h2 class="title">${entrada.funcion.pelicula.titulo}</h2>
		  
		  <div class="sce">
			<p>Tipo de Funcion<br/>${entrada.funcion.horario}</p>
		  </div>
		  <div class="fix"></div>
		  <div class="loc">
			<p>${entrada.funcion.sala.nombreSala}<br/> ${entrada.asiento.ubicacion}<br/> ${entrada.funcion.precio}</p>
		  </div>
		  <div class="fix"></div>
		  <button class="cancel">Imprimir</button>
		</div> <!-- end item-right -->
	  </div> <!-- end item -->
	  
	</div>
	    

	<%@include file="footer.jsp"%>
</body>
</html>