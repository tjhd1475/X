HITAssistant后台api
一：添加地点、菜品、上传图片
（1）访问后台http://1.15.152.95:8080/HITadmin/index.jsp进行添加

二：请求已上传的地点信息
（1）发送请求http://1.15.152.95:8080/HITadmin/facilityItem/find?title=<!--上传时的地点名称-->
（2）得到json格式数据例：{"title":"宿舍","info":"不睡觉","positionX":1.1,"positionY":1.2,"price":0.0,"time":"全天？","facililyItemId":2}

三：请求已上传的图片
（1）发送请求http://1.15.152.95:8080/HITadmin/canteenItem/findImg?name=<!--上传时的菜品名称-->或http://1.15.152.95:8080/HITadmin/canteenItem/findImg?canteenItemId=<!--菜品Id-->（同时带这两个参数时canteenItemId优先作用）
（2）得到json格式数据例（json数组）：[{"imageId":17,"imgLoc":"C:\\Users\\A\\Desktop\\programm\\SpringMVC\\HitAssistant\\target\\ch02-ssm-1.0-SNAPSHOT\\WEB-INF\\upload\\2021\\6\\20210628204834122.png","canteenItemId":6,"isMainImg":1},{"imageId":15,"imgLoc":"C:\\Users\\A\\Desktop\\programm\\SpringMVC\\HitAssistant\\target\\ch02-ssm-1.0-SNAPSHOT\\WEB-INF\\upload\\2021\\6\\20210628204811364.jpg","canteenItemId":6,"isMainImg":0},{"imageId":16,"imgLoc":"C:\\Users\\A\\Desktop\\programm\\SpringMVC\\HitAssistant\\target\\ch02-ssm-1.0-SNAPSHOT\\WEB-INF\\upload\\2021\\6\\20210628204823451.jpg","canteenItemId":6,"isMainImg":0}]
（3）根据每个json的imageId属性找到图片，(i)发送请求http://1.15.152.95:8080/HITadmin/image/download?imgId=<!--json中的imageId值-->，以下载文件。(ii)发送请求http://1.15.152.95:8080/HITadmin/image/getImage?imgId=<!--json中的imageId值-->，以下载文件（该方法对于图片会直接显示）
注①：isMainImg为1的是主图
注②：测试时最好新上传些菜品和图片，因为本地测试时有些地址在我的本地，是找不到的。

四：请求已上传的所有地点、菜品信息
（1）发送请求http://1.15.152.95:8080/HITadmin/facilityItem/findall，得到json数组例：[{"facilityItemId":1,"title":"荔园一食堂","info":"便宜食堂","positionX":1.5,"positionY":2.5,"price":7.5,"time":"6:30~20:30"},{"facilityItemId":2,"title":"宿舍","info":"不睡觉","positionX":1.1,"positionY":1.2,"price":0.0,"time":"全天？"},{"facilityItemId":3,"title":"荔园二食堂","info":"超好吃","positionX":1.0,"positionY":1.6,"price":15.0,"time":"全天"},{"facilityItemId":4,"title":"教室","info":"学习","positionX":4.1,"positionY":2.7,"price":0.0,"time":"全天"},{"facilityItemId":5,"title":"荔园四食堂","info":"新开","positionX":1.0,"positionY":1.0,"price":12.0,"time":"饭点"}]
（2）发送请求http://1.15.152.95:8080/HITadmin/canteenItem/findall，得到json数组例：[{"canteenItemId":1,"name":"红烧狮子头","facilityItemId":1,"info":"好吃","price":5.0,"spicyRate":5.5,"sweetRate":5.5,"mark":8.0},{"canteenItemId":5,"name":"鸡肉","facilityItemId":1,"info":"？","price":5.0,"spicyRate":5.0,"sweetRate":5.0,"mark":5.0},{"canteenItemId":6,"name":"猪肉","facilityItemId":3,"info":"不好

五：意见、设施反馈
（1）发送请求http://1.15.152.95:8080/HITadmin/opinion/submit?type=<!--1：意见反馈，2：设施反馈-->&content=<!--反馈内容-->，成功则返回success，失败返回fail
（2）处理反馈，访问后台http://1.15.152.95:8080/HITadmin/index.jsp进行处理（也有添加反馈的备用通道）

