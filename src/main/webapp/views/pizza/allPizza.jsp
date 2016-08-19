<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Ярослав
  Date: 17.08.2016
  Time: 2:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="templatemo_main">
    <div style="height: 100%">
        <div>
            <c:forEach items="${pizza}" var="a">

                <div class="col one_third gallery_box">
                    <div class="img_frame img_frame_13 img_nom"><span></span>
                        <a href="data:image/jpeg;base64,${a.image}" rel="lightbox[portfolio]"> <img
                                src="data:image/jpeg;base64,${a.image}" alt="Image 03"></a>
                    </div>
                    <h4><p>${a.name}</p></h4>
                    <h4><p>Ціна ${a.price} грн.</p></h4>
                    <p>${a.description}</p>
                        <sec:authorize access="hasRole('ADMIN')">
                    <a href="/editP/${a.id_p}">
                        <button type="submit">Модифікувати</button>
                    </a>
                        </sec:authorize>
                    <p>
                            <sec:authorize access="hasRole('ADMIN')">
                        <form:form action="/pizza/delete/${a.id_p}" method="post">
                            <button type="submit">Видалити</button>
                        </form:form>
                            </sec:authorize>
                    </p>
                    <sec:authorize access="hasRole('USER')">
                        <p>
                            <a href="/pizz/${a.id_p}">Додати в замовлення</a>
                        </p>
                    </sec:authorize>
                </div>

            </c:forEach>
        </div>
    </div>
    <div class="clear"></div>
</div>