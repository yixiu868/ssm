<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ww.servlet.listener.MyHttpSessionBindingListener" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HttpSession绑定解绑监听器</title>
</head>
<body>
<%
// 绑定
session.setAttribute("bean", new MyHttpSessionBindingListener("哈哈"));
// 解绑
session.removeAttribute("bean");
%>
</body>
</html>