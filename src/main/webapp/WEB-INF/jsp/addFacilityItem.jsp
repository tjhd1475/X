<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/6/28
  Time: 15:34
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
<font style="font-size: 30px" color="#663399">添加设施</font>
<%--<button onclick="window.location.href = 'index.jsp'">返回</button><br>--%>
<form action="facilityItem/add">
    <table border="1">
        <tr>
            <td>地名</td>
            <td><input type="text" name="title" id="title" value="${title}"></td>
        </tr>
        <tr>
            <td>相关信息</td>
            <td><input type="text" name="info" value="${info}"></td>
        </tr>
        <tr>
            <td>坐标x</td>
            <td><input type="text" name="positionx" value="${positionx}"></td>
        </tr>
        <tr>
            <td>坐标y</td>
            <td><input type="text" name="positiony" value="${positiony}"></td>
        </tr>
        <tr>
            <td>价格</td>
            <td><input type="text" name="price" value="${price}"></td>
        </tr>
        <tr>
            <td>开放时间</td>
            <td><input type="text" name="time" value="${time}"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="提交"></td>
        </tr>

    </table>
</form>
<script type="text/javascript">
    var p="${info}";
    if(p!==""&&p!=null){
        var title=document.getElementById("title")
        title.setAttribute("readonly","true")
    }
</script>

</body>
</html>
