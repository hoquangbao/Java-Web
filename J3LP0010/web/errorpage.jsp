<%-- 
    Document   : errorpage
    Created on : Sep 27, 2020, 3:58:05 PM
    Author     : anhnd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1><font color="red">Oops! An error has been detected</font></h1><br/>
        <c:url var="homePage" value="backToHome"/>
        <a href="${homePage}">Back To Your Home</a>
    </body>
</html>
