<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<HEAD>
<TITLE>用户登录</TITLE>
<LINK href="images/Default.css" type=text/css rel=stylesheet>
<LINK href="images/xtree.css" type=text/css rel=stylesheet>
<LINK href="images/User_Login.css" type=text/css rel=stylesheet>
</HEAD>
<body>
	<h3>修改页面</h3>
	<br>
	<form action="modify" method="post">
		<table>
			<tr>
				<td><label>id：</label></td>
				<td><input type="text" id="loginname" name="id" value="${user.id}"  ></td>
			</tr>
			<tr>
				<td><label>登录名：</label></td>
				<td><input type="text" id="loginname" name="userName" value="${user.userName}"  ></td>
			</tr>
			<tr>
				<td><label>密码：</label></td>
				<td><input type="password" id="password" name="password" value="${user.password}"></td>
			</tr>
			<tr>
				<td><label>年龄：</label></td>
				<td><input type="text" id="age" name="age" value="${user.age}"></td>
			</tr>
			<tr>
				<td><input id="submit" type="submit" value="修改"></td>
			</tr>
		</table>
	</form>
</body>
</html>