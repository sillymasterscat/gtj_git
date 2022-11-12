<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@ include file="/pages/common/head.jsp" %>
    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <div>
        <c:if test="${empty sessionScope.username}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <c:if test="${not empty sessionScope.username}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
            <a href="pages/order/order.jsp">我的订单</a>
            <a href="user/logout">注销</a>&nbsp;&nbsp;
            <a href="index.jsp">返回</a>
        </c:if>


        <a href="cart/cartPage?id=${sessionScope.user.id}">购物车</a>
        <c:if test="${sessionScope.user.status == 1}">
            <a href="pages/manager/manager.jsp">后台管理</a>
        </c:if>
    </div>
</div>

<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="book/pageByPrice" method="get">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>

        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button class="add_bookitem" bookId=${book.id}>加入购物车</button>
                    </div>
                    <Script type="text/javascript">
                        $(function () {
                            $("button.add_bookitem").click(function () {
                                // location.href="www.baidu.com";
                                var bookId = $(this).attr("bookId");
                                location.href = "/ssm_war/cart/add?id=${sessionScope.user.id}&bookId=" + bookId;
                            });
                        })
                    </Script>
                </div>
            </div>
        </c:forEach>

    </div>

    <%--静态包含分页条--%>
    <%@include file="/pages/common/page_nav.jsp" %>


</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>

</body>
</html>
