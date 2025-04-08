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
        .treemenu > ul > li { list-style: circle; }
        .treemenu > ul > li > ul,
        .treemenu > ul > li > ul  > li > ul  { margin-left: 20px; list-style: disc;}
        .treemenu li { margin-top: 10px; }
    </style>
    <script>
    $(function(){

        // 전체 리스트
        var treeList = ${treeList};
        // 1뎁스 리스트
        var depth1arr = treeList.filter(i => i.menuDepth1 == 0);
        for (let i=0; i<depth1arr.length; i++){
            // 2뎁스 그리기
            let depth1Id = depth1arr[i].menuId;
            let depth2Txt = makeDepth2(depth1Id);
            // 1뎁스 그리는거
            $('.treemenu > ul').append('<li>'+ depth1arr[i].menuNm + depth2Txt +'</li>')

        }

        // 2뎁스 리스트 만드는 function
        function makeDepth2 (indx){
            let depth2Arr = treeList.filter(i => i.menuDepth1 == indx && i.menuDepth2 == 0);
            let txt = '';
            txt += '<ul>'
            for(let i=0; i<depth2Arr.length; i++){
                // 3뎁스 값
                let depth2Id = depth2Arr[i].menuId;
                let depth3Txt = makeDepth3(indx,depth2Id);
                // 2뎁스 그리기
                txt += '<li>'+ depth2Arr[i].menuNm + depth3Txt +'</li>';
            }
            txt += '</ul>'
            return txt;
        }

        // 3뎁스 리스트 만드는 function
        function makeDepth3 (indx, depth2Id){
            let depth3Arr = treeList.filter(i => i.menuDepth1 == indx && i.menuDepth2 == depth2Id && i.menuDepth3 == 0);

            let txt = '';
            txt += '<ul>'
            for(let i=0; i<depth3Arr.length; i++){
                txt += '<li>'+depth3Arr[i].menuNm+'</li>';
            }
            txt += '</ul>'
            return txt;
        }

    })

    </script>
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
              <tr onclick="location.href='/menu/form/${result.menuId}.do'">
                <td>${result.menuId}</td>
                <td>${result.menuNm}</td>
                <td>${result.menuDesc}</td>
                <td>${result.menuDepth1}</td>
                <td>${result.menuDepth2}</td>
                <td>${result.menuDepth3}</td>
                <td>${result.menuUrl}</td>
                <td>${result.menuPopYn}</td>
                <td>${result.menuUseYn}</td>
              </tr>
            </c:forEach>
                <tr>
                <td colspan="9"><button type="button" onclick="location.href='/menu/form.do'">등록</button></td>
                </tr>
            </tbody>
        </table>
    </div>