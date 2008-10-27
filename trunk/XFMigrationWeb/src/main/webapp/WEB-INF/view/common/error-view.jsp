<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK REL='Stylesheet' TYPE='text/css' HREF="<c:url value="/css/tables.css"/>">
<style type="text/css">
	div.main_info {
		font-family: verdana;
		font-size: 13px;
		padding: 10px;
	}
</style>
<title>Application error.</title>
</head>
<body>
	<div class="title"><span>Application error</span>
		<hr>
	</div>
	<div class="table_border">
		<div class="main_info"><c:out value="${exception.message}"></c:out></div>
	</div>
</body>
</html>