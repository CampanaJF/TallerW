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
    
<c:if test="${not empty entradas}">				  
<div class="container">
  <h1 class="upcomming">Entrada/s</h1>
  
   <c:forEach items="${entradas}" var="entrada">
  
  	<div class="item">
  
		<div class="item-right">
		  <h4 class="num">${entrada.asiento.numero}</h4>
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
			<p>${entrada.funcion.sala.nombreSala}<br/>
			Asiento ${entrada.asiento.ubicacion} ${entrada.asiento.numero}<br/>
			 ${entrada.funcion.precio} $</p>
		  </div>
		  <div class="fix"></div>
		  <a href="<c:url value="/entrada-cancelar?entrada=${entrada.id}"/>">
		  <button type="submit" class="mt-3 btn-lg btn buttonA">Cancelar Reserva</button>
		  </a>
		</div> 
		
	  </div>
	   
	</c:forEach>  
	
	 
</div>
</c:if>	




	
	    

	<%@include file="footer.jsp"%>
</body>
</html>