
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>
<head>
    <title>Title</title>
</head>
<body
        <%--style="--%>
<%--margin: 0px;--%>
        <%--padding: 0px;--%>
        <%--background: url();--%>
">


<div><tiles:insertAttribute name="header"/></div>
<div><tiles:insertAttribute name="body"/></div>
<div><tiles:insertAttribute name="footer"/></div>
</body>
</html>
