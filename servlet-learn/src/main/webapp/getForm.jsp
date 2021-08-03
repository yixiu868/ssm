<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>Get提交中文参数乱码问题</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/getChineseMsgServlet" method="get">
        姓名:<input type="text" name="name" />
        <input type="submit" value="get方式提交表单" />
    </form>
</body>
</html>