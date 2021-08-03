<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>post请求中文乱码</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/postChinese" method="post">
用户名：<input type="text" name="userName" />
<input type="submit" value="post方式提交表单" />
</form>
</body>
</html>