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
		<h2>Register</h2>
	</div>
	
	<div class = "mb-3">
		<form:form action = "/register" method = "post" modelAttribute = "newUser">
		<p>
			<form:label class = "form-label" path = "fullName">Full Name</form:label>
			<form:errors path = "fullName"/>
			<form:input path = "fullName"/>
		</p>
		<p>
			<form:label class = "form-label" path = "streetAddress">Street Address</form:label>
			<form:errors path = "streetAddress"/>
			<form:input path = "streetAddress"/>
		</p>
		<p>
			<form:label class = "form-label" path = "isCustomer">Is this a Customer?</form:label>
			<form:errors path = "isCustomer"/>
			<form:radiobutton path = "isCustomer" value = "true" label = "Yes"/>
			<form:radiobutton path = "isCustomer" value = "false" label = "No"/>
		</p>
		<p>
			<form:label class = "form-label" path = "isAdmin">Is this an Administrator?</form:label>
			<form:errors path = "isAdmin"/>
			<form:radiobutton path = "isAdmin" value = "true" label = "Yes"/>
			<form:radiobutton path = "isAdmin" value = "false" label = "No"/>
		</p>
		<p>
			<form:label path = "lawnMaintenancePrice">Lawn Maintenance Price:</form:label>
			<form:errors path = "lawnMaintenancePrice"/>
			<form:input type = "number" path = "lawnMaintenancePrice"/>
		</p>
		
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
		<p>
			<form:label path = "confirmPW">Confirm PW:</form:label>
			<form:errors path = "confirmPW"/>
			<form:password path = "confirmPW"/>
		</p>
		
		<input type = "submit" value = "Register"/>		
		
		</form:form>
	</div>

</body>
</html>