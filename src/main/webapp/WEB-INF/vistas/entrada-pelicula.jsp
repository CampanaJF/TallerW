<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>	

	<%@include file="head.jsp"%>
	
	<title>${pelicula.titulo}</title>
	
<body class="bgColor">

	<%@include file="header.jsp"%>
	
	<c:if test="${not empty error}">
			<div class="p-5 alertbg">
				<h5 class="text-center text-white">${error}</h5> 	
			</div>
	 	</c:if>
	
	<div class="container-md pt-2 pb-2">
		<div class="row">

			<div class="col">
				<div class="text-center mt-5"> 
					<img class="img-fluid portadaPelicula" 
					src="<c:url value="${pelicula.poster}"/>">
				</div>
			</div>

			<div class="col align-self-center text-center text-white">
				<h1>${pelicula.titulo}</h1>
				<h4 class="mt-4">${pelicula.director}</h4>
				<h5 class=" mt-4 pb-3">${pelicula.protagonista}</h5>
				
				
				<form:form class="mt-4" action="entrada-preparacion" modelAttribute="datosCine" method="POST">
					<form:hidden path="pelicula" value="${pelicula.id}" />
					
					<c:if test="${empty cines}">
					<p class="text-light">Esta pelicula no se encuentra disponible</p>
					
					</c:if>
					
					<c:if test="${not empty cines}">
					
				<form:select class="form-select" path="cine" >
							  
				 <c:forEach items="${cines}" var="cines">
				  
				  <form:option value="${cines.cine.id}" label="${cines.cine.nombreCine}"/>
				  
				 </c:forEach>
				 
				</form:select>
					
				<button type="submit" class=" mt-4 btn-lg btn buttonA">Comprar Entradas</button>
					</c:if>
				</form:form> 
				
			</div>

		</div>

	</div>


<%@include file="footer.jsp"%>

</body>
</html>