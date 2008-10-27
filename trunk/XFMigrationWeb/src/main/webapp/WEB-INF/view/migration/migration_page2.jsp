<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PoC::Project Migration::Step 2</title>
<LINK REL='Stylesheet' TYPE='text/css' HREF="<c:url value="/css/tables.css"/>">
</head>
<body>
<form:form commandName="migrationWizardForm">
	<div class="title"><span>Project Migration::Details</span>
<hr>
</div>
	<div class="table_border">
	<table class="base_table">
		<tr>
			<th colspan="2">Step 2</th>
		</tr>
		<tr>
			<td>Source Forge:</td>
			<td><c:out value="${migrationWizardForm.srcForgeName}"></c:out></td>
		</tr>
		<tr>
			<td>Destination Forge:</td>
			<td><c:out value="${migrationWizardForm.destForgeName}"></c:out></td>
		</tr>
		<tr>
			<td>Project Unixname:</td>
			<td><c:out value="${migrationWizardForm.projectURL}"></c:out></td>
		</tr>
		<tr>
			<td>Project data location:</td>
			<td>
				<c:choose>
					<c:when test="${migrationWizardForm.remotedata == true}">
						Remote data
					</c:when>
					<c:otherwise>
						Local data
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th colspan="2">Available forge services:</th>
		</tr>
		<c:choose>
			<c:when test="${ not empty servicesList}">
				<c:forEach items="${servicesList}" var="service" varStatus="status">
				<tr>
					<td colspan="2" align="left"><c:out value="${service.serviceName}" /></td>
				</tr>
				<tr>
					<td align="left">${service.description}</td>
					<td align="right">
						<form:checkbox path="operations"
							value="${service.serviceName}" />
					</td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="2">No services defined.</td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tr>
			<td>
				<div>
					<input type="submit" name="_target1" value="Previous step"/>
				</div>
			</td>
			<td align="right">
				<div>
					<input type="submit" name="_target2" value="Next step"/>
				</div>
			</td>
		</tr>
	</table>
	</div>
</form:form> 
</body>
</html>