<%@ page language="java" import="com.dao.GoodsDao,com.beans.GoodsInfo" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  	<%
  		if(request.getParameter("goodsId")!=null){
  			int goodsId= Integer.parseInt(request.getParameter("goodsId"));
  	  		GoodsDao dao=new GoodsDao();
  	  		GoodsInfo goods=  dao.getGoodsById(goodsId);
  	  		response.setContentType("image/jpg");
  	  	
  	  		ServletOutputStream outstream =response.getOutputStream();	
  	  		outstream.write(goods.getPictureData());
  	  		out.clear();
  	  		out=pageContext.pushBody();
  		}
  		
  	 %>

</body>
</html>