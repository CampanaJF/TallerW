<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
    <title>Pelicula</title>
</head>
<body class="bgColor">
<%@include file="header.jsp"%>

     <c:if test="${not empty pelicula}">

<div class="container-md pt-2 pb-2 mt-5">
    <div class="row">
        <div class="col">
            <div class="text-center">

                <img src="${pelicula.poster}" class="mb-2 mt-5 img-fluid" width="270" height="386" >

            </div>
        </div>

        <div class="col align-self-center  text-white">
            <h1 class="titulo-cartelera">${pelicula.titulo}</h1>
            <h6 class="clasif-borde">${pelicula.clasificacionPelicula.descripcion}</h6>
            <h5 class="pb-3">${pelicula.sinopsis}</h5>
            <h5><span class="titulo-cartelera">Direcci&oacute;n</span> ${pelicula.director}</h5>
            <h5><span class="titulo-cartelera">Reparto principal </span>${pelicula.protagonista}</h5>
            <h5><span class="titulo-cartelera">G&eacute;nero </span>${pelicula.genero.descripcion}</h5>
            <h5><span class="titulo-cartelera">Duraci&oacute;n </span>${pelicula.duracionEnHoras}</h5>

            <a href="entrada-pelicula?peliculaId=${pelicula.id}" type="button" class="btn-lg btn buttonA mt-3">Comprar Entradas</a>
        </div>

         <div class="col">
             <div class="text-white text-center pt-2 pb-2 mt-5">
                 <c:if test="${not empty promedio}">
                     <h4 >Calificaci&oacute;n de usuarios</h4>
                     <div class="d-flex  flex-row flex-wrap align-items-center justify-content-center">
                         <img src="icons/estrella.png" class="bi me-2" width="40" height="40" role="img">
                         <h4> <span class="titulo-cartelera"> ${promedio} </span>/ 5</h4>
                     </div>
                     <h6 class="mt-2">Basado en ${votos} votos</h6>
                 </c:if>
             </div>
             <div class="d-flex  flex-row flex-wrap align-items-center justify-content-center calif-subtitulo mt-5">
                <label> <i class="fa fa-star"></i> </label>
                <a href="calificar-pelicula?pelicula=${pelicula.id}" class="a-calif"><h2 class="  text-center">Calificar</h2> </a>
             </div>
    <!--       <div>
             <form action="guardar-calificacion" method="get">
             <div class="container-rating">
                 <div class="rating-wrap">
                     <h2 class="text-white text-center">Calificar ahora</h2>
                     <div class="center-rating">
                         <div class="rating">
                             <input type="hidden" value="${pelicula.id}" name="peliculaId"/>
                             <label for="star5" class="full"></label>
                             <input type="submit"  id="star5" name="puntos" value="5">

                             <label for="star4" class="full"></label>
                             <input type="submit" id="star4" name="puntos" value="4">

                             <label for="star3" class="full"></label>
                             <input type="submit"  id="star3" name="puntos" value="3">

                             <label for="star2" class="full"></label>
                             <input type="submit"  id="star2" name="puntos" value="2">

                             <label for="star1" class="full"></label>
                             <input type="submit"  id="star1" name="puntos" value="1">
                         </div>
                     </div>
               </div>
             </div>
           </form>
          </div>-->
    </div>
</div>
         <div>
             <h2 class="text-white mt-lg-5 ms-2">Peliculas similares</h2>
             <section class="d-flex  flex-row flex-wrap text-center">
                 <c:forEach items="${similares}" var="pelicula">
                     <article class="col-lg-4 col-xl-3 mt-4 mb-5 pb-5 ">
                         <div>
                             <a href="ver-pelicula?pelicula=${pelicula.id}" class="text-decoration-none">
                                 <img src="${pelicula.poster}" class=" mb-2" width="250" height="320" >
                                 <h4  class="text-center text-white">${pelicula.titulo}</h4>
                             </a>
                         </div>
                     </article>
                 </c:forEach>
             </section>
         </div>
</div>
     </c:if>



</body>
</html>
