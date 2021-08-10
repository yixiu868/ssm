<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to Spring Web MVC project</title>
</head>
<body>
<h3>Hello, Spring MVC</h3>
欢迎：${sessionScope.user.userName }
<a href="${pageContext.request.contextPath}/user/logout.do">退出</a>
</body>
</html>