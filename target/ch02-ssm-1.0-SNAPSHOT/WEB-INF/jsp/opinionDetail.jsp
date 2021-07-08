<%--
  Created by IntelliJ IDEA.
  User: A
  Date: 2021/7/4
  Time: 22:16
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
<h3>${opinion.content}</h3><br>
    <form action="opinion/solution">
        解决方案<br>
        <textarea name="solution" rows="6" cols="60"></textarea><br>
        是否已解决：<input type="radio" name="result" value="1">是<input type="radio" name="result" value="0">否
        <input type="hidden" name="id" value="${opinion.id}">
        <input type="submit" value="提交">
    </form>
<button onclick="window.location.href = 'index.jsp'">返回</button>
</body>
</html>
