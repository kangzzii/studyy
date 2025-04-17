<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    table{ width: 100%; border: 1px solid #ddd; }
    th { border: 1px solid #ddd; background: #f1f1f1; padding: 5px 10px; }
    td { border: 1px solid #ddd; text-align: center;  padding: 5px 10px; }
    .soarting-box{ padding: 10px 0; text-align: right; }
    .soarting { padding: 10px 20px; border: 1px solid #ddd; }
</style>
<script>
$(function(){
    list.init();
})
var list = {
        init : function(){
            changeDate()
            list.event();
        }
        ,event : function(){
            $('.soarting').change(function(){
                list.soarting($(this).val());
            })
        }
        ,soarting : function(numb){
            $.ajax({
                url: '/bbs/notice/list.do'
                ,type: 'post'
                ,data: {numb: numb}
               ,success: function(res){
                   $('tbody tr').remove();
                   let item = JSON.parse(res);
                   let txt = '';
                   for(i=0;i<item.length;i++){
                       txt += '<tr>';
                       txt += '    <td>'+ item[i].noticeId+'</td>';
                       txt += '    <td>'+ item[i].noticeType+'</td>';
                       txt += '    <td><a href="/bbs/notice/form/'+item[i].noticeId+'.do">'+ item[i].noticeTitle+'</a></td>';
                       txt += '    <td>'+ item[i].noticeInqCnt+'</td>';
                       txt += '    <td>'+ item[i].noticeDispYn+'</td>';
                       txt += '    <td>'+ item[i].noticeYn+'</td>';
                       txt += '    <td>'+ item[i].noticeUseYn+'</td>';
                       txt += '    <td>'+ item[i].cretUserId+'</td>';
                       txt += '    <td class="dateCell" data-date="'+item[i].cretDt+'"></td>';
                       txt += '    <td>'+ item[i].modUserId+'</td>';
                       txt += '    <td class="dateCell" data-date="'+item[i].modDt+'"></td>';
                       txt += '</tr>';
                   }
                   $('tbody').append(txt);
                   changeDate();
               }
            })
        }
}
function changeDate(){
    $('tbody').find('.dateCell').each(function(){
        let txt = $(this).data('date');
        $(this).text( txt.split(' ')[0] );
    })
}
</script>
<div>
    <div class="soarting-box">
        <select class="soarting">
            <option value="10">10개</option>
            <option value="20">20개</option>
            <option value="30">30개</option>
            <option value="40">40개</option>
            <option value="50">50개</option>
        </select>
    </div>
    <table>
        <colgroup>
            <col style="width: auto"/>
            <col style="width: auto"/>
            <col style="width: auto"/>
            <col style="width: auto"/>
            <col style="width: auto"/>
            <col style="width: auto"/>
            <col style="width: auto"/>
        </colgroup>
        <thead>
            <tr>
                <th scope="col">번호</th>
                <th scope="col">타입</th>
                <th scope="col">제목</th>
                <th scope="col">조회수</th>
                <th scope="col">공지사항<br/>게시여부</th>
                <th scope="col">공지사항<br/>공지여부</th>
                <th scope="col">공지사항<br/>사용여부</th>
                <th scope="col">작성자</th>
                <th scope="col">작성일시</th>
                <th scope="col">수정자</th>
                <th scope="col">수정일시</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="result" items="${resultList}">
            <tr>
                <td>${result.noticeId}</td>
                <td>${result.noticeType}</td>
                <td><a href="/bbs/notice/form/${result.noticeId}.do">${result.noticeTitle}</a></td>
                <td>${result.noticeInqCnt}</td>
                <td>${result.noticeDispYn}</td>
                <td>${result.noticeYn}</td>
                <td>${result.noticeUseYn}</td>
                <td>${result.cretUserId}</td>
                <td class="dateCell" data-date="${result.cretDt}"></td>
                <td>${result.modUserId}</td>
                <td class="dateCell" data-date="${result.modDt}"></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!--  -->
    <div>

    </div>
    <button type="button" onclick="location.href='/bbs/notice/form.do'">등록</button>
</div>