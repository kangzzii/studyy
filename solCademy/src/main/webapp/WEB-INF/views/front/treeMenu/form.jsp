<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    th,td { border: 1px solid #000; padding: 10px; }
    th { background: #ddd; }
    input, select { width: 100%; min-width: 150px; }
</style>
<script>
var menuForm = {
        init: function(){
            menuForm.event();
            // 수정일 경우
            if($('#editId').val() !=''){
                menuForm.edit();
            }
        }
        ,event : function(){
            // 단계1 선택시
            $('#menuDepth1').on('change', function(){
                let depth1Id = $(this).val();
                let target = $('#menuDepth2');
                if(depth1Id != 0){
                    menuForm.ajax(depth1Id, target)
                }
            });

             // 단계2 선택시
            $('#menuDepth2').on('change', function(){
                let depth1Id = $('#menuDepth1').val();
                let depth2Id = $(this).val();
                let target = $('#menuDepth3');
                menuForm.ajax(depth1Id, target, depth2Id)
            });
        }
        // ajax
        , ajax: function(depth1Id, target, depth2Id){
           if(depth2Id == null) {
               depth2Id = -1;
           }
           $.ajax({
                url: '/system/menu/getDepth.do'
                ,type: 'post'
                ,async: false
                ,data: {id1: depth1Id, id2: depth2Id}
                ,success: function(data){
                    // 첫번째 요소 빼고 제거
                    target.find('option').not(':first-child').remove();
                   if(data.length > 0){
                        for(let i=0;i<data.length;i++){
                            target.append('<option value="'+data[i].menuId+'">'+data[i].menuNm+'</option>')
                        }
                    }
                }
                ,complete : function(){
                    // 수정일때
                    if( $('#editId').val() != '' ){
                        let targetVal = $('#hid'+target[0].id+'').val();
                        target.val(targetVal).prop('selected', true);
                    }
                }
            })
        }
        // 수정일때
        ,edit : function(){
            let depth1Val = $('#hidmenuDepth1').val();
            let depth2Val = $('#hidmenuDepth2').val();
            let depth3Val = $('#hidmenuDepth3').val();
            let popYnVal = $('#hidmenuPopYn').val();
            let useYnVal = $('#hiduseYn').val();
            // 1뎁스 셋팅
            $('#menuDepth1').val(depth1Val).prop('selected', true);
            // 2뎁스 셋팅
            menuForm.ajax(depth1Val, $('#menuDepth2'));
            // 3뎁스 셋팅
            menuForm.ajax(depth1Val, $('#menuDepth3'), depth2Val);
            // popup Yn
            $('#menuPopYn').val(popYnVal).prop('selected', true);
            // use Yn
            $('#useYn').val(useYnVal).prop('selected', true);
            // btn 변경
            $('#btnSubmit').text('수정');

        }
        // input 체크
        ,menuAction: function(){
            if( $('#menuNm').val() == '' ){
                alert("메뉴명 입력하세요."); return false;
            } else {
                // 수정일때
                if($('#editId').val() !=''){
                    let elId = $('#editId').val();
                    $('#menuForm').attr('action','/system/menu/form/' + elId +'.do')
                }
                $('#menuForm').submit();
            }
        }
}
</script>
  <input type="hidden" id="editId" value="${result.menuId}">
  <input type="hidden" id="hidmenuDepth1" value="${result.menuDepth1}">
  <input type="hidden" id="hidmenuDepth2" value="${result.menuDepth2}">
  <input type="hidden" id="hidmenuDepth3" value="${result.menuDepth3}">
  <input type="hidden" id="hidmenuPopYn" value="${result.menuPopYn}">
  <input type="hidden" id="hiduseYn" value="${result.useYn}">
  <form id="menuForm" action="/menu/form.do" method="post">
    <table>
        <tr>
          <th>메뉴명</th>
          <td>
            <input type="text" id="menuNm" name="menuNm" value="${result.menuNm}">
          </td>
          <th>메뉴url</th>
          <td colspan="3">
            <input type="text" id="menuUrl" name="menuUrl" value="${result.menuUrl}">
          </td>
        </tr>
        <tr>
          <th>메뉴설명</th>
          <td colspan="5">
            <input type="text" id="menuDesc" name="menuDesc" value="${result.menuDesc}">
          </td>
        </tr>
        <tr>
          <th>단계1</th>
          <td>
            <select id="menuDepth1" name="menuDepth1">
                <option value="0">최상위</option>
                <c:forEach var="depth" items="${depth}">
                <option value="${depth.menuId}">${depth.menuNm}</option>
                </c:forEach>
            </select>
          </td>
          <th>단계2</th>
          <td>
            <select id="menuDepth2" name="menuDepth2">
                <option value="0">상위</option>
            </select>
          </td>
          <th>단계3</th>
          <td>
            <select id="menuDepth3" name="menuDepth3">
                <option value="0">상위</option>
            </select>
          </td>
        </tr>
        <tr>
          <th>순서</th>
          <td>
            <input type="text" id="menuOrder" name="menuOrder" value="${result.menuOrder}">
          </td>
          <th>팝업여부</th>
          <td>
            <select name="menuPopYn" id="menuPopYn">
              <option value="N">N</option>
              <option value="Y">Y</option>
            </select>
          </td>
          <th>사용여부</th>
          <td>
            <select name="useYn" id="useYn">
              <option value="N">N</option>
              <option value="Y">Y</option>
            </select>
          </td>
        </tr>
        <tr>
          <th>추가파라미터</th>
          <td colspan="5">
            <input type="text" name="menuParam" id="menuParam"  value="${result.menuParam}">
          </td>
        </tr>
      </table>
      <button type="button" onclick="location.href='/system/menu/list.do'">목록</button>
      <button type="button" id="btnSubmit" onclick="menuForm.menuAction()">등록</button>
  </form>

<script>
$(function(){
    menuForm.init();
})

</script>