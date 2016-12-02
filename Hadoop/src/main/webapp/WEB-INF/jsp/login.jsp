<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String context = request.getContextPath();
	request.setAttribute("context",context);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>
	<form action="${context}/login.do">
		<input type="text" name="userName" value="" placeholder="请输入姓名~">
		<input type="password" name="password" value="" placeholder="请输入密码~">
		<input type="submit" value="点我登录">
	</form>
</body>
</html>