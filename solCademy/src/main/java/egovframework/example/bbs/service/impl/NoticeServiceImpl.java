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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.example.bbs.mapper.NoticeMapper;
import egovframework.example.bbs.service.NoticeService;
import egovframework.example.bbs.vo.NoticeVo;
import egovframework.example.cmmn.mapper.AttachFileMapper;
import egovframework.example.cmmn.vo.AttachFileVo;

@Service
public class NoticeServiceImpl implements NoticeService {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    NoticeMapper noticeMapper;
    @Autowired
    AttachFileMapper attachFileMapper;

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
    public NoticeVo getForm(int id){
        noticeMapper.updateHit(id);
        List<AttachFileVo> fileList = attachFileMapper.selectFileList(id);
        NoticeVo noticeData = noticeMapper.selectData(id);
        noticeData.setAttachFileList(fileList);
//        log.info("noticeData-------------------{}",noticeData);
        return noticeData;
    }

    /**
     *  등록
     */
    @Override
    public void registForm(NoticeVo noticeVo){
        noticeMapper.insertForm(noticeVo);
        int generatedId = noticeVo.getNoticeId();
        String cretUserId = noticeVo.getCretUserId();
        String modUserId = noticeVo.getModUserId();
        if (noticeVo.getAttachFileList() != null) {
            for (AttachFileVo fileVo : noticeVo.getAttachFileList()) {
                fileVo.setBbsId(generatedId);  // id 셋팅
                fileVo.setCretUserId(cretUserId);
                fileVo.setModUserId(modUserId);
                attachFileMapper.insertAttachFile(fileVo);
            }
        }
    }
    /**
     * 수정
     */
    @Override
    public void updateForm(int id, NoticeVo noticeVo) {
        noticeVo.setNoticeId(id);
        String cretUserId = noticeVo.getModUserId();
        String modUserId = noticeVo.getModUserId();
        if (noticeVo.getAttachFileList() != null) {
            for (AttachFileVo fileVo : noticeVo.getAttachFileList()) {
                fileVo.setBbsId(id);  // id 셋팅
                fileVo.setCretUserId(cretUserId);
                fileVo.setModUserId(modUserId);
                attachFileMapper.insertAttachFile(fileVo);
            }
        }
        if (noticeVo.getAttachDelFileList() != null) {
            for (AttachFileVo fileVo : noticeVo.getAttachDelFileList()) {
                fileVo.setBbsId(id);
                attachFileMapper.upDateAttachFile(fileVo);
            }
        }
        noticeMapper.updateForm(noticeVo);
    }

}
