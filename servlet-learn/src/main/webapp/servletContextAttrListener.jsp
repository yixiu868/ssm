<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ServletContext属性监听器</title>
</head>
<body>
<%
// 增加属性
application.setAttribute("name", "set了个寂寞");

// 替换name属性值
application.setAttribute("name", "好大一只");

// 删除name属性
application.removeAttribute("name");
%>
</body>
</html>