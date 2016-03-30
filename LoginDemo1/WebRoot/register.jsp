<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/ScriptStyle.jsp" %>
<html lang="en"> 
<head>
<meta charset="utf-8">
<title>Zpark Contact Register</title>
 
</head>
<body>
	<img src="./images/logo.png" width="280" height="100" style="position:absolute;  left:100px; top:60px; z-index:3;"/> 
	<img src="./images/11.jpg" height="100%" width="100%" style="position:absolute;z-index:2;"/> 
	<div class="container" style="margin-top: 10%; font-family: fantasy; margin-top: 10%; font-family: fantasy;position:absolute;z-index:4;width: 1300px; left:30px; top:80px;">
		<section id="content">
			<form action="/LoginDemo1/servlet/RegistUserServlet" method='post'>
				<h1>注册用户</h1>
				<div>
					<input type="text" placeholder="用户名" required="" name="username" style="background:url(./images/2.png) no-repeat; "/>
				</div>
				<div>
					<input type="text" placeholder="昵称" required="" name="nickname" style="background:url(./images/2.png) no-repeat; "/>
				</div>
				<div>
					<input type="password" placeholder="密码" required="" name="pwd" style="background:url(./images/1.png) no-repeat; "/>
				</div>
				<div>
					<input type="password" placeholder="确认密码" required="" name="confirmPwd" style="background:url(./images/1.png) no-repeat; "/>
				</div>
				<div>
					<input name ="butt" type="submit" value="注册" />
					<input name="butt" type="button" value="返回登陆" onClick="location.href='./login.jsp'" />
				</div>
			</form>
		</section>
	</div>
</body>
</html>