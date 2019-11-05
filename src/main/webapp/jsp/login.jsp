<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="header.jsp" />

<c:if test="${failure}"><p><b style="color: red">Incorrect phone number or password specified</b></p></c:if>

<form:form id="login" action="/auth/login" modelAttribute="login" method="post">
    <table>
        <tr>
            <td>
                <form:label path="phoneNumber">Phone Number</form:label>
            </td>
            <td>
                <form:input path="phoneNumber" name="phoneNumber" id="phoneNumber" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password">Password</form:label>
            </td>
            <td>
                <form:password path="password" name="password" id="password" />
            </td>
        </tr>
        <tr>
            <td>
                <form:button id="login" name="login">Login</form:button>
            </td>
            <td>
                <button id="register" name="register"><a href="/auth/register">Register</a></button>
            </td>
        </tr>

    </table>
</form:form>

<c:import url="footer.jsp" />