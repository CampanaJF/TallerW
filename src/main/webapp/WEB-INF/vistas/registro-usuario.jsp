<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<%@include file="head.jsp"%>

<body class="bgColor">

	<c:if test="${not empty mensaje}">
		<div class="p-1 alertbg">
			<h5 class="text-center text-white">${mensaje}</h5> 	
		</div>
	</c:if>
	
<main class="mt-3 form-signin">

        <form:form action="procesarRegistro" method="POST" modelAttribute="datosUsuario">
            
            <a class="row" href="home">
			<img class="mb-4" src="<c:url value="/icons/popcorn-movie-cinema-svgrepo-com.svg"/>" alt="" width="72" height="57">
			</a>
			<h1 class="text-white text-center h3 mb-3 fw-normal">Nuevo Usuario</h1>

			<div class="form-floating">
			  <form:input type="email" path="email" class="form-control" id="floatingInput" placeholder="name@example.com"/>
			  <label for="floatingInput">Correo</label>
			</div>
			
			<div class="form-floating">
			  <form:input type="text" path="nombre" class="form-control" id="floatingInput"/>
			  <label for="floatingInput">Nombre</label>
			</div>
            
	         <div class="form-floating">
			  <form:input type="password" path="password" class="form-control" id="floatingPassword" placeholder="Password"/>
			  <label for="floatingPassword">Contraseña</label>
			</div>
		
			<div class="form-floating">
			  <form:input type="password" path="passwordRe" class="form-control" id="floatingPassword" placeholder="Password"/>
			  <label for="floatingPassword">Repita su Contraseña</label>
			</div>
           
          <button class="mt-2 w-100 buttonA btn-lg btn-primary" type="submit">Ingresar</button>
	  </form:form>
	  
	  <a class="text-center nav-link px-2 text-white nav-text" href="login">¿Ya tenes una cuenta? Ingresa aca</a>

</main>
  
<%@include file="footer.jsp"%>
</body>
</html>