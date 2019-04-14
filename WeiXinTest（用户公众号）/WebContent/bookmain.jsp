<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>${libaryname}首页</title>
	<link href="css/bootstrap.css" rel="stylesheet" media="screen">
	<link href="skins/eden.css" rel="stylesheet" media="screen">
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="css/animate.css" rel="stylesheet">
	<link href="css/index.css" rel="stylesheet" type="text/css" />
	<script src="js/jquery-1.10.1.min.js"></script>
	<script src="js/jquery.cookie.js"></script>
	<style>
		.navbar-holder-dark {padding: 20px 20px 200px 20px;background: #333333;}
	</style>
</head>
<body>
	<div>
		<img src="image/logo1.jpg" style="height:150px;width:100%">
	</div>
	<div><img src="image/2.jpg" style="width: 100%;height: 15px;"></div>
	<div class="container">
		<div style="position:fixed;bottom:0px;z-index:10;right:0">
			<img src="image/search.png" style="height:50px;width:50px" onclick='window.open("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2Fsearch.jsp&response_type=code&scope=snsapi_base&state=123#wechat_redirect")'>
		</div>
		<div>
			<p style="height: 10px; font-size:18px;font-weight:bold">&nbsp;&nbsp;数学<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FMoreBook%3Fcategoryid%3D1&response_type=code&scope=snsapi_base&state=123#wechat_redirect" style="float:right;height: 10px;font-size:12px"">更多&gt;&gt;&gt;</a></p>
        </div>

        <div class="box" style="margin-top: 20px;">
            <div class="box-1">
                <table>
        			<tr>
        				<c:forEach begin="0" end="2" var="book" items="${book_math}">
							<td style="text-align: center;padding-right: 10px;">
								<img src="${book.face}" style="width: 100%;height: 120px;">
								<a style="font-size:15px" href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FBookSearch%3Fbookid%3D${book.bookId}&response_type=code&scope=snsapi_base&state=123#wechat_redirect">${book.title }</a>
							</td>
						</c:forEach>
					</tr>
					<c:forEach begin="3" end="6" var="book" items="${book_math}">
						<td>
							<tr>
	                        	<td><a style="font-size:15px" class="box-text1" href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FBookSearch%3Fbookid%3D${book.bookId}&response_type=code&scope=snsapi_base&state=123#wechat_redirect">${book.title }</a></td>
	                        	<td></td>
	                        	<td class="box-time" style="text-align: right;font-size:15px">${book.author }</td>
	                   		</tr>
						</td>
					</c:forEach>
        		</table>
             </div>
        </div>
        <hr/>
        <div >
			<p style="height: 10px;font-size:18px;font-weight:bold">&nbsp;&nbsp;经济<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FMoreBook%3Fcategoryid%3D2&response_type=code&scope=snsapi_base&state=123#wechat_redirect" style="float:right;height: 10px;font-size:12px">更多&gt;&gt;&gt;</a></p>
        </div>
        <div class="box" style="margin-top: 20px;">
            <div class="box-1">
                <table>
        			<tr>
        				<c:forEach begin="0" end="2" var="book1" items="${book_emconomy}">
							<td style="text-align: center;padding-right: 10px;">
								<img src="${book1.face}" style="width: 100%;height: 120px;">
								<a style="font-size:15px" href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FBookSearch%3Fbookid%3D${book1.bookId}&response_type=code&scope=snsapi_base&state=123#wechat_redirect">${book1.title }</a>
							</td>
						</c:forEach>    
						<c:forEach begin="3" end="6" var="book1" items="${book_emconomy}">
							<td>
								<tr>
	                        		<td><a style="font-size:15px" class="box-text1" href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FBookSearch%3Fbookid%3D${book1.bookId}&response_type=code&scope=snsapi_base&state=123#wechat_redirect">${book1.title }</a></td>
	                        		<td></td>
	                        		<td class="box-time" style="text-align: right;font-size:15px">${book1.author }</td>
	                   			</tr>
							</td>
						</c:forEach>
					</tr>
        		</table>
             </div>
        </div>
        <hr>
	</div>
<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>