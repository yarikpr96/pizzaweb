<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div style="height: 120%;overflow: auto;">
    <div id="templatemo_main">

        <h1 style="text-align: center;float: inherit">Замовлення</h1>


        <c:forEach items="${pizzas}" var="a">

            <div style="font-size: 23px;  width: 100%; height: auto; background-color: gainsboro; color: #101010;   border: 2px double black; border-radius: 0px;">
                <form:form action="/pizzdell/${a.id_p}" method="post">
                <a style="font-size: 23px;">${a.name} - ${a.price} грн.


                    <button style="float: right" type="submit">Видалити</button>
                        </form:form>
                </a>


            </div>


        </c:forEach>


        <div style="font-size: 20px; text-align: center; width: 100%; height: auto; background-color: lightgreen; color: #101010; float: left;  border: 2px double black; border-radius: 0px;">
            <p>Сума замовлення ${price} грн.</p>

            <form:form action="/pizz" method="post">
                <p><input id="search" name="address" type="text" placeholder="Адреса"></p>
                <p><input id="submit" type="submit" value="Замовити"></p>
            </form:form>
            <form:form action="/pizzdel" method="post">
            <button type="submit"> Очистити список</button>
            </form:form>
        </div>


    </div>
</div>
