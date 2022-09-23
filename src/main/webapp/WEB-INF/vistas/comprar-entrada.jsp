<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>	

	<%@include file="head.jsp"%>
	
	<title>${entrada}</title>
	
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
				
				<select class="form-select" aria-label="Default select example">
				<option selected>Elija el Cine</option>
				  
				 <c:forEach items="${cines}" var="cines">
				  
				  <option value="${cines.cine.id}">${cines.cine.nombre}</option>
				  
				 </c:forEach>
				 
				 </select>
		
				
				
				
				<%--  
				<form:form action="procesar-compra-entrada" method="POST">
				<form:select class="form-select" type="select" path="cine" />
				<select class="form-select" aria-label="Default select example">
				<option selected>Elija el Cine</option>
				  
				 <c:forEach items="${cines}" var="cines">
				  
				  <option value="${cines.id}">${cines.nombre}</option>
				  
				 </c:forEach>

				</select>
				<button type="submit" class="btn-lg btn buttonA">Comprar Entradas</button>
				</form:form>
				--%>
				
			</div>

		</div>

	</div>


<%@include file="footer.jsp"%>

</body>
</html>