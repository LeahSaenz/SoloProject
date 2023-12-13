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
<link rel="stylesheet" href="static/css/style.css"/>
<meta charset="UTF-8">
<title>Interstate Landscaping</title>
</head>
<body>
	<img src = "static/img/Original on Transparent.png"/>
	
	
	<div>
		<h2>Log in</h2>
	</div>
	<div>
		<form:form action = "/login" method = "post" modelAttribute = "newLogin">
		<p>
			<form:label path = "email">Email:</form:label>
			<form:errors path = "email"/>
			<form:input type = "email" path = "email"/>
		</p>
		<p>
			<form:label path = "password">Password:</form:label>
			<form:errors path = "password"/>
			<form:password path = "password"/>
		</p>
		
		<input type = "submit" value = "Login"/>	
		
		</form:form>
	</div>

</body>
</html>