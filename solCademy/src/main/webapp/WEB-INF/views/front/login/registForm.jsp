<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
var signUp = {
        init: function(){
            signUp.event();
        }
        ,event: function(){
            // 회원가입 버튼 이벤트
            $('.btnSubmit').on('click',function(){
                if( $('input[name="userNm"]').val() == '' ){
                    alert("이름 입력")
                    return false;
                } else if( $('input[name="userId"]').val() == '' ){
                    alert("아이디 입력")
                    return false;
                } else if( $('input[name="userPw"]').val() == '' ){
                    alert("비밀번호 입력")
                    return false;
                } else {
                    signUp.regist();
                }
            });
        }
        ,regist: function(){
            let formData = $('#userRegistForm').serialize();
            //console.log(formData);
             $.ajax({
                url: '/registSignup.do'
               ,type : 'POST'
               ,dataType: 'json'
               ,data:formData
               ,success: function(res){
                   if(res.res == 'success') {
                       let alertMsg;
                       alertMsg = alert("로그인페이지로 이동합니다");
                       if(alertMsg = true) {
                           location.href='/loginForm.do';
                       }
                   } else {
                       alert("에러지롱");
                   }
               }
            });
        }
}
</script>
<div class="login-box">
    <form id="userRegistForm">
        <ul>
            <li>회원가입</li>
            <li><p class="label">이름</p><input type="text" name="userNm"/></li>
            <li><p class="label">ID</p><input type="text" name="userId"/></li>
            <li><p class="label">PW</p><input type="password" name="userPw"/></li>
            <li>
                <p class="label">사용여부</p>
                <select name="useYn">
                    <option value="Y">사용</option>
                    <option value="N">미사용</option>
                </select>
            </li>
            <li><a href="javascript:void(0)" class="btnSubmit">회원가입</a></li>
        </ul>
    </form>
</div>
<script>
$(function(){
    signUp.init();
})
</script>