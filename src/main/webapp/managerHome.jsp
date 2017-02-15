<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
</head>
<body>
	<%@ include file="tiles/Header.jsp"%>
	<div class="container">
	<div class="row">
		<div class="col-md-3 col-md-offset-9">
			<h4 class="userWelcomeText">Hello, ${currentuser.firstName} ${currentuser.lastName}</h4>
		</div>
	</div>
		<div class ="panel panel-default">
			<div class="panel-heading">Reimbursements</div>
			<div class="panel-body">
			<%
				if (session.getAttribute("rmbmtList") != null) {
			%>	
				<div class="col-md-2">
					<h5>Status</h5>
					<select id="filterStatusId" name="filterStatus" 
						onchange="filterStatus()">
						<option>All</option>
						<option>Pending</option>
						<option>Approved</option>
						<option>Declined</option>
					</select>
				</div>
			<div class="col-md-2">
				<h5>Employees</h5>
				<form action="filterByEmployee.do">
					<select id="selectEmployee" name="filterEmployee"
					onchange ="filterEmployees()">
					</select>
				</form>
			</div>
			<div >
				<button onclick="updateReimbursements()" class=" btn pull-right btn-primary">Save</button>
			</div>
			</div>
			<div class="divScroll">
				<table class="table table-bordered tableScroll" class="empTable" id="empTable">
							<thead>
								<tr class="header">
									<th>Employee</th>
									<th>Amount</th>
									<th>Description</th>
									<th>Date Submitted</th>
									<th>Type</th>
									<th>Status</th>
									<th>Resolved Date</th>
									<th>Reimbursement ID</th>
									<th>Receipt</th> 
								</tr>
							</thead>
						
							<tbody>
							
								<c:forEach items="${rmbmtList}" var="rmbmt">
								<fmt:formatNumber type="currency" value="${rmbmt.amount}" var="newAmountVar"></fmt:formatNumber>
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
									<td id="amount">${newAmountVar}</td>
									<td>${rmbmt.desc}</td>
						<td><fmt:formatDate type="both" dateStyle="short"
								timeStyle="short" value="${rmbmt.submitDate}" /></td>
						<td>${rmbmt.type.type}</td>
						<c:if test="${rmbmt.status.id == 1 }">

							<td><select id="statusSelect" name="t_status">
									<option selected="selected">PENDING</option>
									<option>APPROVE</option>
									<option>DECLINE</option>

							</select></td>
						</c:if>
						<c:if test="${rmbmt.status.id == 2 }">

							<td><!-- <select id="statusSelect" name="t_status">
									<option>PENDING</option>
									<option selected="selected">APPROVE</option>
									<option>DECLINE</option>

							</select> -->APPROVED</td>
						</c:if>
						<c:if test="${rmbmt.status.id == 3 }">

							<td><!-- <select id="statusSelect" name="t_status">
									<option>PENDING</option>
									<option>APPROVE</option>
									<option selected="selected">DECLINE</option>

							</select> -->DECLINED</td>
						</c:if>
						<td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${rmbmt.resolvedDate}"/> </td>
						<td class="r_id_for_image">${rmbmt.id }</td>
													
						 <c:if test="${rmbmt.receiptBlob != null }">
						<td><a href="data:image/jpeg;base64,${rmbmt.getImageString()}">Receipt</a></td>
						</c:if> 
						<c:if test="${rmbmt.receiptBlob == null }">
							<td>N/A</td>
						</c:if> 						
						</c:forEach>
							</tbody>
						</table>
						</div>

			<%
				} else {
			%>
				<h4>No Reimbursements yet.</h4>
			<%
				}
			%>
	</div>
	</div>
	<script src="resources/js/manager.js"></script>
	<script type="text/javascript" src="resources/js/dataTable.js"></script> 

	
	
</body>
</html>