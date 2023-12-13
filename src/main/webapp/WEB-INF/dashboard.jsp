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
	
	<h1><c:out value = "${loggedUser.streetAddress }"/></h1>
	<p><a href = "/logout">Log Out</a></p>
	
	<div>
		<table class = "table">
		<thead>
			<tr>
				<th>Service Date</th>
				<th>Service</th>
				<th>Price</th>
			</tr>
		</thead>
		<tbody>			
			
			<c:forEach var = "thisInvoice" items = "${ invoice }">
			
				<tr>
				<c:if test = "${ loggedUser.streetAddress == thisInvoice.streetAddress }">					
					<td><c:out value = "${ thisInvoice.serviceDate }"/></td>
					<td><c:out value = "${ thisInvoice.service }"/></td>
					<td>$<c:out value = "${ thisInvoice.servicePrice }"/>.00</td>
				</c:if>
				</tr>
			
			</c:forEach>
			
		</tbody>
	</table>
	</div>

</body>
</html>