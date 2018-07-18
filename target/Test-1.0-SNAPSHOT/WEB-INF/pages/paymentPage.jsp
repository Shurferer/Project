<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Page</title>
    </head>
    <body>
        <br/>
        <br/>
        
        <h1>Thanks for order.</h1>
        <h2>
            Your final order is next:
        </h2>
        <c:if test="${!empty orderList}">
            <table border = "1">
                <tr>
                    <th width ="120"> Product Name </th>
                    <th width ="120"> Product Price </th>
                    <th width ="160"> Number of products</th>
                </tr>
                <c:forEach items="${orderList}" var= "order">
                    <tr>
                        <td> ${order.product.name}</td>
                        <td> ${order.product.price}</td>
                        <td> ${order.numberOfProducts}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if> 
        Result price is ${print}. 
        <h3> Some service for pay.</h3>
         <a href="<c:url value="/finishOrder"/>">Finish</a>
    </body>
</html>
