<%--
  Created by IntelliJ IDEA.
  User: A
  Date: 2021/7/4
  Time: 21:38
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
    <base href="<%=basePath%>"/>
    <title>Title</title>
</head>
<body>
    <font style="font-size: 30px" color="#663399">添加建议</font>
<%--    <button onclick="window.location.href = 'index.jsp'">返回</button><br>--%>
    <form action="opinion/submit">
        <table border="1">
            <tr>
                <td>
                    <select name="type">
                        <option value="1">意见反馈</option>
                        <option value="2">设施反馈</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><textarea name="content" rows="6" cols="60" style="resize:none"></textarea></td>
            </tr>
            <tr>
                <td><input type="submit" value="提交"></td>
            </tr>
        </table>
    </form>

</body>
</html>
