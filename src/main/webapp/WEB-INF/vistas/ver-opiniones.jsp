<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
    <title>Reseñas</title>
</head>
<body class="bgColor">
<%@include file="header.jsp"%>
<div class="row">

<div class="col pt-4 pb-5 mb-5" style="width: 660px; margin-left: 380px">
    <div>
        <h3 style="color:#9ab">Reseñas de</h3>
        <h1 class="mt-2 mb-3 titulo-cartelera text-white">${pelicula.titulo}</h1>
    </div>
    <c:if test="${not empty sinvaloracion}">
        <h4 class="text-white ">${sinvaloracion} , <a href="calificar-pelicula?pelicula=${pelicula.id}" class="text-decoration-underline" style="color: #ffffff"> califica ahora </a></h4>
    </c:if>
<c:forEach items="${valoraciones}" var="val">
    <div>
          <div class="d-flex flex-row flex-wrap ">
              <div>
                  <img src="icons/profile-pic.png" width="41px" height="41px" style="margin-right: 15px">
              </div>
              <div>
          <c:if test="${val.puntos == 1}">
              <img src="icons/estrella.png"  width="15px" height="15px">
          </c:if>
        <c:if test="${val.puntos == 2}">
            <img src="icons/estrella.png"    width="15px" height="15px">
            <img src="icons/estrella.png"    width="15px" height="15px">
        </c:if>
        <c:if test="${val.puntos == 3}">
            <img src="icons/estrella.png"    width="15px" height="15px">
            <img src="icons/estrella.png"    width="15px" height="15px">
            <img src="icons/estrella.png"    width="15px" height="15px">
        </c:if>
        <c:if test="${val.puntos == 4}">
            <img src="icons/estrella.png"    width="15px" height="15px">
            <img src="icons/estrella.png"    width="15px" height="15px">
            <img src="icons/estrella.png"    width="15px" height="15px">
            <img src="icons/estrella.png"    width="15px" height="15px">
        </c:if>
        <c:if test="${val.puntos == 5}">
            <img src="icons/estrella.png"     width="15px" height="15px">
            <img src="icons/estrella.png"     width="15px" height="15px">
            <img src="icons/estrella.png"     width="15px" height="15px">
            <img src="icons/estrella.png"     width="15px" height="15px">
            <img src="icons/estrella.png"     width="15px" height="15px">
        </c:if>
              </div>
              <h6 class="mt-2 ms-2 " style="color: #9ab">reseña de ${val.usuario.nombre}</h6>
          </div>
        <div style="color: #9ab; margin-left: 50px">${val.comentario} </div>
        <hr style="color: #9ab">
    </div>
</c:forEach>
    <div>
        <a href="ver-pelicula?pelicula=${pelicula.id}" class="text-decoration-none btn btn-secondary mt-2">Volver</a>
    </div>
</div>
    <div class="col mt-5 ms-5">
        <img src="${pelicula.poster}" width="240px" height="385px">
    </div>
</div>
</body>
</html>
