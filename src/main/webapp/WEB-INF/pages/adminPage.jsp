<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AdminPage</title>
    </head>
    <body>
        <h1>${print}</h1>
        <br/>
        <br/>
        <a href="<c:url value="/products"/>">Manage Products</a>
        <br/>
        <br/>
        <a href="<c:url value="/users"/>">Users</a>
        <br/>
        <br/>
        <a href="<c:url value="/"/>">Exit</a>

    </body>
</html>
