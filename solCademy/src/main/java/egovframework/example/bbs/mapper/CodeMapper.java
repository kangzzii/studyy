/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: CodeMapper.java
* @author		: kkang
* @date			: 2025.04.09
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.09        kkang       최초 생성
*/
package egovframework.example.bbs.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.bbs.vo.CodeCommonVo;
import egovframework.example.bbs.vo.CodeGroupVo;

@Mapper
public interface CodeMapper {

    /**
    * @methodName	: selectGroupList
    * @author		: kkang
    * @desc			: 그룹코드 리스트 출력
    * @date			: 2025.04.09
    * @return
    */
    List<CodeGroupVo> selectGroupList();

    /**
    * @methodName	: selectCommonList
    * @author		: kkang
    * @desc			: ajax 공통코드 출력
    * @date			: 2025.04.09
    * @param id
    * @return
    */
    List<CodeCommonVo> selectCommonList(String id);
    /**
    * @methodName	: insertGroupCod
    * @author		: kkang
    * @desc			: 그룹코드 등록
    * @date			: 2025.04.09
    * @param codeGroupVo
    */
    void insertGroupCode(CodeGroupVo codeGroupVo);

    /**
    * @methodName	: insertCommonCode
    * @author		: kkang
    * @desc			: 공통코드 등록
    * @date			: 2025.04.10
    * @param codeCommonVo
    */
    void insertCommonCode(CodeCommonVo codeCommonVo);

    /**
    * @methodName	: countCommonCode
    * @author		: kkang
    * @desc			: 공통코드 등록시 아이디 체크
    * @date			: 2025.04.10
    * @param codeCommonVo
    * @return
    */
    int countCommonCode(CodeCommonVo codeCommonVo);
}
