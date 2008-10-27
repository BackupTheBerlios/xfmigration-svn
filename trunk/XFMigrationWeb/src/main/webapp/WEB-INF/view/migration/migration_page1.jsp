<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Poc::Migration::Step 1</title>
<LINK REL='Stylesheet' TYPE='text/css' HREF="<c:url value="/css/tables.css"/>">
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

<form:form commandName="migrationWizardForm">
<div class="title"><span>Project Migration::Details</span>
<hr>
</div>
<div class="table_border">
<table class="base_table">
		<tr>
			<th colspan="2">Step 1</th>
		</tr>
		<tr>
			<td>Source Forge Name:*</td>
			<td>
				<form:select path="srcForgeName" onchange="submit()" id="srcForgeSelect">
					<form:option value="UNSET" label="-choose src forge-" />
						<form:options items="${forges}" itemValue="forgeName"
					itemLabel="forgeName" />
				</form:select>
					<span class="errorClass"><form:errors path="srcForgeName"/></span>
				</td>
		</tr>
		<tr>
			<td>Destination Forge Name:*</td>
			<td>
			<form:select path="destForgeName" onchange="submit()"
				id="destForgeSelect">
				<form:option value="UNSET" label="-choose forge-" />
				<form:options items="${destForges}" itemValue="forgeName"
					itemLabel="forgeName" />
			</form:select>
				<span class="errorClass"><form:errors path="destForgeName"/></span>
			</td>
		</tr>
		<tr>
			<td>Mapping Name:*</td>
			<td>
						<form:select path="mappingId">
							<form:option value="-1" label="-choose mapping-" />
							<form:options items="${mappings}" itemValue="id" itemLabel="mappingName" />
						</form:select>
					<span class="errorClass"><form:errors path="mappingId" /></span>
			</td>
		</tr>
		<tr>
			<td rowspan="2">Project Data Source Location*</td>
			<td><form:radiobutton path="remotedata" value="true" onchange="submit()"/>Remote Data</td>
		</tr>
		<tr>
			<td><form:radiobutton path="remotedata" value="false" onchange="submit()"/>Local Data</td>
		</tr>
		<c:choose>
			<c:when test="${not migrationWizardForm.remotedata and not empty localProjectNames}">
				<tr>
					<td>Project Unixname:* </td>
							<td>
								<form:select path="projectURL">
									<form:option value="">-select project-</form:option>
									<form:options items="${localProjectNames}"/>
								</form:select>
								<span class="errorClass"><form:errors path="projectURL"/></span>
					</td>
				</tr>				
			</c:when>
			<c:otherwise>
				<tr>
					<td>Project Unixname:* </td>
							<td><form:input path="projectURL" />
							<span class="errorClass"><form:errors path="projectURL"/></span>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tr>
			<td class="desc">*Mandatory fields</td>
			<td>
			<div><input type="submit" name="_target1" value="Next step"/>
			</div>
			</td>
		</tr>
	</table>
	</div>
</form:form> 
</body>
</html>