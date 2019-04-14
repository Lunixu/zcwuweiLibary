<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无微不至管理系统</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/index_menu.js"></script>
</head>

<body>
	<div class="top"></div>
	<div id="header">
		<div class="logo">无微不至后台管理系统</div>
		<div class="navigation">
			<ul>
				<li>欢迎您！</li>
				<li>
					<c:forEach var="result" items="${users}">
						<a href="#">${result.adminUsername}</a>
					</c:forEach>
				</li>
				<li><a href="#">修改密码</a></li>
				<li><a href="#">设置</a></li>
				<li><a href="login.jsp">退出</a></li>
			</ul>
		</div>
	</div>
	<div id="content">
		<div class="left_menu" style="margin-right:10px">
			<ul id="nav_dot">
				<li>
					<h4 class="M2"><span></span>图书处理</h4>
					<div class="list-item none" style="text-align:center">
						<a href='book_list.jsp' target="content">图书查询</a>
						<a href='book_classification.jsp' target="content">图书分类</a> 
						<a href='book_add.jsp' target="content">图书添加</a> 
					</div>
				</li>
				<li>
					<h4 class="M2"><span></span>管理员管理</h4>
					<div class="list-item none" style="text-align:center">
						<a href='user_list.jsp' target="content">管理员查询</a> 
					</div>
				</li>
				<li>
					<h4 class="M2"><span></span>用户管理</h4>
					<div class="list-item none" style="text-align:center">
						<a href='#' target="content">用户查询</a> 
					</div>
				</li>
			</ul>
		</div>
		<div class="m-right">
		<div class="main">
			<div class="public-ifame-content">
				<iframe name="content" id="mainframe" width="100%" height="700px" style="margin-left:8px"></iframe>
			</div>
		</div>
	</div>
	</div>
	<div class="bottom"></div>
	<div id="footer">
		<p><a href="#" target="_blank">zcwuweiLibary</a>
		</p>
	</div>
	<script>
		navList(12);
	</script>
</body>
</html>
</html>