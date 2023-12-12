<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="container d-display-flex p-3 my-3 bg-light text-dark ">

	<h1 class="text-primary"><c:out value="${task.name}"/></h1>
	<br>
	<p><a href="/home">Dashboard</a></p>
	<br>
	
	<p>Task Name: <c:out value="${task.name}"/></p>
	
	<p>Added by: <c:out value="${task.user.firstName}"/></p>
	
	<p>Price: <c:out value="${task.taskPrice}"/></p>
	
	<p>Location: <c:out value="${task.locationName}"/></p>
	
	<p>Notes: <c:out value="${task.notesField}"/></p>
	
	<br>

	<c:set var="currentUser" scope="session" value="${user.firstName}"/>
	<c:set var="taskUser" scope="session" value="${task.user.firstName}"/>
	
		<c:if test= "${currentUser == taskUser}">
			<p><a href="/tasks/${task.id}/edit">Edit Task!</a></p>
			<br>
			<form action="/tasks/${task.id}" method="post">
		    				<input type="hidden" name="_method" value="delete">
		    				<input type="submit" value="Delete">
			</form>
			
		
		
		</c:if>

</div>

</body>
</html>