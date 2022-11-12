<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>
</head>
<body>

<div id="header">
    <span class="wel_word">购物车</span>

    <%--静态包含，登录 成功之后的菜单 --%>
    <%@ include file="/pages/common/login_success_menu.jsp" %>


</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>

        <c:forEach items="${sessionScope.cart.list}" var="item">
            <tr>
                <td>${item.name}</td>
                <td><input id="updateCount" bookName="${item.name}" type="text" value="${item.count}"></td>
                <td>${item.price}</td>
                <td>${item.totalPrice}</td>
                <td><a id="delete_item" href="cart/delete?id=${sessionScope.user.id}&bookName=${item.name}">删除</a></td>
            </tr>
        </c:forEach>

        <script type="text/javascript">
            $(function () {
                $("#delete_item").click(function () {
                    return confirm("是否删除" + $(this).parent().parent().find("td:first").text())
                });
                $("#clearCart").click(function () {
                    return confirm("是否清空")
                });

                $("#updateCount").change(function () {
                    var count = this.value;
                    var bookName = $(this).attr("bookName");
                    var name = $(this).parent().find("td:first").text();
                    if (confirm("是否修改" + name +"数量为" + count)) {
                            <%--?id=${sessionScope.user.id}&bookId=" + bookId + "&count=" + count--%>
                        location.href = "/ssm_war/cart/update?id=${sessionScope.user.id}&bookName=" + bookName + "&count=" + count;
                    }else {
                        this.value = this.defaultValue;
                    }
                });
            })
        </script>

    </table>
    <c:if test="${not empty sessionScope.cart.list}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a id="clearCart" href="cart/clear?id=${sessionScope.user.id}">清空购物车</a></span>
            <span class="cart_span"><a href="order/bill">去结账</a></span>
        </div>
    </c:if>

</div>


<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>


</body>
</html>