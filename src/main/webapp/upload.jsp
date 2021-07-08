<%--
  Created by IntelliJ IDEA.
  User: A
  Date: 2021/6/26
  Time: 22:48
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
    <script>
        function search(){
            var xmlHttp=new XMLHttpRequest();
            xmlHttp.onreadystatechange=function() {
                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                    var data = xmlHttp.responseText;
                    var jsonobj = eval("(" + data + ")");
                    var domobj = document.getElementById("typelist");
                    domobj.innerHTML = "";
                    for (var i = 0; i < data.length && i <= 5; i++) {
                        domobj.innerHTML+="<option>"+jsonobj[i].name+"</option>"
                    }
                }
            }
            var domobj2 = document.getElementById("searching");
            var uN=domobj2.value
            xmlHttp.open("get","canteenItem/findLike?Name="+uN,true);
            xmlHttp.send();
        }
    </script>
</head>
<body>
    <font style="font-size: 30px" color="#663399">上传图片</font><br>
    <form action="image/upload" method="post" enctype="multipart/form-data">
        选择图片:<input type="file" name="file" width="120px"><br>
        是否为主图:<input type="radio" name="isMainImg" value="1">是 <input type="radio" name="isMainImg" value="0">否<br>
        所属食品:<input id="searching" type="text" list="typelist" name="canteenItemName" onkeyup="search()">
        <datalist id="typelist">

        </datalist>
        <input type="submit" value="上传">
    </form>
    <button onclick="window.location.href = 'index.jsp'">返回</button>
</body>
</html>
