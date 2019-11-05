<%--@elvariable id="order" type="com.adamratzman.delivery.models.Order"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:import url="header.jsp"/>

<h1>Order <i>${order.id}</i> - ${order.taco.name}</h1>
<h3>Your order
    <c:out value="${order.delivered ? 'has' : 'has NOT'}"/>
    been delivered
</h3>
<h4>
    <c:if test="${order.startedDelivery}">
        A TacoDash employee is on the way!
    </c:if>
    <c:if test="${!order.startedDelivery}">
        We're still preparing your order
    </c:if>
</h4>

<h3>Taco information</h3>
<p>Ingredients:</p>
<ul>
    <c:forEach items="${order.taco.ingredients}" var="ingredient">
        <li>${ingredient.readable}</li>
    </c:forEach>
</ul>
<p>Price: $${order.taco.price}</p>

<div id="order-map"></div>

<c:import url="footer.jsp"/>