<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK REL='Stylesheet' TYPE='text/css' HREF="<c:url value="/css/tables.css"/>">
<!-- background-color: #FAFAFA; -->
</head>
<body>
<div class="title"><span>Forges Registry</span>
<hr>
</div>
<div class="table_border">
<table class="base_table">
	<tr>
		<th class="lp">lp.</th>
		<th>Forge Name</th>
		<th>Ontology Uri</th>
		<th>Forge Description</th>
	</tr>
	<c:forEach items="${forges}" var="forge" varStatus="status">
		<tr>
			<td><c:out value="${status.count}" />.</td>
			<td><a href="<c:url value="/forgeDetails.htm">
						<c:param name="id">${forge.id}</c:param>
					</c:url>" /><c:out
				value="${forge.forgeName}" /> </a></td>
			<td><c:out value="${forge.ontologyUri}" /></td>
			<td><c:out value="${forge.forgeDescription}" /></td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="4" align="right"><a href="<c:url value="/addForge.htm"/>"/>Register Forge</a></td>
	</tr>
</table>
</div>
</body>
</html>