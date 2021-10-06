<%--
  Created by IntelliJ IDEA.
  User: A
  Date: 2021/6/28
  Time: 0:20
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
                    for (var i = 0; i < data.length ; i++) {
                        domobj.innerHTML+="<option>"+jsonobj[i].title+"</option>"
                    }
                }
            }
            var domobj2 = document.getElementById("searching");
            var uN=domobj2.value
            xmlHttp.open("get","facilityItem/findLike?Name="+uN,true);
            xmlHttp.send();
        }
    </script>
</head>
<body>
    <font style="font-size: 30px" color="#663399">添加菜品</font>
<%--    <button onclick="window.location.href = 'index.jsp'">返回</button><br>--%>
    <form action="canteenItem/add">

        <table border="1">
            <tr>
                <td>名称</td>
                <td><input type="text" name="name" id="name" value="${name}"></td>
            </tr>
            <tr>
                <td>相关信息</td>
                <td><input type="text" name="info" value="${info}"></td>
            </tr>
            <tr>
                <td>价格</td>
                <td><input type="text" name="price" value="${price}"></td>
            </tr>
            <tr>
                <td>辣度</td>
                <td><input type="text" name="spicyRate" value="${spicyRate}"></td>
            </tr>
            <tr>
                <td>甜度</td>
                <td><input type="text" name="sweetRate" value="${sweetRate}"></td>
            </tr>
            <tr>
                <td>评分</td>
                <td><input type="text" name="mark" value="${mark}"></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>所属餐厅</td>
                <td><input id="searching" type="text" list="typelist" name="canteenName"  value="${canteenName}" onkeyup="search()"><datalist id="typelist">

                </datalist></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="提交"></td>
            </tr>
        </table>

    </form>

    <script type="text/javascript">
        var p="${info}";
        if(p!==""&&p!=null){
            var title=document.getElementById("name")
            title.setAttribute("readonly","true")
        }
    </script>
</body>
</html>
