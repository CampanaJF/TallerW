<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="head.jsp"%>
<link href="css/asientos.css" rel="stylesheet">

<title>CineClub</title>


<body class="body-home">

<%@include file="header.jsp"%>


	<c:if test="${not empty error}">
		 <div class="p-1 alertbg">
			<h5 class="text-center text-white">${error}</h5> 	
		</div>
	</c:if>
	 	
	  	
<div class="movie-body">

	<div class="movie-container">
      <h1> Pelicula titulo</h1>
      <input type="hidden" id="movie" value="3000">
    </div>

    <ul class="showcase">
      <li>
        <div class="seat"></div>
        <small>Disponible</small>
      </li>
      <li>
        <div class="seat selected"></div>
        <small>Seleccionado/s</small>
      </li>
      <li>
        <div class="seat sold"></div>
        <small>Reservados</small>
      </li>
    </ul>
    
   <form:form action="entrada-compra" modelAttribute="datosEntrada" method="POST"> 
    
    <div class="containerCustom">
      <div class="screen"></div>
      
     <c:forEach items="${filas}" var="fila">
      <div class="row">
         
      <c:forEach items="${fila.value}" varStatus="s" var="asiento">
      
        <form:checkbox path="asiento.id" value="${asiento.id}" class="seat
         <c:if test='${asiento.ocupado == true }'> sold </c:if>"></form:checkbox>
         
         
         <%-- 
         <form:checkbox path="asientos[${s.index}].id" value="${asiento.id}" class="seat
         <c:if test='${asiento.ocupado == true }'> sold </c:if>"></form:checkbox> --%>
         
                 
      </c:forEach>
      
      </div>
      
      </c:forEach>
      
	</div>
    
    <div class="movie-container">
       <p class="text">Cantidad de Entradas: <span id="count">0</span> Total: $ <span id="total">0</span></p>
    </div>
    
    <form:hidden path="funcion.id" value="${datosEntrada.funcion.id}" />
    <form:hidden path="usuario.id" value="${datosEntrada.usuario.id}" />
    <button type="submit" class="mt-3 btn-lg btn buttonA">Comprar Entradas</button>
    
   </form:form>
	
</div>

</body>

 <script src="js/asientos.js"></script>
 <script src="js/jquery-3.6.1.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>


</html>
