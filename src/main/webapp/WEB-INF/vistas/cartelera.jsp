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
		<div class="modal-header" id="modal-header">
		  <h5 class="modal-title text-light" id="exampleModalLabel">Filtrar pelicula por:</h5>
		  <button type="button" class="btn-close text-light cruz" data-bs-dismiss="modal" aria-label="Close"></button>
		</div>
		<div class="modal-body bg-dark">
			<div class="accordion bg-dark" id="accordionExample">
				<div class="accordion-item bg-dark">
				  <h2 class="accordion-header" id="headingOne">
					<button class="accordion-button bg-dark text-light border border-danger mb-2" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
					  G&eacute;neros
					</button>
				  </h2>
				  <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
					<div class="accordion-body">
						<section class="d-flex justify-content-space-around flex-wrap">
							<div class="col-lg-4 mb-3">
								<div class="chip">
									<div class="chip-content text-light">Acci&oacute;n</div>
									
								  </div>
							</div>
							<div class="col-lg-4 mb-3">
								<div class="chip" >
									<div class="chip-content text-light pildora">Comedia</div>
									
								  </div>
							</div>
							<div class="col-lg-4 mb-3">
								<div class="chip">
									<div class="chip-content text-light ">Terror</div>

								  </div>
							</div>
							<div class="col-lg-4 mb-3">
								<div class="chip">
								<div class="chip-content text-light ">Fantasia</div>
							  </div>
							</div>
							
							  <div class="col-lg-4 mb-3">
								<div class="chip">
									<div class="chip-content text-light ">Escalofrios</div>
								  </div>
							  </div>
							  <div class="col-lg-4 mb-3">
								<div class="chip">
									<div class="chip-content text-light ">Anime</div>
								  </div>
							  </div>
							  <div class="col-lg-4 mb-3">
								<div class="chip">
									<div class="chip-content text-light ">Trama</div>
								  </div>
							  </div>
				
						</section>
					</div>
				  </div>
				</div>
				<div class="accordion-item bg-dark">
				  <h2 class="accordion-header" id="headingTwo">
					<button class="accordion-button collapsed text-light bg-dark border border-danger mb-2" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
					  Actor
					</button>
				  </h2>
				  <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
					<div class="accordion-body">
						<section class="d-flex justify-content-space-around flex-wrap">
						<div class="col-lg-4 mr-2 mb-3">
							<div class="chip">
								<div class="chip-content text-light ">Nathalie Emmanuel </div>
							  </div>
						  </div>
						  <div class="col-lg-4 mb-3">
							<div class="chip">
								<div class="chip-content text-light ">Thomas Doherty</div>
							  </div>
						  </div>
						  <div class="col-lg-4 mb-3">
							<div class="chip">
								<div class="chip-content text-light ">Trama</div>
							  </div>
						  </div>
						  <div class="col-lg-4 mb-3">
							<div class="chip">
								<div class="chip-content text-light ">Adrian Suar</div>
							  </div>
						  </div>
						</section>
					</div>
				  </div>
				</div>
				<div class="accordion-item bg-dark">
				  <h2 class="accordion-header" id="headingThree">
					<button class="accordion-button collapsed bg-dark text-light border border-danger" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
					  Director
					</button>
				  </h2>
				  <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
					<div class="accordion-body">
					  <strong>This is the third item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
					</div>
				  </div>
				</div>
			  </div>
		</div>
		<div class="modal-footer" id="modal-footer">
		  <button type="button" class="btn btn-danger">Guardar</button>
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
				<div class="contenedor-carta">
					<div class="carta">
						<div class="carta-delantera bg-dark">
							<img class="pelicula" src="img/peliculas/30-noches-con-mi-ex.jpg" alt="">
						</div>
						<div class="carta-trasera p-0 m-0 ">
						<div class="container d-flex justify-content-center flex-column pt-5 ">
							<div class="col-lg-12">
								<i class="bi bi-clock text-light"></i>
								<p class="text-light ml-2">Duraci&oacute;n: 95 min</p>
							</div>
							<div class="col-lg-12">
								
							<i class="bi bi-emoji-smile text-light"></i>
								<p class="text-light">Calificaci&oacute;n: +13</p>
							</div>
							<div class="col-lg-12 mb-3">
								<i class="bi bi-film text-light"></i>
								<p class="text-light">Categoria: Comedia</p>
							</div>
							
							<p class="text-light">Fecha De Estreno: 11/08/2022</p>
						</div>
					
						</div>
					</div>
					
					
				<div class="container ">	
					
				</div>
				<h6 class="text-light text-center mt-3">30 noches con mi ex</p>
					<button class="btn btn-lg text-light" id="comprarEntradas">Comprar Entradas</button>
			</article>

			<!--
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
		-->
		</section>


	<!-- Dejarlo para la iteracion y que muestre todas las peliculas disponibles
		<c:forEach var="peliculas" items="${cartelera.sucursales}" begin="0">
			<option value="${peliculas.nombre}">${peliculas.nombre}</option>
		</c:forEach>
	-->
	</main>
	
	<%@include file="footer.jsp"%>

