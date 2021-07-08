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
<form action="facilityItem/add">
    <font style="font-size: 30px" color="#663399">添加设施</font><br>
    地名:<input type="text" name="title" id="title" value="${title}"><br>
    相关信息:<input type="text" name="info" value="${info}"><br>
    坐标x:<input type="text" name="positionx" value="${positionx}"><br>
    坐标y:<input type="text" name="positiony" value="${positiony}"><br>
    价格:<input type="text" name="price" value="${price}"><br>
    开放时间:<input type="text" name="time" value="${time}"><br>
    <input type="submit" value="提交">
</form>
<script type="text/javascript">
    var p="${info}";
    if(p!==""&&p!=null){
        var title=document.getElementById("title")
        title.setAttribute("readonly","true")
    }
</script>
<button onclick="window.location.href = 'index.jsp'">返回</button>
</body>
</html>
