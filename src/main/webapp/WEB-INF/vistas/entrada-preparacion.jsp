<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<%@include file="head.jsp"%>
	
	<title>${funciones.get(0).getPelicula().titulo}</title>
	
<body class="bgColor">

	<%@include file="header.jsp"%>
	
	<c:if test="${not empty error}">
			<div class="p-1 alertbg">
				<h5 class="text-center text-white">${error}</h5> 	
			</div>
	 	</c:if>
	
	<div class="container-md pt-2 pb-2">
		<div class="row">

			<div class="col">
				<div class="text-center"> 
					<img class="img-fluid portadaPelicula" 
					src="<c:url value="${funciones.get(0).getPelicula().poster}"/>">
				</div>
			</div>

			<div class="checkout col align-self-center text-center text-white">
				<h1>${funciones.get(0).getPelicula().titulo}</h1>
				<h4>${funciones.get(0).getPelicula().director}</h4>
				<h5 class="pb-3">${funciones.get(0).getPelicula().protagonista}</h5>
				
				<h5 class="pb-3 price"
				 data-price="${funciones.get(0).precio}">Precio por entrada : ${funciones.get(0).precio} $</h5>
				
				<form:form action="entrada-compra" modelAttribute="datosEntrada" method="POST">
                
                
                <h4>Horarios para la funcion 2D</h4>
                <div class="container">
                <div class="row row-cols-3 btn-group" role="group" aria-label="Basic radio toggle button group">

					<c:forEach items="${funciones}" var="funcion">
					
					<c:if test="${funcion.formato.tipo == '2D' }">
                    <div class="col p-0"> 
                    <form:radiobutton class="btn-check" id="${funcion.id}"
                     path="funcion.id" value="${funcion.id}" autocomplete="off"/>  
                    <label class="btn btn-outline-primary" for="${funcion.id}">${funcion.horario}</label>
                    </div>
                     </c:if>
                    
                 
                    </c:forEach> 

                  
                </div>
                </div>
                
                <h4>Horarios para la funcion 3D</h4>
                <div class="container">
                <div class="row row-cols-3 btn-group" role="group" aria-label="Basic radio toggle button group">

					<c:forEach items="${funciones}" var="funcion">
					
					<c:if test="${funcion.formato.tipo == '3D' }">
                    <div class="col p-0"> 
                    <form:radiobutton class="btn-check" id="${funcion.id}"
                     path="funcion.id" value="${funcion.id}" autocomplete="off"/>  
                    <label class="btn btn-outline-primary" for="${funcion.id}">${funcion.horario}</label>
                    </div>
                     </c:if>
                    
                 
                    </c:forEach> 

                  
                </div>
                </div>
                
                <h4>Horarios para la funcion de Realidad Aumentada</h4>
                <div class="container">
                <div class="row row-cols-3 btn-group" role="group" aria-label="Basic radio toggle button group">

					<c:forEach items="${funciones}" var="funcion">
					
					<c:if test="${funcion.formato.tipo == 'Realidad Aumentada' }">
                    <div class="col p-0"> 
                    <form:radiobutton class="btn-check" id="${funcion.id}"
                     path="funcion.id" value="${funcion.id}" autocomplete="off"/>  
                    <label class="btn btn-outline-primary" for="${funcion.id}">${funcion.horario}</label>
                    </div>
                     </c:if>
                    
                 
                    </c:forEach> 

                  
                </div>
                </div>
                
                	Cantidad: <form:input class="quantity form-control cantidad-form" value ="1" placeholder="1" path="cantidad" />
                	<form:hidden path="usuario.id" value="${usuario.id}" />
                	
                	<p class="total">Total: <span id="total">${funciones.get(0).precio}</span></p>
                	               	
                	<button type="submit" class="mt-3 btn-lg btn buttonA">Comprar Entradas</button>
				
				</form:form> 
				
				
			</div>

		</div>

	</div>


<%@include file="footer.jsp"%>
<script src="js/entrada.js"></script>

</body>
</html>