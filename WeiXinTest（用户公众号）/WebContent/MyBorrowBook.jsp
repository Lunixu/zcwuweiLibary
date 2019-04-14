<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>我的图书</title>
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
		                <table class="ta">
		                    <thead>
			                    <tr>
			                        <th style="width:25%">图书名称</th>
			                        <th style="width:25%">图书封面</th>
			                        <th style="width:25%">借阅时间</th>
			                        <th style="width:25%">归还期限</th>
			                    </tr>
		                    </thead>
		                </table>     
		                     
		               <c:forEach var="order" items="${MyBorrowBook}">
		                <table class="ta">
		                	<tbody>
				                <tr valign="middle">				      
			                        <td style="word-break:break-all;width:25%;text-align:center">${order.title}</td>
			                        <td style="word-break:break-all;width:25%;text-align:center"><img alt="" src="${order.face}" style="width: 50px;height:50px"></td>
			                        <td style="word-break:break-all;width:25%;text-align:center">${order.ordertime}</td>			                        
			                        <td style="word-break:break-all;width:25%;text-align:center">${order.returntime}</td>
				                </tr>
				            </tbody>
		                </table>
		                </c:forEach>
		                <p style="text-align:center; font-size:16px;font-weight:bold; color:red;">${BindInfo }</p>
		                <hr>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
</body>
</html>