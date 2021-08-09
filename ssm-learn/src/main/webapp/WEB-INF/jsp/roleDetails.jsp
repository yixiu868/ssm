<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>角色详情</title>
</head>
<body>
<table border="1">
    <tr>
        <td>标签</td>
        <td>值</td>
    </tr>
    <tr>
        <td>角色编号</td>
        <td><c:out value="${role.id}"></c:out></td>
    </tr>
    <tr>
        <td>角色名称</td>
        <td><c:out value="${role.roleName}"></c:out></td>
    </tr>
    <tr>
        <td>角色备注</td>
        <td><c:out value="${role.note}"></c:out></td>
    </tr>
</table>
</body>
</html>