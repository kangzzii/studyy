<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	table{ margin: 20px auto; width: 800px; height: 400px; border: 1px solid #ddd; }
	th { border: 1px solid #ddd; background: #f1f1f1; height: 30px; line-height: 30px;  }
	td { border: 1px solid #ddd; text-align: center; height: 30px; line-height: 30px; }
</style>
<body>
	<table>
	    <colgroup>
	        <col style="width: 16%"/>
	        <col style="width: 16%"/>
	        <col style="width: auto"/>
	        <col style="width: 16%"/>
	        <col style="width: 10%"/>
	        <col style="width: 16%"/>
	    </colgroup>
	    <thead>
	        <tr>
	            <th scope="col">이름</th>
	            <th scope="col">나이</th>
	            <th scope="col">연락처</th>
	            <th scope="col">국적</th>
	            <th scope="col">성별</th>
	            <th scope="col">사용여부</th>
	        </tr>
	    </thead>
	    <tbody>
	    </tbody>
	</table>
</body>
<script>
	$.ajax({
		url : "ajaxView.do"
		,type : 'post'
		,success : function(data) {
   			 initList(data);
	     }
		,error : function() {
			alert("error");
		}
	});
	
	function initList(data){
		var txt = '';
		for(let i=0;i < data.length;i++){
			 txt += '<tr>';
			 txt += '<td>'+data[i].name+'</td>';
			 txt += '<td>'+data[i].age+'</td>';
			 txt += '<td>'+data[i].phone+'</td>';
			 txt += '<td>'+data[i].nation+'</td>';
			 txt += data[i].gender == 'F' ? '<td>여자</td>' :'<td>남자</td>' ;
			 txt += data[i].userYn == 'Y' ? '<td>사용</td>' :'<td>미사용</td>' ;
			 txt += ' </tr>';
		}
		$('table tbody').append(txt);
	}
</script>
</html>