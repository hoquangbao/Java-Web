<%-- 
    Document   : admin_detail_post
    Created on : Sep 27, 2020, 3:16:51 PM
    Author     : anhnd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post Detail</title>
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
        <c:set var="likes" value="${requestScope.LIKES}"/>
        <c:set var="dislikes" value="${requestScope.DISLIKES}"/>
        <c:set var ="article" value="${requestScope.ARTICLE}"/>
        <c:set var="comments" value="${requestScope.COMMENTS}"/>
        <c:set var="emotion" value="${requestScope.EMOTIONOFUSER}"/>
        <h1 style=" display: flex;justify-content: center;margin-bottom: 50px">Post Details</h1>
        <div class="container">
            <table border="0">
                <tbody>
                    <tr>
                        <td style="font-weight: bold">Title: </td>
                        <td>${article.title}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold">Description: </td>
                        <td>${article.description}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold">Image: </td>
                        <td>
                            <c:if test="${not empty article.imagePath}">
                                <img src="images/${article.imagePath}" style="display:block; width:100px; height:100px;">
                            </c:if>
                            <c:if test="${empty article.imagePath}">
                                <img src="images/notfound.jpg" style="display:block; width:100px; height:100px;">
                            </c:if></td>
                    </tr>
                    <tr><c:if test="${not empty likes}">
                            <td>${likes} likes </td>
                        </c:if>
                        <c:if test="${empty likes}">
                            <td>0 likes </td>
                        </c:if>
                        <c:if test="${not empty dislikes}">
                            <td>${dislikes} dislikes </td>
                        </c:if>
                        <c:if test="${empty dislikes}">
                            <td>0 dislikes </td>
                        </c:if>
                    </tr>
                </tbody>
            </table>
            <br/>
            <c:if test="${not empty comments}">
                <c:forEach var="dto" items="${comments}" varStatus="counter">
                    <table border="0">
                        <tbody>
                            <tr>
                                <td>${dto.email}: </td>
                                <td>${dto.content}</td>
                            </tr>
                        </tbody>
                    </table>
                </c:forEach>
            </c:if>
            <c:if test="${empty comments}">
                <h1>No comment here!!!</h1>
            </c:if>
            <br/>
            <c:url var="homePage" value="backToHome"/>
            <a href="${homePage}">Back To Your Home</a>
        </div>
    </body>
</html>
