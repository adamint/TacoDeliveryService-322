<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:import url="header.jsp"/>

<h2>Welcome back, ${user.firstName} ${user.lastName}!</h2>

<c:if test="${empty orders}"><h3>You don't have any active orders. <a href="/orders/new">Order a taco!</a></h3></c:if>

<c:if test="${orders.size() > 0}">
    <h3>Your orders</h3>
    <ul>
        <c:forEach items="${orders}" var="order">
            <li>Taco <b>${order.taco.name}</b> - <a href="/orders/view/${order.id}">View order</a></li>
        </c:forEach>
    </ul>
</c:if>

<c:import url="footer.jsp"/>