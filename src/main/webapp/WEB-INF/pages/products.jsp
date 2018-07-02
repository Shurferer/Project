<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib  uri="http://www.springframework.org/tags" prefix="spring"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Page</title>
    </head>
    <body>
        <a href="<c:url value="/adminPage"/>">Back to main menu</a>
        <br/>
        <br/>
        <h1> Product list </h1> 
        <c:if test="${!empty listProducts}">
            <table border = "1">
                <tr>
                    <th width ="80" > ID </th>
                    <th width ="120"> Name </th>
                    <th width ="120"> Price </th>
                    <th width ="60"> Edit </th>
                    <th width ="60"> Delete </th>

                </tr>
                <c:forEach items="${listProducts}" var= "product">
                    <tr>
                        <td> ${product.ID}</td>
                        <td> ${product.name}</td>
                        <td> ${product.price}</td>
                        <td><a href="<c:url value='/${product.ID}'/>">Edit</a></td>
                        <td><a href="<c:url value='/remove/${product.ID}'/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <h1> Add product </h1>
        <c:if test="${!(empty print)}">
            ${print}
            Please click here: <a href="<c:url value="/products"/>">Return</a>
        </c:if>
        <c:if test="${(empty print)}">

            <form:form action="products/add" modelAttribute="product">
                <table border = "1">
                    <c:if test="${!empty product.name}">
                        <tr>
                            <td>
                                <form:label path="ID">
                                    <spring:message text="ID"/>
                                </form:label>
                            </td>
                            <td>
                                <form:input path="ID" readonly="true" size ="10" disabled="true"/>
                                <form:hidden path = "ID"/>  
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>
                            <form:label path="name">
                                <spring:message text="Name"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="name"/>
                            <form:errors path="name" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="price">
                                <spring:message text="Price (1-1000)"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="price"/>
                            <form:errors path="price" />

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <c:if test="${!empty product.name}">
                                <input type="submit"
                                       value="<spring:message text="Edit Product" />"/>
                            </c:if>
                            <c:if test="${empty product.name}">
                                <input type="submit"
                                       value="<spring:message text="Add Product" />"/>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </form:form>
        </c:if>
    </body>
</html>
