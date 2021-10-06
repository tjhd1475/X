<%--
  Created by IntelliJ IDEA.
  User: A
  Date: 2021/6/26
  Time: 22:47
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
    <title>管理面板</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/indexStyle.css"/>
    <base href="<%=basePath%>"/>
    <script>
        var S=0,E=5,tname
        function findAll(tname){
            $("#frame1").attr("hidden",true)
            $("#table").attr("hidden",false)
            var xmlHttp=new XMLHttpRequest();
            xmlHttp.onreadystatechange=function() {
                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                    var data = xmlHttp.responseText;
                    var jsonobj = JSON.parse(data)
                    var div=document.getElementById("table")
                    div.innerHTML="";
                    var tempRow;
                    tempRow='<tr>';
                    for(var i=0 ;i< jsonobj.colname.length;i++){
                        tempRow+='<td>'+jsonobj.colname[i]+'</td>'
                    }
                    tempRow+='<td>'+"操作"+'</td>'
                    tempRow+='</tr>'
                    div.innerHTML+=tempRow;
                    for(var i=0;i<jsonobj.content.length;i++){
                        tempRow="<tr>"
                        if(tname=='facilityItem'){
                            tempRow+="<td>"+jsonobj.content[i].facilityItemId+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].title+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].info+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].positionX+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].positionY+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].price+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].time+"</td>"
                            tempRow+="<td>"+"<a href="+tname+"/remove?id="+jsonobj.content[i].facilityItemId+">删除</a>"+"|"+
                                "<a href="+tname+"/addPage?title="+jsonobj.content[i].title+
                                "&info="+jsonobj.content[i].info+
                                "&positionx="+jsonobj.content[i].positionX+
                                "&positiony="+jsonobj.content[i].positionY+
                                "&price="+jsonobj.content[i].price+
                                "&time="+jsonobj.content[i].time+">更新</a>"+"</td>"
                        }else if(tname=='canteenItem'){
                            tempRow+="<td>"+jsonobj.content[i].canteenItemId+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].name+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].facilityItemId+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].info+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].price+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].spicyRate+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].sweetRate+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].mark+"</td>"
                            tempRow+="<td>"+"<a href="+tname+"/remove?id="+jsonobj.content[i].canteenItemId+">删除</a>"+"|"+
                                "<a href="+tname+"/addPage?name="+jsonobj.content[i].name+
                                "&canteenId="+jsonobj.content[i].facilityItemId+
                                "&info="+jsonobj.content[i].info+
                                "&price="+jsonobj.content[i].price+
                                "&spicyRate="+jsonobj.content[i].spicyRate+
                                "&sweetRate="+jsonobj.content[i].sweetRate+
                                "&mark="+jsonobj.content[i].mark+">更新</a>"+"</td>"
                        }else if(tname=='image'){
                            tempRow+="<td>"+jsonobj.content[i].imageId+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].imgLoc+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].canteenItemId+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].isMainImg+"</td>"
                            tempRow+="<td>"+"<a href="+tname+"/remove?id="+jsonobj.content[i].imageId+">删除</a>"+"</td>"
                        }else if(tname=='opinion'){
                            tempRow+="<td>"+jsonobj.content[i].id+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].content+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].solution+"</td>"
                            if(jsonobj.content[i].type==1){
                                tempRow+="<td>意见反馈</td>"
                            }else if(jsonobj.content[i].type==2){
                                tempRow+="<td>设施反馈</td>"
                            }
                            if(jsonobj.content[i].result==0){
                                tempRow+="<td>x</td>"
                            }else{
                                tempRow+="<td>√</td>"
                            }
                            tempRow+="<td>"+"<a href="+tname+"/detail?id="+jsonobj.content[i].id+">详情处理</a>"+"</td>"
                        }else if(tname=='comment'){
                            tempRow+="<td>"+jsonobj.content[i].id+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].userId+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].canteenItemId+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].content+"</td>"
                            tempRow+="<td>"+jsonobj.content[i].mark+"</td>"
                            if(jsonobj.content[i].isLike!=0){
                                tempRow+="<td>√</td>"
                            }else{
                                tempRow+="<td>x</td>"
                            }
                            tempRow+="<td>"+"<a href="+tname+"/remove?id="+jsonobj.content[i].id+">删除</a>"+"</td>"
                        }

                        tempRow+="</tr>"
                        div.innerHTML+=tempRow;
                    }
                }
            }
            xmlHttp.open("get",tname+"/findAll",true);
            xmlHttp.send();
        }
        $(function () {
            $("#addFI,#addCI,#addCom,#addImg,#addOpi").click(function (){
                $("#frame1").attr("hidden",false)
                $("#table").attr("hidden",true)
                var subhref = "";
                if(event.data.url.indexOf("action")>=0){//获取当前页面的URL，URL为action形式
                    subhref = event.data.url+"?projectId="+event.data.projectId+event.data.param;
                }else{//URL为jsp形式
                    subhref = event.data.url;
                }
                $("#frame1").attr("src",subhref)
            })
        })
    </script>

</head>
<body>
<div class="container">
    <div class="topBar" id="topBar">
        <div id="logo"><img width="100px" src="img/logo.jpg"></div>
        <div id="title">HITSZ校园管家后台管理系统</div>
    </div>
    <div class="leftBar" id="leftBar">
        <div><a id="addFI" class="btn1" href="facilityItem/addPage" target="frame1">添加地点页</a></div>
        <div><a class="btn1" onclick="findAll('facilityItem')">查看所有地点</a></div>
        <div><a id="addCI" class="btn1" href="canteenItem/addPage" target="frame1">添加菜品页</a></div>
        <div><a class="btn1" onclick="findAll('canteenItem')">查看所有菜品</a></div>
        <div><a id="addImg" class="btn1" href="image/addPage" target="frame1">上传图片页</a></div>
        <div><a class="btn1" onclick="findAll('image')">查看所有图片</a></div>
        <div><a id="addOpi" class="btn1" href="opinion/addPage" target="frame1">提交反馈页</a></div>
        <div><a class="btn1" onclick="findAll('opinion')">查看反馈</a></div>
        <div><a id="addCom" class="btn1" href="comment/addPage" target="frame1">提交评论页</a></div>
        <div><a class="btn1" onclick="findAll('comment')">查看评论</a></div>
        <div></div>
    </div>
    <div class="centerArea" id="centerArea">
        <iframe name="frame1" id="frame1"  scrolling="no" frameborder="0" hidden></iframe>
        <table border="2" id="table">

        </table>
    </div>
</div>

</body>
</html>
