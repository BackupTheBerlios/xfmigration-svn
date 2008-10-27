<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PoC::Update Forge Details</title>
<LINK REL='Stylesheet' TYPE='text/css' HREF="<c:url value="/css/tables.css"/>">
<script type="text/javascript">
	function goBack() {
		var url = "<c:url value="/forges.htm"/>";
		window.location = url;
	}
</script>
<style type="text/css">
		span.errorClass {
			font-size: 11px;
			color: red;
			text-align: right;
			margin-left: 10px;
		}
</style>
</head>
<body>
<div class="title"><span>Update Forge Details</span>
<hr>
</div>

 <form:form commandName="forgeDetailsForm">
 	<div class="table_border">
	<table class="base_table">
		<tr>
			<th colspan="2">Forge Details</th>
		</tr>
		<tr>
			<td>Forge name*</td>
			<td>
				<form:input path="forge.forgeName"/>
				<span class="errorClass"><form:errors path="forge.forgeName" /></span>
			</td>
		</tr>
		<tr>
			<td>Forge url*</td>
			<td>
			<form:input path="forge.ontologyUrl"/>
			<span class="errorClass"><form:errors path="forge.ontologyUrl" /></span>
			</td>
		</tr>
		<tr>
			<td>Forge uri*</td>
			<td>
				<form:input path="forge.ontologyUri"/>
				<span class="errorClass"><form:errors path="forge.ontologyUri"/></span>
			</td>
		</tr>
		<tr>
			<td>Forge owl-s sequence path for creating individuals:*</td>
			<td><form:input path="forge.owlsCreateIndividualsSequencePath"/>
				<span class="errorClass"><form:errors path="forge.owlsCreateIndividualsSequencePath" /></span>
			</td>
		</tr>
		<tr>
			<td>Forge owl-s sequence path for creating properties:*</td>
			<td>
				<form:input path="forge.owlsCreateObjPropertiesSequencePath"/>
				<span class="errorClass">
					<form:errors path="forge.owlsCreateObjPropertiesSequencePath" />
				</span>
			</td>
		</tr>
		<tr>
			<td>Forge importing data owl-s:</td>
			<td>
				<form:input path="forge.importOwlsPath"/>
			</td>
		</tr>
		<tr>
			<td>Forge description*</td>
			<td>
					<form:textarea path="forge.forgeDescription"/>
					<span class="errorClass"><form:errors path="forge.forgeDescription"/></span>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="desc">* Mandatory fields.</td>
		</tr>
		
		<tr>
			<td>
				<div>
					<button type="button" onclick="goBack()">Back</button>
				</div>
			</td>
			<td>
				<div>
					<button type="submit">Update Forge</button>
				</div>
			</td>
		</tr>
		
		<tr>
			<th colspan="2" align="left">Services:</th>
		</tr>
		<c:forEach items="${forgeDetailsForm.forge.services}" var="service" varStatus="status">
			<tr>
				<td align="left">${service.serviceName}</td>
				<td align="right">
					<a href="<c:url value="/deleteService.htm">
						<c:param name="id">${service.serviceId}</c:param>
					</c:url>" />Remove</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="2" align="right">
				<a href="<c:url value="/addService.htm">
						<c:param name="id">${forgeDetailsForm.forge.id}</c:param>
					</c:url>" />Add Service</a>
			</td>
		</tr>
		<tr>
			<th colspan="2" align="left">Local projects:</th>
		</tr>
		<c:forEach items="${projectsNames}" var="p" varStatus="status">
			<tr>
				<td align="left">${p}</td>
				<td align="right">
					<a href="<c:url value="/deleteLocalProject.htm">
						<c:param name="name" value="${p}" />
						<c:param name="forgeId" value="${forgeDetailsForm.forge.id}" />
					</c:url>" />Remove</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="2" align="right">
				<a href="<c:url value="/addLocalProject.htm">
						<c:param name="id">${forgeDetailsForm.forge.id}</c:param>
					</c:url>" />Add Local Project</a>
			</td>
		</tr>
		
			
	</table>
	</div>
</form:form>
</body>
</html>