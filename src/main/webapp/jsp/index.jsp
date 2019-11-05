<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:import url="header.jsp"/>

<p>What would you like to do?</p>
<ul>
    <li><a href="/orders/new">Order a taco</a></li>
    <sec:authorize access="!isAuthenticated()">
        <li><a href="/auth/register">Register</a></li>
        <li><a href="/auth/login">Login</a></li>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <li><a href="/me/">Go to my account</a></li>
        <li><a href="/auth/logout"> Logout</a></li>
    </sec:authorize>
</ul>

<a href="/status">View Taco delivery status page</a>

<c:import url="footer.jsp"/>