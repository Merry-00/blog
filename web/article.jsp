
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>查看文章</title>
    <link type="text/css" href="./editormd/css/editormd.min.css" rel="stylesheet" />
    <link type="text/css" href="./css/article.css" rel="stylesheet" />
    <script type="text/javascript" src="./js/jquery-1.8.3.js"></script>
</head>
<body id="body">
<div class="container">
        <h1 id="title" ></h1>
        <div class="info">
            <ul class="nav">
                <li> <a href=""><span id="nickName"></span></a></li>
                <li><div class="browse">
                    <img src="./img/browse.png" alt="">
                    <span id="browse"></span>
                </div>
                </li>
                <li>
                    <div class="publishTime">
                        <span id="time"></span>
                    </div>
                </li>

                <li>
                    <div class="collect">
                        <img src="./img/collect.png" alt="">
                        <span id="collect"></span>
                    </div>
                </li>
            </ul>
        </div>
        <div class="content" id="articleContainer">
            <textarea class="editormd-html-textarea" name="textArticle" id="textArticle"></textarea>
        </div>
    <div class="com_container">
        <form onsubmit="return false">
            <div class="comment">
                <textarea id="comment" name="content" id="content" cols="67" rows="3" placeholder="请输入评论"></textarea>
                <div class="button">
                    <button type="button" id="comBtn"  class="btn btn-primary">发布评论</button>
                </div>
            </div>
        </form>
        <div class="show">
            <h1>评论</h1>
            <table>
                <!--对文章的评论放这里-->
                <tbody id="tbody">
                </tbody>
            </table>
        </div>
        <!-这部分先隐藏--->
        <div class="more-comment" id="light">
            <!--white用来装某个对文章评论的子评论，点击查看更多，就显示这个框，点击关闭就隐藏这个框-->
            <a href="javascript:void(0)" onclick="closeDiv()">关闭评论</a>
            <div >
                <table>
                    <!--子评论，放这里对文章的评论放这里-->
                    <tbody id="moreBody">
                    </tbody>
                </table>
            </div>
<%--            <div class="black" id="fade"><!--这不放，空的？对--></div>--%>
        </div>
    </div>

</div>


<script type="text/javascript" src="./editormd/editormd.min.js"></script>
<script type="text/javascript" src="./editormd/lib/marked.min.js"></script>
<script type="text/javascript" src="./editormd/lib/prettify.min.js"></script>

