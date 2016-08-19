<%--
  Created by IntelliJ IDEA.
  User: Ярослав
  Date: 17.08.2016
  Time: 2:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="height: 50%">
    <div style="width: auto;color: #121211; height: auto; background:#f1f1f1;font-size: 20px;margin-left: 40%; position: inherit;float: left ">
        <form action="/pizzaNew?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
            <p>Ім'я</p>
            <a><input type="text" name="name"></a>
            <p>Опис</p>
            <a><textarea name="description"></textarea></a>
            <p>Ціна</p>
            <a><input type="number" name="price"></a>
            <p>Фото</p>
            <a><input type="file" name="image"></a>
            <p></p>
            <button type="submit">Додати!</button>
        </form>
    </div>
</div>