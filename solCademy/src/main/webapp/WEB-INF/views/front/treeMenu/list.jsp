<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style>
        th,td { border: 1px solid #000; padding: 10px; }
        th { background: #ddd; }
        .treemenu {
            display: inline-block;
            background: #f1f1f1;
            padding: 20px;
            height: 100%;
            min-height: 500px;
        }
    </style>
    <div style="display: flex; gap: 0 20px;">
        <div class="treemenu">
            <ul>
            </ul>
        </div>
        <table>
            <colgroup>
                <col style="width: 100px" /> <!-- 메뉴 아이디 -->
                <col style="width: 100px" /> <!-- 이름 -->
                <col style="width: 150px" /> <!-- 설명 -->
                <col style="width: 100px" /> <!-- depth1 -->
                <col style="width: 100px" /> <!-- depth2 -->
                <col style="width: 100px" /> <!-- depth3 -->
                <col style="width: 100px" /> <!-- url -->
                <col style="width: 100px" /> <!-- popup -->
                <col style="width: 100px" /> <!-- user Y/N -->
            </colgroup>
            <thead>
              <tr>
                <th scope="col">메뉴 아이디</th>
                <th scope="col">이름</th>
                <th scope="col">설명</th>
                <th scope="col">depth1</th>
                <th scope="col">depth2</th>
                <th scope="col">depth3</th>
                <th scope="col">url</th>
                <th scope="col">popup</th>
                <th scope="col">user Y/N</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach var="result" items="${resultList}">
              <tr onclick="location.href='/system/menu/form/${result.menuId}.do'">
                <td>${result.menuId}</td>
                <td>${result.menuNm}</td>
                <td>${result.menuDesc}</td>
                <td>${result.menuDepth1}</td>
                <td>${result.menuDepth2}</td>
                <td>${result.menuDepth3}</td>
                <td>${result.menuUrl}</td>
                <td>${result.menuPopYn}</td>
                <td>${result.useYn}</td>
              </tr>
            </c:forEach>
                <tr>
                <td colspan="9"><button type="button" onclick="location.href='/system/menu/form.do'">등록</button></td>
                </tr>
            </tbody>
        </table>
    </div>