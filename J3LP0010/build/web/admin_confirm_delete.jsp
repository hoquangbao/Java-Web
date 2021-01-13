<%-- 
    Document   : admin_delete_post
    Created on : Sep 28, 2020, 9:58:21 AM
    Author     : anhnd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Delete Post</title>
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
        <c:set var="articleId" value="${requestScope.ARTICLEID}"/>
        <div class="container">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Message: </td>
                        <td>Are you sure to delete this post?</td>
                    </tr>
                    <tr>
                        <td><form action="deleteArticle" method="POST">
                                <input type="hidden" name="articleId" value="${articleId}"/>
                                <input type="submit" name="btAction" value="Delete"/>
                            </form></td>
                        <td><form action="backToHome" method="POST">
                                <input type="submit" name="btAction" value="Cancel"/>
                            </form></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
