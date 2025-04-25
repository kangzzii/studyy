<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/resource/SBGrid3/sbgrid3.js" type="text/javascript"></script>
<link href="/resource/SBGrid3/css/sbgrid3.css" rel="stylesheet" />

<div class="search-box">
    <form>
        <select id="searchSoarting">
            <option value="all">전체</option>
            <option value="title">제목</option>
            <option value="cretUser">생성자 이름</option>
        </select>
        <input type="text" placeholder="검색어" id="searchTxt"/>
        <button type="button" id="btnSearch">검색</button>
    </form>
</div>

<div id="grid"></div>

<script id='cellTemplate' type='text/x-handlebars-template'>
  <a href="/bbs/notice/form/{{noticeId}}.do">{{noticeTitle}}</a>
</script>

<script>
$(function(){
  grid.init();
  search.event();
})
let search = {
        event : function(){
            let searchCond = $('#searchSoarting').val();
            let searchTxt = $('#searchTxt').val();
            $('#btnSearch').click(function(){

            });
        }
        doSearch: function(cond, txt){

        }
}
let grid = {
        init: function(){
            let gridConfig = {
                dataSource: this.sourceConfig
                ,container: '#grid'
                ,width: '100%'
                ,height: '550px'
                ,columns: [
                     { field: 'noticeId', caption: '게시글번호'}
                    ,{ field: 'noticeType', caption: '공지사항 타입'}
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
//                 ,pageSize : 10
                ,pagerBar: {
                    left: ['']
                    ,center: ['pager']
                    ,right: ['pageSizes']
                 }
                ,scrollRange:'page' // page , whole
                ,pageable: {
                    pager :{
                        buttonCount : 5             // 화면에 표시할 페이지 갯수
                        ,previousNext : true
                    }
                    ,pageSizes :  [10,20,30]
                  }

            };
            datagrid = SBGrid3.createGrid(gridConfig);
        }
        ,sourceConfig: {
            ajax: {
                select: (request) => {
                    return new Promise((resolve, reject) => {
                        $.ajax({
                            url: '/bbs/notice/getList.do'
                            ,method: 'post'
                            ,contentType: "application/x-www-form-urlencoded; charset=utf-8"
                            ,data: request
                            ,dataType: "json"
                            ,success: function (resData) {
                                resolve({data : resData.list, total: resData.total});
                            }
                            ,error: function () {
                              reject();
                            }
                        });
                    });
                }
                ,selected: 'data'
                ,total: 'total'
            }
            ,serverPaging : true
        }
        ,pager: function(){
            let pageNo = SBGrid3.getPageNo(datagrid);
            let pageSize = SBGrid3.getPageSize(datagrid);
        }
}
</script>