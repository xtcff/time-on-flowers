<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>花点时间登录页</title>
<link href="${res }/themes/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="IndexServlet"><img src="${res }/themes/images/shop.jpg" /></a>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li class="top_li"><a href="IndexServlet">首页</a></li>
						<li class="top_li"><a href="register.jsp">注册</a></li>
						<li class="top_li"><a href="http://bbs.dwzjs.com">反馈</a></li>
						<li class="top_li"><a href="#" target="_blank">帮助</a></li>
					</ul>
				</div>
				<h2 class="login_title">登录花点时间</h2>
			</div>
		</div>
		<div class="loginForm">
			<div class="denglu">
			<c:if test="${failMsg != null}">
				<div style="color:red">${failMsg }</div>
			</c:if>
			<c:if test="${isRegister == true}">
				<div style="color:blue">注册成功，请登录！</div>
			</c:if>
			<form action="${ctx }/LoginServlet" method="post">
				<p>
					<label>用户名：</label>
					<input type="text" name="username" size="20" class="login_input" />
				</p>
				<p>
					<label>密码：&nbsp;&nbsp;&nbsp;</label>
					<input type="password" name="password" size="20" class="login_input" />
				</p>
				<div class="login_bar">
					<input class="sub" type="submit" value="登录" />
					<br>还没有账号？点击<a href="register.jsp">注册</a>
				</div>
			</form>
			</div>
			<div class="question">
				<h4>&nbsp;登录遇到问题？</h4>
				<ul class="bottom_ul">
					<li>忘记了自己的账号？<a href="#">找回账号</a></li><br />
					<li>忘记密码？<a href="#">找回密码</a></li><br />
					<li>账号密码正确但登录不上？可能账号被冻结，<a href="#">点此解锁</a></li>
				</ul>
			</div>
		</div>
		
		
		
		<div id="login_footer">
			Copyright &copy; 2021 hhf All Rights Reserved.
		</div>
	</div>
</body>
</html>