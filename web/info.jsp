<%--
  Created by IntelliJ IDEA.
  User: 86166
  Date: 2021/6/11
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="./boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/reset.css">
    <link rel="stylesheet" href="./css/info.css">
    <title>完善个人基本信息</title>
</head>
<body>
<div class="container">
    <div class="info ">
        <h1>完善基本信息</h1>
        <div class=" clearfix basic">
            <div class="show">
                <ul>
                    <li>昵称</li>
                    <li>性别</li>
                    <li id="head" name="photo">头像</li>
                </ul>
            </div>
            <div class="edit">
                <form id="form" action="http://localhost:8080/blog_war_exploded/editInfo" method="post" enctype="multipart/form-data">
                    <div class="nickname" >
                        <input type="text" name="nickName" id="nickName">
                    </div>
                    <div class="sex">
                        <input type="text" name="sex" id="sex">
                    </div>
                    <div class="img">
                        <input type="file" name="photo" id="photo">
                    </div>
                    <div>
                        <input type="submit" value="提交">
                    </div>
                </form>
            </div>
        </div>
        </div>
    </div>
</div>

<script src="boot/js/jquery/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只-加载单个插件。 -->
<script src="boot/js/bootstrap.min.js"></script>
<script type="text/javascript">
    window.onload=function(){
        var nickName=document.getElementById("nickName");
        nickName.onblur=function(){
            checkNickName();
        }
    }
    function checkNickName(){
        var nickName=document.getElementById("nickName").value;
        $.post(
            'checkName',
            {"nickName":nickName},
            //回调函数 就使后台执行完毕之后再执行的函数  data表示后台执行的结果，返回的值
            function (data) {
                console.log(data);
                alert(data);
            }
        )
    }
</script>
</body>
</html>
