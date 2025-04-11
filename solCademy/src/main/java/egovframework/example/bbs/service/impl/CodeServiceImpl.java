/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: CodeServiceImpl.java
* @author		: kkang
* @date			: 2025.04.09
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.09        kkang       최초 생성
*/
package egovframework.example.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import egovframework.example.bbs.mapper.CodeMapper;
import egovframework.example.bbs.service.CodeService;
import egovframework.example.bbs.vo.CodeCommonVo;
import egovframework.example.bbs.vo.CodeGroupVo;

@Service
public class CodeServiceImpl implements CodeService{

    @Autowired
    CodeMapper codeMapper;

    /**
     *  그룹코드 리스트 출력
     */
    @Override
    public List<CodeGroupVo> getGroupList(){
        return codeMapper.selectGroupList();
    }

    /**
     * ajax 공통코드 출력
     */
    @Override
    public List<CodeCommonVo> getCommonList(String id) {
        return codeMapper.selectCommonList(id);
    }

    /**
     *  그룹코드 등록
     */
    @Override
    public void registGroupCode(CodeGroupVo codeGroupVo) {
        codeMapper.insertGroupCode(codeGroupVo);
    }

    /**
     *  공통코드 등록
     */
    @Override
    public void registCommonCode(CodeCommonVo codeCommonVo) {
        codeMapper.insertCommonCode(codeCommonVo);
    }

    /**
     *  공통코드 등록시 아이디 체크
     */
    @Override
    public int isDuplicate(CodeCommonVo codeCommonVo) {
       return codeMapper.countCommonCode(codeCommonVo);
    }
}
