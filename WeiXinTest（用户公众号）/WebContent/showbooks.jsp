<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>图书显示</title>
	<link href="css/bootstrap.css" rel="stylesheet" media="screen">
	<link href="css/showbook.css" rel="stylesheet" media="screen">
	<script src="js/jquery-1.10.1.min.js"></script>
	<script src="js/jquery.cookie.js"></script>
</head>
<body>
	<div class="container" style="margin-top:4px">
		<div class="bs-docs-section">
		<c:forEach var="book" items="${bookslist}">
		    <div class="row">
			    <div class="col-lg-4 col-sm-6">
			        <div class="thumbnail content-thumbnail" style="float: left;">
			            <img alt="" src="${book.face}" style="width: 100px;height:120px">
			        </div>
			        <div class="caption" style="margin-top:20px">
			         	<div>
			            	<h4>&nbsp;书名:${book.title}</h4>
			            	<h5>&nbsp;&nbsp;作者:${book.author}</h5>
			            </div>
			            <p class="p1">&nbsp;藏书量:${book.leftnum}<br><br>&nbsp;出版社:${book.publish}</p>
			            <input type="text" value="${book.leftnum}" style="display:none" name="leftnum" id="leftnum"/>
			        </div>
			    </div>
			</div>
			<div>
				<a class="btn btn-danger btn-line" href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FInfoOfBooks%3Fisbn%3D${book.ISBN}&response_type=code&scope=snsapi_base&state=123#wechat_redirect">在线阅读</a>
				<a class="btn btn-info btn-line" href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FReserveBook%3Fbookid%3D${book.bookId}&response_type=code&scope=snsapi_base&state=123#wechat_redirect">预&nbsp;&nbsp;&nbsp;订</a>
				<a class="btn btn-info btn-line" id="borrowbook" href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FTomyBook%3Fbookid%3D${book.bookId}&response_type=code&scope=snsapi_base&state=123#wechat_redirect"> 加入借书栏</a>
			</div>
		</c:forEach>
		</div>
		
		<footer>
		    <div class="col-xs-12" style="text-align: center">
		        <hr/>
		        <p>By <a href="#" rel="nofollow">wuweiliberay</a>.</p>
		    </div>
		</footer>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function () {
	var booksearchresult="${bookslist}";
	if(booksearchresult=="[]")
	{
		alert("抱歉！该图书馆暂无此书！！");
	} 
	
	var leftnum=$("#leftnum").val();
	if(leftnum<=1)
	{
		 $("#borrowbook").each(function () {
	           $(this).css("cursor", "default");
	           $(this).attr('href', '#');     //修改<a>的 href属性值为 #  这样状态栏不会显示链接地址  
	           $(this).click(function (event) {
	        	   alert("抱歉，该书馆藏量不足，不能被借阅！");
	        	   function delLink(link) {    
	        		    link.setAttribute("disabled",true);   
	        		    link.removeAttribute('href');     
	        		}
	           });
	        });
	}
});
</script>
</html>