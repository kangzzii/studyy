<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>우와아앙 왜 동작하지</title>
<link href="/resource/common.css" rel="stylesheet">
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
<script src="/resource/jquery-3.7.1.min.js"></script>
<script>
	$(function(){
		$('.btnSidebar').click(function(){
			$('.sidebar').toggleClass('on')
		})
	})
</script>
</html>
