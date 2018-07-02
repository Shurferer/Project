<%@page import="com.test.model.Order"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language = "java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ClientPage</title>
    </head>
    <body>
        <br/>
        <br/>
        <a href="<c:url value="/"/>">Exit</a>
        <!--<h1>${print}</h1>-->
        <h1>${user.name}</h1>
        <h3><a href=" <c:url value="/basket"/>"> Basket </a></h3>
        <h2>Please choose products:</h2>
        <c:if test="${!empty listProducts}">
            <table border = "1">
                <tr>
                    <th width ="120"> Name </th>
                    <th width ="120"> Price </th>
                    <th width ="60"> Number </th>
                </tr>
                <c:forEach items="${listProducts}" var= "product">
                    <tr>
                        <td> ${product.name}</td>
                        <td> ${product.price}</td>
                        <td> 
                            <form:form action="addToOrder/${product.ID}" modelAttribute="order" >
                                <form:input type="text" path="numberOfProducts"/>
                                <form:errors path="numberOfProducts" />
                                <input type="submit" value="Add"/>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
