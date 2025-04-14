/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: UserService.java
* @author		: kkang
* @date			: 2025.04.11
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.11        kkang       최초 생성
*/
package egovframework.example.login.service;

import egovframework.example.login.vo.UserVo;

public interface UserService {
    /**
    * @methodName	: registSignup
    * @author		: kkang
    * @desc			: 회원가입
    * @date			: 2025.04.11
    * @param userVo
    */
    void registSignup(UserVo userVo);

    /**
    * @methodName	: isDuplicateId
    * @author		: kkang
    * @desc			: 아이디 중복 여부 확인
    * @date			: 2025.04.11
    * @param id
    * @return
    */
    int isDuplicateId(String id);

    /**
    * @methodName	: doLogin
    * @author		: kkang
    * @desc			: 로그인
    * @date			: 2025.04.14
    * @param userVo
    * @return
    */
    String doLogin(UserVo userVo);
}
