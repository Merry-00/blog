<%--
  Created by IntelliJ IDEA.
  User: 86166
  Date: 2021/6/9
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人博客系统</title>
</head>

<frameset rows="70px, *" border="1">
    <frameset  >
        <frame src="./top.jsp" name="head">
    </frameset>
    <frameset  cols="18%,*" border="1">
        <frame src="./left.jsp" name="leftmenu">
        <frame  name="main">
    </frameset>
</frameset>
</html>
