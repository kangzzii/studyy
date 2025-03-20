<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table, th, td {
		border: 1px solid #000;
		border-collaps
	}
</style>
</head>
<body>
	<form method="post" action="/printLog.do">
	<input type="hidden" name="actionType" id="actionType" value="${statusType}" />
		<table>
			<colgroup>
				<col style="width: 100px;"/>
				<col style="width: 200px;"/>
				<col style="width: 100px;"/>
				<col style="width: 200px;"/>
			</colgroup>
			<tbody>
				<tr>
					<th>이름</th>
					<td colspan="3">
					<input type="text" id="username" name="username"  value="${username}" />
					</td>
				</tr>
				<tr>
					<th scope="col">연락처</th>
					<td scope="col"><input type="text" id="userphone" name="userphone"  value="${userphone}"/></td>
					<th rowspan="2" scope="col">주소</th>
					<td rowspan="2" scope="col"><input type="text" id="addr" name="addr"  value="${addr}"/></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" id="email" name="email"  value="${email}"/></td>
				<tr/>
				<tr>
					<th>내용</th>
					<td colspan="3">
						<input type="text" id="txt" name="txt"  value="${txt}"/>
					</td>
				</tr>
			</tbody>
		</table>
		<button type="button">목록</button>
		<button id="submitBtn" name="submitBtn" type="submit">수정</button>
	</form>
</body>
<script src="/resource/jquery-3.7.1.min.js"></script>
<script>
	$(function(){
		if($('#actionType').val() == 'add'){
			$('#submitBtn').text('등록');
		}
	})
</script>
</html>