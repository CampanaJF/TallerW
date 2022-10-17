<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
    <title>Buscar</title>
</head>
<body class="body-perfil">
<%@include file="header.jsp"%>

<c:if test="${not empty peliculas}">
    <div>
        <h2 class="text-white  mt-lg-5 ms-5 titulo-cartelera">Resultados de la búsqueda:</h2>
    </div>
    <section class="d-flex  flex-row flex-wrap text-center">
    <c:forEach items="${peliculas}" var="pelicula">
        <article class="col-lg-4 col-xl-3 mt-4 mb-5 pb-5 ">
                      <div>
                         <a href="ver-pelicula?pelicula=${pelicula.id}" class="text-decoration-none">

                            <img src="${pelicula.poster}" class="mb-2" width="270" height="386" >

                            <h4  class="text-center text-white">${pelicula.titulo}</h4>

                        </a>
                      </div>
        </article>
    </c:forEach>
    </section>
</c:if>

<c:if test="${empty peliculas}">
    <div class="body-perfil">
        <h3 class="text-center text-white mt-3">Lo sentimos, no se encontraron resultados para su búsqueda.</h3>
    </div>
</c:if>

</body>

</html>
