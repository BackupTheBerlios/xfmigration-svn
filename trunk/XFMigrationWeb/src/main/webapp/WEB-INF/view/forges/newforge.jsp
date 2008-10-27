<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>PoC::Add Forge</title>
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
<div class="title"><span>Register New Forge</span>
<hr>
</div>
<form:form commandName="registerForm">
	<div class="table_border">
	<table class="base_table">
		<tr>
			<th colspan="2">New Forge Details</th>
		</tr>
		<tr>
			<td>Forge name*</td>
			<td>
				<form:input path="forgeName" />
				<span class="errorClass"><form:errors path="forgeName" /></span>
			</td>
		</tr>
		<tr>
			<td>Forge url*</td>
			<td>
				<form:input path="forgeOntURL" />
				<span class="errorClass"><form:errors path="forgeOntURL" /></span>
			</td>
		</tr>
		<tr>
			<td>Forge wsdl</td>
			<td>
				<form:input path="forgeWSDL" />
			</td>
		</tr>
		<tr>
			<td>Forge owl-s sequence path for creating individuals:*</td>
			<td><form:input path="owlsCreateIndividualsSequencePath" />
				<span class="errorClass"><form:errors path="owlsCreateIndividualsSequencePath" /></span>
			</td>
		</tr>
		<tr>
			<td>Forge owl-s sequence path for creating properties:*</td>
			<td>
				<form:input path="owlsCreateObjPropertiesSequencePath" />
				<span class="errorClass"><form:errors path="owlsCreateObjPropertiesSequencePath" /></span>
			</td>
		</tr>
		<tr>
			<td>Forge importing data owl-s:</td>
			<td>
				<form:input path="importOwlsPath"/>
			</td>
		</tr>
		<tr>
			<td>Forge description*</td>
			<td>
				<form:textarea path="forgeDescription"/>
				<span class="errorClass"><form:errors path="forgeDescription"/></span>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="desc">* Mandatory Fields.</td>
		</tr>
		
		<tr>
			<td>
			<div>
			<button type="button" onclick="goBack()">Back</button>
			</div>
			</td>
			<td>
			<div>
			<button type="submit">Add Forge</button>
			</div>
			</td>
		</tr>
	</table>
	</div>
</form:form>
</body>
</html>