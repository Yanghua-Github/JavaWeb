<%@ page import="com.atguigu.web.CartServlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<%--	静态包含头部脚本--%>
	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$.ajaxSetup( {
				//设置ajax请求结束后的执行动作
				complete : function(XMLHttpRequest, textStatus) {
					alert("lddllldldld");
					// 通过XMLHttpRequest取得响应头，REDIRECT
					var enable = XMLHttpRequest.getResponseHeader("enableRedirect");//若HEADER中含有REDIRECT说明后端想重定向
					if (enable == "true") {
						var win = window;
						while (win != win.top){
							win = win.top;
						}
						//将后端重定向的地址取出来,使用win.location.href去实现重定向的要求
						win.location.href= XMLHttpRequest.getResponseHeader("redirectUrl");
					}
				}
			});

			$(".addCartBtn").click(function () {
				var id = $(this).attr("bookId");
				// 方式1：刷新页面
				<%--location.href = "<%=basePath%>" + "CartServlet?action=addItem&id=" + id;--%>
				// 方式2：使用Ajax进行局部更新
				$.getJSON("<%=basePath%>CartServlet", "action=ajaxAddItem&id=" + id, function (data) {
					$("div.book_sales.sp2").innerText(data.book.sales);
					$("div.book_amount.sp2").innerText(data.book.stock);
				});
			});
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<div>
				<c:if test="${empty sessionScope.user}">
					<a href="pages/user/login.jsp">登录</a> |
					<a href="pages/user/regist.jsp">注册</a>
				</c:if>
				<c:if test="${not empty sessionScope.user}">
					<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
					<a href="pages/order/order.jsp">我的订单</a>
					<a href="UserServlet?action=logout">注销</a>
				</c:if> &nbsp;&nbsp;
				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/manager/manager.jsp">后台管理</a>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/BookServlet" method="get">
					<input type="hidden" name="action" value="getPageByPrice"/>
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<span>您的购物车中有3件商品</span>
				<div>
					您刚刚将<span style="color: red">时间简史</span>加入到了购物车中
				</div>
			</div>

			<c:forEach items="${requestScope.page.items}" var="book">

				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.imgPath}" />
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
							<button class="addCartBtn" bookId="${book.id}">加入购物车</button>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

		<%--静态包含分页条--%>
		<%@include file="/pages/common/page_nav.jsp"%>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>