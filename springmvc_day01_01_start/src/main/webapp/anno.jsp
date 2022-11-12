<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/18 0018
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="anno/useRequestParam?name=hah">请求参数绑定</a>

    <form action="anno/useRequestBody" method="post">
        姓名<input type="text" name="username" value="魂环"> <br>
        年龄<input type="text" name="age" value="10"> <br>
        出生日期<input type="text" name="date" value="2000-01-01"><br>
        <input type="submit" value="提交">
    </form>


    <a href="anno/usePathVariable/1">请求参数绑定</a>

    <a href="anno/RequestHeader">RequestHeader</a>

    <a href="anno/CookieValue">CookieValue</a>

    <a href="anno/RequestHeader">RequestHeader</a>


    <form action="anno/ModelAttribute" method="post">
        姓名<input type="text" name="username" value="魂环"> <br>
        年龄<input type="text" name="age" value="10"> <br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
