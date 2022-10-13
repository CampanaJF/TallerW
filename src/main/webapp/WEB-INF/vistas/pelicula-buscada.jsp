<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
    <title>Buscar</title>
</head>
<body class="body-perfil">
<%@include file="header.jsp"%>

<c:if test="${not empty peliculas}">
<div>
    <c:forEach items="${peliculas}" var="pelicula">
        <div>
            <h2 class="text-white  mt-lg-5 ms-5  titulo-cartelera">Peliculas encontradas:</h2>
        </div>

        <div  class="d-block ms-5 ">
            <ul class="m-2 flex-wrap d-flex list-unstyled">
                <li class="p-5 m-b2 position-relative">
                    <div class="d-block text-center">
                        <a href="" class="text-decoration-none"> <!-- a ver detalle de la pelicula -->
                            <figure>
                                <img  src="img/peliculas/${pelicula.poster}.jpg" width="270" height="386" style="border: 5px solid white">
                            </figure>
                            <h4 class="text-center text-white">${pelicula.titulo}</h4>
                        </a>
                    </div>
                </li>
            </ul>
        </div>
    </c:forEach>
</div>
</c:if>

<c:if test="${empty peliculas}">
    <div class="body-perfil">
        <h3 class="text-center text-white mt-3">Lo sentimos, la pelicula buscada no se encuentra disponible</h3>
    </div>
</c:if>

</body>
</html>
