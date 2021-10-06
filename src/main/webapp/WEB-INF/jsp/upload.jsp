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
    <font style="font-size: 30px" color="#663399">上传图片</font>
<%--    <button onclick="window.location.href = 'index.jsp'">返回</button><br>--%>
    <form action="image/upload" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td align="center">选&nbsp;&nbsp;择&nbsp;&nbsp;图&nbsp;&nbsp;片</td>
                <td><input type="file" name="file"></td>
            </tr>
            <tr>
                <td align="center">是否作为主图</td>
                <td><input type="radio" name="isMainImg" value="1">是&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="isMainImg" value="0">否</td>
            </tr>
            <tr>
                <td align="center">所&nbsp;&nbsp;属&nbsp;&nbsp;食&nbsp;&nbsp;品</td>
                <td>
                    <input id="searching" type="text" list="typelist" name="canteenItemName" onkeyup="search()" style="width: 254px">
                    <datalist id="typelist">

                    </datalist>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="上传" style="width: 356px"></td>
            </tr>
        </table>
    </form>

</body>
</html>
