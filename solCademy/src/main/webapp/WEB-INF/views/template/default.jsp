<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>solCademy</title>
<script src="/resource/js/jquery-3.7.1.min.js"></script>
<link href="/resource/css/common.css" rel="stylesheet">
</head>
<body>
<div id="wrap">
 <tiles:insertAttribute name="header"/>
 <div id="contents">
	 <tiles:insertAttribute name="menu"/>
	 <tiles:insertAttribute name="body"/>
	 <tiles:insertAttribute name="sidebar"/>
 </div>
 <tiles:insertAttribute name="footer"/>
</div>
</body>
<script>
	$(function(){
		$('.btnSidebar').click(function(){
			$('.sidebar').toggleClass('on')
		})
	})
</script>
</html>
