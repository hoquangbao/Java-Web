<%-- 
    Document   : member_notification
    Created on : Sep 25, 2020, 4:13:55 PM
    Author     : anhnd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notification</title>
        <style>
            .container {
                display: flex;
                justify-content: center;
                flex-direction: column;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <c:set var="maps" value="${requestScope.NOTIFICATIONS}"/>
            <h1>See Notifications</h1>
            <c:forEach var= "entry" items="${maps}" varStatus="counter">
                <c:set var="key" value="${entry.key}"/>
                <c:set var="value" value="${entry.value}"/>
                <c:forEach var= "dto" items="${value}" varStatus="counter">
                    <c:url var="seePostDetailLink" value="seePostDetail">
                        <c:param name="articleId" value="${key}"/>
                        <c:param name="notificationId" value="${dto.notificationID}"/>
                    </c:url>
                    <p> ${dto.createBy} has ${dto.type} on your post ${key} on ${dto.createDate} <a href="${seePostDetailLink}">View detail</a></p> 
                </c:forEach>
            </c:forEach>
            <c:url var="homePage" value="backToHome"/>
            <a href="${homePage}">Back To Your Home</a>
        </div>
    </body>
</html>
