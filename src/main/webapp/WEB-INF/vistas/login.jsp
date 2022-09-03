<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<%@include file="head.jsp"%>
	
	<body>
		<div class = "container">
			<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		
				<form:form action="validar-login" method="POST" modelAttribute="datosUsuario">
				
			    	<h3 class="form-signin-heading">Taller Web I</h3>


		
					<form:input path="email" id="email" type="email" class="form-control" />
					<form:input path="password" type="password" id="password" class="form-control"/>     		  
					
					<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Login</button>
					
				</form:form>
				
				<a href="registrarme">Registrarme</a>
				
				<c:if test="${not empty error}">
			        <h4><span>${error}</span></h4>
			        <br>
		        </c:if>
		        
				${msg}
				
			</div>
		</div>
		
		<%@include file="footer.jsp"%>
		
	</body>
</html>
