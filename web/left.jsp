<%--
  Created by IntelliJ IDEA.
  User: 86166
  Date: 2021/6/10
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Bootstrap -->
    <link href="./boot/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./css/left.css">
    <title>Title</title>
</head>
<body>
<!-- 左边的列表菜单栏 -->
<div class="myinfo">
    <ul class="nav nav-pills nav-stacked">
        <li role="presentation" class="active"><a href="javascript:void(0);"onclick="showInfo()" target="main">个人信息</a></li>
        <li role="presentation"><a href="#">修改密码</a></li>
        <li role="presentation"><a href="#">通知</a></li>
    </ul>
<%--    <ul class="list-group">--%>
<%--        <li class="list-group-item"><a href="https://www.baidu.com" target="main">头像</a></li>--%>
<%--        <li class="list-group-item"><span>昵称</span></li>--%>
<%--        <li class="list-group-item"><span>性别</span></li>--%>
<%--        <li class="list-group-item"><span>博客数</span></li>--%>
<%--        <li class="list-group-item"><span>粉丝</span></li>--%>
<%--        <li class="list-group-item"><span>关注</span></li>--%>
<%--        <li class="list-group-item"><span>浏览</span></li>--%>
<%--    </ul>--%>
</div>
<div class="list-group ">
    <a href="#" class="list-group-item active">
        博客管理
    </a>
    <a href="javascript:void(0);" onclick="manage()" target="main" class="list-group-item">查看博客</a>
    <a href="javascript:void(0);" onclick="manage()" class="list-group-item">查看评论</a>
</div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="boot/js/jquery/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只-加载单个插件。 -->
<script src="boot/js/bootstrap.min.js"></script>
<script>
    function manage(){
        parent.frames["main"].window.location.href="/blog_war_exploded/readMyBlog?email="+JSON.parse(localStorage.getItem("email"));
    }
    function showInfo(){
        parent.frames["main"].window.location.href="/blog_war_exploded/showInfo?email="+JSON.parse(localStorage.getItem("email"));
    }
</script>
</body>
</html>
