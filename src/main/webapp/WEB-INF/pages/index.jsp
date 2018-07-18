<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test</title>
    </head>
    <body>
        <h1>Welcome. Please sign into your account or create new.</h1>
        <h2> ${print} </h2>
        <form:form method="POST" modelAttribute="user" >
            <table>
                <tr>
                    <td>
                        <label for="login">Login </label>
                    </td>
                    <td>
                        <form:input type="text" path="login" id="login" placeholder="Login"/>
                        <form:errors path="login" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="password">Password </label>
                    </td>
                    <td>
                        <form:input type="password" path="password" id="password" placeholder="Password"/>
                        <form:errors path="password" />
                    </td>
                    <td>
                        <form:input type="text" path="name" value="test" hidden="hidden"/>
                        <form:input type="text" path="phone" value="291000000" hidden="hidden"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Login" >
                    </td>
                </tr>
            </table>
        </form:form>

        <br/>
        <a href="<c:url value="/reg"/>">Registration</a>
        <br/>
    </body>
</html>
