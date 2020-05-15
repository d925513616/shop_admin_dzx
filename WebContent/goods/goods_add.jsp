<%@ page language="java" import="com.beans.CateInfo,com.dao.CateDao,java.util.List" contentType="text/html; charset=UTF-8"
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
<title>Insert title here</title>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="css/edittable.css"  ></link>  
<link rel="stylesheet" type="text/css" href="css/validate.css"  ></link>  
<script type="text/javascript"  src="js/jquery-1.8.0.js"></script>
<script>
$(function(){
	
	if($("#bigId").val()!=-1){
		var pid=$("#bigId").val();
		$("#smallId").empty();
		 $.ajax({
				url:"CateServlet.do",
				type:"post",
				dataType:'json',
				data:{pid:pid,flag:"selectSmallCate"},
				success:function(data){
					$.each(data,function(index,obj){
						var str = null;
						var id = obj.id;
						var cateName = obj.cateName;
						if(id == "${param.smallId}"){
							str="<option value="+id+" selected>"+cateName+"</option>"
						}
						else{
							str="<option value="+id+">"+cateName+"</option>"
						}
						$("#smallId").append(str);
					});
				 },
			});
	}
	var b=[false,false,false,false];
	 $("input[type=text],textarea").focus(function(){
		  $(this).addClass("input_focus");
		  
		}).blur(function(){
				$(this).removeClass("input_focus");
				
		});
	 $("#goodName").focus(function(){
			showInfo($(this).attr("id"),"2-20位；非空白字符");
		}).blur(function(){
			$.ajax({
				url:"GoodsServlet.do",
				type:"post",
				data:{goodName:$("#goodName").val(),flag:"cnki"},
				success:function(data){
					if(data=="√") {
						b[0] = true;
						showOk($("#goodName").attr("id"));
					}
					else{
						b[0] = false;
						showError($("#goodName").attr("id"),data);
					}
				 }
			});
		});
	 $("#bigId").change(function(){
		 $("#smallId").empty();
		 var pid=this.value;
		 if(pid==-1){
			showInfo($(this).attr("id"),"请选择分类");
			$("#smallId").append("<option>请选择</option>");
			b[1] = false;
		 }
		 else{
			 $.ajax({
					url:"CateServlet.do",
					type:"post",
					dataType:'json',
					data:{pid:pid,flag:"selectSmallCate"},
					success:function(data){
						$.each(data,function(index,obj){
							var id = obj.id;
							var cateName = obj.cateName;
							var str="<option value="+id+">"+cateName+"</option>"
							$("#smallId").append(str);
						});
					 },
				});
			 showOk($("#bigId").attr("id"));
			 b[1] = true;
		 }
	 });
	 $("#unit").focus(function(){
			showInfo($(this).attr("id"),"1-10位非空字符");
		}).blur(function(){
			var reg = /^\S{1,10}$/
			if(reg.test($("#unit").val())){
				b[2] = true
				showOk($("#unit").attr("id"));
			}
			else{
				b[2] = false
				showError($("#unit").attr("id"),"计量单位格式非法");
			}
		});
	 $("#price").focus(function(){
			showInfo($(this).attr("id"),"不能为空，以元为单位，可以是小数");
		}).blur(function(){
			var reg = /^\d+(\.\d+)?$/
			if(reg.test($("#price").val())){
				showOk($("#price").attr("id"));
				b[3] = true
			}
			else if($("#price").val()<0){
				b[3] = false
				showError($("#price").attr("id"),"商品价格不能小于零");
			}
			else{
				b[3] = false
				showError($("#price").attr("id"),"商品价格格式非法");
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
	<script type="text/javascript">
		  function setImagePreview(docObj, localImagId, imgObjPreview, width, height) {
	if (docObj.files && docObj.files[0]) { //火狐下，直接设img属性        
		imgObjPreview.style.display = 'block';
		imgObjPreview.style.width = width;
		imgObjPreview.style.height = height;
		//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式        
		imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
	} else { //IE下，使用滤镜      
		docObj.select();
		var imgSrc = document.selection.createRange().text;
		//必须设置初始大小        
		localImagId.style.width = width;
		localImagId.style.height = height;
		//图片异常的捕捉，防止用户修改后缀来伪造图片        
		try {
			localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
		} catch (e) {
			alert("您上传的图片格式不正确，请重新选择!");
			return false;
		}
		imgObjPreview.style.display = 'none';
		document.selection.empty();
	}
}

</script>

</head>
<body>
<%
	CateDao catedao = new CateDao();
	List<CateInfo> list = catedao.getCateInfoByParentId(0);
	
	request.setAttribute("list", list);
%>
<div class ="div_title">
	 <div class="div_titlename"> <img src="images/san_jiao.gif" ><span>商品管理 添加商品</span></div>
 </div>
 <form action="GoodsServlet.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="flag" value="goodsAdd">
	<table class="edit_table" >
	<tr>
	 	<td class="td_info">商品名称:</td>	
	 	<td class="td_input_short"> 
	 		<input type="text" class="txtbox" id="goodName" name="goodName" value="${param.goodName }"/> 
	 	</td>   
	 	<td>
	 		<label class="validate_info" id="goodName_msg"></label>
	 	</td> 
	</tr>
	 <tr>
	 	<td class="td_info">所属分类:</td>	
	 	<td class="td_input_short"> 
	 		<select name="bigId" id="bigId">
	 			<option value="-1">请选择</option>
	 			<c:forEach var="l" items="${list }">
	 				<c:choose>
	 				<c:when test="${l.id==param.bigId }">
						<option value="${l.id }" selected>${l.cateName }</option>
					</c:when>
					<c:otherwise>
						<option value="${l.id }">${l.cateName }</option>
					</c:otherwise>
					</c:choose>
	 			</c:forEach>
	 		</select>
	 		<select name="smallId" id="smallId">
						<option value="-1">请选择</option>
	 		</select>
	 	</td>   
	 	<td>
	 		<label class="validate_info" id="bigId_msg"></label>
	 	</td> 
	</tr>
	<tr>
	 	<td class="td_info">计量单位:</td>	
	 	<td class="td_input_short"> 
	 		<input type="text" class="txtbox" id="unit" name="unit" value="${param.unit }"/> 
	 	</td>   
	 	<td>
	 		<label class="validate_info" id="unit_msg"></label>
	 	</td> 
	</tr>
	<tr>
	 	<td class="td_info">商品价格:</td>	
	 	<td class="td_input_short"> 
	 		<input type="text" class="txtbox" id="price" name="price" value="${param.price }"/> 
	 	</td>   
	 	<td>
	 		<label class="validate_info" id="price_msg"></label>
	 	</td> 
	</tr>
	<tr>
	 	<td class="td_info">生产厂商:</td>	
	 	<td class="td_input_short"> 
	 		<input type="text" class="txtbox" id="producter" name="producter" value="${param.producter }"/> 
	 	</td>   
	 	<td>
	 		<label class="validate_info" id="producter_msg"></label>
	 	</td> 
	</tr>
	<tr>
	 	<td class="td_info">商品图片:</td>	
	 	<td class="td_input_short"> 
	 		<label style="display:none">
	 			<input type="file" name="picture" class="file" id="picture" size="28" 
				onchange="setImagePreview(this,localImag,preview,'100px','125px');" /> 
			 </label>
	 		<button type="button" onclick="picture.click()" style="background:white;width:80px;">找找...</button>
	 		
	 		<div id="localImag" style="margin-left: 24px" ></div>
			<img width="100px" height="125px" id="preview" alt="请上传图片"  src="goods/goods_picture.jsp?goodsId=${goods.id}"  />
	 	</td>   
	 	<td>
	 		<label class="validate_info" id="money_msg"></label>
	 	</td> 
	</tr>
	<tr>
		<td class="td_info">分类描述:</td>	
		<td><textarea rows="4" style="resize: none;" cols="27" name="des" class="txtarea" >${param.des }</textarea> </td>	
		<td><label></label></td>	
			
	</tr>
		
<tr>
	<td class="td_info">${msg } </td>	
		<td> 
		<input class="form_btn" id="submit" type="submit" value="提交" /> 
		<input type="reset"  class="form_btn" value="重置" /> </td>	
		<td>
			<label id="result_msg" class="result_msg"></label>
		</td>	
	</tr>
	</table>
</form>
</body>
</html>