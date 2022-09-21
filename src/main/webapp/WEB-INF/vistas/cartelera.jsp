<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="head.jsp"%>
<title>Cartelera</title>
<body class="body-cartelera">

	<%@include file="header.jsp"%>
	<main class="container">
		<h1 class="text-white mt-lg-5 ml-lg-5 titulo-cartelera">CARTELERA</h1>
		<div class="container d-flex flex-no-wrap mt-lg-4">
		<h4 class="text-white ml-lg-5 mt-lg-2">Filtrar:</h4>
		
			<button type="button" class="btn btn-filtrar" data-bs-toggle="modal" data-bs-target="#exampleModal">
				<i class="bi bi-funnel-fill text-light"></i>
			  </button>
			  
			
	
  <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
	  <div class="modal-content">
		<div class="modal-header">
		  <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
		  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		</div>
		<div class="modal-body">
		  ...
		</div>
		<div class="modal-footer">
		  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		  <button type="button" class="btn btn-primary">Save changes</button>
		</div>
	  </div>
	</div>
  </div>

		</div>
		
		<section>
			<div class="chip mt-5 mr-5">
				<div class="chip-content text-light">Acci&oacute;n</div>
				
				<div class="chip-close" id="closeIcon" onclick="eliminarFiltro(this)">
					<svg class="chip-svg text-light" focusable="false" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm5 13.59L15.59 17 12 13.41 8.41 17 7 15.59 10.59 12 7 8.41 8.41 7 12 10.59 15.59 7 17 8.41 13.41 12 17 15.59z"></path></svg>	
				</div>
			  </div>
			  <div class="chip mt-5" >
				<div class="chip-content text-light pildora">Comedia</div>
				
				<div class="chip-close" onclick="eliminarFiltro(this)">
					<svg class="chip-svg text-light" focusable="false" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm5 13.59L15.59 17 12 13.41 8.41 17 7 15.59 10.59 12 7 8.41 8.41 7 12 10.59 15.59 7 17 8.41 13.41 12 17 15.59z"></path></svg>	
				</div>
			  </div>

			  <div class="chip mt-5" onclick="eliminarFiltro(this)">
				<div class="chip-content text-light ">Terror</div>
				
				<div class="chip-close">
					<svg class="chip-svg text-light" focusable="false" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm5 13.59L15.59 17 12 13.41 8.41 17 7 15.59 10.59 12 7 8.41 8.41 7 12 10.59 15.59 7 17 8.41 13.41 12 17 15.59z"></path></svg>	
				</div>
			  </div>
	
		</section>
		<section class="pt-4 pb-5 mb-5 d-flex flex-wrap text-center">
			
			<article class="col-lg-4 col-xl-3 mt-4 ">
				<div class="container ">
				<img class="pelicula"src="img/peliculas/30-noches-con-mi-ex.jpg" alt="">
			</div>
				<h6 class="text-light text-center mt-3">30 noches con mi ex</p>
			</article>
			<article class="col-lg-4 col-xl-3  mt-4">
				<div class="container">
					<img class="pelicula" src="img/peliculas/barbaro.jpg" alt="">
				</div>
					<h6 class="text-light text-center mt-3">Barbaro</p>
			</article>
			<article class="col-lg-4 col-xl-3 mt-4">
				<div class="container">
					<img class="pelicula" src="img/peliculas/bienvenidos-al-infierno.jpg" alt="">
				</div>
					<h6 class="text-light text-center mt-3">Bienvenidos al infierno</p>
			</article>
			
			<article class="col-lg-2 mt-4">
				<div class="container">
					<img class="pelicula" src="img/peliculas/carajita.jpg" alt="">
				</div>
					<h6 class="text-light text-center mt-3">Carajita</p>
			</article>
		
		</section>


	<!-- Dejarlo para la iteracion y que muestre todas las peliculas disponibles
		<c:forEach var="peliculas" items="${cartelera.sucursales}" begin="0">
			<option value="${peliculas.nombre}">${peliculas.nombre}</option>
		</c:forEach>
	-->
	</main>
	
	<%@include file="footer.jsp"%>

