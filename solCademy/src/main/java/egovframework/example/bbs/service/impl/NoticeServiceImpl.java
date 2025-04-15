/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: NoticeServiceImpl.java
* @author		: kkang
* @date			: 2025.04.14
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.14        kkang       최초 생성
*/
package egovframework.example.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.example.bbs.mapper.NoticeMapper;
import egovframework.example.bbs.service.NoticeService;
import egovframework.example.bbs.vo.NoticeVo;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    /**
     *  공지사항 리스트
     */
    public List<NoticeVo> getList(){
        return noticeMapper.selectList();
    }
}
