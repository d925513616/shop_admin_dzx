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
<title>Insert title here</title>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="css/edittable.css"  ></link>  
<link rel="stylesheet" type="text/css" href="css/validate.css"  ></link>  
<link rel="stylesheet" type="text/css" href="css/maintable.css" ></link>
<script type="text/javascript"  src="js/jquery-1.8.0.js"></script>
<script> 
$(function(){
	$(".main_table tr").mouseover(function(){
		$(this).css("background","#D3EAEF");
		$(this).siblings().css("background","white");
	});
	
	$("input[type=text],textarea").css("color","red");
});
</script>
</head>
<body>
<div class ="div_title">
<div class="div_titlename"> <img src="images/san_jiao.gif" ><span>订单商品列表</span></div>
 </div>
 <table class="main_table" >
 <tr>
	<th>商品名称</th>
	<th>单位</th>
	<th>单价</th>
	<th>购买数量</th>
</tr>
 	<c:forEach var="goods" items="${orderGoods }">
 		<tr>
 			<td>${goods.goodsName }</td>
 			<td>${goods.unit }</td>
 			<td>${goods.price }</td>
 			<td>${goods.saleCount }</td>
 		</tr>
 	</c:forEach>
 </table>

<div class ="div_title">
<div class="div_titlename"> <img src="images/san_jiao.gif" ><span>订单详情列表</span></div>
 </div>
<table class="edit_table" >
	<tr>
	 	<td class="td_info">订单号:</td>	
	 	<td class="td_input_short"> 
	 		<input type="text" class="txtbox" readonly value="${order.orderNo }"/> 
	 	</td>   
	 	<td class="td_info">邮费:</td>	
	 	<td class="td_input_short"> 
	 		<input type="text" class="txtbox" readonly value="${order.postage }"/> 
	 	</td>
	</tr>
	<tr>
	 	<td class="td_info">付款方式:</td>	
	 	<td class="td_input_short"> 
	 		<input type="text" class="txtbox" readonly value="${order.payMethod }"/> 
	 	</td>   
	 	<td class="td_info">邮递方式:</td>	
	 	<td class="td_input_short"> 
	 		<input type="text" class="txtbox" readonly value="${order.postMethod }"/> 
	 	</td>
	</tr>
	<tr>
	 	<td class="td_info">下单日期:</td>	
	 	<td class="td_input_short"> 
	 		<input type="text" class="txtbox" readonly value="${order.orderDate }"/> 
	 	</td>   
	 	<td class="td_info">邮寄地址:</td>	
	 	<td class="td_input_short"> 
	 		<input type="text" class="txtbox" readonly value="${order.address }"/> 
	 	</td>
	</tr>
	<tr>
	 	<td class="td_info">订单状态:</td>	
	 	<td class="td_input_short"> 
	 		<input type="text" class="txtbox" readonly value="${order.orderState }"/> 
	 	</td>   
	 	<td class="td_info">发货日期:</td>	
	 	<td class="td_input_short"> 
	 		<input type="text" class="txtbox" readonly value="${order.editDate }"/> 
	 	</td>
	</tr>
	<tr>
	 	<td class="td_info">订单金额:</td>	
	 	<td class="td_input_short" id="ip"> 
	 		<input type="text" class="txtbox" readonly value="${order.amount }"/> 
	 	</td>   
	 	<td class="td_info">会员ID:</td>	
	 	<td class="td_input_short"> 
	 		<c:choose>
	 			<c:when test="${order.memberName==null }">
	 				<input type="text" class="txtbox" readonly value="临时用户非会员"/> 
	 			</c:when>
	 			<c:otherwise>
	 				<input type="text" class="txtbox" readonly value="${order.memberName }"/> 
	 			</c:otherwise>
	 		</c:choose>
	 		
	 	</td>
	</tr>
	<tr>
	<td></td>
		<td><button class="form_btn" onclick="javascript:history.go(-1)">返回</button></td>
	</tr>
</table>
</body>
</html>