<%--
  Created by IntelliJ IDEA.
  User: hgmyh
  Date: 2020/8/21
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h1>登录</h1>
<font color="red">${msg}</font>
<form action="${pageContext.request.contextPath}/user/login" method="post">
    用户名:<input type="text" name="username" value="${user.username}"/><font color="red">${errors.username}</font><br/>
    密码:<input type="password" name="password" value="${user.password}"/><font color="red">${errors.password}</font><br/>
    <input type="submit" value="登录" />
</form>
</body>
</html>
