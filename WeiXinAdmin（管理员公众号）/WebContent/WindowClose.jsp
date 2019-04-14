<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>提示</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="css/order.css" media="screen">
    <script src="js/jquery-1.10.1.min.js"></script>
</head>
<body onload="closeWindow();">
	<div style="margin:0 auto;">
		<h3 style="text-align:center; font-weight:bold;">${Info}</h3>
		<div id="show" style="text-align:center; font-weight:bold; ">  
		  	将倒计时5秒后关闭当前窗口，返回微信公众号窗口！
		</div>
	</div>
</body>
<script type="text/javascript">
	var time=5;  
	function closeWindow(){  
		window.setTimeout('closeWindow()',1000);  
		if(time>0)
		{  
			document.getElementById("show").innerHTML=" 将倒计时<font color=red>"+time+"</font>秒后关闭当前窗口,返回微信公众号窗口!";  
			time--;  
		}else
		{  
			WeixinJSBridge.call('closeWindow');
			//this.window.opener=null; //关闭窗口时不出现提示窗口  
			//window.close();  
		}
	}
</script>