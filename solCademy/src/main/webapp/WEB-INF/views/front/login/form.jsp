<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
var login = {
        init: function(){
            login.event();
        }
        ,event : function(){
            $('.btnSubmit').on('click',function(){
                if( $('input[name="userNm"]').val() == '' ){
                    alert("아이디 입력")
                    return false;
                } else if( $('input[name="userPw"]').val() == '' ){
                    alert("비밀번호 입력")
                    return false;
                } else {
                    login.doLogin();
                }
            })
        }
        ,doLogin: function(){
            $.ajax({
                url: '/login.do'
                ,type: 'post'
                ,data:  $('#loginForm').serialize()
                ,dataType: 'json'
                ,success: function(res){
                    if(res.status == 'success'){
                        //성공
                        location.href='/main.do';
                    } else if (res.status == 'loginFailFullCnt'){
                        alert("3번 틀렸습니다, 비밀번호 재설정하숑")
                    } else {
                        alert("에러지롱")
                    }

                }
            })
           ;
        }
}
</script>
<div class="login-box">
    <form id="loginForm" method="post">
        <ul>
            <li>LOGIN</li>
            <li><p class="label">ID</p><input type="text" name="userId"/></li>
            <li><p class="label">PW</p><input type="password" name="userPw"/></li>
            <li><a href="javascript:void(0)"  class="btnSubmit">로그인</a></li>
            <li><a href="/signupForm.do">회원가입</a></li>
        </ul>
    </form>
</div>
<script>
$(function(){
   login.init();
});
</script>