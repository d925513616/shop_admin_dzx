<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="<%=basePath%>">
<title>用户添加</title>
<link rel="stylesheet" type="text/css" href="css/edittable.css"  ></link>  
<link rel="stylesheet" type="text/css" href="css/validate.css"  ></link>  
<script type="text/javascript"  src="js/jquery-1.8.0.js"></script>

	 <script>		
			$(function(){
				var b = [false,false,false];
				 $("input[type=text],textarea").focus(function(){
					  $(this).addClass("input_focus");
					  
					}).blur(function(){
							$(this).removeClass("input_focus");
							
					});
				
				$(".form_btn").hover(function(){
						$(this).css("color","red").css("background","#6FB2DB");
					},
					
					function(){
						$(this).css("color","#295568").css("background","#BAD9E3");
					});	
				$("#adminName").focus(function(){
					showInfo($(this).attr("id"),"4-15位；只限数字(0-9)和英文(a-z),不区分大小写");
				}).blur(function(){
					$.ajax({
						url:"AdminServlet.do",
						type:"post",
						data:{adminName:$("#adminName").val(),flag:"cnki"},
						success:function(data){
							
							if(data=="√") {
								b[0] = true;
								
								showOk($("#adminName").attr("id"));
								
							}
							else{
								b[0]=false;
								showError($("#adminName").attr("id"),data);
								
							}
						 }
						
					});
				});
				
				$("#password").focus(function(){
					showInfo($(this).attr("id"),"数字或英文,6-20位");
				}).blur(function(){
					
		 	   		var reg=/^\w{6,20}$/; 
		 	   		if(!reg.test($(this).val())){
		 	   				b[1]=false;
		 	   				showError($(this).attr("id"),"密码格式非法! ");
		 	   		} 
		 	   		else{
		 	   				showOk($(this).attr("id"));
		 	   				b[1]=true;
		 	   		}
				});
				
				$("#pwdconfirm").focus(function(){
					showInfo($(this).attr("id"),"请重复输入密码");
				}).blur(function(){
					
		 	   		if($(this).val()!=$("#password").val()){
		 	   				b[2]=false;
		 	   				showError($(this).attr("id"),"密码宇上次不一致! ");
		 	   		} 
		 	   		else{
		 	   				showOk($(this).attr("id"));
		 	   				b[2] = true;
		 	   		}
				});
				$("#submit").click(function(){
					for(var i = 0; i < b.length; i++){
						
						if(!b[i]){
							alert("存在错误，请检查");
							return false;
						}
					}
					return confirm("验证通过,是否提交");
				});
				
					
						
						
			});		
			 function showInfo(item,msg){
		 	   		$("#"+item+"_msg").html(msg);
		 	   		$("#"+item+"_msg").css("color","#8fa0ac")
		 	   }
		 	   
		 	   function showError(item,msg){
		 	   	  $("#"+item+"_msg").html(msg);
		 	   	  $("#"+item+"_msg").css("color","red");
		 	   }
		 	   
		 	   function showOk(item){
		 	   		$("#"+item+"_msg").html("√");
		 	   		
		 	   }
		 	 
	</script>

</head>

  <body>
     <div class ="div_title">
		 <div class="div_titlename"> <img src="images/san_jiao.gif" ><span>管理员添加</span></div>
	 </div>
				 
	 <form action="AdminServlet.do" method="post">
	 	<input type="hidden" name="flag" value="add">
		 <table class="edit_table" >
		 		<tr>
		 			 	<td class="td_info">用户账号:</td>	
		 			 	<td class="td_input_short"> 
		 			 		<input type="text" class="txtbox" id="adminName" name="adminName" value="${param.adminName }"/> 
		 			 	</td>   
		 			 	<td>
		 			 		<label class="validate_info" id="adminName_msg"></label>
		 			 	</td> 
		 		</tr>
		 			<tr>
		 				<td class="td_info">用户密码:</td>	
		 				<td>
		 					<input type="password"  class="txtbox"  name="password"  id="password" value="${param.password }"/>
		 				</td> 
		 				<td><label  class="validate_info" id="password_msg" ></label></td>	
		 		</tr>
		 			<tr>
		 				<td class="td_info">重复密码:</td>	
		 				<td><input type="password" class="txtbox" id="pwdconfirm" value="${param.password }" /> 
		 				</td>
		 				<td><label  class="validate_info"  id="pwdconfirm_msg"></label></td>	
		 		</tr>
		 		<tr>
		 			<td class="td_info">备注信息:</td>	
		 			<td><textarea rows="4" cols="27" name="note" class="txtarea" >${param.note }</textarea> </td>	
		 			<td><label></label></td>	
		 		</tr>
		 		<tr>
		 			<td class="td_info">${msg } </td>	
		 			<td> 
		 			<input class="form_btn" id="submit"type="submit" value="提交" /> 
		 			<input type="reset"  class="form_btn" value="重置" /> </td>	
		 			<td>
		 				<label id="result_msg" class="result_msg"></label>
		 			</td>	
		 		</tr>
		</table>
     </form>
  </body>
</html>