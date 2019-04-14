<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
		<input type="text" value="${readerid}" style="display:none" name="readerid" id="readerid"/>
		<div class="bs-docs-section">
		    <div class="row">
		        <div class="col-md-6">
		            <div>
		                <table class="table-bordered  ta">
		                    <thead>
			                    <tr>
			                        <th style="width:33%">图书编号</th>
			                        <th style="width:33%">图书名称</th>
			                        <th style="width:33%">图书封面</th>  
			                    </tr>
		                    </thead>
		                </table>
		               <c:forEach var="order" items="${comparebooks}">
		                <input name="id" type="text" value="${order.bookId}" style="display:none">
		                <table class="ta">
		                	<tbody>
				                <tr valign="middle">
			                        <td style="width:33%;text-align:center">${order.ISBN}</td>
			                        <td style="width:33%;text-align:center">${order.title}</td>
			                        <td style="width:33%;text-align:center"><img alt="" src="${order.face}" style="width: 50px;height:50px"></td>
				                </tr>
				            </tbody>
		                </table>
		                </c:forEach>
		            </div>
		        </div>
		    </div>
		</div>
		<div>
		<input type="submit" class="btn" id="submit" value="确认借书"/>
        </div>
	</div>
	<script>
	$.ajaxSetup({
		  async: false
		  });
	$(document).ready(function(){
	$("#submit").click(function(){
		
		    var id="";
		    var readerid=$("#readerid").val();
		    $('input[name="id"]').each(function(i,n){
		    	 id+=$(this).val()+";";
		    	
		    });
			$.post('BorrowBook', //发送的接收地址。
    	            {
    	            	id:id,
    	            	readerid:readerid
    	            },
 
    			function(data,status){
    	           alert(data);
    	           WeixinJSBridge.call('closeWindow');
    			});
	});
	});
	</script>
</body>
</html>