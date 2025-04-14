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

    Map<String, String> selectLoginUserInfo(String id);
}
