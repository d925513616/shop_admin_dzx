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
<link rel="stylesheet" type="text/css" href="css/maintable.css" ></link>
<script type="text/javascript" src="js/jquery-1.8.0.js"></script>

<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script>
$(function(){ 
	if("${msg}"!=''){
		alert("${msg}");
	}
	$("#findOrderState").val("${param.findOrderState}");
	$("#ch_checkall,#top_ch_checkall").click(function(){
		if(this.checked){
			$("input[name=ck_id]").attr("checked","checked");
		}else{
			$("input[name=ck_id]").removeAttr("checked");
		}		
	});
			
	$("table tr").mouseover(function(){
		$(this).css("background","#D3EAEF");
		$(this).siblings().css("background","white");
	});
	$("#find").click(function(){
		document.form1.submit();
	});
});
function ZhuanDao(val){
	reg = /^[0-9]+$/
	if(val>${page.pageCount }||val<1){
		alert("意外的页数！");
		return false;
	} 
	else if(!reg.test(val)){
		alert("必须输入数字！");
		return false;
	}
	else{
		$("#p").val(val);
		$("form").submit();
	}
} 

</script>

</head>
<body>
<div class ="div_title">
<div class="div_titlename"> <img src="images/san_jiao.gif" ><span>订单列表</span></div>
<div class="div_titleoper">
	<input type="checkbox" id="top_ch_checkall"/> 全选 <a href="goods/goods_add.jsp"> <img src="images/add.gif"/>添加 </a> <a href="javascript:void(0)"  onclick="delMore()"><img src="images/del.gif"/>删除</a> </div>
 </div>

 <form action="OrderServlet.do"  name="form1">
  <div style="padding:5px 25px;margin:5px;background-color:#d3eaef">
 
订单号:<input type="text" class="txtbox" id="findOrderNo" name="findOrderNo" value="${param.findOrderNo }"/> 
下单日期从:<input type="text" class="txtbox" id="startTime" onclick="WdatePicker()" name="startTime" value="${param.startTime}"/> 
到:<input type="text" class="txtbox" id="endTime" onclick="WdatePicker()" name="endTime" value="${param.endTime }"/> 
订单状态
<select name="findOrderState" id="findOrderState">
	<option value="-1">全部</option>
	<option value="未付款">未付款订单</option>
	<option value="已支付">已支付订单</option>
	<option value="已发货">已发货订单</option>
</select>
<button id="find">查询</button>
 </div>
 <input type="hidden" id="flag" name="flag" value="manage">
<input type="hidden" id="p" name="pageIndex" value=1 />
 <table class="main_table" >
       <tr>
 				<th><input type="checkbox" id="ch_checkall" /></th>	<th>订单号</th> 	<th>付款方式</th>	<th>订单金额</th>  <th>订单状态</th> <th>邮寄方式</th> <th>生成日期</th> <th>发货地址</th> <th>操作</th>
 		</tr>
	 <c:forEach var="order" items="${list }">
		<tr>
			<td><input type="checkbox" name="ck_id" value="${order.id }"></td>
			<td>${order.orderNo }</td>
			<td>${order.payMethod }</td>
			<td>${order.amount }</td>
			<td>${order.orderState }</td>
			<td>${order.postMethod }</td>
			<td>${order.orderDate }</td>
			<td>${order.address }</td>
			<td>
				
				<a href="OrderServlet.do?flag=select&id=${order.id }">查看</a> |  
				<c:choose>
					<c:when test="${order.orderState=='已支付'}">
						<a href="OrderServlet.do?flag=send&id=${order.id }&pageIndex=${page.pageIndex }" >发货</a> 
					</c:when>
				</c:choose>
			    
				<c:choose>
					<c:when test="${order.orderState=='未付款'}">
						<a href="OrderServlet.do?flag=del&id=${order.id }">删除</a>
					</c:when>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
 	
</table>
</form>
<div class="div_page" >
	  <div class="div_page_left">    共${page.rowCount }条数据，当前第${page.pageIndex }页，共${page.pageCount }页</div>		
	  <div class="div_page_right" > 	 
	<c:choose>
		<c:when test="${page.hasBefore }">
			<button onclick="ZhuanDao(1)">首页</button>
			<button onclick="ZhuanDao(${page.pageIndex-1})">上一页</button>
		</c:when>
		<c:otherwise>
			首页&nbsp;&nbsp;
			上一页&nbsp;&nbsp;
		</c:otherwise>
	</c:choose>

<c:choose>
	<c:when test="${page.hasNext }">
		<button onclick="ZhuanDao(${page.pageIndex+1})">下一页</button>
		<button onclick="ZhuanDao(${page.pageCount})">尾页</button>
	</c:when>
	<c:otherwise>
		下一页&nbsp;&nbsp;
		尾页&nbsp;&nbsp;
	</c:otherwise>
</c:choose>
<button onclick="return ZhuanDao( $('#pageIndex').val())"   > 转到</button> 第 <input id="pageIndex" size="2"> 页

	   </div>
		
</div>
</body>
</html>