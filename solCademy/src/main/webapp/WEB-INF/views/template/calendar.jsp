<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>solCademy</title>
<!-- css -->
<link rel="stylesheet" href="/resource/css/calendar/tui-date-picker.css">
<link rel="stylesheet" href="/resource/css/calendar/tui-time-picker.css">
<link rel="stylesheet" href="/resource/css/calendar/toastui-calendar.min.css">
<link rel="stylesheet" href="/resource/css/calendar/app.css" />
<link rel="stylesheet" href="/resource/css/calendar/icons.css" />
<link href="/resource/css/common.css" rel="stylesheet">
<!-- js -->
<script src="/resource/js/jquery-3.7.1.min.js"></script>
<script src="/resource/js/calendar/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/chance/1.1.8/chance.min.js"></script>
<script src="/resource/js/calendar/tui-time-picker.js"></script>
<script src="/resource/js/calendar/tui-date-picker.js"></script>
<script src="/resource/js/calendar/toastui-calendar.min.js"></script>
<script src="/resource/js/calendar/mockData.js"></script>
<script src="/resource/js/calendar/utils.js"></script>

<style>
    .toastui-calendar-popup-container {left: 250px; top: 100px;}
</style>
</head>
<body>
<div id="wrap">
 <tiles:insertAttribute name="header"/>
 <div id="contents">
     <tiles:insertAttribute name="menu"/>
     <div style="padding: 20px;">
         <tiles:insertAttribute name="body"/>
     </div>
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
