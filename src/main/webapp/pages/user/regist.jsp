<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--	静态包含头部脚本--%>
	<%@include file="/pages/common/head.jsp"%>
	<script src="/common/redirect.js"></script>
	<script type="text/javascript">
		// 页面加载完成之后
		$(function () {
			$("#sub_btn").click(function () {
				$(".errorMsg").text("test1");
				// 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
				var usernamePatt = /^\w{5,12}$/;
				let usrname = $("#username").val();
				if(!usernamePatt.test(usrname)){
					$("span.errorMsg").text("用户名不合法，请重新输入");
					return false;
				}
				// 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
				let passwd = $("#password").val();
				if(!usernamePatt.test(passwd)){
					$("span.errorMsg").text("密码不合法，请重新输入");
					return false;
				}
				// 验证密码是否一致
				let repwd = $("#repwd").val();
				if(passwd != repwd){
					$("span.errorMsg").text("两次输入密码不一致");
					return false;
				}
				// 确认电子邮件是否合法
				var emailPattern = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
				let emailVal = $("#email").val();
				if(!emailPattern.test(emailVal)){
					$("span.errorMsg").text("请输入正确的邮箱号");
					return false;
				}
				//确认验证码是否为空
				let codeVal = $("#code").val();
				codeVal = codeVal.trim()
				if(codeVal == null || codeVal == ""){
					$("span.errorMsg").text("请输入正确的验证码");
					return false;
				}
				// 去掉错误信息
				$("span.errorMsg").text("");
				return true;
			});

			// 给验证码的图片，绑定单击事件
			$("#code_img").click(function () {
				// 在事件响应的function函数中有一个this对象。这个this对象，是当前正在响应事件的dom对象
				// src属性表示验证码img标签的 图片路径。它可读，可写
				// alert(this.src);
				this.src = "${basePath}kaptcha.jpg?d=" + new Date();
			});

			$("#username").blur(function () {
				//1 获取用户名
				var username = this.value;
				$.getJSON("<%=basePath%>UserServlet", "action=ajaxExistUsername&username=" + username,function (data) {
					if (data.existsUsername) {
						$("span.errorMsg").text("用户名已存在！");
					} else {
						$("span.errorMsg").text("用户名可用！");
					}
				});
			});

		});
	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="post">
									<input type="hidden" name="action" value="regist" />
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
										   value="${requestScope.username}" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password"/>
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd"/>
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
										   value="${requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 120px;" name="code" id="code"/>
									<img alt="" id="code_img" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 120px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--		静态包含footer部分--%>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>