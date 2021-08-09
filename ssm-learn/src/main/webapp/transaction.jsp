<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>validate</title>
</head>
<body>
    <form action="<%=pageContext.getServletContext().getContextPath()%>/validate/annotation.do" method="post" enctype="application/x-www-form-urlencoded">
        <table>
            <tr>
                <td>产品编号：</td>
                <td><input name="productId" id="productId" /></td>
            </tr>
            <tr>
                <td>用户编号：</td>
                <td><input name="userId" id="userId" /></td>
            </tr>
            <tr>
                <td>交易日期：</td>
                <td><input name="date" id="date" /></td>
            </tr>
            <tr>
                <td>价格：</td>
                <td><input name="price" id="price" /></td>
            </tr>
            <tr>
                <td>数量：</td>
                <td><input name="quantity" id="quantity" /></td>
            </tr>
            <tr>
                <td>交易金额：</td>
                <td><input name="amount" id="amount" /></td>
            </tr>
            <tr>
                <td>用户邮件：</td>
                <td><input name="email" id="email" /></td>
            </tr>
            <tr>
                <td>备注：</td>
                <td><textarea rows="5" cols="20" id="note" name="note"></textarea></td>
            </tr>
            <tr>
                <td colspan="2" align="right"><input type="submit" value="提交" /></td>
            </tr>
        </table>
    </form>
</body>
</html>