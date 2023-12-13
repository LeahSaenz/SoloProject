<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!-- FORM:FORM -->

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/static/css/style.css"/>
<meta charset="UTF-8">
<title>Interstate Landscaping</title>
</head>
<body>
	<img src = "/static/img/Original on Transparent.png"/>
	
	<h1>Welcome, Interstate!</h1>
	<p><a href = "/logout">Log Out</a></p>
	<p><a href = "/add/customer">Add a New Customer</a></p>
	<p><a href = "/create/invoice">Add a Service</a></p>
	
	
	<div>
	<h1>Services</h1>
		<table class = "table">
		<thead>
			<tr>
				<th>Service Address</th>
				<th>Total Invoice</th>
				<th>View</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var = "streetAddress" items = "${distinctStreetAddresses }">
			
				<tr>
					
					<td><c:out value = "${ streetAddress }"/></td>
					<td>$<c:out value = "${ totalServicePriceByStreet[streetAddress] }"/>0</td>
					<td>
					
					<a href = "/service/view/${streetAddress}">View All Invoices</a>

					</td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
	</div>
	<div>
		<h1>Customers</h1>
		<table class = "table">
		<thead>
			<tr>
				<th>Name</th>
				<th>Service Address</th>
				<th>Edit</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var = "thisCustomer" items = "${ user }">
			
				<tr>
					
					<td><a href = "/customer/${thisCustomer.id}"><c:out value = "${ thisCustomer.fullName }"/></a></td>
					<td><c:out value = "${ thisCustomer.streetAddress }"/></td>
					<td>
					
					<a href = "/customer/${thisCustomer.id}/edit">Edit</a>
					

					</td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
	</div>

</body>
</html>