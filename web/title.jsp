<%--
  Created by IntelliJ IDEA.
  User: 86166
  Date: 2021/6/14
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="./css/reset.css">
    <link rel="stylesheet" href="./css/title.css">
    <title>Title</title>
</head>
<body>
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
                                        <c:if test="${b.is_ok} eq 0">
                                            <li>
                                                <p class="browse" name="browse">待审核</p>
                                            </li>
                                        </c:if>

                                        <c:if test="${b.is_ok} eq 1">
                                            <li>
                                                <p class="browse" name="browse">审核通过</p>
                                            </li>
                                        </c:if>

                                        <c:if test="${b.is_ok} eq 1">
                                            <li>
                                                <p class="browse" name="browse">审核不通过</p>
                                            </li>
                                        </c:if>


                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="right">
                            <c:if test="${b.author_email eq sessionScope.user.email}">
                                <div class="update">
                                    <a href="javascript:void(0);" onclick="editBlog('${b.id}')">编辑</a>
                                    <a href="javascript:void(0);" onclick="deleteBlog('${b.id}')">删除</a>

                                    <c:if test="${b.is_ok} eq 0">
                                        <a href="javascript:void(0)" >待审核</a>
                                    </c:if>

                                    <c:if test="${b.is_ok} eq 1">
                                        <a href="javascript:void(0)" >审核通过</a>
                                    </c:if>


                                    <c:if test="${b.is_ok} eq 2">
                                        <a href="javascript:void(0)" >审核不通过</a>
                                    </c:if>



                                </div>
                            </c:if>
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
<script>
    function toArticle(param) {
        console.log('param'+param);
        window.location.href="article.jsp?blogId="+param;

    }
    function editBlog(id) {
        console.log(id);
        window.location.href="editblog.jsp?blogId="+id;
    }
    function deleteBlog(id){
        console.log(id);
        window.location.href="deleteBlog?blogId="+id;
    }
</script>
</body>
</html>
