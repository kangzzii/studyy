<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/resource/SBGrid3/sbgrid3.js" type="text/javascript"></script>
<link href="/resource/SBGrid3/css/sbgrid3.css" rel="stylesheet" />
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
            list.grid();
        }
        ,grid: function(){
            let jsonListData = JSON.parse('${jsonListData}')
            //console.log(jsonListData)
            let gridConfig = {
                dataSource : {
                    data : jsonListData
                    ,serverPaging : true
                    ,pageSize: 10
                }
                ,container : '#grid'
                ,width: '100%'
                ,height: '600px'
                ,stickyHeader: true
                ,hover: 'row'   // (cell(cell 단위) | row(row 단위))
                ,search:true    // 검색
                ,toolBar:'search'
                ,columns: [
//                         { field: 'noticeId', caption: '게시글번호'}
                        { field: 'noticeType', caption: '공지사항 타입'}
                        ,{ field: 'noticeTitle', caption: '제목', cellTemplate: '#cellTemplate',}
                        ,{ field: 'noticeInqCnt', caption: '조회수'}
                        ,{ field: 'noticeDispYn', caption: '게시여부', filter:true }
                        ,{ field: 'noticeYn', caption: '공지여부' }
                        ,{ field: 'useYn', caption: '사용여부' }
                        ,{ field: 'cretUserId', caption: '생성자명' }
                        ,{ field: 'cretDt', caption: '생성일' , calendarType: 'date', dataType:'date', format: 'yyyy-MM-dd' }
                        ,{ field: 'modUserId', caption: '수정자명' }
                        ,{ field: 'modDt', caption: '수정일', calendarType: 'date', dataType:'date', format: 'yyyy-MM-dd' }
               ]
               ,pagerBar: {
                   left: ['']
                   ,center: ['pager']
                   ,right: ['pageSizes']
                }
               ,pageable: {
                   pager :{
                       buttonCount : 5
                       ,numeric : true
                       ,previousNext : true
                   } // Object pager는  pagerBar 와 같이 사용
                   ,pagerCombo : { listCount : 5 }
                   ,pageSizes :  [10,20,30]
                 }
            }
            datagrid = SBGrid3.createGrid(gridConfig);
    }
}
function changeDate(){
    $('tbody').find('.dateCell').each(function(){
        let txt = $(this).data('date');
        $(this).text( txt.split(' ')[0] );
    })
}
</script>
<div id="grid"></div>
<script id='cellTemplate' type='text/x-handlebars-template'>
  <a href="/bbs/notice/form/{{noticeId}}.do">{{noticeTitle}}</a>
</script>
<div>
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
                <td>${result.useYn}</td>
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