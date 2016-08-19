<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Ярослав
  Date: 17.08.2016
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="height: 50%">
    <div style="width: auto;color: #121211; height: auto; background:#f1f1f1;font-size: 20px;margin-left: 40%; position: inherit;float: left ">
        <form action="/productNew?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
            <p>Ім'я</p>
            <input type="text" name="name">
            <p>Вага</p>
            <input type="number" name="weight">
            <p>Ціна</p>
            <input type="number" name="price">
            <p></p>
            <button type="submit">Add!</button>
        </form>
    </div>
</div>

