<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<style>
    .box { width: 500px; }
    .box p { margin-top: 20px; }
    .box p > button { float: right; }
    table { width: 100%; }
    table caption {text-align: left; margin-bottom: 10px; }
    th,td { border: 1px solid #000; padding: 10px; }
    th { background: #ddd; }

    /* popup */
    #popup { display: none; position: fixed; top: 0; left: 0; right: 0; bottom: 0; background: rgba(0, 0, 0, .75); z-index: 2; width: 100%; height: 100%;  margin: 0; }
    .pop-inner { min-width: 200px; position: absolute; top: 50%; left: 50%; transfrom: translate(-50%,-50%); padding: 20px; background: #fff; }
    .pop-inner h1 {display: flex; justify-content:space-between; align-items: center; margin-bottom : 10px; }
    .pop-inner h1 a { display: inline-block; width: 20px; height: 20px; font-size: 20px; text-align: center;}
    .pop-inner .form-list li { margin-bottom: 10px; text-align: left; display: grid;grid-template-columns: 100px 150px; }
    .pop-inner .form-list input { width: 100%; }
    .pop-inner form { text-align: center; }
</style>
<script>

var CodeList = {
        init : function(){
            CodeList.event();
            // 공통코드 등록 버튼 숨김
            $('#commonCodeList #registBtn').hide();
        }
        , event: function(){
            // 그룹코드 클릭 이벤트
            $('#groupCodeTable table').on('click','tr',function(){
                let id = $(this).data("id");
                CodeList.groupAax(id);
            })

            // 그룹 코드 등록 팝업
            $('#groupCodeTable #registBtn').on('click', function(){
                // 그룹코드 등록 팝업 셋팅
                $('#popup h1 p').text('그룹코드 등록');
                let txt = '';
                txt += '<li>';
                txt += '    <p>공통코드명</p>';
                txt += '    <input type="text" maxlength="10" name="groupCodeNm" required/>';
                txt += '</li>';
                $('#popup .form-list').append(txt);
                $('#popup form').attr('data-code','groupCode');
                $('#popup form').attr('action', '/code/setGroupCode.do');
                $('#popup').show();
            })

            // 공통 코드 등록 팝업
            $('#commonCodeList #registBtn').on('click', function(){
                // 공통코드 등록 팝업 셋팅
                $('#popup h1 p').text('공통코드 등록');
                let txt = '';
                txt+='<li><p>그룹코드 ID</p><input type="text" readonly maxlength="4" name="groupCodeId" value="'+$(this).attr('data-id')+'"/></li>';
                txt+='<li><p>공통코드ID</p><input type="text"maxlength="4" name="commonCodeId" required/></li>';
                txt+='<li><p>공통코드 명</p><input type="text" maxlength="10" name="commonCodeNm" required/></li>';
                $('#popup .form-list').append(txt);
                $('#popup form button').attr('type','button').addClass('btnCommonSubmit');
                $('#popup').show();
            })

            $('#popup h1 a').on('click', function(){
                $('#popup .form-list li').remove();
                $('#popup').hide();
            });

            // 공통 코드 팝언 안 등록버튼 클릭시
            $('#popup').on('click','.btnCommonSubmit', function(){
                if($('input[name="commonCodeId"]').val() == '') { return false; }
                if($('input[name="commonCodeNm"]').val() == '') { return false; }
                CodeList.setCommonCode($(this).parents('form').find('input[name="groupCodeId"]').val());
            })
        }
        ,setCommonCode : function(id){
            let formData = $('#popup form').serialize();
            $.ajax({
               url: '/code/setCommonCode.do'
               ,type: 'post'
               ,dataType: 'json'
               ,data: formData
               ,success: function(res){
                  if(res.status == 'error'){
                      alert("에러지롱")
                  }else {
                      $('#popup h1 > a').click();
                      CodeList.groupAax(id);
                  }
               }
            });
        }
        ,groupAax: function(id){
            // 공통코드 조회
             $.ajax({
                 url:'/code/ajaxGroupCode.do'
                 ,type: 'post'
                 ,data: {id: id}
                 ,success: function(data){
                    // 공통코드 영역 내용 삭제
                    $('#commonCodeList table tbody').find('tr, td').remove();
                    // 주입
                    let item = JSON.parse(data);
                    let txt = '';

                    if(item.length != 0){
                        for(let i=0;i<item.length;i++){
                            txt += '<tr>';
                            txt += '<td>'+ item[i].commonCodeId +'</td>';
                            txt += '<td>'+ item[i].commonCodeNm +'</td>';
                            txt += '<td>'+ item[i].groupCodeId +'</td>';
                            txt += '<td>'+ item[i].groupCodeNm +'</td>';
                            txt += '</tr>';
                        }
                    }else{  // nodata
                        txt += '<tr>';
                        txt += '<td colspan="4">nodata</td>';
                        txt += '</tr>';
                    }
                    $('#commonCodeList table tbody').append(txt);
                 }
                 ,complete : function(){
                     $('#commonCodeList #registBtn').attr('data-id',id).show();
                 }
             })
        }
}
</script>
<div class="box" id="groupCodeTable">
    <p>
        <button type="button" id="registBtn">등록</button>
    </p>
    <table>
        <caption>그룹코드</caption>
        <colgroup>
            <col style="width: 50%"/>
            <col style="width: 50%"/>
        </colgroup>
        <thead>
            <tr>
                <th>그룹코드ID</th>
                <th>그룹코드명</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="group" items="${groupList}">
                <tr data-id="${group.groupCodeId}">
                    <td>${group.groupCodeId}</td>
                    <td>${group.groupCodeNm}</td>
                </tr>
            </c:forEach>
        </thead>
    </table>
</div>
<div id="commonCodeList" class="box">
    <p>
        <button type="button" id="registBtn">등록</button>
    </p>
    <table>
        <caption>공통코드</caption>
        <colgroup>
            <col style="width: 25%"/>
            <col style="width: 25%"/>
            <col style="width: 25%"/>
            <col style="width: 25%"/>
        </colgroup>
        <thead>
            <tr>
                <th>공토코드ID</th>
                <th>공통코드명</th>
                <th>그룹코드ID</th>
                <th>그룹코드명</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td colspan="4">nodata</td>
            </tr>
        </tbody>
    </table>
</div>

<!-- popup -->
<div id="popup">
    <div class="pop-inner">
        <h1>
            <p></p>
            <a href="javascript:void(0)">X</a>
        </h1>
        <form method="post">
            <ul class="form-list">
            </ul>
            <button type="submit">등록</button>
        </form>
    </div>
</div>

<script>
$(function(){
    CodeList.init();
});
</script>