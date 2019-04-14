<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn"> 
	<head> 
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no" /> 
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		<title></title> 
		<style type="text/css"> 
			*{ height: 100%; //设置高度，不然会显示不出来 } 
		</style> 
		<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ndT49gN70aeOF9qi131sfivZdtYrGS2P"></script> 
		<script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
		<script>
			var libaryId=new Array();
			var lname=new Array();
			var gpsx=new Array();
			var gpsy=new Array();
			var j=0;
			<c:forEach var="map" items="${requestScope.libaryMap}">
				libaryId[j]="${map.libaryId}";
				lname[j]="${map.name}";
				gpsx[j]="${map.gpsx}";
				gpsy[j]="${map.gpsy}";
				j++; 
			</c:forEach>
			$(function(){ 
				navigator.geolocation.getCurrentPosition(translatePoint);
			}); 
			function translatePoint(position){ 
				var currentLat = position.coords.latitude; 
				var currentLon = position.coords.longitude; 
				var gpsPoint = new BMap.Point(currentLon, currentLat); 
				BMap.Convertor.translate(gpsPoint, 0, initMap);
			} 
			function initMap(point){  
				map = new BMap.Map("map"); 
				map.addControl(new BMap.NavigationControl()); 
				map.addControl(new BMap.ScaleControl()); 
				map.addControl(new BMap.OverviewMapControl()); 
				map.centerAndZoom(point, 15);
				addMarker(point);
				var markers = [];
				for (var i =0; i <j; i ++) {
				    var mkr =new BMap.Marker(new BMap.Point(gpsx[i], gpsy[i]));
				    var opts = {
			  			position : new BMap.Point(gpsx[i], gpsy[i]),    // 指定文本标注所在的地理位置
			  			offset   : new BMap.Size(15, -30)    //设置文本偏移量
					};
					var label = new BMap.Label(lname[i], opts);
					label.setStyle({color : "black",fontSize : "12px",height : "20px",lineHeight : "20px",fontFamily:"微软雅黑"});
					map.addOverlay(label);
				    markers.push(mkr);
				    map.addOverlay(mkr);
				}
				for (i =0; i <j; i ++) {
				    (function(){
				        var index = libaryId[i];
				        markers[i].addEventListener('click', function(){
				        	location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FLibaryServlet%3FlibaryId%3D"+index+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
				        });    
				    })();
				}
			}
			function addMarker(point){
			  var marker = new BMap.Marker(point);
			  map.addOverlay(marker);
			}
		</script>
	</head> 
	<body> 
		<div id="map">
		</div>
	</body> 
</html>