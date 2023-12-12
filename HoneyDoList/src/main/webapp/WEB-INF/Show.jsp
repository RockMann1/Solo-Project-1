<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>All Honey Do Tasks</title>
</head>
<body>

	<div class="container d-display-flex p-3 my-3 bg-light text-white ">
<h1 class="text-primary"> Welcome, <c:out value="${user.firstName}"/>!!</h1>

<p><a href="/logout">Log Out</a></p>
<p><a href="/tasks/new">Create New task!</a></p>
<br>
<p class="text-secondary">All Honey Do Tasks:</p>
<table class="table table-striped table-bordered">
    <thead>
        <tr>
            <th>Honey Do Tasks</th>
            <th>Task Price</th>
            <th>Location</th>
        </tr>
    </thead>
    <tbody>
    	<c:forEach var="task" items="${tasks}">
    		<tr>
         			<td><a href="/tasks/${task.id}"><c:out value="${task.name}"></c:out></a></td>
         			<td><p><c:out value="${task.taskPrice}"></c:out></p></td>
         			<td><p><c:out value="${task.locationName}"></c:out></p></td>
         	</tr>		
      	</c:forEach>
     
        
    </tbody>
</table>
 <a href="/tasks/new"><button>Create New task!</button></a>

</div>

</body>
</html>