<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<%@include file="head.jsp"%>
	
	<title>CineClub</title>
	
	<body class="body-home">

	<%@include file="header.jsp"%>
	
	
		<c:if test="${not empty mensaje}">
	    <div class="p-1 alertbg">
	        <h5 class="text-center text-white">${mensaje}</h5> 	
	    </div>
	    </c:if>
    	<main class="container mt-5 pb-5">
    	
    	<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="true">

  <div class="carousel-inner">
    <div class="carousel-item active">
       <img src="img/banner/slide1.jpg" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="img/banner/slide2.jpg" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="img/banner/slide3.jpg" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="img/banner/slide4.jpg" class="d-block w-100" alt="...">
    </div>
  </div>
  
  
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  
		  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3" aria-label="Slide 4"></button>
  </div>
  
</div>
		<h1 class="text-light fw-bolder mt-5 mb-2">Recomendadas para ti</h1>
            <section class="pt-4 pb-2 mb-2 d-flex flex-wrap text-center">
                <c:forEach var="peliculasGenero" items="${peliculasGeneroElegido}">
                    <article class="col-lg-4 col-xl-3 mt-4 mb-5 pb-5 ">
                        <div class="fila">
                            <div class="tile">
                                <img class="pelicula" src="${peliculasGenero.pelicula.poster}"
                                     alt="">
                                <div class="overlay ">
                                    <img class="pelicula" src="${peliculasGenero.pelicula.poster}">
                                    <div class="container informacionPeli d-flex flex-column">
                                        <div class="row">
                                            <div class="col-lg-4 mt-2">
                                                <p class="text-light clasificacion">${peliculasGenero.pelicula.clasificacionPelicula.descripcion}</p>
                                            </div>
                                            <div class="col-lg-4 mt-2">
                                                <p class="text-light duracion">${peliculasGenero.pelicula.duracionEnHoras}</p>
                                            </div>
                                            <div class="col-lg-4  fechaEstreno mt-2">
                                                <p class="text-light">${peliculasGenero.pelicula.fechaEstreno.year+1900}</p>
                                            </div>

                                        </div>
                                        <div class="d-flex justify-content-around">
                                            <p class="text-light genero mt-1">${peliculasGenero.pelicula.genero.descripcion}</p>
                                        </div>
                                        <div class="d-flex justify-content-around">

                                            <c:forEach var="etiqueta" items="${peliculasGenero.etiquetas}">
                                                <p class="text-light etiquetaDescripcion">${etiqueta.descripcion}</p>
                                            </c:forEach>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>


                    </article>

                </c:forEach>
            </section>


            <h1 class="text-light fw-bolder mt-5">Próximos estrenos</h1>
		<section class="pt-4 pb-2 mb-2 d-flex flex-wrap text-center">
        <c:forEach var="peliculaDTO" items="${proximosEstrenos}">
            <article class="col-lg-4 col-xl-3 mt-4 mb-5 pb-5 ">
                <div class="fila">
                <div class="tile">
                <img class="pelicula" src="${peliculaDTO.getPelicula().getPoster()}"
                    alt="">
                <div class="overlay ">
                    <img class="pelicula" src="${peliculaDTO.getPelicula().getPoster()}">
                    <div class="container informacionPeli d-flex flex-column">
                        <div class="row">
                        <div class="col-lg-4 mt-2">
                            <p class="text-light clasificacion">${peliculaDTO.getPelicula().getClasificacionPelicula().getDescripcion() }</p>
                        </div>
                        <div class="col-lg-4 mt-2">
                            <p class="text-light duracion">${peliculaDTO.getPelicula().getDuracionEnHoras()}</p>
                        </div>
                        <div class="col-lg-4  fechaEstreno mt-2">
                            <p class="text-light">${peliculaDTO.getPelicula().getFechaEstreno().getYear()+1900}</p> 
                        </div>
                      
                        </div>
                         <div class="d-flex justify-content-around">
                         <p class="text-light genero mt-1">${peliculaDTO.getPelicula().getGenero().getDescripcion() }</p>
                         </div>
                         <div class="d-flex justify-content-around">
									
										<c:forEach var="etiqueta" items="${peliculaDTO.getEtiquetas()}">
										<p class="text-light etiquetaDescripcion">${etiqueta.getDescripcion()}</p>
										</c:forEach>
									</div>
                         
                    </div>
                </div>
                </div>
                </div>
              
               
            </article>
            
        </c:forEach>
        </section>
		
		
		
		<h1 class="text-light fw-bolder mt-2">Estrenos del mes</h1>
		<section class="pt-4 pb-5 mb-5 d-flex flex-wrap text-center">
        <c:forEach var="peliculaDTO" items="${peliculasEstrenos}">
            <article class="col-lg-4 col-xl-3 mt-4 mb-5 pb-5 ">
                <div class="fila">
                <div class="tile">
                <img class="pelicula" src="${peliculaDTO.getPelicula().getPoster()}"
                    alt="">
                <div class="overlay ">
                    <img class="pelicula" src="${peliculaDTO.getPelicula().getPoster()}">
                    <div class="container informacionPeli d-flex flex-column">
                        <div class="row">
                        <div class="col-lg-4 mt-2">
                            <p class="text-light clasificacion">${peliculaDTO.getPelicula().getClasificacionPelicula().getDescripcion() }</p>
                        </div>
                        <div class="col-lg-4 mt-2">
                            <p class="text-light duracion">${peliculaDTO.getPelicula().getDuracionEnHoras()}</p>
                        </div>
                        <div class="col-lg-4  fechaEstreno mt-2">
                            <p class="text-light">${peliculaDTO.getPelicula().getFechaEstreno().getYear()+1900}</p> 
                        </div>
                      
                        </div>
                         <div class="d-flex justify-content-around">
                         <p class="text-light genero mt-1">${peliculaDTO.getPelicula().getGenero().getDescripcion() }</p>
                         <div class="rating-container">
                         <div class="cover"></div>
                         <jsuites-rating class="estrellas" value="${peliculaDTO.getPelicula().getCalificacion()}
                         "tooltip="Muy mala, Mala, Regular, Buena, Muy buena"></jsuites-rating>
                         </div>
                         </div>
                         <div class="d-flex justify-content-around">
									
										<c:forEach var="etiqueta" items="${peliculaDTO.getEtiquetas()}">
										<p class="text-light etiquetaDescripcion">${etiqueta.getDescripcion()}</p>
										</c:forEach>
									</div>
                         
                    </div>
                </div>
                </div>
                </div>
              
                <a href="ver-pelicula?pelicula=${peliculaDTO.getPelicula().id}" class="btn btn-lg text-light" id="comprarEntradas">Comprar
                Entradas</a>
            </article>
            
        </c:forEach>
        </section>
		
		</main>
		
		
		
		
		<%@include file="footer.jsp"%>
		
		
	</body>
