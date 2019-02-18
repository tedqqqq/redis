<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<HEAD>
<TITLE>用户注册</TITLE>
<%
String basePath=request.getContextPath();
System.out.println(basePath);
%>
<LINK href="<c:url value='images/Default.css'/>" type=text/css rel=stylesheet>
<LINK href="<c:url value='images/xtree.css'/>" type=text/css rel=stylesheet>
<LINK href="<c:url value='images/User_Login.css'/>" type=text/css rel=stylesheet>
</HEAD>

<body>
	<h3>注册页面</h3>
	<br>
	<form action="registerR" method="post">
		<table>
			<tr>
				<td><label>登录名：</label></td>
				<td><input type="text" id="loginname" name="userName" value="${user.userName }" ></td>
			</tr>
			<tr>
				<td><label>密码：</label></td>
				<td><input type="password" id="password" name="password" value="${user.password }"></td>
			</tr>
			<tr>
				<td><label>年龄：</label></td>
				<td><input type="text" id="age" name="age" value="${user.age }"></td>
			</tr>
			<tr>
				<td><input id="submit" type="submit" value="注册"></td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
 window.onload=function (){
	var msg = "${message.msg}";
	if(msg!=""){
		alert(msg);
	}
}



</script>
</html>