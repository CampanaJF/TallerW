<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="css/form.css" rel="stylesheet">
    <link rel="stylesheet" href="css/cartelera.css">
    <link href="css/font.css" rel="stylesheet">
    <link href="css/estilos.css" rel="stylesheet">

    <title>Elegir gustos</title>
</head>
<body class="bgColor">
<%@include file="header.jsp"%>
<c:if test="${not empty mensaje}">
    <div class="p-1 alertbg">
        <h5 class="text-center text-white">${mensaje}</h5>
    </div>
</c:if>
<h1 class="text-center text-white mt-3 mb-2"> Elige alguno de estos generos para una mejor experiencia</h1>
<section class="d-flex  flex-row flex-wrap text-center justify-content-center align-items-center">
<form:form action="procesar-elegir-gustos" method="post" modelAttribute="datosGenero">
        <article class="col-4 p-5 m-5">
          <c:forEach items="${generos}" var="genero">
              <form:checkbox path="id" value="${genero.id}" id="${genero.id}"/>
              <form:label for="${genero.id}" path="id" class="text-white"> ${genero.descripcion} </form:label>
          </c:forEach>
           <div>
               <form:button class="buttonA mt-3">Continuar</form:button>
           </div>
        </article>
</form:form>
</section>
</body>
</html>
