<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib  uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=utf-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <a href="<c:url value="/adminPage"/>">Back to main menu</a>
        <br/>
        <br/>
        <h1> Users Black list </h1> 
        <br/>
        <h2>${print}</h2>
        <table border="1">
            <tr>
                <th width ="80" > ID </th>
                <th width ="120"> Name </th>
                <th width ="120"> Login </th>
                <th width ="120"> Password </th>
                <th width ="120"> Phone </th>
                <th width ="60"> Delete From Black List</th>
            </tr>
            <c:forEach items="${listUsers}" var= "user">
                <c:if test="${user.addedToBlackList}">
                    <tr>
                        <td> ${user.id}</td>
                        <td> ${user.name}</td>
                        <td> ${user.login}</td>
                        <td> ${user.password}</td>
                        <td> ${user.phone}</td>
                        <td> <a href="<c:url value='/users/edit2/${user.id}'/>">Delete From Black List</a></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>


    </body>
</html>
