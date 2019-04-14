<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//获取绝对路径路径 
	String path = request.getContextPath();String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>图书管理-图书修改</title>
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
	<form action="bookUpdate_servlet" class="form-horizontal" method="post">
		<h5 class="page-header alert-info" style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
		<!-- 开始1 -->
		<div class="row">
		<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书Id</label>
					<div class="col-xs-9 ">
						<input type="text" name="bookId" class="form-control" readonly="readonly" value="${book.bookId}"/>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书ISBN</label>
					<div class="col-xs-9 ">
						<input type="text" name="ISBN" class="form-control" value="${book.ISBN}"/>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">库存总量</label>
					<div class="col-xs-9 ">
						<input type="text" name="num" class="form-control" value="${book.num}" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">剩余库存</label>
					<div class="col-xs-9 ">
						<input type="text" name="leftnum" class="form-control" value="${book.leftnum}" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书书名</label>
					<div class="col-xs-9 ">
						<input type="text" name="title" class="form-control" value="${book.title}" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group">
					<label class="col-xs-3 control-label">书名全拼</label>
					<div class="col-xs-9 "> 
						<input type="text" name="pingying" class="form-control" value="${book.pingying}" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group">
					<label class="col-xs-3 control-label">首字母</label>
					<div class="col-xs-9 "> 
						<input type="text" name="firstChar" class="form-control" value="${book.firstChar}" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书作者</label>
					<div class="col-xs-9 ">
						<input type="text" name="author" class="form-control" value="${book.author}" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">出版社</label>
					<div class="col-xs-9 ">
						<input type="text" name="publish" class="form-control" value="${book.publish}" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书版本</label>
					<div class="col-xs-9 ">
						<input type="text" name="version" class="form-control" value="${book.version}" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书封面</label>
					<div class="col-xs-9 ">
						<input type="text" name="face" class="form-control" value="${book.face}" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group">
					<label class="col-xs-3 control-label">图书序</label>
					<div class="col-xs-9 "> 
						<input type="text" name="preface" class="form-control" value="${book.preface}" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书目录</label>
					<div class="col-xs-9 ">
						<input type="text" name="directory" class="form-control" value="${book.directory}" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">内容简介</label>
					<div class="col-xs-9 ">
						<input type="text" name="content" class="form-control" value="${book.content}" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书书评</label>
					<div class="col-xs-9 ">
						<input type="text" name="comment" class="form-control" value="${book.comment}" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书导读</label>
					<div class="col-xs-9 ">
						<input type="text" name="introduction" class="form-control" value="${book.introduction}" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group">
					<label class="col-xs-3 control-label">图书种类</label>
					<div class="col-xs-9 "> 
						<input type="text" name="category" class="form-control" value="${book.category}" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group">
					<label class="col-xs-3 control-label">图书馆编号</label>
					<div class="col-xs-9 "> 
						<input type="text" name="libaryId" class="form-control" value="${book.libaryId}" />
					</div>
				</div>
			</div>
		</div>
		<!--结束1 -->

		<h5 class="page-header alert-info" style="margin: 0px; padding: 10px; margin-bottom: 10px;"></h5>
		<div class="row">
			<div class="col-xs-3 col-xs-offset-5">
			 	<input type="submit" class="btn btn-danger" onclick="getPlainTxt()" value="保存图书" />
			</div>
		</div>
	</form>
</body>
</html>