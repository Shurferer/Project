<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration </title>
    </head>
    <body>
        <h1>Create your profile </h1>
        <h2>${print}</h2>

        <form:form method="POST" modelAttribute="user" >
            <table>
                <tr>
                    <td>
                        <label for="name">Name</label>
                    </td>
                    <td>
                        <form:input type="text" path="name" id="name" placeholder="Name"/>
                        <form:errors path="name" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="phone">Phone </label>
                    </td>
                    <td>
                        <form:input type="text" path="phone" id="phone" />
                        <form:errors path="phone" />
                    </td>
                </tr>
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
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Save" >
                    </td>
                </tr>
            </table>
        </form:form>
    </form>
</body>
</html>