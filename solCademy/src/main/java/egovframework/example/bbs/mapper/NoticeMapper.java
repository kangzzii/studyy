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
    List<NoticeVo> selectList();
}
