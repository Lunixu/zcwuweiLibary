<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%> 
<%
	//获取绝对路径路径 
	String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%> 
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>用户管理-用户查询</title>
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="css/layui.css" media="all" />
	<link rel="stylesheet" href="css/global.css" media="all">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/table.css" />
</head>
<body>
	<sql:setDataSource var="snapshot" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" url="jdbc:sqlserver://115.159.201.120:1433; DatabaseName=WeiXIn" user="sa" password="@@siqian2017"/>
	<sql:query dataSource="${snapshot}" var="result">
		SELECT * from administrator;
	</sql:query>
	<form action="" class="form-inline">
		<div class="row" style="padding-left: 15px;">
			<table class="site-table table-condensed" style="text-align:center;table-layout: fixed;">
			<thead>
				<tr>
					<td>用户编号</td>
					<td>用户昵称</td>
					<td>用户密码</td>
					<td style="width:10%"></td>
					<td style="width:10%"></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${result.rows}">
				<tr>  
 					<td><c:out value="${row.adminId}"/></td> 
  				  	<td><c:out value="${row.adminUsername}"/></td>   
 					<td><c:out value="${row.adminPassword}"/></td>
   					<td style="width:10%"><a href="userUpdate_servlet?adminId=${row.adminId}">修改</a></td>
   					<td style="width:10%"><a href="userDelete_servlet?adminId=${row.adminId}">删除</a></td>
				</tr>
				</c:forEach>
			</tbody>
			</table>
		</div>
	</form>
</body>
</html>