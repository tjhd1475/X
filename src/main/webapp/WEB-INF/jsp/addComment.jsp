<%--
  Created by IntelliJ IDEA.
  User: A
  Date: 2021/7/5
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String basePath=request.getScheme()+"://"+
                request.getServerName()+":"+request.getServerPort()+
                request.getContextPath()+"/";
    %>
    <title>Title</title>
    <base href="<%=basePath%>"/>
</head>
<body>
    <font style="font-size: 30px" color="#663399">添加评论</font>
<%--    <button onclick="window.location.href = 'index.jsp'">返回</button><br>--%>
    <form action="comment/addComment">

        <table border="1">
            <tr>
                <td>用户ID</td>
                <td><input type="text" name="userId"></td>
            </tr>
            <tr>
                <td>菜品编号</td>
                <td><input type="text" name="canteenItemId"></td>
            </tr>
            <tr>
                <td>评论</td>
                <td><textarea name="content" rows="3" cols="21" style="resize:none"></textarea></td>
            </tr>
            <tr>
                <td>分数</td>
                <td><input type="text" name="mark"></td>
            </tr>
            <tr>
                <td>是否收藏</td>
                <td><input type="radio" name="isLike" value="1">是
                    <input type="radio" name="isLike" value="0">否</td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="提交"></td>
            </tr>
        </table>
    </form>

</body>
</html>
