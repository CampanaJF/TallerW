<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<%@include file="head.jsp"%>
	
	<title>CineClub</title>
	
	<body>
	
	
		<c:if test="${not empty mensaje}">
	    <div>
	        <p>${mensaje}</p> 	
	    </div>
	    </c:if>
    
    
		<div class = "container">
			<h1>Bienvenidos a Taller Web 1</h1>
		</div>
		
		
		<%@include file="footer.jsp"%>
		
		
	</body>
	
	
</html>