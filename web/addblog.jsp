<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="utf-8" />
  <title>示例</title>
<%--  editormd css --%>
  <!-- Bootstrap -->
  <link href="./boot/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/editormd/css/editormd.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/editormd/css/editormd.preview.min.css" />

</head>
<body>
<div id="layout">
  <header>
    <h1>写博客</h1>
    <div class="blogInfo">
      <form class="form-inline">
        <form class="form-inline">
          <div class="form-group">
            <label class="sr-only" for="title">文章标题</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="文章标题">
          </div>
          <div class="form-group">
            <label class="sr-only" for="classify">文章分类</label>
            <input type="text" class="form-control" id="classify" name="classify" placeholder="文章分类">
          </div>
        </form>
        <div>
          <label >文章标签</label>
          <select id="label" class="form-control">
            <option name="blog" value="java">Java</option>
            <option name="blog" value="python">Python</option>
            <option name="blog" value="front">前端</option>
            <option name="blog" value="end">后端</option>
            <option name="blog" value="frame">框架</option>
            <option name="blog" value="C">C语言</option>
            <option name="blog" value="C++">C++</option>
            <option name="blog" value="interview">面试</option>
            <option name="blog" value="data">大数据</option>
            <option name="blog" value="life">生活</option>
          </select>
        </div>
      </form>
    </div>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </header>
  <div id="test-editormd">
    <textarea style="display:none;" class="form-control" id="editormd" name="editormd"></textarea>
    <textarea class="editormd-html-textarea" name="text" id="editormdData"></textarea>
  </div>
</div>

<%--jQuery --%>

<!-- jQuery  -->
<script src="boot/js/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/editormd/examples/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/editormd/editormd.js"></script>
<script src="${pageContext.request.contextPath}/editormd/lib/marked.min.js"></script>
<script src="${pageContext.request.contextPath}/editormd/lib/prettify.min.js"></script>
<%--<script src="editormd/js/editormd.js"></script>--%>
<script type="text/javascript">
  var testEditor;
    $(function() {
<%--    ${pageContext.request.contextPath}获取到的是项目路径：blog_war_exploded--%>
    console.log("${pageContext.request.contextPath}/imageUpload");
    //Editor初始化：
    testEditor = editormd("test-editormd", {
      width   : "90%",
      height  : 600,
      syncScrolling : "single",
      //theme : "dark",
      path    : "${pageContext.request.contextPath}/editormd/lib/", //依赖lib文件夹路径
      //这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单
      saveHTMLToTextarea : true, // 保存 HTML 到 Textarea，true表示转化为html格式的内容也同样提交到后台
      toolbarAutoFixed:true,//工具栏自动固定定位的开启与禁用
      imageUpload : true,
      imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
      imageUploadURL : "${pageContext.request.contextPath}/imageUpload",//要上传图片的后台服务器路径
      onload: function() {
        //console.log('onload success');
      }
    });
    $("#saveBtn").click(function(){
      var data=testEditor.getMarkdown();
      console.log(data);
      var title = $("#title").val();
      var classify=$("#classify").val();
      var label=$("#label").val();
      var partContent = $(".markdown-body").find(':first').text()+ $(".markdown-body").children().eq(1).text();
      console.log(partContent);
      $.ajax({
        data:{"data":data,"title":title,"classify":classify,"label":label,"partContent":partContent},
        type:"POST",
        url:'${pageContext.request.contextPath}/saveData',
        success:function(msg){
          var result = JSON.parse(msg);
          if(result.message==1){
            console.log(result.url)
            window.location.href=result.url;
          }
          console.log(result)
        },
        error:function(msg){
          alert("ajax连接异常"+msg);
        }
      })

    });
   // editormd.markdownToHTML("content",{emoji:false});
  });

</script>
<button id="saveBtn" type="submit" class="btn btn-primary" style="height:40px; margin:0 auto; line-height: 40px;text-align: center">发布博客</button>
</body>
</html>
