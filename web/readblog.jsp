<%@ page import="com.gcl.blog.model.Blog" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gcl.blog.service.BlogServiceImp" %><%--
  Created by IntelliJ IDEA.
  User: 86166
  Date: 2021/6/10
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="./css/reset.css">
    <!-- bootstrap -->
    <link rel="stylesheet" href="./boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/title.css">
    <title>GBlog首页</title>
    <style>
        .operate .show, .operate .like{
            margin-top:0;
        }
        .show ul{
            margin-top:5px;
        }
        .image{
            top:20px;
        }
    </style>
</head>
<body>
<%
    BlogServiceImp blogServiceImp=new BlogServiceImp();
    ArrayList<Blog> blogs=blogServiceImp.selectAllBlog(1);
    if(blogs==null || blogs.size()==0){
        String message="没有文章列表";
        request.setAttribute("message",message);

    }
    else if(blogs.size()>0){
        request.setAttribute("blogs",blogs);
    }
%>
<!-- 导航栏 -->
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
                <li class="active"><a href="#">首页 <span class="sr-only">(current)</span></a></li>
                <li><a href="#">博客</a></li>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <ul class="nav navbar-nav ">
                <li><a href="#">个人中心</a></li>
                <li><a href="http://localhost:8080/blog_war_exploded/addblog.jsp">创作中心</a></li>
                <li><a href="#">退出登录</a></li>
                <li><a href="#">关于</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h1 style="text-align: center;font-size:22px;font-weight:500;">博客列表</h1>
    <c:if test="${requestScope.blogs!=null||requestScope.blogs.size() gt 0}">
        <c:forEach var="b" items="${requestScope.blogs}">

            <div class="item">
                <div class="content">
                    <div class="con">
                        <a class="blog" href='article.jsp?blogId=&quot;${b.id}&quot;'>
                            <p class="tag" id="title" name="title"><a href="javascript:void(0);" onclick="toArticle('${b.id}')">${b.title}</a></p>
                            <p class="ticontent" id="content" name="content">${b.partContent}</p>
                        </a>
                    </div>

                    <div class="operate">
                        <div class="like">
                            <a href="">
                                <img src="./img/like.png" alt="">
                            </a>
                            <span class="num">${b.like}</span>
                        </div>
                        <div class="show">
                            <ul>
                                <li><a class="nickName" id="nickName" name="nickName" href="">${b.nickName}</a></li>
                                <li><span class="titime" id="publishTime" name="publish_time"><fmt:formatDate value="${b.publish_time}" type="date"/></span></li>
                                <li>
                                    <p class="comment" id="comment" name="comment"><span>${b.comment}</span>评论</p>
                                </li>
                                <li>
                                    <p class="browse" id="browse" name="browse"> <span>${b.browse}</span> 浏览</p>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="right">
                    <c:if test="${b.author_email eq sessionScope.user.email}">
                        <div class="update">
                            <a href="javascript:void(0);" onclick="editBlog('${b.id}')">编辑</a>
                            <a href="javascript:void(0);">删除</a>
                        </div>
                    </c:if>

                    <div class="image">
                        <a href=""><img src="./img/8517807.jpg" alt=""></a>
                    </div>

                </div>
            </div>

        </c:forEach>

    </c:if>
    <c:if test="${requestScope.blogs==null||requestScope.blogs.size() == 0}">
        <div>
            <span>
                    ${requestScope.messege}
            </span>
        </div>
    </c:if>
</div>


<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="boot/js/jquery/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只-加载单个插件。 -->
<script src="boot/js/bootstrap.min.js"></script>
<script>
    function toArticle(param) {
        console.log('param'+param);
        window.location.href="article.jsp?blogId="+param;

    }
    function editBlog(id) {
        console.log(id);
        window.location.href="editblog.jsp?blogId="+id;
    }
</script>

</body>
</html>
