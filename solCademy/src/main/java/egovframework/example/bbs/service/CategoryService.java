/**
* @packageName  : 중소기업기술마켓 정식플랫폼 구축
* @fileName     : CategoryService.java
* @author       : kkang
* @date         : 2025.04.01
* @description  :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.01        kkang       최초 생성
*/
package egovframework.example.bbs.service;

import java.util.List;

import egovframework.example.bbs.vo.CategoryVo;

public interface CategoryService {
    // 리스트 조회
    List<?> getList(CategoryVo categoryVo);
    
    // 상세 조회
    CategoryVo getDetail(int id);
    
    // 수정
    void modifyDetail(CategoryVo categoryVo);
    
    // 삭제
    void deleteDetail(int id);
    
    // 등록
    void registerDetail(String Category_nm);
}
