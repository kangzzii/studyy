/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: UserMapper.java
* @author		: kkang
* @date			: 2025.04.11
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.11        kkang       최초 생성
*/
package egovframework.example.login.mapper;

import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.login.vo.UserVo;

@Mapper
public interface UserMapper {
    /**
    * @methodName	: insertSignup
    * @author		: kkang
    * @desc			: 회원가입
    * @date			: 2025.04.11
    * @param userVo
    */
    void insertSignup(UserVo userVo);

    /**
    * @methodName	: selectFindId
    * @author		: kkang
    * @desc			: 아이디 중복 확인
    * @date			: 2025.04.11
    * @param id
    * @return
    */
    int selectFindId(String id);

    /**
    * @methodName	: selectLoginUserInfo
    * @author		: kkang
    * @desc			: 로그인 정보 조회
    * @date			: 2025.04.14
    * @param id
    * @return
    */
    Map<String, UserVo> selectLoginUserInfo(String id);

    /**
    * @methodName	: updateLastLoginDate
    * @author		: kkang
    * @desc			: 최근 접속 로그인 일자 업데이트
    * @date			: 2025.04.14
    * @param id
    */
    void updateLastLoginDate(String id);

    /**
    * @methodName	: updatefailLoginCnt
    * @author		: kkang
    * @desc			: 로그인 실패 카운트 업데이트
    * @date			: 2025.04.14
    * @param id
    */
    void updatefailLoginCnt(String id);
}
