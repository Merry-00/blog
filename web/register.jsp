<%--
  Created by IntelliJ IDEA.
  User: 86166
  Date: 2021/6/9
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="./boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/reset.css">
    <link rel="stylesheet" href="./css/login.css">
    <title>注册界面</title>
</head>
<body>
<div class="begin">
    <div class=" mybegin jumbotron">
        <h1>Hello!</h1>
        <p>Welcome to GBlog, You can easily use it to publish you blog</p>
        <p><a class="btn btn-primary btn-lg" href="#" role="button">Begin use</a></p>
    </div>
</div>
<div class="login">
    <h1>GBlog个人博客系统</h1>
    <form class="formlogin" action="http://localhost:8080/blog_war_exploded/register" method="POST" onsubmit="return SignUp()">
        <div class="email">
            <input type="text" name="email" id="email" placeholder="QQ邮箱">
            <div class="usernameInfo">
                <span id="userInfo">  &nbsp;</span>
            </div>
        </div>
        <div class="psd">
            <input type="password" name="password" id="psd" placeholder="密码">
            <div class="passwordInfo">
                <span id="psdInfo">  &nbsp;</span>
            </div>
        </div>
        <div class="repsd">
            <input type="password" name="repassword" id="repsd" placeholder="重复密码">
            <div class="repasswordInfo">
                <span id="repsInfo">  &nbsp;</span>
            </div>
        </div>
        <div class="button">
            <input id="loginbutton" type="submit" value="注册">

        </div>
        <div class="create">
            <a href="http://localhost:8080/blog_war_exploded/registerServlet.jsp">已有账号？立即登录</a>
        </div>

    </form>
</div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="boot/js/jquery/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只-加载单个插件。 -->
<script src="boot/js/bootstrap.min.js"></script>
<script>

   function SignUp(){
    var infoAll=document.getElementById("repsdInfo");
    if(check()){
        alert(check())
        return true;
    }else{
        infoAll.innerHTML="格式不正确";
        return false;
    }

   }
   var ele={};//定义一个对象
   //这个方法用于初始化数据和加载页面之后执行的操作函数
    window.onload=function(){
        //失焦时进行验证
        //存放各个input字段的obj
        ele={
            //有三个个变量
            email: document.getElementById("email"),
            password:document.getElementById("psd"),
            repassword: document.getElementById("repsd")

        };
        //1.当email失焦时检测
        ele.email.onblur=function(){
            checkEmail(ele.email.value);
        }
        //2.当password失焦时验证
        ele.password.onblur=function(){
            checkPassword(ele.password.value);
        }
        //3.当repassword失焦时验证
        ele.repassword.onblur=function(){
            checkEqual(ele.password.value,ele.repassword.value);
        }
    }
    //全局变量
   var emailInfo=document.getElementById("userInfo");
   var passwordInfo=document.getElementById("psdInfo");
   var rspInfo=document.getElementById("repsInfo");
   //    var reg2 = new RegExp("^[a-zA-Z0-9_]{6,12}$");
    //检查输入的email
    function checkEmail(email){
        emailInfo.innerHTML="";
        if(email!=""){
            var reg=new RegExp("[1-9]\\d{7,10}@qq.com");
            if(reg.test(email)){
                emailInfo.innerHTML="";
                return true; //email格式正确
            }
            else{
                emailInfo.innerHTML="email格式不正确";
                return false;
            }
        }
        else{
            //email不能为空
            emailInfo.innerHTML="email不能为空"
            return false;
        }
   }
   //检查输入的密码
   function checkPassword(password){
       passwordInfo.innerHTML="";
        var password=document.getElementById("psd").value;
       var reg2 = new RegExp("^[a-zA-Z0-9_]{6,12}$");
       if(password!=""){
           var flag=reg2.test(password);
           passwordInfo.innerHTML = flag?"":"密码格式错误";
           return flag;
       }
       else{
           passwordInfo.innerHTML="密码不能为空";
           return false;
       }
   }
    //检查第一次密码和第二次是否相同
   function checkEqual(password,repassword){
       rspInfo.innerHTML="";
        if(password == repassword){
            rspInfo.innerHTML="";
            return true;
        }
        else{
            rspInfo.innerHTML="两次密码不一致";
            return false;
        }
        return false;
   }
   //检查所有
   function check(){
        var email=document.getElementById("email").value;
        var password=document.getElementById("psd").value;
        var repassword=document.getElementById("repsd").value;
        var emailFlag=false;
        emailFlag=checkEmail(email);
        var psdFlag=false;
        psdFlag=checkPassword(password);
        var reFlag=false;
        reFlag=checkEqual(password,repassword);
        if(emailFlag&&psdFlag&&reFlag){
            return true;
        }
        else {
            return false;//验证不通过
        }
   }
</script>
</body>
</html>
