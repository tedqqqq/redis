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
	<br>
	<form action="registerR" method="post">
		<table>
			<tr>
				<td><label>支付失败！</label></td>
				
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