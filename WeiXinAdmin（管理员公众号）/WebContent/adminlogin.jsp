<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>管理员登录</title>
	<link href="css/bootstrap.css" rel="stylesheet" media="screen">
	<link href="skins/eden.css" rel="stylesheet" media="screen">
	<script src="js/jquery-1.10.1.min.js"></script>
	<style>
		.navbar-holder-dark {padding: 20px 20px 200px 20px;background: #333333;}
		.search_container{height: 70px; margin-bottom: -4px;}
	</style>
</head>
	<body>
		<div class="bs-docs-section">
		    <div class="row">
		        <div class="col-lg-12">
		            <div class="page-header">
		            	<h2 style="text-align: center">管理员账号绑定</h2>
		            </div>
		        </div>
		    </div>
		
		    <div class="row">
		        <div class="col-lg-6">
		            <div class="well bs-component" style="color:green;">
		                <form class="form-horizontal" method="post">
		                	<input type="text" value="${param.adminId}" style="display:none" name="readerId" id="readerId"/>
		                    <fieldset>
		                        <div class="form-group">
		                            <label for="inputEmail" class="col-lg-2 control-label">账 户</label>
		
		                            <div class="col-lg-10">
		                                <input class="form-control required" id="adminUsername" placeholder="输入用户名" type="text">
		                            </div>
		                        </div>
		                        <div class="form-group">
		                            <label for="inputPassword" class="col-lg-2 control-label">密 码</label>
		
		                            <div class="col-lg-10">
		                                <input class="form-control required" id="adminPassword" placeholder="输入密码" type="password">
		                            </div>
		                        </div>
		                        <div class="form-group">
		                        	<div  style="margin:0 auto;width:60px;height:26.8px">
		                        		<input type="submit" value="绑  定" id="submitBtn"/>
		                        	</div>
		                        </div>
		                    </fieldset>
		                </form>
		            </div>
		        </div>
		    </div>
		</div>
		<footer>
		    <div class="col-xs-12" style="text-align: center">
		        <hr/>
		        <p>By <a href="#" rel="nofollow">wuweilibrary</a>.</p>
		    </div>
		</footer>
</body>

	
	<script type="text/javascript">
	$.ajaxSetup({
		  async: false
		  });
	$(document).ready(function(){
		 //如果是必填的，则加红星标识.
		/*$("form :input.required").each(function(){
            var $required = $("<strong style='color:red;'> *</strong>"); //创建元素
            $(this).parent().append($required); //然后将它追加到文档中
        });*/
		 
		/*//文本框失去焦点后
        $('form :input').blur(function(){
             var $parent = $(this).parent();
             $parent.find(".formtips").remove();
             //验证用户名
             if( $(this).is('#adminUsername') ){
                    if( this.value=="" ){
                        var errorMsg = '请正确填写账号.';
                        $parent.append('<span class="formtips onError" style="color:red;">'+errorMsg+'</span>');
                    }
             }
             if( $(this).is('#adminPassword') ){
                 if( this.value=="" ){
                     var errorMsg = '密码不能为空.';
                     $parent.append('<span class="formtips onError" style="color:red;">'+errorMsg+'</span>');
                 }
          }
            
        }).keyup(function(){
           $(this).triggerHandler("blur");
        }).focus(function(){
             $(this).triggerHandler("blur");
        });//end blur*/
		
		$("#submitBtn").click(function(){
			
			/*$("form :input.required").trigger('blur');
            var numError = $('form .onError').length;
            if(numError){
                //return false;
                var adminId=$("#readerId").val();//id
                alert("您输入的信息有误！");
            	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx96b86ea8fdffd49f&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinAdmin%2Fadminlogin.jsp%3FadminId%3D"+adminId+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect"; 
            	return false;
            } 
            else*/
            //{
	    		var adminId=$("#readerId").val();//id
	    		var adminUsername=$("#adminUsername").val();//账号
	    		var adminPassword=$("#adminPassword").val();//密码
	    		if(adminUsername!="" && adminPassword!="")
	    		{
		    
		    		$.post('AdminRegisterServlet', //发送的接收地址
		    	    	{
		    				adminId:adminId,
		    				adminUsername:adminUsername,
		    				adminPassword:adminPassword,
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
	    			alert("请输入正确的信息！");
    				//WeixinJSBridge.call('closeWindow');
	    		}
           //}
		});
	});
	
	</script>
</html>
