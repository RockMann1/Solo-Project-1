<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Create New Task</title>
</head>
<body>

<div class="container d-display-flex p-3 my-3 bg-dark text-white ">
	<h1 class="text-info">New Task</h1>
	<br>
	<p><a href="/home">Dashboard</a></p>
	<br>
<form:form action="/tasks/new" method="post" modelAttribute="task">
    <p>
        <form:label path="name">Task Name</form:label>
        <form:errors path="name"/>
        <form:input path="name"/>
    </p>
    <p>
        <form:label path="taskPrice">Task Price (0-5000)</form:label>
        <form:errors path="taskPrice"/>
        <form:input type="number" path="taskPrice"/>
    </p>
    <p>
        <form:label path="locationName">Location</form:label>
        <form:errors path="locationName"/>
        <form:input path="locationName"/>
    </p>
    <p>
        <form:label path="notesField">Useful Notes:</form:label>
        <form:errors path="notesField"/>
        <form:input path="notesField"/>
    </p>
    <p>
        <form:label path="user"></form:label>
        <form:errors path="user"/>
        <form:input type="hidden" path="user" value="${user.id}"/>
    </p>
        
    <input type="submit" value="Submit"/>
</form:form>    
</div>

</body>
</html>