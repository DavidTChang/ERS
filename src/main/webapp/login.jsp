<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<%@ include file="tiles/bootstrap.jsp"%>


<body>
	<c:if test="${wrongPass != null }">
	<div class="row">
		<div class="col-md-4"></div>
		<div class="alert alert-danger col-md-3" role="alert">
			<strong>Invalid password or username.</strong> 
		</div>
	</div>
	</c:if>
	<div class="col-md-4">
	
	</div>
	
	<div class="container">
		<div class="col-md-4 ">
			<form method="POST" class="form-signin" action="login.do">
				<h2 class="form-signin-heading">Avenging The Costs </h2>
				<label for="inputUsername" class="sr-only">Username</label> 
				<input name="username" type="text" id="userName" class="form-control" placeholder="Username" 
				required autofocus> 
				<label for="inputPassword" class="sr-only">Password</label> 
					<input type="password" id="inputPassword" name="password" class="form-control" 
						placeholder="Password" required>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
					in</button>
			</form>

		</div>
	</div>
</body>
</html>