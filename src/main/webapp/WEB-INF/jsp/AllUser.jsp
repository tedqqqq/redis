<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src='<c:url value="/js/jquery.js"></c:url>'></script>
<title>用户信息</title>
</head>
<body>
	<form id="form1" action="queryByParm" method="post">
		<div align="center">
			<input id="np" name="pageNow" type="hidden" /> 姓名：<input
				name="userName" value="${user.userName }"> 年龄：<input
				name="age" value="${user.age }"> id:<input name="id"
				value="${user.id }"> <input id="subbu" type="submit"
				value="查询"><br />
		</div>
		<a href="javascript:void(0);" onclick="delMyBlog();"
			rel="external nofollow">删除选中</a>
		<table align="center">
			<tr>

			</tr>
			<c:forEach var="users" items="${alluser}">
				<tr>
					<td><input type="checkbox" name="quanxuani" id="usCheck"
						value='${users.id}'></td>
					<td><c:out value="id：${users.id}" /></td>
					<td><c:out value="姓名：${users.userName}" /></td>
					<td><c:out value="年龄：${users.age}" /></td>
					<td><a href="del?id=${users.id}" onclick="java">删除</a> <a
						href="update?id=${users.id}">修改</a></td>
				</tr>
			</c:forEach>
		</table>
		<div align="center">
			<font size="2">共 ${page.totalCount} 条记录 页次:</font> <font size="2">
				${page.pageNow}/${page.totalPageCount} 页</font> <a
				href="javascript:dopage(1);" rel="external nofollow">首页</a>
			<c:choose>
				<c:when test="${page.pageNow - 1 > 0}">
					<a href="javascript:dopage(${page.pageNow - 1});"
						rel="external nofollow">上一页</a>
				</c:when>
				<c:when test="${page.pageNow - 1 <= 0}">
					<a href="javascript:dopage(1);" rel="external nofollow">上一页</a>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${page.totalPageCount==0}">
					<a href="javascript:dopage(${page.pageNow});"
						rel="external nofollow">下一页</a>
				</c:when>
				<c:when test="${page.pageNow + 1 < page.totalPageCount}">
					<a href="javascript:void(0);"
						onclick="dopage(${page.pageNow + 1});" rel="external nofollow">下一页</a>
				</c:when>
				<c:when test="${page.pageNow + 1 >= page.totalPageCount}">
					<a href="javascript:dopage('${page.totalPageCount}');"
						rel="external nofollow">下一页</a>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${page.totalPageCount==0}">
					<a href="javascript:dopage('${page.pageNow}');"
						rel="external nofollow">下一页</a>
				</c:when>
				<c:otherwise>
					<a href="javascript:dopage('${page.totalPageCount}');"
						rel="external nofollow">尾页</a>
				</c:otherwise>
			</c:choose>
		</div>
	</form>
</body>
<script type="text/javascript">
function dopage(pageNow){
	document.getElementById('np').value=pageNow;
	document.getElementById('form1').submit();
}
function delMyBlog(){
	var ques_id = [];
	$("[name=quanxuani]:checked").each(function(){
	ques_id.push($(this).val());
	}); 
	if(ques_id!='' && ques_id!=null){
		if(confirm("你确定删除吗？")){
			$.post('del/batch',{'id':ques_id},function(data){
			if (data == "0")
			alert("删除失败！");
			else {
			alert("删除成功！");
			window.location.reload();
			}
			});
		}
	}else{
	alert("请选择要删除的项目！");
	}	
	}
</script>
</html>