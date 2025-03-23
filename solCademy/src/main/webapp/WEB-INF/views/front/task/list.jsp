<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	table{ margin: 20px auto; width: 800px; height: 400px; border: 1px solid #ddd; }
	th { border: 1px solid #ddd; background: #f1f1f1; height: 30px; line-height: 30px;  }
	td { border: 1px solid #ddd; text-align: center; height: 30px; line-height: 30px; }
</style>
<table>
    <colgroup>
        <col style="width: 16%"/>
        <col style="width: 16%"/>
        <col style="width: auto"/>
        <col style="width: 16%"/>
        <col style="width: 10%"/>
        <col style="width: 16%"/>
    </colgroup>
    <thead>
        <tr>
            <th scope="col">이름</th>
            <th scope="col">나이</th>
            <th scope="col">연락처</th>
            <th scope="col">국적</th>
            <th scope="col">성별</th>
            <th scope="col">사용여부</th>
        </tr>
    </thead>
    <tbody>
    	<c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${user.phone}</td>
            <td>${user.nation}</td>
            <td>
            	<c:if test="${user.gender == 'F'}">
            		여자
            	</c:if>
            	<c:if test="${user.gender == 'M'}">
            		남자
            	</c:if>
            </td>
            <td>
            	<c:choose>
            		<c:when test="${user.userYn == 'Y'}">사용</c:when>
            		<c:when test="${user.userYn == 'N'}">미사용</c:when>
            	</c:choose>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>