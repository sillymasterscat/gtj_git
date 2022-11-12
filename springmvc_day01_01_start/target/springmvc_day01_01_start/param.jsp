<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/18 0018
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    <a href="param/basic?username=hah&age=10">请求参数绑定</a>--%>
<%--    <form action="param/bean" method="post">--%>
<%--        姓名<input type="text" name="username" value="魂环"> <br>--%>
<%--        年龄<input type="text" name="age" value="10"> <br>--%>

<%--        用户姓名<input type="text" name="user[0].username" value="呵呵"> <br>--%>
<%--        用户年龄<input type="text" name="user[0].age" value="10" > <br>--%>

<%--        用户姓名<input type="text" name="map['one'].username" value="呵呵"> <br>--%>
<%--        用户年龄<input type="text" name="map['one'].age" value="10" > <br>--%>

<%--        <input type="submit" value="提交">--%>
<%--    </form>--%>

<form action="param/bean1" method="post">
    姓名<input type="text" name="username" value="魂环"> <br>
    年龄<input type="text" name="age" value="10"> <br>
    出生日期<input type="text" name="date" value="2000-01-01"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
