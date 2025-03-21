<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="inner">
	<div>
	</div>
	<div id="toastCalendar" style="height: 100%;"></div>
</div>
<link rel="stylesheet" href="/resource/css/toastui-calendar.min.css">
<link rel="stylesheet" href="/resource/css/tui-date-picker.css">
<link rel="stylesheet" href="/resource/css/tui-time-picker.css">
<script src="/resource/js/toastui-calendar.min.js"></script>
<script src="/resource/js/tui-time-picker.js"></script>
<script src="/resource/js/tui-date-picker.js"></script>
<script src="/resource/js/jquery-3.7.1.min.js"></script>
<script>
	$(function(){
		init.calendar();
	})
	var init = {
		calendar : function(){
			 new tui.Calendar($('#toastCalendar')[0], {
				 defaultView : 'month'
				 ,useFormPopup : true
				 ,useDetailPopup: true
			 });
		}
	}
</script>