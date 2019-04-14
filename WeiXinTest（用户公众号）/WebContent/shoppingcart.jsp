<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>借书详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="css/order.css" media="screen">
     <script src="js/jquery-1.10.1.min.js"></script>
  
</head>
<body>
	<div class="container">
		<div class="bs-docs-section">
		    <div class="row">
		        <div class="col-md-6">
		            <div>
		                <table class="table-bordered  ta">
		                    <thead>
			                    <tr>
			                        <th>图书编号</th>
			                        <th>图书名称</th>
			                        <th>图书封面</th>
			                        <th>图书押金</th>
			                    </tr>
		                    </thead>
		                </table>
		                <input id="borrowbooksid" type="text" style="display:none" value="${sessionScope.borrowbooksid}">
		                <c:forEach var="book" items="${sessionScope.borrowbooks}">
		                <table class="ta">
		                	<tbody>
				                <tr valign="middle">
			                        <td class="divcss5-td-2">${book.ISBN}</td>
			                        <td class="divcss5-td-3">${book.title}</td>
			                        <td class="divcss5-td-4"><img alt="" src="${book.face}" style="width: 50px;height:50px"></td>			                        
			                        <td class="divcss5-td-5">暂无</td>
				                </tr>
				            </tbody>
		                </table>
		                </c:forEach>
		            </div>
		        </div>
		    </div>
		</div>
		<div>
		<hr/>
		<input type="submit" class="btn" id="submit" value="确认生成借书二维码"/>
        </div>
	</div>
	<script>
	$.ajaxSetup({
		  async: false
		  });
	$(document).ready(function(){
	$("#submit").click(function(){
		 
		    var borrowbooksid = $("#borrowbooksid").val();
			$.post('QRcode', //发送的接收地址。
    	            {
    	            	id:borrowbooksid
    	            },
 
    			function(data,status){
    	           location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FQRcode.jsp&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
    			});
	});
	});
	</script>
</body>
</html>