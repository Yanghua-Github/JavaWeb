<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--	静态包含头部脚本--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$(".deleteItemBtn").click(function () {
				var bookName = $(this).parent().parent().find("td:first").text();
				return confirm("您确定要删除【" + bookName + "】吗？");
			});
			
			$("#clearBtn").click(function () {
				return confirm("您确定要清空购物车吗？");
			});
			
			$(".countText").change(function () {
				var bookName = $(this).parent().parent().find("td:first").text();
				var bookId = $(this).attr("bookId");
				// 获取商品数量
				var count = this.value;
				if(confirm("你确定要将【" + bookName + "】的数量修改为" + count + "吗？")){
					location.href = "<%=basePath%>CartServlet?action=updateItem&id=" + bookId + "&count=" + count + "&defaultCount=" + this.defaultValue;
				}else{
					this.value = this.defaultValue;
				}
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%--				静态包含登录成功之后的菜单 --%>
			<%@include file="/pages/common/login_success_menu.jsp"%>
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
			<c:if test="${sessionScope.cart.totalCount == 0}">
				<td colspan="5"><a href="client/BookServlet?action=getPage">亲，购物车暂时没有宝贝哦，快和小伙伴去浏览商品吧！！！</a></td>
			</c:if>
			<c:if test="${sessionScope.cart.totalCount != 0}">
				<c:forEach items="${sessionScope.cart.items}" var="cartItem">
					<tr>
						<td>${cartItem.value.name}</td>
						<td><input type="text" class="countText" value="${cartItem.value.count}" bookId="${cartItem.value.id}"/></td>
						<td>${cartItem.value.price}</td>
						<td>${cartItem.value.totalPrice}</td>
						<td><a class="deleteItemBtn" href="CartServlet?action=deleteItem&id=${cartItem.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>

		<c:if test="${sessionScope.cart.totalCount != 0}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearBtn" href="CartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="client/OrderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	</div>

	<%--		静态包含footer部分--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>