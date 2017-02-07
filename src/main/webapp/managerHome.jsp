<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.revature.services.*" %>
<%@ page import="com.revature.pojo.*" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logged in - ${currentuser.firstName}</title>
<%@ include file="tiles/bootstrap.jsp"%>
<link rel="stylesheet" href="resources/css/dabokiStyle.css"/>
<!-- Datatables CSS CDN -->
	<link href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel='stylesheet' type='text/css' >
<!-- jQuery CDN -->
	<script src="https://code.jquery.com/jquery-1.12.3.js" type="text/javascript" ></script>
<!-- Datatable jQuery CDN -->
	<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js" type="text/javascript"></script>
	<script src="resources/js/employee.js"></script>
</head>
<body>
	<%@ include file="tiles/Header.jsp"%>
	<div class="container">
		<div class="col-md-3 col-md-offset-9">
			<h4>Hello, ${currentuser.firstName} ${currentuser.lastName}</h4>
		</div>
		<div class ="panel panel-default col-md-12">
			<div class="panel-heading">Reimbursements</div>
			<div class="panel-body">
			<%
				if (session.getAttribute("rmbmtList") != null) {
			%>	
			<div class="row">
			<h5 class="col-md-1">Status</h5>
			<div class="col-md-1">
			<!-- <form action="filterStatus.do" name="filterForm" method= "POST">
				<select id="filterStatusId" name="filterStatus" 
					onchange="javascript:document.filterForm.submit();">
					<option>All</option>
					<option>PENDING</option>
					<option>APPROVED</option>
					<option>DECLINED</option>
				</select>
			</form> -->
			</div>
				<div class="col-md-2">
					<form action="filterByEmployee.do">
						<select id="selectEmployee" name="filterEmployee">
						</select>
					</form>
				</div>
			</div>
			</div>
				<table class="table" class="empTable" id="empTable">
							<thead>
								<tr>
									<th>Employee</th>
									<th>Amount</th>
									<th>Description</th>
									<th>Date Submitted</th>
									<th>Type</th>
									<th>Status</th>
									<th>Resolved Date</th>
									<th>Reimbursement ID</th>
									<!-- <th>Receipt</th> -->
								</tr>
							</thead>
						
							<tbody>
							
								<c:forEach items="${rmbmtList}" var="rmbmt">
									<% Rmbmt rm = (Rmbmt)pageContext.getAttribute("rmbmt");
									   int authorId = rm.getAuthorId();%>
									<%User user = new UserService().getUserById(authorId); %>
									
									<%String status = ""; %>
									<c:if test="${rmbmt.status.id == 1}">
										<%status ="PENDING"; %>
										<tr class="active">
									</c:if>
									<c:if test="${rmbmt.status.id == 2}">
										<%status ="APPROVED"; %>
										<tr class="success">
									</c:if>
									<c:if test="${rmbmt.status.id == 3}">
										<%status ="DECLINED"; %>
										<tr class="danger">
									</c:if>
									<td><%= user.getFirstName() + " " + user.getLastName()%></td>
									<td id="amount">${rmbmt.amount}</td>
									<td>${rmbmt.desc}</td>
									<td>${rmbmt.submitDate}</td>
									<td>${rmbmt.type.type}</td>
							<td><select id="statusSelect" name="t_status">
									<option>PENDING</option>
									<option>APPROVE</option>
									<option>DECLINE</option>
							</select></td>
							<td>${rmbmt.resolvedDate}</td>
							<td>${rmbmt.id }</td>
	
						</c:forEach>
							</tbody>
						</table>
						<button onclick="updateReimbursements()">Save</button>
						
			<%
				} else {
			%>
				<h4>No Reimbursements yet.</h4>
			<%
				}
			%>
	</div>
	</div>
	<button id="testButton">test</button>
	<script src="resources/js/manager.js">

	
	</script>
</body>
</html>