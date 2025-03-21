<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>popup</title>
<link href="/resource/common.css" rel="stylesheet">
</head>
<body>
	<div id="popup">
		 <tiles:insertAttribute name="header"/>
		 <div class="inner">
			 <tiles:insertAttribute name="body"/>
		 </div>
	</div>
</body>
</html>
