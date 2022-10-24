<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
    <title>Title</title>
</head>
<body class="bgColor">
<%@include file="header.jsp"%>
<c:if test="${not empty mensaje}">
    <h2 class="text-white text-center mt-2">${mensaje}</h2>
</c:if>
<div class="text-center">
    <img src="${pelicula.poster}"  class="img-fluid" width="270" height="345">
</div>

<div class="text-center">
<c:if test="${puntos == 1}">
    <h3 class="text-white text-center mt-2">Calificaci&oacute;n :</h3>
    <img src="icons/estrella.png" width="32px" height="32px">
</c:if>
<c:if test="${puntos == 2}">
    <h3 class="text-white text-center mt-2">Calificaci&oacute;n :</h3>
    <img src="icons/estrella.png" width="32px" height="32px">
    <img src="icons/estrella.png" width="32px" height="32px">
</c:if>
<c:if test="${puntos == 3}">
    <h3 class="text-white text-center mt-2">Calificaci&oacute;n :</h3>
    <img src="icons/estrella.png" width="32px" height="32px">
    <img src="icons/estrella.png" width="32px" height="32px">
    <img src="icons/estrella.png" width="32px" height="32px">

</c:if>
<c:if test="${puntos == 4}">
    <h3 class="text-white text-center mt-2">Calificaci&oacute;n :</h3>
    <img src="icons/estrella.png" width="32px" height="32px">
    <img src="icons/estrella.png" width="32px" height="32px">
    <img src="icons/estrella.png" width="32px" height="32px">
    <img src="icons/estrella.png" width="32px" height="32px">
</c:if>
<c:if test="${puntos == 5}">
    <h3 class="text-white text-center mt-2">Calificaci&oacute;n :</h3>
    <img src="icons/estrella.png" width="32px" height="32px">
    <img src="icons/estrella.png" width="32px" height="32px">
    <img src="icons/estrella.png" width="32px" height="32px">
    <img src="icons/estrella.png" width="32px" height="32px">
    <img src="icons/estrella.png" width="32px" height="32px">
</c:if>
</div>
<div class="text-center mt-2 mb-2">
    <a href="ver-pelicula?pelicula=${pelicula.id}" class="btn btn-secondary">Volver</a>
</div>

</body>
</html>
