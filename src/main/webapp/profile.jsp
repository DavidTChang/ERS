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
<script src="resources/js/profile.js"></script>
</head>
<body>
<%@ include file="tiles/Header.jsp"%>

	

	<div class="container">
	<h1>Personal Information</h1>
	
	<div class="col-md-2">
	</div>
	
	<div class="col-md-8 jumbotron">
	<form action="updateProfile.do" method="POST" >
		<div class="row">
			<h4 class="col-md-3">First Name:</h4> 
			<input class = "col-md-3" type="text" name = "e_firstname" value="${currentuser.firstName}">
		</div>
		<div class="row">		
			<h4 class="col-md-3">Last Name:</h4>
			<input class = "col-md-3" type="text" name = "e_lastname" value="${currentuser.lastName}">
		</div>
		<div class="row">
			<h4 class="col-md-3">Username:</h4>
			<input class = "col-md-3" type="text" name = "e_username" value="${currentuser.userName}">
		</div>
		<div class="row">
			<h4 class="col-md-3">Email:</h4>
			<input class = "col-md-3" type="text" name = "e_email" value="${currentuser.email}">
		</div>
		<div class="row">
			<h4 class="col-md-3">Password:</h4> 
			<input class = "col-md-3" type="text" name = "e_password" value="${currentuser.password}">
		</div>
		<button type="submit" class="pull-right btn btn-default">Update</button>
	</form>
	</div>
	</div>
</body>
</html>