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

<div class="container-md pt-2 pb-2">
    <div class="row">

        <div class="col">
            <div class="text-center">
                <img src="${pelicula.poster}" class="mb-2 mt-5 img-fluid"  >
            </div>
        </div>

        <div class="col align-self-center text-center text-white">
            <h1 class="text-center text-white titulo-cartelera">${pelicula.titulo}</h1>
            <h4  class="text-center text-white mt-2">Dirigida por ${pelicula.director}</h4>
            <h5 class="pb-3 text-center text-white mt-4">${pelicula.sinopsis}</h5>

            <a href="entrada-pelicula?peliculaId=${pelicula.id}" type="button" class="btn-lg btn buttonA">Comprar Entradas</a>
        </div>
        <!--
         <div class="col-4">
             <form action="guardar-calificacion" method="post" modelAttribute="datosValoracion" >
             <div class="container-rating">
                 <div class="rating-wrap">
                     <h2 class="text-white text-center">Star Rating</h2>
                     <div class="center-rating">
                         <fieldset class="rating">
                            <form path="valoracion.pelicula" value="{pelicula.id}"/>

                             <input type="radio" id="star5" name="estrellas" value="5" path="valoracion.estrellas"></input>
                             <label for="star5" class="full" title="Awesome" path=""></label>
                             <label for="star4.5" class="half"></label>
                             <input type="radio" id="star4.5" name="estrellas" value="4.5"></input>
                             <label for="star4" class="full"></label>
                             <input type="radio" id="star4" name="estrellas" value="4"/>
                             <label for="star3.5" class="half"></label>
                             <input type="radio" id="star3.5" name="estrellas" value="3.5"/>
                             <label for="star3" class="full"></label>
                             <input type="radio" id="star3" name="estrellas" value="3"/>
                             <label for="star2.5" class="half"></label>
                             <input type="radio" id="star2.5" name="estrellas" value="2.5"/>
                             <label for="star2" class="full"></label>
                             <input type="radio" id="star2" name="estrellas" value="2"/>
                             <label for="star1.5" class="half"></label>
                             <input type="radio" id="star1.5" name="estrellas" value="1.5"/>
                             <label for="star1" class="full"></label>
                             <input type="radio" id="star1" name="estrellas" value="1"/>
                             <label for="star0.5" class="half"></label>
                             <input type="radio" id="star0.5" name="estrellas" value="0.5"/>
                         </fieldset>
                     </div>
                     <div>
                     <h4 id="rating-value"></h4>
                     <input type="submit" value="Puntuar">
                 </div>
                <div>
                    <c test="{not empty mensaje}">
                        <span>{mensaje}</span>
                    </c>

                </div>
         </div>
             </div>
             </form>
             -->
    </div>
</div>
     </c:if>

<div class="container mt-3">
     <h2 class="text-white mt-lg-5 ms-5 mb-2">Peliculas similares</h2>

    <section class="d-flex flex-row flex-wrap text-center">
        <c:forEach items="${similares}" var="pelicula">
            <article class="col-lg-4 col-xl-3 mt-4 mb-5 pb-5 ">
           
           	  <div class="fila">
                <div class="tile">
                <img class="pelicula" src="${pelicula.getPoster()}"
                    alt="">
                <div class="overlay ">
                    <img class="pelicula" src="${pelicula.getPoster()}">
                    <div class="container informacionPeli d-flex flex-column">
                        <div class="row">
                        <div class="col-lg-4 mt-2">
                            <p class="text-light clasificacion">${pelicula.getClasificacionPelicula().getDescripcion() }</p>
                        </div>
                        <div class="col-lg-4 mt-2">
                            <p class="text-light duracion">${pelicula.getDuracionEnHoras()}</p>
                        </div>
                        <div class="col-lg-4  fechaEstreno mt-2">
                            <p class="text-light">${pelicula.getFechaEstreno().getYear()+1900}</p> 
                        </div>
                      
                        </div>
                         <div class="d-flex justify-content-around">
                        
                         <p class="text-light genero mt-1">${pelicula.getGenero().getDescripcion() }</p>
                          
                 		  <div class="rating-container">
                         <div class="cover"></div>
                         <jsuites-rating class="estrellas" value="${pelicula.getCalificacion()}
                         "tooltip="Muy mala, Mala, Regular, Buena, Muy buena"></jsuites-rating>
                         </div>
 						
						
                </div>
                </div>
                </div>
              </div>
              </div>
        
                <!--  
                <div>
                    <a href="ver-pelicula?pelicula=${pelicula.id}" class="text-decoration-none">
                        <img src="${pelicula.poster}" class="mb-2" style="width:270px;" >
                        <h4  class="text-center text-white">${pelicula.titulo}</h4>
                    </a>
                </div>
                __-->
            </article>
        </c:forEach>
    </section>
</div>

    <%@include file="footer.jsp"%>
