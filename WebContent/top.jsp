<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html> 
<head>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="css/top.css" ></link>
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script>
		setInterval(refresh,1000);
		function refresh(){
			$("#div_date").html(new Date().toString());
		}
		$(function(){
			$("#log_out").click(function(){
				if(confirm("确定要退出吗？")){
					window.parent.location="AdminServlet.do?flag=logOut";
				}
				
			})
			
		});
		
	</script>
</head>
<body>
<table id="t_head">
  		<tr>
  			<td id="td1" ></td>
  			<td id="td2">&nbsp;</td>
  			<td id="td3">
  				<a id="td3_a1"  target="centerFrame" href="admin/password_edit.jsp"><img src="images/btn_head_bg1.jpg"/>修改密码</a>
  				<a  target="centerFrame" href="admin/admin_info.jsp"><img src="images/btn_head_bg1.jpg"/>用户信息</a>
  				<a id="log_out" href="javascript:void(0)"><img src="images/btn_head_bg1.jpg"/>退出系统</a>
  			</td>

  		</tr>
  </table>
<table id="t_bar" >
		<tr>
				<td id="bar_td1"></td>
				<td id="bar_td2">
					<div id="div_date"></div>
				</td>
				
			<td id="bar_td3">
				 商城后台管理系统</td>
		
		</tr>
</table>
<table id="t_title">
		<tr>
				<td id="title_td1">
						<img src="images/main_28.gif"/>
				</td>	
				<td id="title_td2"><img src="images/main_29.gif" /></td>	
				<td id="title_td3"><img src="images/main_30.gif" /></td>	
				<td id="title_td4">&nbsp;
						 <label class="admininfo">当前登录用户: ${now_admin.adminName  }
					</label>
				</td>	
				<td id="title_td5"><img src="images/main_32.gif" /></td>
		</tr>
</table>
</body>
</html>