<script type="text/javascript">
    var testEditor;
    var titleId="";
    var parentId="";//parentId默认为titleId或者为随机字符
    $(function () {
        getArticleRequest();
        selectComment();
        $("#comBtn").click(function () {
            var param = getQueryVariable("blogId");
            var comment = $("#comment").val();
            var email = JSON.parse(localStorage.getItem("email"));
            var parentId = decodeURI(param);
            var titleId = decodeURI(param);
            var url = 'saveComment';
            var data = {
                "comment":comment,
                "email":email,
                "parentId":parentId,
                "titleId":titleId
            };
            var success = function(data){
                if(data=="尚未登录，权限不足！"){
                    alert(data);
                    window.location.href="http://localhost:8080/blog_war_exploded/login.jsp";
                }
                else{
                    alert(data);
                }
            }
            $.post(url,data,success);
        });

    })

    //解析url中的参数
    function getQueryVariable(variable)
    {
        //index.jsp?blogId=123456
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return(false);
    }

    /**
     * 获取文章内容的请求
     */
    function getArticleRequest() {

        var param = getQueryVariable("blogId");

        //发送post请求
        $.post(
            "readBlog",
            {"blogId":decodeURI(param)},
            function (data) {
            if(data){
                var result=data;
                $("#title").html(result.title);
                $("#collect").html(result.collect);
                $("#browse").html(result.browse);
                var time =result.publish_time.time;
                $("#time").html(new Date(time).toLocaleDateString());
                $("#nickName").html(result.author_email);
                $("#textArticle").html(result.content);
                showArticle();
            }
        });
    }

    function showArticle(){
        testEditor = editormd.markdownToHTML("articleContainer", {
            width  :  "70%",
            height :  500,
            preview  : true,
            watch : true,
            editor : false,    // 不可编辑
            path   : "./editormd/lib/"
        });
    }
    //提交请求，查询评论
    function selectComment(){
        var param = getQueryVariable("blogId");
        $.post(
            "selectComment",
            {"articleId":decodeURI(param)},
            function (result) {
                if(result!="还没有人评论"){
                    var data=JSON.parse(result);
                    console.log(data);
                    console.log("获取到了吗");
                    var body=document.getElementById("tbody");
                    showComment(data,body);

                }
                else{
                    console.log("怎么会这样呢")
                }
            }
        );
    }
    // 评论显示
    function showComment(data,body){
        body.innerHTML="";
        for(var i=0;i<data.length;i++){
            //对文章的评论的人的nickName
            var td_name=document.createElement("td");
            var text_name=document.createTextNode(data[i].nickName);
            td_name.appendChild(text_name);
            //对文章评论的评论内容
            var td_content=document.createElement("td");
            var text_content=document.createTextNode(data[i].content);
            td_content.appendChild(text_content);
            var info=document.createElement("span");
            var text=document.createTextNode(":");
            info.append(text);
            td_name.appendChild(info);
            td_name.setAttribute("class","email");
            td_content.setAttribute("class","content");
            //创建a标签
            var ele_a=document.createElement("a");
            ele_a.setAttribute("class","reply");
            var text_a=document.createTextNode("回复:");
            ele_a.setAttribute("href","javascript:void(0)");
            ele_a.setAttribute("onclick",'handleReplyCommentClick('+JSON.stringify(data[i])+')');
            ele_a.appendChild(text_a);
            td_content.appendChild(ele_a);
            //创建查看更多评论的a标签
            var ele_show=document.createElement("a");
            ele_show.setAttribute("class","showComment");
            ele_show.setAttribute("href","javascript:void(0)");
            ele_show.setAttribute("onclick",'showMore('+JSON.stringify(data[i])+')');var text_show=document.createTextNode("查看更多评论");
            ele_show.appendChild(text_show);
            td_content.appendChild(ele_show);
            // //创建关闭窗口的a标签
            // var ele_close=document.createElement("a");
            // ele_close.setAttribute("class","closeDiv");
            // ele_close.setAttribute("href","javascript:void(0)");
            // ele_close.setAttribute("onclick",'closeDiv("'+data[i]+'")');
            // var ele_textShow=document.createTextNode("关闭窗口");
            // ele_close.appendChild(ele_textShow);
            // //两个div
            // var ele_white=document.createElement("div")
            // ele_white.setAttribute("class","white");
            // ele_white.setAttribute("id","light");
            // //阴影的div
            // var ele_black=document.createElement("div");
            // ele_black.setAttribute("class","black");
            // ele_black.setAttribute("id","fade");
            // // //将关闭的a标签添加到white div中
            // ele_white.appendChild(ele_close);
            //4.创建tr,将td添加到tr中
            var tr=document.createElement("tr");
            tr.appendChild(td_name);
            tr.appendChild(td_content);//是
            body.appendChild(tr);

        }
    }
    //点击查看更多评论弹出显示框
    function showMore(data) {
        if ($("#light")) {
            $("#light").show();
        }
        showMoreComment(data);

    }

    /**
     * 查看子评论将
     * @param data
     */
    function showMoreComment(data){
            var commentId=data.commentId;
            $.post(
                "replyComment",
                {
                    "articleId":data.titleId,
                    "parentId":commentId
                },
                function(success){
                   if(success){
                       if(success=="还没有人评论")//就这样可以了呀
                       {
                           $("#light").hide();
                           var body=document.getElementById("moreBody");
                           body.innerHTML="";//清空之前的数据
                           alert("没有人回复此条消息");
                       }
                       else{
                           var result=JSON.parse(success);
                           console.log("查询更多评论",result);
                           //
                           var body=document.getElementById("moreBody");
                           body.innerHTML="";//清空之前的数据
                           showComment(result,body);
                       }

                   }
                }
            );
        }
        //展示所有的回复
        //关闭窗口
        function closeDiv() {
            if ($("#light")) {
                $("#light").hide();
            }
        }
        //处理回复的评论的输入框
        function handleReplyCommentClick(data) {
        console.log(data);
            var result = data;
            var nickName = result.nickName;
            var comment = window.prompt("回复@ " + nickName, "请输入评论内容");
            var parentId = result.commentId;
            if (comment) {
                $.post(
                    "saveComment",
                    {
                        "comment": comment,
                        "parentId": parentId,
                        "titleId": result.titleId,
                        "email": JSON.parse(localStorage.getItem("email"))
                    },
                    function (result) {
                        alert(result);
                    }
                );
            } else if (comment == "") {
                alert("您还没有输入内容");
            }
        }
</script>
</body>
</html>
