<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="tiles/bootstrap.jsp"%>

<link rel="stylesheet" href="resources/css/dabokiStyle.css"/>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${currentuser.firstName } - Profile</title>

<script src="http://code.jquery.com/jquery.min.js"></script>
</head>
<body>
<%@ include file="tiles/Header.jsp"%>

	

	<div class="container">
	<h1 class="titleFont" id="profileTitle">Personal Information</h1>
	
	<div class="col-md-6 col-md-offset-3 jumbotron">
	<form action="updateProfile.do" method="POST" >
		<div class="row inputRow">
			<div class="col-md-6">
				<label for="firstName">First Name:</label> 
				<input class = "form-control" id="firstName" type="text" name = "e_firstname" value="${currentuser.firstName}">					
			</div>
			<div class="col-md-6">
				<label for="lastName"  >Last Name:</label>
				<input class = "form-control" type="text" id="lastName" name = "e_lastname" value="${currentuser.lastName}">			
			</div>
		</div>
		<div class="row inputRow">
			<div class="col-md-12">
				<label for="email" >Email:</label>
				<input class = "form-control" id ="email" type="text" name = "e_email" value="${currentuser.email}">
			</div>
		</div>
		<div class="row inputRow">
			<div class="col-md-6">
				<label for="username">Username:</label>
				<input class = "form-control" id="username" type="text" name = "e_username" value="${currentuser.userName}">
			</div>
			<div class="col-md-6">
				<label for="password" >Password:</label> 
				<input class = "form-control" id="password" type="text" name = "e_password" value="${currentuser.password}">
			</div>
		</div>
		<div class="pull-right">
			<button type="submit" class="pull-right btn btn-default">Update</button>		
		</div>
	</form>
	</div>
	</div>
</body>
</html>