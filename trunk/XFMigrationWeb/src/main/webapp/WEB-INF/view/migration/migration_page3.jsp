<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>PoC::Migration::Step 3</title>
	<script type='text/javascript' src='/XFMigrationWeb/dwr/interface/JsDwrMonitorBean.js'></script>
	<script type='text/javascript' src='/XFMigrationWeb/dwr/engine.js'></script>
	<script type='text/javascript' src='/XFMigrationWeb/dwr/util.js'></script>
	<script type='text/javascript'>
  
  	function displayMessages(messages) {
		var content= '';
		for ( var itr in messages) {
			content = content + messages[itr] + "<BR>";
		}
		var monitor_div = document.getElementById('monitor');
		monitor_div.innerHTML = content;
		monitor_div.scrollTop = monitor_div.scrollHeight;
	}

  	function monitor() {
  		JsDwrMonitorBean.getMessages(displayMessages);
	}
	
  	function startMonitor() {
  		window.setInterval("monitor()", 1000);
  		document.getElementById('monitor_display').style.display='block';
  	}
  	
  	function stopMonitor() {
  		window.clearInterval();
  	}
  </script>
	<LINK REL='Stylesheet' TYPE='text/css'
	HREF="<c:url value="/css/tables.css"/>">
</head>
<body onunload="stopMonitor()">
<form:form commandName="migrationWizardForm">
	<div class="title"><span>Project Migration::Details</span>
	<hr>
	</div>
	<div class="table_border">
	<table class="base_table">
		<tr>
			<th colspan="2">Step 3</th>
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
			<td><c:choose>
				<c:when test="${migrationWizardForm.remotedata == true}">
						Remote data
						</c:when>
				<c:otherwise>
						Local data
						</c:otherwise>
			</c:choose></td>
		</tr>
		<tr>
			<th colspan="2">Services to be executed</th>
		</tr>
		<c:choose>
			<c:when test="${not empty allowedServices}">
				<c:forEach items="${allowedServices}" var="service" varStatus="status">
					<tr>
						<td colspan="2" align="left"><c:out
							value="${service.serviceName}" /></td>
					</tr>
					<tr>
						<td align="left" colspan="2">${service.description}</td>
					</tr>
				</c:forEach>	
			</c:when>
			<c:otherwise>
				<tr>
					<td align="left" colspan="2">No services selected</td>
					</tr>
			</c:otherwise>
		</c:choose>
		
		<tr>
			<td>
			<div><input type="submit" name="_target2"
				value="Change details" /></div>
			</td>
			<td align="right">
			<div><input type="submit" name="_finish" value="Migrate"
				<c:if test="${empty allowedServices}"> disabled="disabled" </c:if>
				onclick="startMonitor()"/></div>
			</td>
		</tr>
	</table>
	</div>
</form:form>

<div id="monitor_display" style="display: none;">
	<div class="title">
		<span>Project Migration Monitor</span>
		<hr>
	</div>
	<div class="monitor" id="monitor"></div>
</div>

</body>
</html>