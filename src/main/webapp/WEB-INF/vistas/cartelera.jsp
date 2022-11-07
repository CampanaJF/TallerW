<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="head.jsp"%>
<title>Cartelera</title>
<body class="body-cartelera">
	<%@include file="header.jsp"%>
	<main class="container">
		<h1 class="text-white mt-lg-5 ml-lg-5 titulo-cartelera">CARTELERA</h1>
		<div class="container d-flex flex-no-wrap mt-lg-4">
			<h4 class="text-white ml-lg-5 mt-lg-2">Filtrar:</h4>
			<button type="button" class="btn btn-filtrar" data-bs-toggle="modal"
				data-bs-target="#exampleModal">
				<i class="bi bi-funnel-fill text-light"></i>
			</button>



			<button type="button" class="btn btn-filtrar mx-3 text-light"
				data-bs-toggle="modal" data-bs-target="#exampleModal1">Ordenar
				por</button>

			<c:if test="${filtrosSeleccionados.size()>0}">
				<button type="button" class="btn mx-3 btn-danger btn-eliminarFiltro"
					onclick="limpiarFiltros()">
					<div class="chip-close d-inline mx-1 mr-0" id="closeIcon">
						<svg class="chip-svg text-light" focusable="false"
							viewBox="0 0 24 24" aria-hidden="true">
                            <path
								d="M12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm5 13.59L15.59 17 12 13.41 8.41 17 7 15.59 10.59 12 7 8.41 8.41 7 12 10.59 15.59 7 17 8.41 13.41 12 17 15.59z"></path>
                        </svg>
					</div>
					Limpiar Filtros
				</button>
			</c:if>

			<!-- Modal -->
			<div class="modal fade" id="exampleModal1" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content bg-dark">
						<form action="">
							<input type="hidden" id="ordenFiltro" name="orden" value="">

							<div class="modal-header bg-dark">
								<h1 class="modal-title text-light fs-5" id="exampleModalLabel">Ordenar
									pelicula por:</h1>
								<button type="button" class="btn-close text-light cruz"
									data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body bg-dark">
								<section id="orden"
									class="d-flex justify-content-space-around flex-wrap">
									<div class="mb-3">

										<div class="chip" onclick="seleccionarOrden(this,'orden')"
											id="Director">
											<!-- 
												<div class="chip-content text-light">Director</div>
												 -->
											<button class="btn">
												<div class="chip-content text-light">Director</div>
											</button>

										</div>
									</div>

									<div class="mb-3">
										<div class="chip" onclick="seleccionarOrden(this,'orden')"
											id="Titulo">
											<button class="btn">
												<div class="chip-content text-light">Titulo</div>
											</button>
										</div>
									</div>
									<div class="mb-3">
										<div class="chip" onclick="seleccionarOrden(this,'orden')"
											id="Calificacion">
											<button class="btn">
												<div class="chip-content text-light">Valoracion</div>
											</button>
										</div>
									</div>
								</section>





							</div>
							<div class="modal-footer bg-dark"></div>
						</form>
					</div>

				</div>
			</div>


			<!-- Modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<form action="">
							<input type="hidden" id="generoFiltro" name="genero" value="">
							<input type="hidden" id="clasificacionFiltro"
								name="clasificacion" value="">
							<div class="modal-header" id="modal-header">
								<h5 class="modal-title text-light" id="exampleModalLabel">Filtrar
									pelicula por:</h5>
								<button type="button" class="btn-close text-light cruz"
									data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body bg-dark">
								<div class="accordion bg-dark" id="accordionExample">
									<div class="accordion-item bg-dark">
										<h2 class="accordion-header" id="headingOne">
											<button
												class="accordion-button bg-dark text-light border border-danger mb-2"
												type="button" data-bs-toggle="collapse"
												data-bs-target="#collapseOne" aria-expanded="true"
												aria-controls="collapseOne">G&eacute;neros</button>
										</h2>
										<div id="collapseOne" class="accordion-collapse collapse show"
											aria-labelledby="headingOne"
											data-bs-parent="#accordionExample">
											<div class="accordion-body">
												<section id="filtroGenero"
													class="d-flex justify-content-space-around flex-wrap">
													<c:forEach var="genero" items="${generos}">
														<div class="mb-3">
															<div class="chip"
																onclick="seleccionarFiltro(this,'filtroGenero')"
																id="${genero.getId()}">
																<button class="btn">
																	<div class="chip-content text-light">${genero.getDescripcion()}</div>
																</button>
															</div>
														</div>
													</c:forEach>
												</section>
											</div>
										</div>
									</div>
									<div class="accordion-item bg-dark">
										<h2 class="accordion-header" id="headingTwo">
											<button
												class="accordion-button collapsed text-light bg-dark border border-danger mb-2"
												type="button" data-bs-toggle="collapse"
												data-bs-target="#collapseTwo" aria-expanded="false"
												aria-controls="collapseTwo">Clasificaci&oacute;n</button>
										</h2>
										<div id="collapseTwo" class="accordion-collapse collapse"
											aria-labelledby="headingTwo"
											data-bs-parent="#accordionExample">
											<div class="accordion-body">
												<section id="clasificacion"
													class="d-flex justify-content-space-around flex-wrap">
													<c:forEach var="clasificacion" items="${clasificaciones}">
														<div class="mb-3">
															<div class="chip"
																onclick="seleccionarFiltro(this,'clasificacion')"
																id="${clasificacion.getId()}">
																<button class="btn">
																	<div class="chip-content text-light">${clasificacion.getDescripcion()}</div>
																</button>
															</div>
														</div>
													</c:forEach>
												</section>
											</div>
										</div>
									</div>

								</div>
							</div>
							<div class="modal-footer" id="modal-footer"></div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<section>
			<c:forEach var="filtro" items="${filtrosSeleccionados}">
				<div class="chip mt-5 mr-5">
					<div class="chip-content text-light">${filtro}</div>
				</div>
			</c:forEach>
		</section>
		<section class="pt-4 pb-5 mb-5 d-flex flex-wrap text-center">
			<c:forEach var="peliculaDTO" items="${peliculasDTO}">
				<article class="col-lg-4 col-xl-3 mt-4 mb-5 pb-5 ">
					<div class="fila">
						<div class="tile">
							<img class="pelicula" src="${peliculaDTO.getPelicula().getPoster()}" alt="">
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
											<jsuites-rating class="estrellas"
												value="${peliculaDTO.getPelicula().getCalificacion()}
                         "
												tooltip="Muy mala, Mala, Regular, Buena, Muy buena"></jsuites-rating>
										</div>


									</div>
									<div class="d-flex justify-content-around">
									
										<c:forEach var="etiqueta" items="${peliculaDTO.getEtiquetas()}">
										<span class="text-light  etiquetaDescripcion">${etiqueta.getDescripcion()}</span>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
					<a href="ver-pelicula?pelicula=${peliculaDTO.getPelicula().id}"
						class="btn btn-lg text-light" id="comprarEntradas">Comprar
						Entradas</a>
				</article>

			</c:forEach>
		</section>
	</main>
	<%@include file="footer.jsp"%>