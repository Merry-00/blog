<%--
  Created by IntelliJ IDEA.
  User: 86166
  Date: 2021/6/27
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>审核博客</title>
    <link rel="stylesheet" href="./css/reset.css">
    <link rel="stylesheet" href="./css/isOk.css">
</head>
<body>
    <div class="big" style="width:100%;height:auto">
        <div class="ispublish">
            <div class="container">
                <h1 style="text-align: center;font-size:22px;font-weight:500;">博客列表</h1>
                <h1 style="text-align: center;font-weight:400;font-size:18px;margin-top:10px;">没有审核的博客列表</h1>
                <c:if test="${requestScope.blogs!=null||requestScope.blogs.size() gt 0}">
                <c:forEach var="b" items="${requestScope.blogs}">


                    <div class="item">

                        <div class="content">

                            <div class="con">
                                <a class="blog" href='article.jsp?blogId=&quot;${b.id}&quot;'>
                                    <p class="tag"  name="title"><a href="javascript:void(0);" onclick="toArticle('${b.id}')">${b.title}</a></p>
                                    <p class="ticontent"  name="content">${b.partContent}</p>
                                </a>
                            </div>

                            <div class="show">
                                <ul>
                                    <li><a class="nickName" id="nickName" name="nickName" href="">${b.nickName}</a></li>
                                    <li><span class="titime" id="publishTime" name="publish_time"><fmt:formatDate value="${b.publish_time}" type="date"/></span></li>
                                </ul>
                            </div>

                        </div>

                        <div class="right">
                            <div class="update">
                                <a href="javascript:void(0);" onclick="verifyBlog('${b.id}',1)">通过</a>
                                <a href="javascript:void(0);" onclick="verifyBlog('${b.id}',2)">不通过</a>
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

            </div>
        </div>
    </div>
    <!-- jQuery  -->
    <script src="boot/js/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript">
        function toArticle(param) {
            console.log('param'+param);
            window.location.href="article.jsp?blogId="+param;

        }

        function verifyBlog(data,num){
            console.log(data);
            //$.ajax({type:"get"});//这种你的告诉它请求的方式post或get
            //这种你不需要告诉它post或get，它是对$.ajax的进一步封装
            $.post(
                "verifyBlog",
                {
                    "blogId":data,
                    "operate":num
                },
                function (success) {
                    alert(success);
                    window.reload();
                }
            );
        }

    </script>

</body>

</html>
