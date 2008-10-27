<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<frameset framespacing="0" frameborder="no" rows="95,*">
        <frame src="<c:url value="/header.view"/>" name="header" frameborder="0" noresize="noresize" scrolling="no" marginheight="0" marginwidth="0"/>
         <frameset framespacing="0" frameborder="no" cols="180,*">
                <frame src="<c:url value="/menu.view"/>" name="left" frameborder="0" noresize="noresize" scrolling="no" marginheight="0" marginwidth="0"/>
                <frame src="<c:url value="/main.view"/>" name="main" frameborder="0" noresize="noresize" scrolling="auto" marginheight="0" marginwidth="0"/>
        </frameset>    
    </frameset>
</html>
