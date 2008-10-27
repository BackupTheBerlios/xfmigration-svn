<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PoC::Add Mapping</title>
<LINK REL='Stylesheet' TYPE='text/css'
	HREF="<c:url value="/css/tables.css"/>">
<script type="text/javascript">
	function goBack() {
		var url = "<c:url value="/mappings.htm"/>";
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
<form:form commandName="registerForm" enctype="multipart/form-data">
	<div class="title"><span>Add Mapping</span>
	<hr>
	</div>
	<div class="table_border">
	<table class="base_table">
		<tr>
			<th colspan="2" align="left">New Mapping Details</th>
		</tr>
		<tr>
			<td>Mapping name*</td>
			<td>
				<form:input path="mappingName" />
				<span class="errorClass"><form:errors path="mappingName"/></span>
			</td>
		</tr>
		<tr>
			<td>Source Forge Name*</td>
			<td>
					<form:select path="srcForgeName">
						<form:option value="0" label="-choose forge-" />
						<form:options items="${forges}" itemValue="forgeName"
							itemLabel="forgeName" />
					</form:select>
					<span class="errorClass"><form:errors path="srcForgeName" /></span>
			</td>
		</tr>
		<tr>
			<td>Dest Forge Name*</td>
			<td>
					<form:select path="destForgeName">
						<form:option value="0" label="-choose forge-" />
						<form:options items="${forges}" itemValue="forgeName"
							itemLabel="forgeName" />
					</form:select>
					<span class="errorClass"><form:errors path="destForgeName" /></span>
			</td>
		</tr>
		<tr>
			<td>Mapping OWL*</td>
			<td>
				<input type="file" name="mappingFile"> 	
			</td>
		</tr>
		<tr>
			<td>Description</td>
			<td>
					<form:textarea path="description" cssStyle="width: 95%;"/>
			</td> 
		</tr>
		<tr>
			<td colspan="2" class="desc">*Mandatory fields.</td>
		</tr>
		<tr>
			<td>
				<div>
					<button type="button" onclick="goBack()">Back</button>
				</div>
			</td>
			<td>
				<div>
					<button type="submit">Add Mapping</button>
				</div>
			</td>
		</tr>
	</table>
	</div>
</form:form>
</body>
</html>