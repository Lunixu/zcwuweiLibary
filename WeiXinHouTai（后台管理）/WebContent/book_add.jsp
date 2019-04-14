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
	<title>图书管理-图书添加</title>
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
	<form action="bookAdd_servlet" class="form-horizontal" method="post">
		<h5 class="page-header alert-info" style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
		<!-- 开始1 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书ISBN</label>
					<div class="col-xs-9 ">
						<input type="text" name="ISBN" class="form-control" placeholder="请输入图书ISBN" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">库存总量</label>
					<div class="col-xs-9 ">
						<input type="text" name="num" class="form-control" placeholder="请输入库存总量" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">剩余库存</label>
					<div class="col-xs-9 ">
						<input type="text" name="leftnum" class="form-control" placeholder="请输入剩余库存" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书书名</label>
					<div class="col-xs-9 ">
						<input type="text" name="title" class="form-control" placeholder="请输入图书书名" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group">
					<label class="col-xs-3 control-label">书名全拼</label>
					<div class="col-xs-9 "> 
						<input type="text" name="pingying" class="form-control" placeholder="请输入书名全拼" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group">
					<label class="col-xs-3 control-label">首字母</label>
					<div class="col-xs-9 "> 
						<input type="text" name="firstChar" class="form-control" placeholder="请输入首字母" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书作者</label>
					<div class="col-xs-9 ">
						<input type="text" name="author" class="form-control" placeholder="请输入图书作者" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">出版社</label>
					<div class="col-xs-9 ">
						<input type="text" name="publish" class="form-control" placeholder="请输入出版社" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书版本</label>
					<div class="col-xs-9 ">
						<input type="text" name="version" class="form-control" placeholder="请输入图书版本" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书封面</label>
					<div class="col-xs-9 ">
						<input type="text" name="face" class="form-control" placeholder="请输入图书封面" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group">
					<label class="col-xs-3 control-label">图书序</label>
					<div class="col-xs-9 "> 
						<input type="text" name="preface" class="form-control" placeholder="请输入图书序" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书目录</label>
					<div class="col-xs-9 ">
						<input type="text" name="directory" class="form-control" placeholder="请输入图书目录" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">内容简介</label>
					<div class="col-xs-9 ">
						<input type="text" name="content" class="form-control" placeholder="请输入内容简介" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书书评</label>
					<div class="col-xs-9 ">
						<input type="text" name="comment" class="form-control" placeholder="请输入图书书评" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书导读</label>
					<div class="col-xs-9 ">
						<input type="text" name="introduction" class="form-control" placeholder="请输入图书导读" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group">
					<label class="col-xs-3 control-label">图书种类</label>
					<div class="col-xs-9 "> 
						<input type="text" name="category" class="form-control" placeholder="请输入图是种类" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group">
					<label class="col-xs-3 control-label">图书馆编号</label>
					<div class="col-xs-9 "> 
						<input type="text" name="libaryId" class="form-control" placeholder="请输入图书馆编号" />
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