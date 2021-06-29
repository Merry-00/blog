<%--
  Created by IntelliJ IDEA.
  User: 86166
  Date: 2021/6/15
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="./boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/reset.css">
    <link rel="stylesheet" href="./css/showInfo.css">
    <title>展示个人信息</title>
</head>
<body>
<div class="header">
    <div class="top">
        <h1>个人信息</h1>
    </div>
    <div class="edit"> <button type="button" class="btn btn-default" onclick="editInfo()">完善信息</button></div>
</div>
<div class="foot">

<c:if test="${requestScope.user!=null}">

    <div class="img">
        <img src="./${requestScope.user.img}" alt="" class="img-circle">
    </div>
    <div class="nickName">
        <h2>${requestScope.user.nickName}</h2>
    </div>
    <div class="info">
        <div class="flex">
            <div class="a">
                <div class="browseNum"><a href="">${requestScope.user.browseNum}</a></div>
                <div class="browse">被访问量</div>
            </div>
            <div class="b">
                <div class=blogNum><a href="">${requestScope.user.commentNum}</a></div>
                <div class=blog>原创文章</div>
            </div>
            <div class="c">
                <div class="focusNum"><a href="">${requestScope.user.focus}</a></div>
                <div class="focus">关注数量</div>
            </div>
            <div class="d">
                <div class="fansNum"><a href="">${requestScope.user.fans}</a></div>
                <div class="fans">粉丝数量</div>
            </div>
        </div>
    </div>
</div>
<div class="center clearfix">
    <div class="left">
        <div class="achive">
            <h1>获得成就</h1>
            <div class="like">获得<span>${requestScope.user.likeNum}</span>次点赞</div>
            <div class="comment">获得<span>${requestScope.user.commentNum}</span>次评论</div>
            <div class="collect">获得<span>${requestScope.user.collectNum}</span>次收藏</div>
        </div>
        </c:if>

        <div class="show" >
            <c:if test="${requestScope.blogs!=null}">
            <c:forEach var="b" items="${requestScope.blogs}">
            <h1>Ta的专栏</h1>
            <div class="classify">${b.classify}<span>:</span>${b.count}<span>篇</span></div>
        </div>
        </c:forEach>
        </c:if>
    </div>

    <div class="right">
        <div class="analyz">
            <h1>博客作品数据</h1>
            <div class="data"><a href="javascript:void(0)" onclick="showData()">博客数据Echarts展示</a></div>
        </div>
    </div>
</div>

<script src="boot/js/jquery/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只-加载单个插件。 -->
<script src="boot/js/bootstrap.min.js"></script>
<script>
    function editInfo(){
        window.location.href="http://localhost:8080/blog_war_exploded/info.jsp";
    }
    function showData(){
        window.location.href="http://localhost:8080/blog_war_exploded/showData.jsp";
    }
</script>
</body>
</html>
