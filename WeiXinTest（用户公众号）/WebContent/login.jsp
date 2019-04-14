<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户绑定</title>
		<meta content="yes" name="apple-mobile-web-app-capable"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;"/>
		<script type="text/javascript" src="js/year_month_day.js"></script>
		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		<!-- Custom Theme files -->
		<link href="css/UserLogin.css" rel="stylesheet" type="text/css" media="all" />
		<!-- //Custom Theme files -->
		<!-- web font -->
		<link href="//fonts.googleapis.com/css?family=Old+Standard+TT:400,400i,700" rel="stylesheet"/>
		<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'/>
		<!--web font-->
		<!-- //web font -->
	</head>
	<body>
		<!-- main -->
		<div class="main main-agileits">
			<h1></h1>
			<div class="main-agilerow"> 
				<div class="signup-wthreetop">
					<h2>Welcome to Library</h2>
				</div>	
				<div class="contact-wthree">
					<form method="post">					
							<input type="text" value="${param.readerId}" style="display:none" name="readerId" id="readerId"/>							
						<div class="form-w3step1" style="padding-top:10px">
							<input type="text" name="name" placeholder="请输入昵称" required="" id="name"/>
						</div> 
						<div class="form-w3step1">
							<select id="Sex" name="sex" >
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</div>
						<div class="form-w3step2">
							<select class="Birth" id="selYear" name="year" ></select><label>年</label>
							<select class="Birth" id="selMonth" name="month" ></select><label>月</label>
							<select class="Birth" id="selDay" name="day" ></select><label>日</label>
						</div>
						<div class="form-w3step1">
							<select id="Education" name="education" >
								<option value="小学">小学</option>
								<option value="中学">中学</option>
								<option value="专科">专科</option>
								<option value="本科">本科</option>
								<option value="本科以上">本科以上</option>
							</select>
						</div>
						<div class="form-w3step1" >
							<input type="checkbox" id="Literature" value="文学" name="hobby"/><label ><span></span>文学</label>
							<input type="checkbox" id="Science" value="科技" name="hobby"/><label ><span></span>科技</label>
							<input type="checkbox" id="Geology" value="地质" name="hobby"/><label ><span></span>地质</label>
							<input type="checkbox" id="Delicious" value="美食" name="hobby"/><label ><span></span>美食</label>
						</div>
						<div class="form-w3step1 agileits-row2 w3ls-formrow2">
							<input type="checkbox" id="brands" value="true" name="isAccept" required=""/>
							<label for="brands"><span></span>I accept the terms of Use</label> 
						</div>  
						
						<input type="submit" value="Ok" id="submitBtn"/>		
					</form>
				</div>  
			</div>	
		</div>	
		<!-- //main -->
		<!-- copyright -->
		<div class="w3copyright-agile">
			<p>© 2017 Client Signup Form. All rights reserved | Design by <a href="#" target="_blank">WuWeiBuZhi</a></p>
		</div>
		<!-- //copyright --> 
	</body>

<script type="text/javascript">
	$.ajaxSetup({
		  async: false
		  });
	var selYear = window.document.getElementById("selYear");
	var selMonth = window.document.getElementById("selMonth");
	var selDay = window.document.getElementById("selDay");
	new DateSelector(selYear, selMonth, selDay, 2004, 2, 29);
	
	$(document).ready(function(){
		
		$("#submitBtn").click(function(){
    		var readerId=$("#readerId").val();//openid
    		var name=$("#name").val();//姓名
    		
    		var sex=$("#Sex").val();//性别
    		
    		var education=$("#Education").val();//学历
    			
    		var isAccept="false";//协议  isAccept
    		if($("#brands").is(":checked")){//选中  
    			isAccept="true";
    		}
    		
            var year=$("#selYear").val();//年
            var month=$("#selMonth").val();//月
            var day=$("#selDay").val();//日
            
            var hobbys=new Array();  //爱好
            $('input[name="hobby"]:checked').each(function(){  
            	hobbys.push($(this).val());//向数组中添加元素  
            });  
            var hobby=hobbys.join(','); //将数组元素连接起来以构建一个字符串          

            if(name!="")
            {
    			$.post('RegisterServlet', //发送的接收地址
    	        	{
    				readerId:readerId,
    	    		name:name,
    	    		sex:sex,
    	    		education:education,
    	    		isAccept:isAccept,
    	            year:year,
    	            month:month,
    	            day:day,
    	            hobby:hobby,
    	        	},

    			function(data,status){
    				alert(data);
    				WeixinJSBridge.call('closeWindow');
    				//this.window.opener = null;  
    				//window.close(); 
    			});
            }
            else
            {
            	alert("请正确填写信息！！");
            }
		});
	});
	
</script>

</html>