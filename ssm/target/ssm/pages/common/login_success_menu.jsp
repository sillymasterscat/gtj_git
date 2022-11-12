<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
    <c:if test="${not empty sessionScope.user.username}">
        <a href="order/userOrder">我的订单</a>
        <a href="cart/cartPage?id=${sessionScope.user.id}">购物车</a>
        <a href="user/logout">注销</a>&nbsp;&nbsp;
    </c:if>
    <a href="index.jsp">返回</a>
</div>