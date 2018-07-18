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
        <a href="<c:url value="/usersBlackList"/>">Show Users BlackList</a>
        <br/>
        <br/>        
        <h1> User list </h1> 
        <h2>${print}</h2>
        <table border="1">
            <tr>
                <th width ="80" > ID </th>
                <th width ="120"> Name </th>
                <th width ="120"> Login </th>
                <th width ="120"> Password </th>
                <th width ="120"> Phone </th>
                <th width ="60"> Black List </th>
                <th width ="60"> Delete </th>
            </tr>
            <c:forEach items="${listUsers}" var= "user">
                <tr>
                    <td> ${user.id}</td>
                    <td> ${user.name}</td>
                    <td> ${user.login}</td>
                    <td> ${user.password}</td>
                    <td> ${user.phone}</td>
                    <td><a href="<c:url value='/users/edit/${user.id}'/>">Add to Black List</a></td>
                    <td><a href="<c:url value='/users/remove/${user.id}'/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
