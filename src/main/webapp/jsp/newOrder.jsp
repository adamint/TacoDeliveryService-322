<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="header.jsp"/>

<form:form id="login" action="/orders/new" method="post">
    <c:forEach items="${tacoPairs}" var="tacoPair">
        <div class="ingredient-group">
            <h3>Choose your ${tacoPair.first.readable}:</h3>
            <select name="choose-${tacoPair.first}" id="choose-${tacoPair.first}">
                <c:forEach items="${tacoPair.second}" var="ingredient">
                    <option id="${ingredient}" name="${ingredient}" value="${ingredient}">${ingredient.readable} ($${ingredient.price})</option>
                </c:forEach>
            </select>
        </div>
    </c:forEach>

    <div>
        <div>
            <h3>Name your taco:</h3>
            <input type="text" id="tacoName" name="tacoName" placeholder="Awesome taco"/>
        </div>

        <br />
        <br />

        <div>
            <input type="submit" id="submit" value="Place your Order" />
        </div>
    </div>
</form:form>

<c:import url="footer.jsp"/>