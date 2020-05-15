<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<title>登录</title>
	<link rel="stylesheet" type="text/css" href="css/login.css" ></link>
	<meta charset="UTF-8">
	<base href="<%=basePath%>">
	<title>Insert title here</title>
</head>
<body>

<div id="div_center">
	<form action="LoginServlet" method="post">
	<div id="div_inputbox">
		<input type="text" id="adminName" name="adminName"  />
		<input type="password" id="password" name="password"/>
	</div>
	<input id="btn_img" type="image" src="images/bg_login_btn.jpg" />
	<div id="msg">${msg }</div>
	</form>
</div>
</body>
</html>