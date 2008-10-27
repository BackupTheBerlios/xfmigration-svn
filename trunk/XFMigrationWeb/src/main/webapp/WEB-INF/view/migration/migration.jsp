<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
	<LINK REL='Stylesheet' TYPE='text/css' HREF="<c:url value="/css/tables.css"/>">
  <script type='text/javascript' src='/XFMigrationWeb/dwr/interface/JsDwrTestBean.js'></script>
  <script type='text/javascript' src='/XFMigrationWeb/dwr/engine.js'></script>
  <script type='text/javascript' src='/XFMigrationWeb/dwr/util.js'></script>
  <script type='text/javascript'>
  
  	function displayMessages(messages) {
		//alert(messages);
		var content= '';
		for ( var itr in messages) {
			content = content + messages[itr] + "<BR>";
		}
		document.getElementById('monitor').innerHTML = content;
	}

  	function monitor() {
  		JsDwrTestBean.getMessages(displayMessages);
	}
	
  	function startMonitor() {
  		window.setInterval("monitor()", 1000);
  	}
  	
  	function stopMonitor() {
  		window.clearInterval();
  	}
  </script>
</head>
<body onunload="stopMonitor()">
<h4>Migration:</h4>
<form:form commandName="migrationForm">
	<table>
		<tr>
			<th></th>
		</tr>
		<tr>
			<td>Source forge:</td>
			<td><form:select path="srcForgeName" onchange="submit()"
				id="srcForgeSelect">
				<form:option value="UNSET" label="-choose src forge-" />
				<form:options items="${forges}" itemValue="forgeName"
					itemLabel="forgeName" />
			</form:select></td>
		</tr>
		<tr>
			<td>Destination forge:</td>
			<td><form:select path="destForgeName" onchange="submit()"
				id="destForgeSelect">
				<form:option value="UNSET" label="-choose forge-" />
				<form:options items="${destForges}" itemValue="forgeName"
					itemLabel="forgeName" />
			</form:select></td>
		</tr>
		<tr>
			<td>Mapping choice:</td>
			<td><form:select path="mappingId">
				<form:option value="-1" label="-choose mapping-" />
				<form:options items="${mappings}" itemValue="id"
					itemLabel="description" />
			</form:select></td>
		</tr>
		<tr>
			<td>Project unixname</td>
			<td><form:input path="projectURL" /></td>
		</tr>
		
		<tr>
			<td rowspan="2">Project data source</td>
			<td><form:radiobutton path="remotedata" value="true"/>Remote data</td>
		</tr>
		<tr>
			<td><form:radiobutton path="remotedata" value="false"/>Local data</td>
		</tr>
		<tr><td colspan="2"></td></tr>
		<tr><td colspan="2">Migration Services:</td></tr>
		<c:forEach items="${servicesList}" var="service" varStatus="status">
			<tr>
				<td align="right">
					<form:checkbox path="operations"
						value="${service.serviceName}" />
				</td>
				<td>
					<c:out value="${service.serviceName}" />
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td></td>
			<td>
			<div><input type="submit" name="submit_button" value="Migrate"/>
			</div>
			</td>
		</tr>
		<tr>
			<td>Monitor:</td><td></td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="monitor"></div>
			</td>
		</tr>
	</table>
</form:form>
</body>
</html>
