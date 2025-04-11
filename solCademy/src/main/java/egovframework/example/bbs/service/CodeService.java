/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: CodeService.java
* @author		: kkang
* @date			: 2025.04.09
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.09        kkang       최초 생성
*/
package egovframework.example.bbs.service;

import java.util.List;

import egovframework.example.bbs.vo.CodeCommonVo;
import egovframework.example.bbs.vo.CodeGroupVo;

public interface CodeService {

    /**
    * @methodName	: getGroupList
    * @author		: kkang
    * @desc			: 그룹코드 리스트 출력
    * @date			: 2025.04.09
    * @return
    */
    List<CodeGroupVo> getGroupList();

    /**
    * @methodName	: getCommonList
    * @author		: kkang
    * @desc			: ajax 공통코드 출력
    * @date			: 2025.04.09
    * @return
    */
    List<CodeCommonVo> getCommonList(String id);

    /**
    * @methodName	: registGroupCode
    * @author		: kkang
    * @desc			: 그룹 코드 등록
    * @date			: 2025.04.09
    * @param codeGroupVo
    */
    void registGroupCode(CodeGroupVo codeGroupVo);

    /**
    * @methodName	: registCommonCode
    * @author		: kkang
    * @desc			: 공통코드 등록
    * @date			: 2025.04.10
    * @param codeCommonVo
    */
    void registCommonCode(CodeCommonVo codeCommonVo);

    /**
    * @methodName	: isDuplicate
    * @author		: kkang
    * @desc			: 공통코드 등록시 아이디 체크
    * @date			: 2025.04.10
    * @param codeCommonVo
    * @return
    */
    int isDuplicate(CodeCommonVo codeCommonVo);
}
