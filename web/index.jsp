<%@ page import="com.gcl.blog.model.Blog" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gcl.blog.service.BlogServiceImp" %>
<%@ page import="com.gcl.blog.model.PageBean" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 86166
  Date: 2021/6/10
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    BlogServiceImp blogService=new BlogServiceImp();
    //取当前页
    String currentPage = request.getParameter("currentPage");
    //第一次访问，默认currentPage访问第一页
    if (currentPage == null) {
        currentPage = "1";
    }
    Integer currentPageNum = Integer.parseInt(currentPage);
    //页面大小设置成固定值4
    //分页查询所有
    PageBean<Blog> pageBean = blogService.queryBlogByPage(currentPageNum, 4);
    if(pageBean!=null){
        //servlet重在业务逻辑，取值传参JSP侧重于页面显示
        request.setAttribute("page", pageBean);
    }
    else{
        String message="博客列表为空";
        request.setAttribute("message",message);
    }
%>
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
            <form class="navbar-form navbar-left" accept-charset="UTF-8" action="http://localhost:8080/blog_war_exploded/searchData" method="post">
            <div class="form-group">
                <input name="info" type="text" id="info" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
            <ul class="nav navbar-nav ">
                <li><a href="http://localhost:8080/blog_war_exploded/login.jsp">成为管理员</a></li>
                <li><a href="http://localhost:8080/blog_war_exploded/register.jsp">注册</a></li>
                <li><a href="http://localhost:8080/blog_war_exploded/login.jsp">登录</a></li>
                <li><a href="#">关于</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h1 style="text-align: center;font-size:22px;font-weight:500;">博客列表</h1>
    <c:if test="${requestScope.page.list!=null||requestScope.page.list.size() gt 0}">
        <c:forEach var="b" items="${requestScope.page.list}">

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
        <div class="page" style="text-align: center">
            <table>
                <tr>
                    <td colspan="7">
                        <input type="button" value="首页" onclick="toFirst()">
                        <input type="button" value="上一页" onclick="toPrev()">
                        当前页 ${requestScope.page.currentPageNum}| ${requestScope.page.totalPageNum} 总页数
                        <input type="button" value="下一页" onclick="toNext()">
                        <input type="button" value="末页" onclick="toLast()">
                    </td>
                </tr>
            </table>
        </div>
    </c:if>


    <c:if test="${requestScope.page.list==null||requestScope.page.list.size() == 0}">
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
    var currentPage=${requestScope.page.currentPageNum};
    var totalPage=${requestScope.page.totalPageNum};
    function toFirst() {
                    location.href="listPage?currentPage=1";
                }
    function toPrev() {
                    //控制页面显示风格
        var url="";
            if(currentPage==1){
                        url="listPage?currentPage=1";
                    }
            else{
                        url="listPage?currentPage="+(currentPage-1);
                    }
            location.href=url;

                }
    function toNext() {
                    //控制页面显示风格
        var url="";
            if(currentPage=totalPage){
                        url="listPage?currentPage="+totalPage;
                    }else{
                        url="listPage?currentPage="+(currentPage+1);
                    }
                    location.href=url;

                }
    function toLast() {
        console.log(${requestScope.page.totalPageNum});
        location.href="listPage?currentPage="+${requestScope.page.totalPageNum};

                }
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
