<%--
  Created by IntelliJ IDEA.
  User: 86166
  Date: 2021/6/27
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="./css/reset.css">
    <link rel="stylesheet" href="./css/isOk.css">
    <title>Title</title>
</head>
<body>
<div class="publish">
    <div class="container">
        <h1 style="text-align: center;font-size:22px;font-weight:500;">博客列表</h1>
        <h1 style="text-align: center;font-weight:400;font-size:18px">审核通过的博客列表</h1>
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
<script type="text/javascript">
    function toArticle(param) {
        console.log('param'+param);
        window.location.href="article.jsp?blogId="+param;

    }
</script>
</body>
</html>
