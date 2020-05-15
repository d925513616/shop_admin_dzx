<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/edittable.css"  ></link>  
<link rel="stylesheet" type="text/css" href="../css/validate.css"  ></link>  
<script type="text/javascript"  src="../js/jquery-1.8.0.js"></script>
<script>
$(function(){
	 $("input[type=text],textarea").css("color","red");
});
</script>
</head>
<body>
 <table class="edit_table" >
		 		<tr>
		 			 	<td class="td_info">用户账号:</td>	
		 			 	<td class="td_input_short"> 
		 			 		<input type="text" class="txtbox" id="adminName" name="adminName" value="${now_admin.adminName }"/> 
		 			 	</td>   
		 			 	<td>
		 			 		<label class="validate_info" id="adminName_msg"></label>
		 			 	</td> 
		 		</tr>
		 			<tr>
		 				<td class="td_info">用户密码:</td>	
		 				<td>
		 					<input type="text"  class="txtbox"  name="password"  id="password" value="此处不显示密码信息"/>
		 				</td> 
		 				<td><label  class="validate_info" id="password_msg" ></label></td>	
		 		</tr>
		 		<tr>
		 			<td class="td_info">备注信息:</td>	
		 			<td><textarea rows="4" cols="27" name="note" class="txtarea" >${now_admin.note }</textarea> </td>	
		 			<td><label></label></td>	
		 		</tr>
		 		<tr>
		 			<td class="td_info">最后更新日期 </td>	
		 			<td> 
		 				<input type="text" class="txtbox" value="${now_admin.editDate }"/> 
		 			<td>
		 				<label></label>
		 			</td>	
		 		</tr>
		</table>
</body>
</html>