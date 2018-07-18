<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Basket</title>
    </head>
    <body>
        <a href="<c:url value="/clientPage"/>">Back to previous page</a>
        <br/>
        <br/>
        <h1> Order list </h1> 
        <c:if test="${!empty orderList}">
            <table border = "1">
                <tr>
                    <th width ="120"> Product Name </th>
                    <th width ="120"> Product Price </th>
                    <th width ="160"> Number of products</th>
                    <th width ="160"> Delete from order</th>
                </tr>
                <c:forEach items="${orderList}" var= "order">
                    <tr>
                        <td> ${order.product.name}</td>
                        <td> ${order.product.price}</td>
                        <td> ${order.numberOfProducts}</td>
                        <td><a href="<c:url value='/orders/remove/${order.product.ID}'/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if> 
        <form:form action="basket/saveOrders" >
            <input type="submit"  value="Save and pay" />
        </form:form>

    </body>
</html>
