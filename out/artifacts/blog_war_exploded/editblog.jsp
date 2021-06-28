
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑博客</title>
    <link type="text/css" href="./editormd/css/editormd.min.css" rel="stylesheet" />
    <link type="text/css" href="./css/article.css" rel="stylesheet" />
    <script type="text/javascript" src="./js/jquery-1.8.3.js"></script>
</head>
<body>
<div class="container">
    <h1 id="title" ></h1>
    <div class="content" id="articleContainer">
        <textarea style="display:none;" class="form-control" id="editormd" name="editormd"></textarea>
        <textarea class="editormd-html-textarea" name="textArticle" id="textArticle"></textarea>
    </div>

</div>
<button id="publishBtn" class="btn btn-primary" style="height:40px; margin:0 auto; line-height: 40px;text-align: center">发布博客</button>
<script type="text/javascript" src="./editormd/editormd.min.js"></script>
<script type="text/javascript" src="./editormd/lib/marked.min.js"></script>
<script type="text/javascript" src="./editormd/lib/prettify.min.js"></script>

<script type="text/javascript">
    var testEditor;
    $(function () {

        getArticleRequest();

        $("#publishBtn").click(function(){
            var param=getQueryVariable("blogId");
            var blogId=decodeURI(param);
            var data=testEditor.getMarkdown();
            var title = $("#title").val();
            var partContent = $(".markdown-body").find(':first').text()+ $(".markdown-body").children().eq(1).text();
            console.log("进入点击事件");
            console.log(partContent);
            $.ajax({
                data:{"data":data,"title":title,"partContent":partContent},
                type:"POST",
                dataType:"json",
                url:"${pageContext.request.contextPath}/editBlog?blogId="+blogId,
                success:function(msg){
                    if(msg.message==0){
                        window.location.href=msg.url;
                    }
                    else{
                        window.location.href=msg.url;
                    }
                },
                error:function(msg){
                    alert("ajax连接异常"+msg);
                }
            })
        });

    })
    //解析url参数
    function getQueryVariable(variable)
    {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return(false);
    }
    function getArticleRequest() {

        var param = getQueryVariable("blogId");

        console.log(decodeURI(param));

        $.post(
            "readBlog",
            {"blogId":decodeURI(param)},
            function (data) {
                if(data){
                    var result=data;
                    console.log(data);
                    $("#title").html(result.title);
                    $("#collect").html(result.collect);
                    $("#browse").html(result.browse);
                    var time =result.publish_time.time;
                    $("#time").html(new Date(time).toLocaleDateString());
                    $("#nickName").html(result.author_email);
                    $("#editormd").html(result.content);
                    showArticle();
                }
            });
    }
    function showArticle(){
        testEditor = editormd("articleContainer", {
            width   : "90%",
            height  : 600,
            syncScrolling : "single",
            editor:true,
            // theme : "dark",
            path    : "${pageContext.request.contextPath}/editormd/lib/", //依赖lib文件夹路径
            //这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单
            saveHTMLToTextarea : true, // 保存 HTML 到 Textarea，true表示转化为html格式的内容也同样提交到后台
            toolbarAutoFixed:true,//工具栏自动固定定位的开启与禁用
            imageUpload : true,
            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL : "${pageContext.request.contextPath}/imageUpload",//要上传图片的后台服务器路径
        });
    }


</script>

</body>
</html>
