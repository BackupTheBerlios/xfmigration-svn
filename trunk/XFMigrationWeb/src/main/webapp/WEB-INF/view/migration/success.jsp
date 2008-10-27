<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<body>
 <h4>Migration results:</h4>
 	<ul>
		<c:forEach items="${migrationResults}" var="msg" varStatus="status">
			<li><c:out value="${msg}"/>.</li>
		</c:forEach>
	</ul>
 	<a href="<c:url value="/migration.htm"/>">Back to migration.</a>
</body>
</html>
