<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
$(function(){
    // 전체 리스트
    var treeList = ${treeList};
    // 1뎁스 리스트
    var depth1arr = treeList.filter(i => i.menuDepth1 == 0);
    for (let i=0; i<depth1arr.length; i++){
        if(depth1arr[i].menuUseYn == 'Y'){
         // 2뎁스 그리기
            let depth1Id = depth1arr[i].menuId;
            let depth2Txt = makeDepth2(depth1Id);
            // 1뎁스 그리는거
            if(depth1arr[i].menuUrl == '') {
                depth1arr[i].menuUrl = '#'
            }
            $('.treemenu > ul').append('<li><a href="'+depth1arr[i].menuUrl+'">'+ depth1arr[i].menuNm + depth2Txt +'</a></li>')
        }
    }

    // 2뎁스 리스트 만드는 function
    function makeDepth2 (indx){
        let depth2Arr = treeList.filter(i => i.menuDepth1 == indx && i.menuDepth2 == 0);
        let txt = '';
        txt += '<ul>'

        for(let i=0; i<depth2Arr.length; i++){
            if(depth2Arr[i].menuUseYn == 'Y'){
                // 3뎁스 값
                let depth2Id = depth2Arr[i].menuId;
                let depth3Txt = makeDepth3(indx,depth2Id);
                // 2뎁스 그리기
                if(depth2Arr[i].menuUrl == '') {
                    depth2Arr[i].menuUrl = '#'
                }
                txt += '<li><a href="'+depth2Arr[i].menuUrl+'">'+ depth2Arr[i].menuNm + depth3Txt +'</a></li>';
            }
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
            if(depth3Arr[i].menuUseYn == 'Y'){
                if(depth3Arr[i].menuUrl == '') {
                    depth3Arr[i].menuUrl = '#'
                }
                txt += '<li><a href="'+depth3Arr[i].menuUrl+'">'+depth3Arr[i].menuNm+'</a></li>';
            }
        }
        txt += '</ul>'
        return txt;
    }

})

</script>
<nav class="treemenu">
	<ul>
        <li><a href="/code/list.do">코드관리</a></li>

	</ul>
</nav>