<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK REL='Stylesheet' TYPE='text/css' HREF="<c:url value="/css/tables.css"/>">
<title></title>
<style type="text/css">
	div.main_info {
		font-family: verdana;
		font-size: 13px;
		padding: 10px;
	}
</style>
</head>
<body>
<div class="title">
	<span>Cross-Forge Project Migration Proof of Concept</span>
	<hr>
</div>
<div class="table_border">
	<div class="main_info">
		
		The proof of concept demonstrates an approach for enabling the migration of projects<br> 
		or their parts between interoperable forges. Bearing in mind the heterogeneity of forges,<br> 
		a presented approach assumes that the data model of each forge is described by its own ontology<br> 
		and holds the requested information.<br> 
		XFMigration project focuses on bringing the process of migration to the semantic level<br> 
		and applying the semantic mapping rules to bridge information across heterogenous data models.<br>
		
	</div>
</div>
</body>
</html>
