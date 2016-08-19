<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Ярослав
  Date: 17.08.2016
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div style="height: 150%; overflow: auto">
    <div id="templatemo_main">
        <c:forEach items="${product}" var="a">
            <div style="font-size: 20px; text-align: center; width: 170px; height: auto; background-color: gainsboro; color: #101010; float: left; margin: 28px; border: 2px double black; border-radius: 0px;">
                <p style="font-size: 20px;">${a.name}</p>
                <p style="font-size: 20px;">Вага: ${a.weight} гр.</p>
                <p style="font-size: 20px;">Ціна: ${a.price} грн.</p>
                <p>
                        <sec:authorize access="hasRole('ADMIN')">
                    <a href="/productEdit/${a.id}">
                        <button type="submit">Модифікувати</button>
                    </a>
                        </sec:authorize>
                </p>
                <p>
                        <sec:authorize access="hasRole('ADMIN')">
                    <form:form action="/product/delete/${a.id}" method="post">
                        <button type="submit">Видалити</button>
                    </form:form>
                        </sec:authorize>
                </p>
                <p>
                    <sec:authorize access="hasRole('USER')">
                        <a style="font-size: 13px" href="/test/${a.id}">Додати до своєї піци</a>
                    </sec:authorize>
                </p>
            </div>
        </c:forEach>
    </div>
</div>
