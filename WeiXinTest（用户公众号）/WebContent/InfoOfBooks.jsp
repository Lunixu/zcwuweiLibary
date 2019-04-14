<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>  
<html>  
<head>  
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />  
  <meta charset="UTF-8">  
  <title>${bookstitle}</title>  
  <link href="__PDF__/jquery.touchPDF.css" rel="stylesheet" media="screen" />  
    <style>  
        body , html{  
            background-color: #404040;  
            height: 100%;  
            padding: 0;  
            margin: 0;  
        }  
    </style>  
</head>  
<body>  
  
<div id="myPDF" style="height: 100%; width: 100%; margin: auto;"></div>  
<script type="text/javascript" src="__PDF__/pdf.compatibility.js"></script>  
<script type="text/javascript" src="__PDF__/pdf.js"></script>  
<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>  
<script type="text/javascript" src="__PDF__/jquery.touchSwipe.js"></script>  
<script type="text/javascript" src="__PDF__/jquery.touchPDF.js"></script>  
<script type="text/javascript" src="__PDF__/jquery.panzoom.js"></script>  
<script type="text/javascript" src="__PDF__/jquery.mousewheel.js"></script>  
<script type="text/javascript" src="__PDF__/pdf.worker.js"></script>  
  
<script type="text/javascript">  
	var bookscontent="${bookscontent}";
    $(function() {  
        $("#myPDF").pdf( {  
            source: "BookContent/"+bookscontent,  
               
        } );  
    });  
</script>  
</body>  
</html> 