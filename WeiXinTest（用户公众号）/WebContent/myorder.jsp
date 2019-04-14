<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>已借详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="css/order.css" media="screen">
    <script src="js/jquery-1.10.1.min.js"></script>
</head>
<body>
	<div class="container">
		<input type="text" value="${readerid}" style="display:none" name="readerid" id="readerid"/>
		<div>
			<table class="table-bordered  ta">
				<thead>
					<tr>
						<th>图书编号</th>
						<th>图书名称</th>
						<th>图书封面</th>
						<th>借阅时间</th>
						<th>归还期限</th>
					</tr>
				</thead>
			</table>
			<c:forEach var="order" items="${myorder}"> 
				<input name="id" type="text" value="${order.bookId}" style="display:none">
				<table>
					<tr valign="middle">
						<td style="word-break:break-all;width:20%;text-align:center">${order.ISBN}</td> 
						<td style="word-break:break-all;width:20%;text-align:center">${order.title}</td>
						<td style="word-break:break-all;width:20%;text-align:center"><img alt="" src="${order.face}" style="width: 50px;height:50px"></td>
						<td style="word-break:break-all;width:20%;text-align:center">${order.ordertime}</td>			                        
						<td style="word-break:break-all;width:20%;text-align:center">${order.returntime}</td>
					</tr>
				</table>
			</c:forEach>
			<p style="text-align:center; font-size:16px;font-weight:bold; color:red;">${BindInfo }</p>
			<input type="text" value="${flag}" style="display:none" name="flag" id="flag"/>
		</div>
		<div>
			<hr>
			<input id="submit" type="submit" class="btn" value="确认生成还书二维码"/>
		</div>
	</div>
	<script>
	$.ajaxSetup({
		  async: false
		  });
	$(document).ready(function(){
	
		var flag=$("#flag").val();
		if(flag!="")
		{
			$("#submit").hide();
		}
		
	$("#submit").click(function(){
		    var id="";
		    var readerid=$("#readerid").val();
		    $('input[name="state"]').each(function(i,n){
		    	if($(this).is(":checked"))
		    		{
		    		 
		    		$('input[name="id"]').each(function(s,t){
		 		    	if(i==s)
		 		    		{
		 		    		   id+=$(this).val()+";";
		 		    		   return false;
		 		    		}
		    		});
		    		}
		    	
		    });
		    
			$.post('ReturnQRcode', //发送的接收地址。
    	            {
    	            	id:id,
    	            	readerid:readerid
    	            },
 
    			function(data,status){
    	           location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FQRcode.jsp&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
    			});
	});
	});
	</script>
</body>
</html>