<%-- 
    Document   : hello
    Created on : 25 Jun, 2014, 3:25:58 AM
    Author     : krishna


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <img src="<c:url value="/resources/images/add.png" />">
    </body>
</html>
