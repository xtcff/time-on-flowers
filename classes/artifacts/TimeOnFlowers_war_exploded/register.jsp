<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>花点时间注册页</title>
<link href="${res }/themes/css/register.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="register">
		<div id="register_header">
			<h1 class="register_logo">
				<a href="IndexServlet"><img src="${res }/themes/images/shop.jpg" /></a>
			</h1>
			<div class="register_headerContent">
				<div class="navList">
					<ul>
						<li class="top_li"><a href="IndexServlet">首页</a></li>
						<li class="top_li"><a href="login.jsp">登录</a></li>
						<li class="top_li"><a href="http://bbs.dwzjs.com">反馈</a></li>
						<li class="top_li"><a href="#" target="_blank">帮助</a></li>
					</ul>
				</div>
				<h2 class="register_title">注册花点时间</h2>
			</div>
		</div>
		<div class="registerForm">
			<div class="zhuce">
			<form action="RegisterServlet" method="post" onsubmit="return regis();">
				<p>
					<label>用户名：&nbsp;&nbsp;&nbsp;</label>
					<input type="text" name="username" size="20" class="register_input" id="username"/>
				</p>
				<p>
					<label>密码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<input type="password" name="password" size="20" class="register_input" id="password"/>
				</p>
				<p>
					<label>确认密码：</label>
					<input type="password" name="password_q" size="20" class="register_input" id="password_q"/>
				</p>
				<p>
					<label>手机：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<input type="text" name="phone" size="20" class="register_input" id="phone"/>
				</p>
				<p>
					<label>邮箱：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<input type="text" name="email" size="20" class="register_input" />
				</p>
				<p>
					<label>地址：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<input type="text" name="address" size="20" class="register_input" />
				</p>
				<div class="register_bar">
					<input class="sub" type="submit" value="注册" />
					<br>已有账号？点击<a href="login.jsp">登录</a>
				</div>
			</form>
			</div>
			<div class="question">
				<h4>&nbsp;注册遇到问题？</h4>
				<ul class="bottom_ul">
					<li>忘记了自己的账号？<a href="#">找回账号</a></li><br/>
					<li>忘记密码？<a href="#">找回密码</a></li><br/>
					<li>账号密码正确但登录不上？可能账号被冻结，<a href="#">点此解锁</a></li>
				</ul>
			</div>
		</div>
		
		
		
		<div id="register_footer">
			Copyright &copy; 2021 hhf All Rights Reserved.
		</div>
	</div>
</body>
<script src="${js }/jquery-1.7.2.js" type="text/javascript"></script>
<script>
	function regis() {
		/* ----------- 验证输入的账户是否合法 ------------------------- */
		if (/^[\u4e00-\u9fa5]+$/.test($('#username').val())) {
			alert("用户名不能输入汉字！");
			return false;
		}
		/* ----------- 验证输入的联系电话是否合法 --------------------- */
		if (isNaN($('#phone').val())) {
			alert("手机号请输入数字");
			return false;
		}
		/* ----------- 验证两次输入的密码是否一致 --------------------- */
		var pwd = document.getElementById("password").value;
		var pwd2 = document.getElementById("password_q").value;
		if (pwd !== pwd2) {
			alert('密码前后不一致！');
			return false;
		}
		return true;
	}
</script>
<!-- //验证输入的信息是否合法 -->
</html>