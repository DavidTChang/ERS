<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="resources/js/easterEgg.js"></script>
<title>Login</title>
<%@ include file="tiles/bootstrap.jsp"%>
<link rel="stylesheet" type="text/css"  href="resources/css/dabokiStyle.css"/>
</head>
<body id="bodyImg">
	<c:if test="${wrongPass != null }">
	<div class="row">
		<div class="col-md-4"></div>
		<div class="alert alert-danger col-md-3" role="alert">
			<strong>Invalid password or username.</strong> 
		</div>
	</div>
	</c:if>

	
	<div class="container" id ="loginContainer">
		<div class="col-md-6 col-md-offset-3 text-center well">
			<form method="POST" class="form-signin" action="login.do">
				<h2 class="form-signin-heading text-center" id="loginTitleFont">
					Avenging The Costs
				</h2>			
				<div class="row">
					<div class="col-md-8 col-md-offset-2 loginField">
						<label for="inputUsername" class="sr-only">Username</label> 
						<input name="username" type="text" id="inputUsername" class="form-control" placeholder="Username" required autofocus> 
					</div>
				</div>
				<div class="row">
					<div class="col-md-8 col-md-offset-2 loginField">
						<label for="inputPassword" class="sr-only">Password</label> 
						<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>						
					</div>
				</div>
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<div class="row">
							<div class="col-md-4 pull-right">
								<button class="btn btn-primary btn-block" type="submit">Sign in</button>						
							</div>						
						</div>
					</div>
				</div>								
			</form>

		</div>

	</div>
<!-- 					<button onclick="changeBackground()" class="btn">M</button>
		<img id="bob" alt="" src="resources/images/Bob-Ross.png"> -->
</body>
</html>