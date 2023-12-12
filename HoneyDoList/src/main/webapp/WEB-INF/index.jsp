<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Honey Do List</title>
</head>
<body>

<div class="container d-display-flex p-3 my-3 bg-dark text-white ">

		<h1 class="text-info">Welcome to your Honey-Do-List!</h1>

</div>
<div class="container flex-fill d-flex p-3 my-3 bg-dark text-white ">
	<div class="flex-fill">
		<h2>Register</h2>

		<form:form action="/register" method="post"  modelAttribute="newUser">
		
			<p>
			    <form:label path="firstName">First Name:</form:label>
			    <form:errors path="firstName"/>
			    <form:input path="firstName"/>
			</p>
			    
			<p>
			    <form:label path="lastName">Last Name:</form:label>
			    <form:errors path="lastName"/>
			    <form:input path="lastName"/>
			</p>
		
		
		    <p>
		        <form:label path="email">Email:</form:label>
		        <form:errors path="email"/>
		        <form:textarea type="email" path="email"/>
		    </p>
	
		    <p>
		        <form:label path="password">Password:</form:label>
		        <form:errors path="password"/>     
		        <form:input type="password" path="password"/>
		    </p>

		    <p>
		        <form:label path="confirm">Confirm PW:</form:label>
		        <form:errors path="confirm"/>     
		        <form:input type="password" path="confirm"/>
		    </p>  
   
		    <input type="submit" value="Submit"/>
		</form:form>
	</div>
	<div class="flex-fill">
		<h2>Log In</h2>
		<form:form action="/login" method="post" modelAttribute="newLogin">
		    <p>
		        <form:label path="email">Email:</form:label>
		        <form:errors path="email"/>
		        <form:input path="email"/>
		    </p>
		    <p>
		        <form:label path="password">Password:</form:label>
		        <form:errors path="password"/>
		        <form:input type="password" path="password"/>
		    </p>       
		    <input type="submit" value="Submit"/>
		</form:form>
	</div>
	
</div>

</body>
</html>