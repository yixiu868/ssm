<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request、Session属性监听器</title>
</head>
<body>
<%
// session属性
session.setAttribute("city", "shenzhen");
session.setAttribute("city", "guangzhou");
session.removeAttribute("city");

// request属性
request.setAttribute("country", "china");
request.setAttribute("country", "US");
request.removeAttribute("country");
%>
</body>
</html>