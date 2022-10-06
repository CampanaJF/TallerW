<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>	

	<%@include file="head.jsp"%>

	<title>Perfil</title>
	
<body class="body-perfil">

<%@include file="header.jsp"%>
<div class="container">
	<div >
		<div class="profile-title " >
			<span>PERFIL</span>
		</div>
	</div>

	<div class="profile-form-content">
		<div class="row">
			<div class="col-xs-12col-md-6">
				<div class="form-input">
					<div class="row">
						<div class="col-xs-12 col-sm-4">
							<div class="form-input-title">
								<span>NOMBRE</span>
							</div>
						</div>
						<div class="col-xs-12 col-sm-4">
							<div class="form-input-box">
								<input name="nombre" type="text" value="${datosUsuario.nombre}" id="nombre" required >
							</div>
						</div>

					</div>

				</div>
				<div class="form-input">
					<div class="row">
						<div class="col-xs-12 col-sm-4">
							<div class="form-input-title">
								<span>APELLIDO</span>
							</div>
						</div>
						<div class="col-xs-12 col-sm-4">
							<div class="form-input-box">
								<input name="apellido" type="text" value="" id="apellido" required>
							</div>
						</div>
					</div>
				</div>

				<div class="form-input">
					<div class="row">
						<div class="col-xs-12 col-sm-4">
							<div class="form-input-title">
								<span>TELEFONO</span>
							</div>
						</div>
						<div class="col-xs-12 col-sm-4">
							<div class="form-input-box">
								<input name="telefono" type="tel" value="" id="telefono" required>
							</div>
						</div>
					</div>
				</div>
				<div class="form-input">
					<div class="row">
						<div class="col-xs-12 col-sm-4">
							<div class="form-input-title">
								<span>EMAIL</span>
							</div>
						</div>
						<div class="col-xs-12 col-sm-4">
							<div class="form-input-box">
								<span id="email">${datosUsuario.email}</span>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>
<%@include file="footer.jsp"%>

</body>
</html>