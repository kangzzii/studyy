/**
* @packageName  : 중소기업기술마켓 정식플랫폼 구축
* @fileName     : CategoryMapper.java
* @author       : kkang
* @date         : 2025.04.01
* @description  :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.01        kkang       최초 생성
*/
package egovframework.example.bbs.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.bbs.vo.CategoryVo;

@Mapper
public interface CategoryMapper {
    // 리스트 조회
    List<?> selectList(CategoryVo categoryVo);

    // 상세 조회
    CategoryVo selectDetail(int id);
    
    // 수정
    void updateDetail(CategoryVo categoryVo);
    
    // 삭제
    void deleteDetail(int id);
    
    // 등록
    void inserDetail(String Category_nm);
}
