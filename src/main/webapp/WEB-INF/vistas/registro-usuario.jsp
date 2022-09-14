<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<%@include file="head.jsp"%>

<body>
<div class = "container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
    
        <form:form action="procesarRegistro" method="POST" modelAttribute="datosUsuario">
            <h3 class="form-signin-heading">Nuevo Usuario</h3>


            <form:input path="email" name="email" id="email" class="form-control"/>
            
            <form:input path="nombre" name="nombre" id="nombre" class="form-control"/>
           
            <form:input path="password" type="password" id="password" class="form-control"/>
            
            <form:input path="passwordRe" type="password" id="passwordRe" class="form-control"/>

            <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block" Type="Submit"/>Registrarme</button>
            
        </form:form>

        <c:if test="${not empty mensaje}">
            <h4><span>${mensaje}</span></h4>
            <br>
        </c:if>
    </div>
</div>

<%@include file="footer.jsp"%>
</body>
</html>