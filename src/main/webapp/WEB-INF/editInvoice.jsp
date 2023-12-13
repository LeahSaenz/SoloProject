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
		<h2>Edit Service</h2>
	</div>
	<p><a href = "/logout">Log Out</a></p>
	<p><a href = "/admin/dashboard">Go Back to Dashboard</a></p>
	
	<div class = "mb-3">
		<form:form action = "/service/${thisInvoice.id }/update" method = "put" modelAttribute = "thisInvoice">
		<p>
			<form:label class = "form-label" path = "streetAddress">Street Address:</form:label>
			<form:errors path = "streetAddress"/>
			<form:input path = "streetAddress"/>
		</p>
		<p>
			<form:label path = "serviceDate">Service Date:</form:label>
			<form:errors path = "serviceDate"/>
			<form:input path = "serviceDate"/>
		</p>
		<p>
			<form:label class = "form-label" path = "invoiceMonthYear">Invoice Month/Year:</form:label>
			<form:errors path = "invoiceMonthYear"/>
			<form:input path = "invoiceMonthYear"/>
		</p>
		<p>
			<form:label class = "form-label" path = "service">Service:</form:label>
			<form:errors path = "service"/>
			<form:input path = "service"/>
		</p>
		<p>
			<form:label class = "form-label" path = "servicePrice">Service Price:</form:label>
			<form:errors path = "servicePrice"/>
			<form:input type = "number" path = "servicePrice"/>
		</p>
		
		
		<input type = "submit" value = "Edit Service"/>	
				
		</form:form>
		
	</div>

</body>
</html>