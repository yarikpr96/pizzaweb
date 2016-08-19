<%--
  Created by IntelliJ IDEA.
  User: Ярослав
  Date: 18.07.2016
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div style="height: 120%;overflow: auto;">
    <div id="templatemo_main">
        <div class="img_frame img_frame_13 img_nom"><span></span>
            <a href="/resources/images/portfolio/06-l.jpg" rel="lightbox[portfolio]"><img
                src="/resources/images/portfolio/06.jpg" alt="Image 06 "></a>
        </div>
        <c:forEach items="${product}" var="a">
            <div style="font-size: 23px;  width: 100%; height: auto; background-color: gainsboro; color: #101010;   border: 2px double black; border-radius: 0px;">
                <form:form action="/testdd/${a.id}" method="post">
                <a style="font-size: 20px;">+ ${a.name}</a>
                <a style="font-size: 20px;">${a.price} грн.</a>


                    <button style="float: right" type="submit">Видалити</button>
                    </form:form>
            </div>


        </c:forEach>


        <div style="font-size: 20px; text-align: center; width: 100%; height: 130px; background-color: lightgreen; color: #101010; float: left;  border: 2px double black; border-radius: 0px;">
            <p>Ціна піци ${price} грн.</p>

            <form:form action="/testt" method="post">
                <button type="submit"> Додати до замовлення</button>
            </form:form>

            <form:form action="/testd" method="post">
            <button type="submit"> Очистити список</button>
            </form:form>
        </div>


    </div>
</div>
