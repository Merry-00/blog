<%--
Created by IntelliJ IDEA.
User: 86166
Date: 2021/4/1
Time: 10:45
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>

    <link rel="stylesheet" href="./boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/reset.css">
    <link rel="stylesheet" href="./css/login.css">
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/jquery.cookie.js"></script>
    <script type="text/javascript" src="js/jquery.base64.js"></script>


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
    <form class="formlogin" action="http://localhost:8080/blog_war_exploded/login" method="POST" onsubmit="return SignIn();">
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
        <div class="button">
            <input id="loginbutton" type="submit" value="登录">

        </div>
        <div class="checkbox">
            <label>
                <input name="check" id="check" type="checkbox"> 记住密码
            </label>
            <label>
                <input id="autologin" class="antologin" type="checkbox"> 自动登录
            </label>
        </div>
        <div class="forget">
            <a href="">忘记密码？</a>
        </div>
        <div class="create">
            <a href="http://localhost:8080/blog_war_exploded/registerServlet.jsp">没有注册？立即注册一个。</a>
        </div>

    </form>
</div>

<%--<script src="boot/js/jquery/dist/jquery.min.js"></script>--%>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只-加载单个插件。 -->
<script src="boot/js/bootstrap.min.js"></script>
<script type="text/javascript">
    /**
     * 利用javascript和html css实现一个简单的登录功能
     分析：
     需求：
     1.有两个输入框，第一个是输入用户名，第二个是输入密码
     2.一个登录按钮，当点击登录按钮之后，要获取用户输入的信息，进行判断
     2.1判断输入框是否为空
     是---提示不能为空的信息

     2.2判断输入的用户名，密码格式是否正确
     是---进行下一步
     否---提示格式错误，并重新输入
     2.3 进行在数组中查找用户名和密码是否匹配
     * 2.1为false 2.2为ture 2.3 为true -----(登录成功)
     2.3为false----登录失败----(用户名不存在或者密码错误！)
     功能：
     1. 登录按钮绑定一个事件---onclick;
     2. 给事件一个方法SignIn()----当点击了登录按钮就会执行此方法
     */
    var ele = {};//定义一个对象
    //这个方法用于初始化数据和加载页面之后执行的操作函数
    window.onload=function(){
        //失焦时进行验证---调用checkname()和checkPsd()方法
        //存放各个input字段的obj
        ele = {
            //有两个变量
            email: document.getElementById("email"),
            password: document.getElementById("psd"),
        };
        //当username失去焦点时检测
        ele.email.onblur = function(){
            checkName(ele.email.value);
        }
        //当password失去焦点时检测
        ele.password.onblur = function(){
            checkPassword(ele.password.value);
        }
        getCookie();
    }
    /**
     SignIn()用于当点击登录按钮之后，先进行格式是否匹配判断，在进行用户名和密码是否匹配判断
     当匹配成功之后，会跳转到登录成功的界面，否则提示登录失败的信息。
     跳转html界面使用的是window.location.href="";
     */
    function SignIn(){
        var infoall=document.getElementById("psdInfo");
        if(check()){
            infoall.innerHTML="";
            //判断是否选中复选框,如果选中，添加cookie
            var jizhupsd=$("input[type='checkbox']").is(':checked');
            console.log("是否记住密码"+jizhupsd);
            if(jizhupsd){
                setCookie();
            }else{
                clearAllCookie();
            }
            localStorage.setItem("email",JSON.stringify(ele.email.value));
            //window.location.href="http://127.0.0.1:5500/%E4%BB%BFApple%E5%95%86%E5%9F%8E%E9%A1%B9%E7%9B%AE%E5%AE%9E%E9%AA%8C%E4%B8%80/loginM.html";
            return true;
            // window.location.href= "http://localhost:8080/demo_war_exploded/loginM.html"
        }
        else{
            var y="输入的格式不对！";
            infoall.innerHTML=y+"";
            return false;
        }
    }
    /**
     *checkName()用户检测用户输入的用户名是否为空以及用户名格式是否正确
     */
    function checkName(email){
        var info=document.getElementById("userInfo");

        if(email!=""){
            var reg1=new RegExp("[1-9]\\d{7,10}@qq\\.com");//格式为qq邮箱
            var flag=reg1.test(email);
            info.innerHTML="";
            return flag;
        }
        else{
            var x="QQ邮箱不能为空！"
            info.innerHTML=x+"";
            return false;
        }

    }
    /**
     * checkPassword()用于检测用户输入的密码是否为空以及密码格式是否输入正确。
     */
    function checkPassword(password){

        var infopsd=document.getElementById("psdInfo");
        if(password!=""){
            var reg2=new RegExp("^[a-zA-Z0-9_]{6,12}$");//6-12位的字母，数字，下划线
            infopsd.innerHTML="";
            return reg2.test(password);
        }
        else{
            var psdinfo="密码不能为空！";
            infopsd.innerHTML=psdinfo+"";
            return false;
        }

    }
    //提交表单时进行check()验证
    function check(){
        var nameok = false;
        var passwordok = false;
        if(checkName(ele.email.value)){
            nameok = true;
        }
        if(checkPassword(ele.password.value)){
            passwordok = true;
        }
        if(nameok && passwordok){
            return true;
        }
        return false;
    }

    //设置cookie
    function setCookie(){
        var loginCode=$("#email").val();//获取用户名信息
        var psd=$("#psd").val();//获取登录密码
        //获取是否勾选自动登录
        var check=$("input[type='checkbox']").is(':checked');
        var flag=$("#autologin").is(':checked');
        if(check){
            alert("进入cookie存储");
            //设置cookie过期时间
            var date=new Date();
            date.setTime(date.getTime()+60*60*1000);//60表示60秒
            //调用jquery.cookie.js中的方法设置cookie中的用户名
            $.cookie("login_code",loginCode,{expires:date,path:'/'});
            //调用jquery.base64.js进行加密
            $.cookie("psd",$.base64.encode(psd),{expires: date,path:'/'});
        }else{
            $.cookie("login_code",null);
            $.cookie("psd",null);
        }
        if(flag){
            //设置cookie过期时间
            var date=new Date();
            date.setTime(date.getTime()+60*60*1000);//60表示60秒
            //$.cookie("email",loginCode,{expires:date,path:'/'});
            $.cookie("email",encodeURI(loginCode),{expires:date,path:'/'});
            //调用jquery.base64.js进行加密
            $.cookie("psd",$.base64.encode(psd),{expires: date,path:'/'});
            $.cookie("autologin",flag,{expires: date,path:'/'});
        }
        else{
                $.cookie("autologin",null);
                $.cookie("email",null);
                $.cookie("psd",null);
            }
    }
    //清除所有的cookie函数
    function clearAllCookie(){
        var date=new Date();
        date.setTime(date.getTime()-10000);
        var keys=document.cookie.match(/[^=;]+(?=\=)/g);
        console.log("需要删除的cookie名字"+keys);
        if(keys){
            for(var i=keys.length;i--;){
                document.cookie=keys[i]+"=0;expire="+date.toGMTString()+";path=/";
            }
        }
    }
    //获取cookie
    function getCookie(){
        var loginCode=$.cookie("login_code");
        var psd=$.cookie("psd");
        var autoLogin = $.cookie("autologin");
        if(!loginCode|| loginCode==0){
            console.log("账号未被记住");
        }
        else{
            $("#email").val(loginCode);
        }
        if(!psd||psd==0){
            console.log("密码"+!psd);
        }else{
            //密码存在的话把密码填充到密码文本框
            console.log("密码解密后:"+$.base64.decode(psd));
            $("#psd").val($.base64.decode(psd));
            //密码存在的话就把记住密码勾选住
            $("[name='check']").attr("checked","true");

        }
        if(!autoLogin || autoLogin==0){
            console.log("未选择自动登录");
        }else{
            $("#autologin").attr("checked","true");
        }
    }
    //异步登录逻辑
    // function login(email,psd){
    //     $.post(
    //         "login",{
    //             "email":email,
    //             "psd":psd,
    //         },
    //         function(data){
    //             if(data==1){
    //                 location.href="main.jsp";
    //             }
    //             else{
    //                 alert("登录失败");
    //             }
    //
    //         }
    //     );
    // }
</script>
</body>
</html>
