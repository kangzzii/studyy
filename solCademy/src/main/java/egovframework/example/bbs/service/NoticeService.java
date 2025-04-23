/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: NoticeService.java
* @author		: kkang
* @date			: 2025.04.14
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.14        kkang       최초 생성
*/
package egovframework.example.bbs.service;

import java.util.List;
import java.util.Map;

import egovframework.example.bbs.vo.NoticeVo;

public interface NoticeService {

    /**
    * @methodName	: getList
    * @author		: kkang
    * @desc			: 공지사항 리스트
    * @date			: 2025.04.14
    * @return
    */
    List<NoticeVo> getList(int numb);

    /**
    * @methodName	: getForm
    * @author		: kkang
    * @desc			: 글 상세 불러오기(수정페이지)
    * @date			: 2025.04.15
    * @param id
    * @return
    */
    NoticeVo getForm(int id);

    /**
    * @methodName	: registForm
    * @author		: kkang
    * @desc			: 등록
    * @date			: 2025.04.15
    * @param param
    */
    void registForm(NoticeVo noticeVo);
    /**
    * @methodName	: updateForm
    * @author		: kkang
    * @desc			: 수정
    * @date			: 2025.04.15
    * @param param
    */
    void updateForm(int id, NoticeVo noticeVo);

}
