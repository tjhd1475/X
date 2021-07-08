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

    <form action="comment/addComment">
        <font style="font-size: 30px" color="#663399">添加评论</font><br>
        用户ID：<input type="text" name="userId"><br>
        菜品编号：<input type="text" name="canteenItemId"><br>
        评论：<textarea name="content"></textarea><br>
        分数：<input type="text" name="mark"><br>
        是否收藏：<input type="radio" name="isLike" value="1">是
        <input type="radio" name="isLike" value="0">否
        <input type="submit" value="提交">
    </form>
    <button onclick="window.location.href = 'index.jsp'">返回</button>
</body>
</html>
