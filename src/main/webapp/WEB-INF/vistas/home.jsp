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
		
		<h1 class="text-light fw-bolder mt-5">Anticipadas:</h1>
		
		
		
		
		<h1 class="text-light fw-bolder mt-5">Estrenos del mes:</h1>
		<section class="pt-4 pb-5 mb-5 d-flex flex-wrap text-center">
        <c:forEach var="pelicula" items="${peliculasEstrenos}">
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
                         <div class="row">
                         <p class="text-light genero">${pelicula.getGenero().getDescripcion() }</p>
                         <p class="calificacion">Calificacion</p>
                         </div>
                         
                         
                    </div>
                </div>
                </div>
                </div>
              
                <button class="btn btn-lg text-light" id="comprarEntradas">Comprar
                Entradas</button>
            </article>
            
        </c:forEach>
        </section>
		
		</main>
		
		
		
		
		<%@include file="footer.jsp"%>
		
		
	</body>