六：通过食堂id找到该食堂所有菜品
（1）发送请求http://1.15.152.95:8080/HITadmin/canteenItem/findByFI?facilityItemId=<!--食堂id-->，得到json数组例：[{"canteenItemId":1,"name":"红烧狮子头","facilityItemId":2,"info":"好吃","price":8.0,"spicyRate":5.0,"sweetRate":5.0,"mark":5.33333},{"canteenItemId":5,"name":"鸡肉","facilityItemId":2,"info":"好吃","price":5.0,"spicyRate":5.0,"sweetRate":3.0,"mark":2.0}]
注①：设施不存在返回空json数组：[]

七：提交评论、评分、收藏
（1）发送请求http://1.15.152.95:8080/HITadmin/comment/addComment?userId=<!--用户编号-->&canteenItemId=<!--菜品编号-->&content=<!--用户评论-->&mark=<!--用户评分-->&isLike=<!--是否收藏：1：收藏，0：不收藏-->
注①：userId和canteenItemId属性必有，没有则提交失败返回fail，失败可能是菜品不存在导致
注②：除了上述两个属性，其他属性不一定要有，如可以省写为：http://1.15.152.95:8080/HITadmin/comment/addComment?userId=1&canteenItemId=1&isLike=1，此时未提交的属性会有默认值，mark=-1(可认为是未评分状态)，isLike=0，content=null
注③：当提交的userId和canteenItemId属性相同时表示同一用户向同一菜品提交，此时只要新提交的属性不是默认值则会覆盖原来提交的属性

八：获得某菜品所有评论、评分、收藏信息
（1）发送请求http://1.15.152.95:8080/HITadmin/comment/findall?CIId=<!--菜品Id-->
（2）得到json数组，例[{"id":3,"userId":1224,"canteenItemId":2,"content":"好吃","mark":5.0,"isLike":1},{"id":4,"userId":1223,"canteenItemId":2,"content":"真不错","mark":3.0,"isLike":1}]
注：userId：评论的用户Id，content：用户评论内容，mark：该用户对该菜品的评分，isLike：该用户是否收藏该菜品

九：获得某用户对某菜品的评论、评分、收藏信息
（1）发送请求http://1.15.152.95:8080/HITadmin/comment/findUserComment?userId=<!--用户id-->&CIId=<!--菜品id-->
（2）得到json对象，例{"id":2,"userId":1223,"canteenItemId":1,"content":"？","mark":2.0,"isLike":1}
注①：用户可能未填写评论，即("content":"")
注②：不存在的用户或用户为评价收藏菜品则返回null

十：获得某食堂的平均价格、平均评分
（1）发送请求http://1.15.152.95:8080/HITadmin/facilityItem/getAvgPriceAndMark?FIId=<!--设施id-->
（2）得到json，例：{"price":6.5,"mark":3.6666667}
注①：每次提交新的菜品会重新计算平均价格，故菜品提交时价格为必填。
注②：评分不单独储存，故每次请求都会根据菜品评分单独计算一次。
注③：不存在的设施返回null

十一：获得所有食堂的平均价格、平均评分
（1）发送请求http://1.15.152.95:8080/HITadmin/facilityItem/getAllAvg
（2）得到json数组，例：[{"facilityItemId":1,"title":"荔园一食堂","mark":3.1111112,"price":3.6666667},{"facilityItemId":2,"title":"荔园二食堂","mark":4.1666665,"price":6.5}]

十二：获得某菜品的平均评分、收藏总数、评论总数
（1）发送请求http://1.15.152.95:8080/HITadmin/canteenItem/getMLC?CIId=<!--菜品id-->
（2）得到json，例：{"CntComment":"1","CntLike":"2","mark":"5.3333335"}
注①：如果菜品还没有人评分则mark属性为-1.0，不存在的菜品CntComment=0，CntLike=0，mark=-1



TODO：找到不同食堂同名菜品的解决方案(?)
暂时解决方案：先通过（四）找到所有食堂--->通过食堂id找到该食堂所有菜品（六）--->依次展示每个菜品--->通过菜品id找到其所有图片（三）
TODO：为后台增加修改和删除操作(finished)
TODO：为后台增加添加反馈和用户评论模块的测试功能(finished)
TODO：后台加密(unfinished)
