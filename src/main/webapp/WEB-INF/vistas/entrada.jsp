<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<%@include file="head.jsp"%>
	
	<title class="bgColor">CineClub</title>
	
	<body>

	<%@include file="header.jsp"%>
	
	<h1>${entrada.funcion.cine.nombre}</h1>
	<h1>${entrada.funcion.pelicula.titulo}</h1>
	<h1>${entrada.funcion.horario}</h1>
	<h1>${entrada.funcion.sala.nombre}</h1>



	<%@include file="footer.jsp"%>
</body>
</html>