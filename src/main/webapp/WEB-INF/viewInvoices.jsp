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
	
	<div>
		<h2>All Invoices for <c:out value = "${streetAddress }"/></h2>
	</div>
	<p><a href = "/logout">Log Out</a></p>
	<p><a href = "/admin/dashboard">Go Back to Dashboard</a></p>
	
	
			<c:forEach var = "invoice" items = "${invoices }">
			
				<h3>Invoice Number: <c:out value = "${invoice.id}"/></h3>
				<h5>Service Date: <c:out value = "${invoice.serviceDate}"/></h5>
				<h5>Invoice Month/Year: <c:out value = "${invoice.invoiceMonthYear}"/></h5>
				<h5>Service: <c:out value = "${invoice.service }"/></h5>
				<h5>Service Price: <c:out value = "${invoice.servicePrice }"/></h5>
				<a href = "/service/${invoice.id}/edit">Edit</a> |
					<form action = "/delete/service/${invoice.id}" method = "post">
						<input type = "hidden" name = "_method" value = "delete">
						<input type = "submit" value = "Delete">
					</form>
				
			</c:forEach>
			
		
	
	
	

</body>
</html>