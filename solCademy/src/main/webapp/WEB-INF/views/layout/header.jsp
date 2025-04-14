<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<header>
    <c:if test="${sessionScope.userId == null}">
        <a href="/loginForm.do">로그인</a>
    </c:if>
    <c:if test="${sessionScope.userId != null}">
        <p>${sessionScope.userId} 님 </p>
        <a href="/logout.do">로그아웃</a>
    </c:if>
	<a href="javascript:void(0)" class="btnSidebar">사이드바 나오기</a>
</header>