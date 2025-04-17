/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: NoticeMapper.java
* @author		: kkang
* @date			: 2025.04.14
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.14        kkang       최초 생성
*/
package egovframework.example.bbs.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.bbs.vo.NoticeVo;

@Mapper
public interface NoticeMapper {

    /**
    * @methodName	: selectList
    * @author		: kkang
    * @desc			: 공지사항 리스트
    * @date			: 2025.04.14
    * @return
    */
    List<NoticeVo> selectList(int numb);

    /**
    * @methodName	: updateHit
    * @author		: kkang
    * @desc			: 조회수 업데이트
    * @date			: 2025.04.15
    * @param id
    */
    void updateHit(int id);

    /**
    * @methodName	: selectData
    * @author		: kkang
    * @desc			: 글 상세 불러오기(수정페이지)
    * @date			: 2025.04.15
    * @param id
    * @return
    */
    Map<String, Object> selectData(int id);

    /**
    * @methodName	: insertForm
    * @author		: kkang
    * @desc			: 등록
    * @date			: 2025.04.15
    * @param param
    */
    void insertForm(Map<String, Object> param);

    void updateForm(Map<String, Object> param);
}
