/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: UserServiceImpl.java
* @author		: kkang
* @date			: 2025.04.11
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.11        kkang       최초 생성
*/
package egovframework.example.login.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import egovframework.example.login.mapper.UserMapper;
import egovframework.example.login.service.UserService;
import egovframework.example.login.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {

    Logger log = LoggerFactory.getLogger(getClass());

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    UserMapper userMapper;

    /**
     *  회원가입
     */
    @Override
    public void registSignup(UserVo userVo) {
        userMapper.insertSignup(userVo);
    }

    /**
     *  아이디 중복확인
     */
    @Override
    public int isDuplicateId(String id) {
        return userMapper.selectFindId(id);
    }

    /**
     *  로그인
     */

    // 1트 -> failCnt : 0
    // 2트 -> failCnt : 1
    // 3트 -> failCnt : 2

    @Override
    public String doLogin(UserVo userVo) {
        String userId = userVo.getUserId();
        String userPw = userVo.getUserPw();
        Map<String, ?> userInfo = userMapper.selectLoginUserInfo(userId);
        int failCnt = (int) userInfo.get("login_fail_cnt");
        if(userInfo != null && failCnt <=2) { // 해당 아이디가 있을때
            if(!encoder.matches(userPw, String.valueOf(userInfo.get("user_pw")))) {
                // 비밀번호가 틀렸을때
                userMapper.updatefailLoginCnt(userId);  // 로그인 실패 이력 +1
                if(failCnt == 2) {  // 3회차
                    return "loginFailFullCnt";
                } else {    // 일반회차
                    return "error";
                }
            } else {    // 로그인 성공
                userMapper.updateLastLoginDate(userId); // 최근 접속 로그인 일자 업데이트
                return "success";
            }
        } else if( failCnt > 2) {
            return "loginFailFullCnt";  // 비밀번호 3회 이상 오류
        } else {    // 아이디 없음 에러!
            return "error";
        }
    }


}
