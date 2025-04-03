<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    table{ margin: 20px auto; border: 1px solid #ddd; }
    th { border: 1px solid #ddd; background: #f1f1f1; height: 30px; line-height: 30px;  }
    td { border: 1px solid #ddd; text-align: center; height: 30px; line-height: 30px; }
</style>
<div>
    <form action="/categories/form.do" method="post">
        <c:if test="${result.category_id == null}">
            <input type="hidden" name="category_id" id="category_id" value="0">
        </c:if>
        <c:if test="${result.category_id != null}">
            <input type="hidden" name="category_id" id="category_id" value="${result.category_id}">
        </c:if>
        <table>
            <colgroup>
                <col style="width: 30%"/>
                <col style="width: 70%"/>
            </colgroup>
            <tbody>
                <tr>
                    <th scope="col">카테고리</th>
                    <td scope="col">
                        <input type="text" name="category_nm" value="${result.category_nm}">
                    </td>
                </tr>
            </tbody>
        </table>
        <button type="button" onclick="location.href='/categories/delete/${result.category_id}.do'">삭제</button>
        <button type="submit" id="btnSubmit">수정</button>
        <button type="button"  onclick="location.href='/categories.do'">목록</button>
    </form>
</div>
<script>
    $(function(){
        if($('#category_id').val() == 0){
            $('#btnSubmit').text('등록')
        }
    })
</script>