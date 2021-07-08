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
    <title>Title</title>
    <base href="<%=basePath%>"/>
    <script>
        var S=0,E=5,tname
        function findAll(tname){
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

    </script>
</head>
<body>
<a href="facilityItem/addPage">添加地点页</a><button onclick="findAll('facilityItem')">查看所有地点</button>
<a href="canteenItem/addPage">添加菜品页</a><button onclick="findAll('canteenItem')">查看所有菜品</button>
<a href="upload.jsp">上传图片页</a><button onclick="findAll('image')">查看所有图片</button>
<a href="opinion/addPage">提交反馈页</a><button onclick="findAll('opinion')">查看反馈</button>
<a href="comment/addPage">提交评论页</a><button onclick="findAll('comment')">查看评论</button><br>
    <table border="2" id="table">

    </table>

</body>
</html>
