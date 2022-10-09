<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>	

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

			<div class="col align-self-center text-center text-white">
				<h1>${funciones.get(0).getPelicula().titulo}</h1>
				<h4>${funciones.get(0).getPelicula().director}</h4>
				<h5 class="pb-3">${funciones.get(0).getPelicula().protagonista}</h5>
				<h5 class="pb-3">Precio : ${funciones.get(0).getPelicula().protagonista} $</h5>
				
				
				<form:form action="entrada-compra" modelAttribute="datosEntrada" method="POST">
					
				<form:select class="form-select" path="funcion.id" >
							  
				 <c:forEach items="${funciones}" var="funcion">
				  
				  <form:option value="${funcion.id}" label="${funcion.horario}"/>
				  
				 </c:forEach>
				 
				</form:select>
				
				
				<form:hidden path="usuario.id" value="${usuario.id}" />
				
				
				<button type="submit" class="btn-lg btn buttonA">Comprar Entradas</button>
				
				</form:form> 
				
			</div>

		</div>

	</div>


<%@include file="footer.jsp"%>

</body>
</html>