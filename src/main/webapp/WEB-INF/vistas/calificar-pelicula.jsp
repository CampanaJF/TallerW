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

<div class="container-md">

              <div class="d-flex  flex-row flex-wrap align-items-center justify-content-center">
                   <h2 class="text-white titulo-cartelera mt-5 ">${pelicula.titulo}</h2>
               </div>
        <h3 class="text-white text-center" >Califica</h3>
    <form action="guardar-calificacion" method="get" onsubmit="return validaCalificacion()">
        <div class="container-rating">
            <div class="rating-wrap">
                <div class="center-rating">
                    <div class="rating">
                        <input type="hidden" value="${pelicula.id}" name="peliculaId"/>

                        <input type="radio"  id="star5" name="puntos" value="5">
                        <label for="star5" class="fa fa-star mb-2 ms-2"></label>

                        <input type="radio" id="star4" name="puntos" value="4">
                        <label for="star4" class="fa fa-star mb-2 ms-2"></label>

                        <input type="radio"  id="star3" name="puntos" value="3">
                        <label for="star3" class="fa fa-star mb-2 ms-2"></label>

                        <input type="radio"  id="star2" name="puntos" value="2">
                        <label for="star2" class="fa fa-star mb-2 ms-2"></label>

                        <input type="radio"  id="star1" name="puntos" value="1">
                        <label for="star1" class="fa fa-star mb-2 ms-2"></label>
                    </div>
                </div>
            </div>
        </div>
        <div class="mt-2">
            <h3 class="text-white text-center">Comenta</h3>
        </div>

        <div class="text-center d-flex justify-content-center">
            <textarea maxlength="1000" rows="6" required
                      style="height: 320px; width: 300px; margin-bottom: 4px; background-color: #a9a9a9;
                       border:none; border-radius: 3px; color: #1a1a1a"  name="comentario" id="comentario">
            </textarea>
        </div>
        <div class="d-flex  flex-row flex-wrap align-items-center justify-content-center mt-3">
        <div class="text-center">
            <a href="ver-pelicula?pelicula=${pelicula.id}" class="btn btn-secondary">Volver</a>
        </div>
            <div class="text-center justify-content-center ">
                <input type="submit" class="btn buttonA text-white boton-calif " value="Calificar"  >
            </div>
        </div>
    </form>


        </div>

</body>
</html>
