<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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

						<img src="${pelicula.poster}" class="mb-2 mt-5 ">

					</div>
				</div>


				<div class="col align-self-center  text-white">
					<h1 class="titulo-cartelera">${pelicula.titulo}</h1>
					<h6 class="clasif-borde">${pelicula.clasificacionPelicula.descripcion}</h6>
					<h5 class="pb-3">${pelicula.sinopsis}</h5>
					<h5>
						<span class="titulo-cartelera">Direcci&oacute;n</span>
						${pelicula.director}
					</h5>
					<h5>
						<span class="titulo-cartelera">Reparto principal </span>${pelicula.protagonista}</h5>
					<h5>
						<span class="titulo-cartelera">G&eacute;nero </span>${pelicula.genero.descripcion}</h5>
					<h5>
						<span class="titulo-cartelera">Duraci&oacute;n </span>${pelicula.duracionEnHoras}</h5>


					<a href="entrada-pelicula?peliculaId=${pelicula.id}" type="button"
						class="btn-lg btn buttonA mt-3">Comprar Entradas</a>
				</div>

				<div class="col">
					<div class="text-white text-center pt-2 pb-2 mt-5">

						<c:if test="${not empty promedio}">
							<c:if test="${promedio > 0}">
								<h4>Valoraciones de usuarios</h4>
							</c:if>
							<div
								class="d-flex  flex-row flex-wrap align-items-center justify-content-center">
								<c:if test="${promedio > 0}">
									<img src="icons/estrella.png" class="bi me-2" width="40"
										height="40" role="img">
								</c:if>
								<h4 class="mt-2">
									<c:if test="${promedio < 1}">

										<a href="calificar-pelicula?pelicula=${pelicula.id}"
											class="text-decoration-underline" style="color: #ffffff">Sé
											el primero en calificar</a>

									</c:if>
									<c:if test="${promedio >0}">
										<span class="titulo-cartelera" data-bs-toggle="tooltip"
											data-bs-placement="bottom" title="Basado en ${votos} votos">
											${promedio} </span>
									</c:if>


								</h4>
							</div>
						</c:if>
					</div>
					<div class="ms-2">
						<c:if test="${promedio > 0}">
							<div
								class="d-flex 
								gap-3 flex-row flex-wrap align-items-center justify-content-center  mt-5">
								<a href="calificar-pelicula?pelicula=${pelicula.id}"
									class="a-calif"><h2
										class="text-center btn btn-secondary calif-subtitulo">CALIFICAR</h2>
								</a> <a href="ver-opiniones?pelicula=${pelicula.id}" class="a-calif"><h2
										class="text-center btn btn-secondary calif-subtitulo">VER
										RESEÑAS</h2> </a>

							</div>
						</c:if>

					</div>
				</div>
			</div>
		</div>
	</c:if>

	<div class="container d-flex mt-5">
	<div id="map" class="m-auto"></div>
	</div>
	
	<div class="container mt-3">
		<h2 class="text-white mt-lg-5 ms-5 mb-2">Peliculas similares</h2>


		<section class="d-flex flex-row flex-wrap text-center">

			<c:forEach items="${similares}" var="pelicula">
				<article class="col-lg-4 col-xl-3 mt-4 mb-5 pb-5 ">

					<div class="fila">
						<div class="tile">
							<img class="pelicula" src="${pelicula.poster}" alt="">
							<div class="overlay ">
								<a href="ver-pelicula?pelicula=${pelicula.id}"
									class="text-decoration-none"> <img class="pelicula"
									src="${pelicula.poster}">
								</a>
								<div class="container informacionPeli d-flex flex-column">
									<div class="row">
										<div class="col-lg-4 mt-2">
											<p class="text-light clasificacion">${pelicula.clasificacionPelicula.descripcion}</p>
										</div>
										<div class="col-lg-4 mt-2">
											<p class="text-light duracion">${pelicula.duracionEnHoras}</p>
										</div>
										<div class="col-lg-4  fechaEstreno mt-2">
											<p class="text-light">${pelicula.fechaEstreno.year+1900}</p>
										</div>

									</div>
									<div class="d-flex justify-content-around">

										<p class="text-light genero mt-1">${pelicula.genero.descripcion}</p>

										<div class="rating-container">
											<div class="cover"></div>
											<jsuites-rating class="estrellas"
												value="${pelicula.calificacion}
                         "
												tooltip="Muy mala, Mala, Regular, Buena, Muy buena"></jsuites-rating>
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
	
	<script>
	const data=${listadoDeCines}
	
	</script>
	<script async src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkdOr5LUWRDGdX1rrdIzJGr5DN_hXoBUE
   &libraries=places&callback=initMap">

</script>
<script src="js/map.js"></script>
	<%@include file="footer.jsp"%>