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
    @Override
    public String doLogin(UserVo userVo) {
        String userId = userVo.getUserId();
        String userPw = userVo.getUserPw();
        Map<String, String> userInfo = userMapper.selectLoginUserInfo(userId);
        if(userInfo != null &&  encoder.matches(userPw, userInfo.get("user_pw"))) {
            return "success";
        } else {
            return "error";
        }
    }


}
