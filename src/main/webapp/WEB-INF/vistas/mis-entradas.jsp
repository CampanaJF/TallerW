<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<%@include file="head.jsp"%>
	
	<title class="bgColor">CineClub</title>
	
	<body>

	<%@include file="header.jsp"%>
	
	 

<table class="table table-dark table-striped">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Pelicula</th>
      <th scope="col">Cine</th>
      <th scope="col">Horario</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${entradas}" var="entrada">

    <tr >
      <th scope="row">${entrada.id}</th>
      <td>${entrada.funcion.pelicula.titulo}</td>
      <td>${entrada.funcion.cine.nombre}</td>
      <td>${entrada.funcion.horario}-HS</td>
      <td><a class="nav-link text-white nav-text" href="ver-entrada?entrada=${entrada.id}">Ver</a></td>
    </tr>
    
  </c:forEach>
  </tbody>
</table>


	<%@include file="footer.jsp"%>
</body>
</html>