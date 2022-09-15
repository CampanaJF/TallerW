<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<%@include file="head.jsp"%>
	
	<title>CineClub</title>
	
	<body>

	<%@include file="header.jsp"%>
	
	
		<c:if test="${not empty mensaje}">
	    <div class="p-1 alertbg">
	        <h5 class="text-center text-white">${mensaje}</h5> 	
	    </div>
	    </c:if>
    
    
		<div class = "container">
			<h1>Bienvenidos a Taller Web 1</h1>
		</div>
		
		
		<%@include file="footer.jsp"%>
		
		
	</body>
