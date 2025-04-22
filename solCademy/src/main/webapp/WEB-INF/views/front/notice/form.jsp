<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- uploader -->
<script type="text/javascript" src="/xFreeUploader/js/xFreeUploader.js"></script>
<link href='/resource/SynapEditorPackage/SynapEditor/synapeditor.min.css' rel='stylesheet' type='text/css'>
<!-- <script src='/resource/SynapEditor/synapeditor.config.js'></script> -->
<script src='/resource/SynapEditorPackage/SynapEditor/synapeditor.min.js'></script>
<script src="/resource/SynapEditorPackage/SynapEditor/externals/SEDocModelParser/SEDocModelParser.min.js"></script>
<script src="/resource/SynapEditorPackage/SynapEditor/externals/SEShapeManager/SEShapeManager.min.js"></script>

<style>
    table{ width: 800px; border: 1px solid #ddd; table-layout: fixed; }
    th { border: 1px solid #ddd; background: #f1f1f1; padding: 5px 10px; }
    td { border: 1px solid #ddd; text-align: center;  padding: 5px 10px; }
    input,
    select { width: 100%; border: 1px solid #ddd; padding: 10px; }
</style>
<script>
var formData = {
        init: function(){
            // 업로드, 에디터
            formData.uploader();
            formData.editor();
            // event
            formData.event();
            // edit
            if($('#noticeIdHidden').val() != ''){
                $('.btnSubmit').text('수정');
                formData.initEdit();
            }
        }
        ,event : function(){
            $('.btnSubmit').click(function(){
                formData.wFile.fileSelectedSubmit();
            })
        }
        ,initEdit: function(){
            let dispYnVal= $('#noticeDispYnHidden').val();
            let ynVal= $('#noticeYnHidden').val();
            let useYnVal= $('#useYnHidden').val();
            let typeVal = $('#noticeTypeHidden').val();
            $('#noticeType option').each(function(){
                if($(this).val()== typeVal) {
                    $(this).prop('selected',true);
                }
            })
            $('#noticeDispYn option').each(function(){
                if($(this).val()== dispYnVal) {
                    $(this).prop('selected',true);
                }
            })
             $('#noticeYn option').each(function(){
                if($(this).val()== ynVal) {
                    $(this).prop('selected',true);
                }
            })
             $('#useYn option').each(function(){
                if($(this).val()== useYnVal) {
                    $(this).prop('selected',true);
                }
            })
        }
        ,register : function(){
            $('#formData').attr('action', '/bbs/notice/form.do').submit();
        }
        ,uploadInput: function(data){
            let txt = '';
            for(let i=0;i<data.length;i++) {
                txt += '<input type="hidden" name="attachFileList['+i+'].attachFileName" value="'+data[i].fileName+'" />';
                txt += '<input type="hidden" name="attachFileList['+i+'].attachFileOriName" value="'+data[i].fileReName+'" />';
                txt += '<input type="hidden" name="attachFileList['+i+'].attachFileSize" value="'+data[i].fileSize+'" />';
                txt += '<input type="hidden" name="attachFileList['+i+'].attachFilePath" value="'+data[i].savePath+'" />';
                txt += '<input type="hidden" name="attachFileList['+i+'].useYn" value="Y" />';
                txt += '<input type="hidden" name="attachFileList['+i+'].bbsCodeId" value="'+$('#noticeType').val()+'" />';
            }
            $('#formData').append(txt);
            formData.register();    // form submit
        }
        ,wFile : null
        ,uploader: function(){
            this.wFile = new xFreeUploader({
                render: 'fileUpload'
                ,selectMode: 'hybrid'
                ,basePath: '/xFreeUploader'
                ,uploadUrl: '/tagfree/xfuUpload.do'
                ,onBeforeSubmit: function (data) {
                    console.log("파일전송하기전");
                }
                // 업로드 정상 콜백
                ,onAllSuccessCallback: function (data) {
                    console.log("업로드 정상 콜백");
                    console.log(data)
                    formData.uploadInput(data);
                }
            })
        }
        ,editor : function(){
            var synapEditorConfig = {
                'editor.license': '/resource/SynapEditorPackage/SynapEditor/license.json'
                ,'editor.size.width': '100%'
                ,'editor.size.height': '100%'
                ,'editor.lang.default': 'ko'
                ,'editor.menu.show': false
                ,'editor.toolbar': [
                    'new', 'open', 'template', 'layout', '|',
                    'contentsProperties', '|',
                    'undo', 'redo', '|',
                    'copy', 'cut', 'paste', '|',
                    'link', 'unlink', 'bookmark', '|',
                    'image', 'background', 'video', 'file', '|',
                    'table', 'div', 'horizontalLine', 'quote', '|',
                    'specialCharacter', 'emoji', '-',
                    'paragraphStyleWithText', '|',
                    'fontFamilyWithText', '|',
                    'fontSizeWithText', '|',
                    'bold', 'italic', 'underline', 'strike', '|',
                    'growFont', 'shrinkFont', '|',
                    'fontColor', 'fontBackgroundColor', '|',
                    'bulletList', 'numberedList', 'multiLevelList', '|',
                    'align', '|',
                    'lineHeight', '|',
                    'decreaseIndent', 'increaseIndent'
                ]
                ,'editor.upload.image.api': "/uploadFile.do"  // 이미지 업로드
                ,'editor.upload.image.preview': true
                ,'editor.import.api': "/importDoc.do"  // 문서 불러오기
            }
            var se = new SynapEditor("synapEditor", synapEditorConfig);
        }
}
</script>
    <!-- 수정 데이터 -->
    <input type="hidden" name="noticeTypeHidden" id="noticeTypeHidden" value="${result.notice_type}">
    <input type="hidden" name="noticeDispYnHidden" id="noticeDispYnHidden" value="${result.notice_disp_yn}">
    <input type="hidden" name="noticeYnHidden" id="noticeYnHidden" value="${result.notice_disp_yn}">
    <input type="hidden" name="useYnHidden" id="useYnHidden" value="${result.notice_use_yn}">

    <!-- 등록 수정 데이터 -->
    <form id="formData" method="post" enctype="multipart/form-data">
        <c:if test="not empty ${result.notice_id}">
            <input type="hidden" name="noticeId" id=noticeIdHidden value="${result.notice_id}">
        </c:if>
        <table>
        <colgroup>
            <col style="width: 100px;"/>
            <col style="width: auto"/>
            <col style="width: 100px;"/>
            <col style="width: auto"/>
            <col style="width: 100px;"/>
            <col style="width: auto"/>
        </colgroup>
        <tbody>
            <tr>
                <th>제목</th>
                <td scope="col" colspan="5">
                    <input type="text" name="noticeTitle" id="noticeTitle" value="${result.notice_title}">
                </td>
            </tr>

            <tr>
                <th>공지사항<br/>타입</th>
                <c:choose>
                    <c:when test="${result.notice_id != null}">
                    <!-- 수정 -->
                    <td colspan="3">
                        <select name="noticeType" id="noticeType">
                            <option value="G001C001">G001C001</option>
                            <option value="G001C002">G001C002</option>
                            <option value="G001C003">G001C003</option>
                        </select>
                    </td>
                    <th>조회수</th>
                    <td>${result.notice_inq_cnt}</td>
                    </c:when>
                    <c:otherwise>
                        <!-- 등록 -->
                        <td colspan="5">
                            <select name="noticeType" id="noticeType">
                                <option value="G001C001">G001C001</option>
                                <option value="G001C002">G001C002</option>
                                <option value="G001C003">G001C003</option>
                            </select>
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
            <tr>
                <th scope="col">공지사항<br/>게시여부</th>
                <td scope="col">
                    <select name="noticeDispYn" id="noticeDispYn">
                        <option value="Y">사용</option>
                        <option value="N">미사용</option>
                    </select>
                </td>
                <th scope="col">공지사항<br/>공지여부</th>
                <td scope="col">
                    <select name="noticeYn" id="noticeYn">
                        <option value="Y">사용</option>
                        <option value="N">미사용</option>
                    </select>
                </td>
                <th scope="col">공지사항<br/>사용여부</th>
                <td scope="col">
                    <select name="useYn" id="useYn">
                        <option value="Y">사용</option>
                        <option value="N">미사용</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="5">
                    <div class="xFreeUploader-pnl" id="fileUpload"></div>
                </td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="5">
                    <div style="height:700px;">
                        <textarea id="synapEditor" name="noticeContents">
                            ${result.notice_contents}
                        </textarea>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
        <button type="button" class="btnSubmit">등록</button>
    </form>
<script>
$(function(){
    formData.init();
})
</script>