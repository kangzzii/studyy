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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.example.bbs.mapper.NoticeMapper;
import egovframework.example.bbs.service.NoticeService;
import egovframework.example.bbs.vo.NoticeVo;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    Logger log = LoggerFactory.getLogger(getClass());

    /**
     *  공지사항 리스트
     */
    @Override
    public List<NoticeVo> getList(int numb){
        return noticeMapper.selectList(numb);
    }

    /**
     *  글 상세 불러오기(수정페이지)
     */
    @Override
    public Map<String, Object> getForm(int id){
        noticeMapper.updateHit(id);
        return noticeMapper.selectData(id);
    }

    /**
     *  등록
     */
    @Override
    public void registForm(Map<String, Object> param){
        //noticeMapper.insertForm(param);
    }
    /**
     * 수정
     */
    @Override
    public void updateForm(Map<String, Object> param) {
        param.put("noticeId", Integer.parseInt((String)param.get("noticeId")));
        noticeMapper.updateForm(param);
    }




}
