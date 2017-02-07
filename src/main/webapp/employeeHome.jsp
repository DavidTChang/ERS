<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.revature.enums.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<body>
	<%@ include file="tiles/Header.jsp"%>
	<div class="container">
	<div class="row">
		<div class="col-md-10">
			
		</div>
		<div class="col-md-2">
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#reimbursePop">New
				Reimbursement</button>
				
				<div class="modal fade" id="reimbursePop" tabindex="-1" role="dialog"
				aria-labelledby="reimburseLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="reimburseLabel">New
								Reimbursement</h4>
						</div>
						<form method="POST" action="newrmbmt.do">
						<div class="modal-body container">
								<div class="row">
									<div class="form-group col-md-2">
										<label for="recipient-name" class="control-label">Amount:</label>
										<input type="number" class="form-control" name="amount" id="recipient-name" required>
									</div>
									<div class = "form-group col-md-2">
									<label for="sel1">Type</label> 
									<select id="sel1" name="type">
										<c:forEach items="${RType.values()}" var="crntType">
											<option>${crntType.type}</option>
										</c:forEach>
									</select>
									</div>
								</div>
								<div class="form-group col-md-4">
									<label for="message-text" class="control-label">description</label>
									<textarea class="form-control" id="message-text" name="desc" required
									placeholder="Description"></textarea>
								</div>
							
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
							<button type="submit"  class="btn btn-primary">Create</button>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- End of row -->
<div class="container">
	<div class="row">
	
		<div>

				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">Reimbursements</div>
					<div class="panel-body">
				<%
					if (session.getAttribute("rmbmtList") != null) {
				%>
<!-- 						<form action="filterStatus.do" name="filterForm" method= "POST">
							<select id="filterStatusId" name="filterStatus" 
								onchange="javascript:document.filterForm.submit();">
								<option>Filter</option>
								<option>ALL</option>
								<option>PENDING</option>
								<option>APPROVED</option>
								<option>DECLINED</option>
							</select>
						</form> -->
							<select id="filterStatusId" name="filterStatus" 
								onchange="filterStatus()">
								<option>All</option>
								<option>Pending</option>
								<option>Approved</option>
								<option>Declined</option>
							</select>
					</div>
					<!-- Table -->
					<table class="table" class="empTable" id="empTable">
					
					<c:if test="${addRows != null }">
						<c:set var="appendRows" scope="session" value ="${true }"/>
					</c:if>
					<c:if test="${addRows == null }">
						<c:set var="appendRows" scope="session" value ="${false }"/>
					</c:if>
					
						<thead>
							<tr>
								<th>Amount</th>
								<th>Description</th>
								<th>Date Submitted</th>
								<th>Type</th>
								<th>Status</th>
								<th>Resolved Date</th>	
								<th>Manager</th>
<%-- 								<c:if test="${appendRows == true }">

								</c:if> --%>
								<!-- <th>Receipt</th> -->
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${rmbmtList}" var="rmbmt">
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

								<td>${rmbmt.amount}</td>
								<td>${rmbmt.desc}</td>
								<td>${rmbmt.submitDate}</td>
								<td>${rmbmt.type.type}</td>
								<td><%= status %></td>
								<td>${rmbmt.resolvedDate}</td>
								<c:set var="user" scope="session" value="${addRows.get(rmbmt.id) }"/>
								<c:set var="managerName" scope="session" value= "${user.firstName }" />
								<c:set var="managerLName" scope="session" value="${user.lastName }"/>
								<td>${managerName} ${managerLName}</td>
				<%-- 				<c:if test="${appendRows == true }">
				
								</c:if> --%>

							</c:forEach>
						</tbody>
					</table>
					<%
						} else{
					%>
						<h3>No Reimbursements yet</h3>
					
					<%
						}
					%>
				</div>
			</div>

	</div>
	</div>
		<script type="text/javascript" src="resources/js/employee.js"></script></head> 
	
	<script src ="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
</body>
</html>