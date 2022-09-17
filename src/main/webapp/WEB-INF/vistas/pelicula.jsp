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
				
				<a href="comprar-ticket" type="button" class="btn-lg btn buttonA">Comprar Entradas</a>
				
			</div>

		</div>

	</div>


<%@include file="footer.jsp"%>

</body>
</html>