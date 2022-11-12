<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>


</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>

		<%-- 静态包含 manager管理模块的菜单  --%>
		<%@include file="/pages/common/manager_menu.jsp"%>

	</div>
	
	<div id="main">
		<table>
			<tr>
                <td>订单号</td>
                <td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>发货</td>
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
					<td>
						<c:if test="${orderItem.status == 0}">
							<a href="order/update?oid=${orderItem.orderId}">发货</a>
						</c:if>
					</td>
                    <script type="text/javascript" >
                        $(function () {
                            $("td a").click(function () {
                                var name = $(this).parent().parent().find("td:first-child").text();
                                return confirm("是否将 " + name+" 发货")
                            })

                        })
                    </script>
				</tr>
			</c:forEach>
		</table>
	</div>


	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>