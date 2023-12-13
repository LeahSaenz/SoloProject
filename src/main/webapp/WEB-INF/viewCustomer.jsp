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
	<p><a href = "/logout">Log Out</a></p>
	<p><a href = "/admin/dashboard">Go Back to Dashboard</a></p>
	
	<h1>Customer Full Name: <c:out value = "${thisCustomer.fullName}"/></h1>
	
	<h3> Street Address: <c:out value = "${thisCustomer.streetAddress}"/></h3>
	<h3> Email: <c:out value = "${thisCustomer.email}"/></h3>
	<h3> Lawn Maintenance Price: $<c:out value = "${thisCustomer.lawnMaintenancePrice}"/></h3>
	
	<form action = "/delete/customer/${thisCustomer.id}" method = "post">
		<input type = "hidden" name = "_method" value = "delete">
		<input type = "submit" value = "Delete">
	</form>

</body>
</html>