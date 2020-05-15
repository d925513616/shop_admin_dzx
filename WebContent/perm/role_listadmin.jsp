<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="<%=basePath%>">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/maintable.css" ></link>

<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
<script type="text/javascript">
		$(function(){
			$("table tr").mouseover(function(){
				$(this).css("background","#D3EAEF");
				$(this).siblings().css("background","white");
			});
		}); 
</script>
</head>
<body>
<div class ="div_title">
<div class="div_titlename"> <img src="images/san_jiao.gif" ><span>管理人员基本信息列表</span></div>
</div>
<div>
<table class="main_table" >
<tr>
	<th>账号</th> 	<th>状态</th>	<th>用户角色</th>  <th>最后更新日期</th> 	<th>操作</th>
	<c:forEach var="adminRole" items="${list }">
		<tr>
			<td>${adminRole.adminName }</td>
			<td>
				<c:choose>
					<c:when test="${adminRole.state==2}">已锁定</c:when>
					<c:when test="${adminRole.state==1}"></c:when>	
				</c:choose>
			</td>
			<td>${adminRole.roleName }</td>
			<td>${adminRole.editDate }</td>
			<td>
				<c:if test="${adminRole.state=='2'}">
					
				</c:if>
				<c:if test="${adminRole.state=='1'}">
					<a href="RoleServlet.do?flag=roleSet&id=${adminRole.id }">角色分配</a> 
				</c:if>
			    
			</td>
		</tr>
	</c:forEach>
</tr>
</table>
</div>
</body>
</html>