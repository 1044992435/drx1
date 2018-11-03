<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2018/11/2
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Title</title>
</head>
<%@ include file="/common/mystyle.jsp" %>
<body>
<form id="roles">
    <div class="form-group">
        <label for="proName">角色名称</label>
        <input type="hidden" name="id" value="${list.id}">
        <input type="text" class="form-control" name="rname" value="${list.rname}" id="proName" placeholder="角色名称">
    </div>
</form>
</body>
</html>
