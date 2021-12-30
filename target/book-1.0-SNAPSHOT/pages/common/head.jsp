<%--
  Created by IntelliJ IDEA.
  User: HowardY
  Date: 2021/12/26
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":"
            + request.getServerPort()
            + request.getContextPath() + "/";

    session.setAttribute("basePath",basePath);
//    pageContext.setAttribute("basePath",basePath);
%>
<%=basePath%>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
