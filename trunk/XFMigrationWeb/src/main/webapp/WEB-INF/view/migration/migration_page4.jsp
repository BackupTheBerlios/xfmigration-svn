<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PoC::Migration::Step 4</title>
  <script type='text/javascript' src='/XFMigrationWeb/dwr/interface/JsDwrMonitorBean.js'></script>
  <script type='text/javascript' src='/XFMigrationWeb/dwr/engine.js'></script>
  <script type='text/javascript' src='/XFMigrationWeb/dwr/util.js'></script>
  <script type='text/javascript'>
  
  	function displayMessages(messages) {
		//alert("mesages found: " + messages);
		var content= '';
		for ( var itr in messages) {
			content = content + messages[itr] + "<BR>";
		}
		document.getElementById('monitor').innerHTML = content;
	}

  	function monitor() {
  		JsDwrMonitorBean.getMessages(displayMessages);
	}
	
  	function startMonitor() {
  		window.setInterval("monitor()", 600);
  	}
  	
  	function stopMonitor() {
  		window.clearInterval();
  	}
  </script>
  <LINK REL='Stylesheet' TYPE='text/css' HREF="<c:url value="/css/tables.css"/>">
</head>
<body onload="monitor()">
	
<div class="title"><span>Project Migration::Summary</span>
<hr>
</div>
<div class="table_border">
<table class="base_table">
	<tr>
		<th colspan="2" align="left">Migration Details</th>
	</tr>
		<tr>
			<td>Source Forge Name:</td>
			<td><c:out value="${migrationWizardForm.srcForgeName}"></c:out></td>
		</tr>
		<tr>
			<td>Destination Forge Name:</td>
			<td><c:out value="${migrationWizardForm.destForgeName}"></c:out></td>
		</tr>
		<tr>
			<td>Applied Mapping:</td>
			<td><c:out value="${mapping.description}"></c:out></td>
		</tr>
		<tr>
			<td>Project Name:</td>
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
			<th colspan="2" align="left">Destination Forge Executed Services:</th>
		</tr>
		<c:forEach items="${executedServices}" var="service" varStatus="status">
			<tr>
				<td colspan="2" align="left"><c:out value="${service.serviceName}" /></td>
			</tr>
			<tr>
				<td align="left" colspan="2">${service.description}</td>
			</tr>
		</c:forEach>
		</table>
		</div>
		
		<div class="title">
			<span>Migration Execution Log:</span>
			<hr>
		</div>

		<div class="monitor" id="monitor">
		</div>
		
</body>
</html>