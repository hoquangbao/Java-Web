<%-- 
    Document   : register
    Created on : Sep 19, 2020, 9:56:12 AM
    Author     : anhnd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <c:set var="errors" value="${requestScope.ERROR}"/>
        <h1 style=" display: flex;justify-content: center;margin-bottom: 50px">Create new account</h1> <br><br>
        <c:if test="${empty errors}">
            <form action="register" method="POST">
                <table border="0">
                    <tbody>
                        <tr>
                            <td>Email: </td>
                            <td><input type="text" name="txtEmail" value="${param.txtEmail}"/></td>
                        </tr>
                        <tr>
                            <td>Name:  </td>
                            <td><input type="text" name="txtName" value="${param.txtName}"/></td>
                        </tr>
                        <tr>
                            <td>Password: </td>
                            <td><input type="password" name="txtPassword" value="${param.txtPassword}"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" name="btAction" value="Register"/> <input type="reset" value="Reset"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><a href="login.html"><p>Back to login</p></a></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </c:if>
        <c:if test="${not empty errors}">
            <form action="register" method="POST">
                <table border="0">
                    <tbody>
                        <tr>
                            <td>Email: </td>
                            <td><input type="text" name="txtEmail" value="${param.txtEmail}"/></td>
                                <c:if test="${not empty errors.emailIsEmpty}">
                                <td> <font color="red">
                                    ${errors.emailIsEmpty}
                                    </font></td>
                                </c:if>
                                <c:if test="${not empty errors.emailInvalid}">
                                <td> <font color="red">
                                    ${errors.emailInvalid}
                                    </font></td>
                                </c:if>
                                <c:if test="${not empty errors.emailIsExisted}">
                                <td> <font color="red">
                                    ${errors.emailIsExisted}
                                    </font></td>
                                </c:if>
                        </tr>
                        <tr>
                            <td>Name:  </td>
                            <td><input type="text" name="txtName" value="${param.txtName}"/></td>
                                <c:if test="${not empty errors.nameIsEmpty}">
                                <td> <font color="red">
                                    ${errors.nameIsEmpty}
                                    </font></td>
                                </c:if>
                        </tr>
                        <tr>
                            <td>Password: </td>
                            <td><input type="password" name="txtPassword" value="${param.txtPassword}"/></td>
                                <c:if test="${not empty errors.passwordRange}">
                                <td> <font color="red">
                                    ${errors.passwordRange}
                                    </font></td>
                                </c:if>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" name="btAction" value="Register"/> <input type="reset" value="Reset"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><a href="login.html"><p>Back to login</p></a></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </c:if>
    </body>
</html>
