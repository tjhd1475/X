<%--
  Created by IntelliJ IDEA.
  User: A
  Date: 2021/8/17
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆失败</title>
</head>
<body>
<form action="login">
        <table border="2">
            <tr>
                <td>用户名</td>
                <td><input name="userName" type="text"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input name="pwd" type="password"></td>
            </tr>
            <tr>
                <td colspan="2"><input value="登录" type="submit"></td>
            </tr>
        </table>
        <h5 style="color: red">登陆失败，用户名或密码错误</h5>
</form>
</body>
</html>
