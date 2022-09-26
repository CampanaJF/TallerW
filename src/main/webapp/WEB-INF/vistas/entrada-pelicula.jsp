<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>	

	<%@include file="head.jsp"%>
	
	<title>${pelicula}</title>
	
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
					src="<c:url value="/img/peliculas/backtothefuture.jpg"/>">
				</div>
			</div>

			<div class="col align-self-center text-center text-white">
				<h1>Back to the Future</h1>
				<h4>Steven Spielberg</h4>
				<h5 class="pb-3">Michael J. Fox - Christopher Lloyd</h5>
				
				
				<form:form action="entrada-preparacion" modelAttribute="datosCine" method="POST">
					
				<form:select class="form-select" path="cine" >
							  
				 <c:forEach items="${cines}" var="cines">
				  
				  <form:option value="${cines.cine.id}" label="${cines.cine.nombre}"/>
				  
				 </c:forEach>
				 
				</form:select>
				
				
				<form:hidden path="pelicula" value="${pelicula}" />
				
				
				<button type="submit" class="btn-lg btn buttonA">Comprar Entradas</button>
				
				</form:form> 
	
	
		<%-- 
				<form:form action="entrada-preparacion" method="POST">
				
				
				
				<form:select class="form-select" path="cineId" >
							  
				 <c:forEach items="${cines}" var="cines">
				  
				  <form:option value="${cines.cine.id}" label="${cines.cine.nombre}"/>
				  
				 </c:forEach>
				 
				</form:select>
				
				
				
				<form:hidden path="peliculaId" value="${pelicula}" />
				
				
				
				<button type="submit" class="btn-lg btn buttonA">Comprar Entradas</button>
				
				</form:form>   
				
				--%>
				
				
			</div>

		</div>

	</div>


<%@include file="footer.jsp"%>

</body>
</html>