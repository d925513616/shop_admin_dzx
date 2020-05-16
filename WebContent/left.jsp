<%@ page language="java" import="com.beans.*,com.dao.MenuDao,java.util.List" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<base href="<%=basePath%>">
	<title>Insert title here</title>
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="js/jquery.easing.js"></script>
	<script type="text/javascript" src="js/jquery.accordion.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#navigation').accordion({
				active:1,   /* 第三个被激活 */
				header: '.head',
				/*navigation1: false,  */
				event: 'click',
				fillSpace: true,
				animated: 'bounceslide'   /*slide,bounceslide ,bounceslide,easeslide'*/
			});
		});
	</script>
	<link rel="stylesheet" type="text/css" href="css/menu.css">
	
</head>
<body>
<%
	MenuDao mdao = new MenuDao();
	AdminInfo admininfo = (AdminInfo)request.getSession().getAttribute("now_admin");
	List<MenuInfo> mlist = mdao.getMenuList(0, admininfo.getRoleId());
	request.setAttribute("mlist",mlist);
	
%>
<ul id="navigation">
	<c:forEach var="menu" items="${mlist }">
		<li>
			<a class="head">${menu.menuName }</a>
			<ul>
				<c:forEach var="subMenu" items="${menu.subMenuList }">
					<li>
						<a href="${subMenu.url }" target="${subMenu.target }"><img src="images/${subMenu.icon }" /><label>${subMenu.menuName }</label></a>
					</li>
				</c:forEach>
			</ul>
		</li>
	</c:forEach>
    <li> <a class="head">网站前台</a>
      <ul>
        <li><a href="http://localhost:8080/shop-web/index.jsp" target="_blank"><img src="images/main_50.gif" /><label>网站前台</label></a></li>
      </ul>
    </li>
  </ul>

</body>
</html>