<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Poc::Mappings Registry</title>
<LINK REL='Stylesheet' TYPE='text/css' HREF="<c:url value="/css/tables.css"/>">
</head>
<body>
<div class="title"><span>Mappings Registry</span>
<hr>
</div>

<div class="table_border">
<table class="base_table">
	<tr>
		<th class="lp">lp.</th>
		<th>Source Forge Name</th>
		<th>Dest Forge Name</th>
		<th>Mapping Url</th>
		<th>Description</th>
		<th></th>
	</tr>
	<c:forEach items="${mappings}" var="m" varStatus="status">
		<tr>
			<td><c:out value="${status.count}"/>.</td>
			<td><c:out value="${m.srcForge.forgeName}"/></td>
			<td><c:out value="${m.destForge.forgeName}"/></td>
			<td><c:out value="${m.mappingURL}"/></td>
			<td><c:out value="${m.description}"/></td>
			<td><a href="<c:url value="/deleteMapping.htm">
				<c:param name="mappingId">${m.id}</c:param>
			</c:url>"/>delete</a> </td>
		</tr>	
	</c:forEach>
	<tr>
		<td colspan="6" align="right"><a href="<c:url value="/addMapping.htm"/>"/>Add Mapping</a></td>
	</tr>
</table>
</div>
</body>
</html>