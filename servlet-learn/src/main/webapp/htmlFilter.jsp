<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>html转义过滤器</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/htmlFilterServlet" method="post">
        留言：
        <textarea rows="8" cols="70" name="message">
	        <script type="text/javascript">
	        while (true) {
	        	alert("一直循环...");
	        }
	        </script>
	        <a href="http://www.baidu.com">百度</a>
        </textarea>
        <input type="submit" value="发表" />
    </form>
</body>
</html>