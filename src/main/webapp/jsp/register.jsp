<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="header.jsp" />

<form:form id="registration" modelAttribute="user" action="/auth/register" method="post">
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
                <form:label path="firstName">First Name</form:label>
            </td>
            <td>
                <form:input path="firstName" name="firstName" id="firstName" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="lastName">Last Name</form:label>
            </td>
            <td>
                <form:input path="lastName" name="lastName" id="lastName" />
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <form:button id="register" name="register">Register</form:button>
            </td>
        </tr>
    </table>
</form:form>

<c:import url="footer.jsp" />