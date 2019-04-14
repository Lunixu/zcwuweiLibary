<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>图书检索</title>
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
	<div class="navbar navbar-default navbar-fixed-top gover_search">
		<div class="container gover_search_form clearfix" style="height: 70px; margin-bottom: -4px;">
			<div class="navbar-header gover_search_form clearfix">
				<form class="navbar-form navbar-left" role="search"   method="post" onsubmit="return FormValidation()">
					<div class="navbar-brand col-xs-4 col-sm-3 text-left" style="width: 70px; height: 33.6px; padding-top: 0; margin: 0;">
						<span>图书检索</span>
					</div>
					<div class="form-group col-xs-8 col-sm-3" style="margin-left: -22px;">
						<input type="text" autocomplete="off" name="search" class="form-control" id="gover_search_key" placeholder="书名、类别、关键字等"/>
						<div class=" search_suggest" id="gov_search_suggest">  
			            	<ul>  
			            	</ul>  
			        	</div> 
					</div>
					<div class="col-xs-1 col-sm-3" style="margin-left: -15px;">		
						 <input type="button"  class="btn btn-default" id="submitBtn" value="搜&nbsp;索"/> 
					</div>
				</form>
			</div>
		</div>
	</div>
	
<script>
var SearchHistory = decodeURI($.cookie('SearchHistory')).split(";");
var input = $('#gover_search_key');
var suggestWrap = $('#gov_search_suggest'); 
var ShowHistory = function(){
	if(SearchHistory!='undefined')
    {
     var li;  
     var tmpFrag = document.createDocumentFragment();  
     suggestWrap.find('ul').html('');  
     for(var i=0; i<SearchHistory.length; i++){  
         li = document.createElement('LI');  
         li.innerHTML = SearchHistory[i]; 
         tmpFrag.appendChild(li);  
     }  
     suggestWrap.find('ul').append(tmpFrag);  
     suggestWrap.show();  
     //为下拉选项绑定鼠标事件  
     suggestWrap.find('li').hover(function(){  
             suggestWrap.find('li').removeClass('hover');  
             $(this).addClass('hover');  
            
         },function(){  
             $(this).removeClass('hover');  
     }).bind('click',function(){  
         input.val(this.innerHTML);  
         suggestWrap.hide();  
     });  
    }
    
}

function FormValidation(){
	input.val(input.val().replace(/(^\s*)|(\s*$)/g,""));
	if(input.val()=="")
		{
		$('.err').html("请输入关键词");
		return false;
		}
	else
		{
		return true;
		}
}

var hideSuggest = function(){ 
	
    suggestWrap.hide();  
}
input.bind('blur',function(){setTimeout(hideSuggest,100);});
input.bind('focus',ShowHistory);
</script>
<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
<script type="text/javascript">
$.ajaxSetup({
	  async: false
	  });
	$(document).ready(function(){
		
		$("#submitBtn").click(function(){
    		var search=$("#gover_search_key").val();   
			if(search!="")
			{
				if(search.replace(/\s+/g,"")!="")
				{
				$.post("BookSearch", //发送的接收地址
	    	        	{
	    				search:search
	    	        	},
	 
	    				function(data,status){
	    	        		//alert("SSS"+status);
	    	        		location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2Fshowbooks.jsp&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
	    			});	
				}
				else
				{
					alert("您输入的内容有误！！");
				}
			}
			else
			{
    			alert("请输入搜索内容！！");
			};
		});
	});
</script>
</html>