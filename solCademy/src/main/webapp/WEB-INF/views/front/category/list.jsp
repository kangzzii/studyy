<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    table{ width: 800px; height: 400px; border: 1px solid #ddd; }
    th { border: 1px solid #ddd; background: #f1f1f1; height: 30px; line-height: 30px;  }
    td { border: 1px solid #ddd; text-align: center; height: 30px; line-height: 30px; }
</style>
<div>
    <table>
        <colgroup>
            <col style="width: 30%"/>
            <col style="width: 70%"/>
        </colgroup>
        <thead>
            <tr>
                <th scope="col">번호</th>
                <th scope="col">제목</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="result" items="${result}">
            <tr >
                <td>${result.category_id}</td>
                <td><a href="/categories/${result.category_id}.do">${result.category_nm}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button type="button" onclick="location.href='/categories/form.do'">등록</button>
</div>