<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
ResourceBundle resource = ResourceBundle.getBundle("com.ww.servlet.i18n.resource.wanggw", request.getLocale());
%>
<body>
    <form action="" method="post">
        <%=resource.getString("username")%>:<input type="text" name="username" /><br />
        <%=resource.getString("password")%>:<input type="password" name="password" /><br />
        <input type="submit" value="<%=resource.getString("submit")%>" />
    </form>
</body>
</html>