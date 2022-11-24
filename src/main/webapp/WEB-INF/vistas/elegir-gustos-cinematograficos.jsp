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
<h1 class="text-center text-white mt-3 mb-2"> Eleg&iacute;  tus generos favoritos para una mejor experiencia</h1>

 <form:form action="procesar-elegir-gustos" method="post" modelAttribute="datosGenero">
       <section class="pt-4 pb-5 mb-5 d-flex flex-wrap text-center">
          <c:forEach items="${generos}" var="genero">
              <article class="col-lg-4 col-xl-3 mt-4 mb-5 pb-5 ">
               <div class="img-fluid">
                   <img src="${genero.poster}" width="200px" height="230px">
               </div>
              <div  class="d-flex  flex-row flex-wrap mt-2 text-center justify-content-center align-items-center" >
                  <form:checkbox path="generos" value="${genero.id}" id="${genero.id}"/>
                  <form:label for="${genero.id}" path="generos" class="text-white"> ${genero.descripcion} </form:label>
              </div>
              </article>
          </c:forEach>
           <form:hidden path="usuario.id" value="${usuario.id}"></form:hidden>
           <div class="d-flex  flex-row flex-wrap align-items-center justify-content-center mt-3" style="position: relative; margin-left: 45%">
               <div class="text-center">
                   <a href="home" class="btn btn-secondary" style="margin-right: 6px">En otro momento</a>
               </div>
               <div class="text-center justify-content-center ">
                   <input type="submit" class="btn buttonA text-white boton-calif " value="Continuar"  >
               </div>
           </div>

</form:form>
    </section>
</body>
</html>
