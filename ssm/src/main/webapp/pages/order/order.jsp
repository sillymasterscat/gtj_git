<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>


	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">我的订单</span>

		<%--静态包含，登录 成功之后的菜单 --%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>


	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
			</tr>

			<c:forEach items="${requestScope.orderList}" var="orderItem">
				<tr>
					<td>${orderItem.orderId}</td>
					<td>${orderItem.datetime}</td>
					<td>${orderItem.price}</td>
					<td>
						<c:choose>
							<c:when test="${orderItem.status == 0}">
								未发货
							</c:when>
							<c:when test="${orderItem.status == 1}">
								已发货
							</c:when>
							<c:when test="${orderItem.status == 2}">
								待签收
							</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>

		</table>
		
	
	</div>


	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>