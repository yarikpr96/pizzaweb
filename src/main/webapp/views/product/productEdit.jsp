<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="height: 100%">
    <div style="margin-left: 35%;margin-top: 1%; height: 300px;width: 400px;background: #f8f8f8">
        <form action="/productEdit?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">

            <table cellpadding="5px">
                <tr>
                    <td align="right">
                        Id:
                    </td>
                    <td align="left">
                        <input type="text" name="id" value="${product.id}">
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        Назва:
                    </td>
                    <td align="left">
                        <input type="text" name="name" value="${product.name }">
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        Вага:
                    </td>
                    <td align="left">
                        <input type="number" name="weight" value="${product.weight}">
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        Ціна:
                    </td>
                    <td align="left">
                        <input type="number" name="price" value="${product.price}">
                    </td>
                </tr>
                <tr>
                    <td>

                    </td>
                    <td align="left">
                        <button type="submit">Редагувати</button>
                    </td>
                </tr>
            </table>


        </form>
    </div>
</div>