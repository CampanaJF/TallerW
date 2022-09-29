<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<%@include file="head.jsp"%>
	
	<title class="bgColor">CineClub</title>
	
	<body>

	<%@include file="header.jsp"%>
	
	 <body class="text-white bgColor">  


		<c:if test="${not empty mensaje}">
	    <div class="p-1 alertbg">
	        <h5 class="text-center text-white">${mensaje}</h5> 	
	    </div>
	    </c:if>
	    
        <div class="container pt-3">
            <div class="row">
              <div class="col align-self-start">
              </div>
              <div class="col-6 align-self-center align-items-center text-center">
                <h2 class="pt-2">Tu Entrada</h2>
                <h4 class="pt-2">Cine: ${entrada.funcion.cine.nombre}</h4>
                <h4 class="pt-2">Titulo: ${entrada.funcion.pelicula.titulo}</h4>
                <h4 class="pt-2">Horario: ${entrada.funcion.horario}</h4>
                <h4 class="pt-2">Sala: ${entrada.funcion.sala.nombre}</h4>
				<p class="pt-5">${entrada.id}</p>
              </div>
              <div class="col align-self-end ">
              </div>
            </div>
          </div>



	<%@include file="footer.jsp"%>
</body>
</html>