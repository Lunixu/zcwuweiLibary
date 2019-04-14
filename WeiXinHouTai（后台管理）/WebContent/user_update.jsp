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
	<title>用户管理-用户修改</title>
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
	<form action="userUpdate_servlet" class="form-horizontal" method="post">
		<h5 class="page-header alert-info" style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
		<!-- 开始1 -->
		<div class="row">
		<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户Id</label>
					<div class="col-xs-9 ">
						<input type="text" name="adminId" class="form-control" readonly="readonly" value="${users.adminId}"/>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户昵称</label>
					<div class="col-xs-9 ">
						<input type="text" name="adminUsername" class="form-control" value="${users.adminUsername}"/>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">库用户密码</label>
					<div class="col-xs-9 ">
						<input type="text" name="adminpassword" class="form-control" value="${users.adminpassword}" />
					</div>
				</div>
			</div>
		</div>
		<!--结束1 -->

		<h5 class="page-header alert-info" style="margin: 0px; padding: 10px; margin-bottom: 10px;"></h5>
		<div class="row">
			<div class="col-xs-3 col-xs-offset-5">
			 	<input type="submit" class="btn btn-danger" onclick="getPlainTxt()" value="保存用户" />
			</div>
		</div>
	</form>
</body>
</html>