<%--
  Created by IntelliJ IDEA.
  User: 86166
  Date: 2021/6/10
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Bootstrap -->
    <link href="./boot/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./css/main.css">
    <title>Title</title>
</head>
<body>
<!-- 导航栏 首页 博客 个人中心 创作中心 关于 -->

<nav id="mynav"class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">GBlog</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="javascript:void(0);" onclick="selectAllBlog()">首页 <span class="sr-only">(current)</span></a></li>
                <li class="active"><a href="#">博客</a></li>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <ul class="nav navbar-nav ">
                <li><a href="javascript:void(0);" onclick="toMakeCenter()">创作中心</a></li>
                <li><a href="#">退出登录</a></li>
                <li><a href="#">关于</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="boot/js/jquery/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只-加载单个插件。 -->
<script src="boot/js/bootstrap.min.js"></script>
<script type="text/javascript">
    function toMakeCenter(){
        console.log('跳转');
        top.location.href = "./addblog.jsp";
    }
    function selectAllBlog() {
        top.location.href="http://localhost:8080/blog_war_exploded/readAllBlog?email="+localStorage.getItem("email");
    }
</script>
</body>
</html>
