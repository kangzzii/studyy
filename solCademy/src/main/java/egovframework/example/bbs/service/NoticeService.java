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

import egovframework.example.bbs.vo.NoticeVo;

public interface NoticeService {

    /**
    * @methodName	: getList
    * @author		: kkang
    * @desc			: 공지사항 리스트
    * @date			: 2025.04.14
    * @return
    */
    List<NoticeVo> getList();
}
