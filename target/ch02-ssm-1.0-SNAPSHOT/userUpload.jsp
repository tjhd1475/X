<%--
  Created by IntelliJ IDEA.
  User: A
  Date: 2021/8/17
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+
            request.getServerName()+":"+request.getServerPort()+
            request.getContextPath()+"/";
%>
<html>
<head>
    <title>Title</title>
    <base href="<%=basePath%>"/>

</head>
<body>
<center>
    <font style="font-size: 30px" color="#663399">${requestScope.canteenItemName}</font>
    <form action="image/uploadById" method="post" enctype="multipart/form-data">
        <table border="1">
            <tr>
                <td align="center">选&nbsp;&nbsp;择&nbsp;&nbsp;图&nbsp;&nbsp;片</td>
                <td><input type="file" name="file"></td>
            </tr>
            <tr>
                <td align="center">是否作为主图</td>
                <td align="center"><input type="radio" name="isMainImg" value="1">是&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="isMainImg" value="0">否</td>
            </tr>
            <tr>
                <td colspan="2"><input style="width:356px;height: 30px;font-size: 20px;background-color: cornflowerblue" type="submit" value="上传"></td>
            </tr>
        </table>
        <input type="text" name="canteenItemId" value="${requestScope.canteenItemId}" hidden><br>

    </form>
</center>

</body>
</html>
