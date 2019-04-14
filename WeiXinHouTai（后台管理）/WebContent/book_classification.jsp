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
	<title>图书管理-图书查询</title>
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="css/layui.css" media="all" />
	<link rel="stylesheet" href="css/global.css" media="all">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/table.css" />
	<style>
		td {white-space:nowrap;overflow:hidden;text-overflow: ellipsis;}
	</style>
</head>
<body>
	<sql:setDataSource var="snapshot" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" url="jdbc:sqlserver://115.159.201.120:1433; DatabaseName=WeiXIn" user="sa" password="@@siqian2017"/>
	<sql:query dataSource="${snapshot}" var="result">
		SELECT * from Book group by category;
	</sql:query>
	<form action="" class="form-inline">
		<div class="row" style="padding-left: 15px;">
			<table class="site-table table-condensed" style="text-align:center;table-layout: fixed;">
			<thead>
				<tr>
					<td>图书条形码</td>
					<td>书名</td>
					<td>书名全拼</td>
					<td>作者</td>
					<td>首字母</td>
					<td>库存余量</td>
					<td>剩余库存</td>
					<td>出版社</td>
					<td>版本</td>
					<td>封面图片</td>
					<td>序(前言)</td>
					<td>目录</td>
					<td>内容简介</td>
					<td>书评</td>
					<td>导读</td>
					<td>种类</td>
					<td>图书馆编号</td>
					<td></td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${result.rows}">
				<tr>  
  				  	<td><c:out value="${row.ISBN}"/></td>   
 					<td><c:out value="${row.title}"/></td>
 					<td><c:out value="${row.pingying}"/></td>
   					<td><c:out value="${row.author}"/></td>
   					<td><c:out value="${row.firstChar}"/></td>
   					<td><c:out value="${row.num}"/></td>
   					<td><c:out value="${row.leftnum}"/></td>
   					<td><c:out value="${row.publish}"/></td>
   					<td><c:out value="${row.version}"/></td>
   					<td><c:out value="${row.face}"/></td>
   					<td><c:out value="${row.preface}"/></td>
   					<td><c:out value="${row.directory}"/></td>
   					<td><c:out value="${row.content}"/></td>
   					<td><c:out value="${row.comment}"/></td>
   					<td><c:out value="${row.introduction}"/></td>
   					<td><c:out value="${row.category}"/></td>
   					<td><c:out value="${row.libaryId}"/></td>
   					<td><a href="bookUpdate_servlet?bookId=${row.bookId}">修改</a></td>
   					<td><a href="bookDelete_servlet?bookId=${row.bookId}">删除</a></td>
				</tr>
				</c:forEach>
			</tbody>
			</table>
		</div>
	</form>
</body>
</html>