<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    table{ width: 800px; border: 1px solid #ddd; }
    th { border: 1px solid #ddd; background: #f1f1f1; padding: 5px 10px; }
    td { border: 1px solid #ddd; text-align: center;  padding: 5px 10px; }
</style>
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
            </tr>
        </thead>
        <tbody>
        <c:forEach var="result" items="${resultList}">
            <tr>
                <td>${result.noticeId}</td>
                <td>${result.noticeType}</td>
                <td>${result.noticeTitle}</td>
                <td>${result.noticeInqCnt}</td>
                <td>${result.noticeDispYn}</td>
                <td>${result.noticeYn}</td>
                <td>${result.noticeUseYn}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="#">등록</a>
</div>