<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PoC::Forge Service</title>
<LINK REL='Stylesheet' TYPE='text/css'
	HREF="<c:url value="/css/tables.css"/>">
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
<div class="title"><span>New Forge Service</span>
<hr>
</div>

<form:form commandName="forgeServiceForm">
	<div class="table_border">
	<table class="base_table">
		<tr>
			<th colspan="2">Service Details</th>
		</tr>
		<tr>
			<td>Service name*</td>
			<td>
				<form:input path="serviceName" />
				<span class="errorClass"><form:errors path="serviceName" /></span>
			</td>
		</tr>
		<tr>
			<td>Service path (URL)*</td>
			<td>
				<form:input path="servicePath" />
				<span class="errorClass"><form:errors path="servicePath" /></span>
			</td>
		</tr>
		<tr>
			<td>Description</td>
			<td>
				<form:textarea path="description" cssStyle="width: 95%;"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="desc">*Mandatory fields</td>
		</tr>
		<tr>
			<td>
			<div><a
				href="<c:url value="/forgeDetails.htm">
						<c:param name="id">${forgeServiceForm.forgeId}</c:param>
					</c:url>" />Back</a>
			</div>
			</td>
			<td>
			<div>
			<button type="submit">Save service</button>
			</div>
			</td>
		</tr>
	</table>
	</div>
</form:form>
</body>
</html>