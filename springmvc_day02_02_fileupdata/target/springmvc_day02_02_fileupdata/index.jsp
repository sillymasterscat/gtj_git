<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/21 0021
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="user/fileupdata" method="post" enctype="multipart/form-data">
        <input type="file" name="upload"> <br>
        <input type="submit" value="提交"><br>
    </form>

    <form action="user/MultipartFile" method="post" enctype="multipart/form-data">
        <input type="file" name="upload"> <br>
        <input type="submit" value="提交"><br>
    </form>

</body>
</html>
