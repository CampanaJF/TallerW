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

<div>
    <h2 class="text-white text-center">Califica ${pelicula.titulo}</h2>
    <form action="guardar-calificacion" method="get">
        <div class="container-rating">
            <div class="rating-wrap">
                <div class="center-rating">
                    <div class="rating">
                        <input type="hidden" value="${pelicula.id}" name="peliculaId"/>

                        <label for="star5" class="full"></label>
                        <input type="radio"  id="star5" name="puntos" value="5">

                        <label for="star4" class="full"></label>
                        <input type="radio" id="star4" name="puntos" value="4">

                        <label for="star3" class="full"></label>
                        <input type="radio"  id="star3" name="puntos" value="3">

                        <label for="star2" class="full"></label>
                        <input type="radio"  id="star2" name="puntos" value="2">

                        <label for="star1" class="full"></label>
                        <input type="radio"  id="star1" name="puntos" value="1">
                    </div>
                </div>
            </div>
        </div>
        <div class="d-flex  flex-row flex-wrap text-center justify-content-center">
            <div class="m-2">
                <a href="ver-pelicula?pelicula=${pelicula.id}" class="btn btn-secondary">Volver</a>
            </div>
            <div>
                <input type="submit" class="btn buttonA text-white boton-calif" value="Calificar">
            </div>

        </div>


    </form>

</div>
</body>
</html>
