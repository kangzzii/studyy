/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: NoticeVo.java
* @author		: kkang
* @date			: 2025.04.14
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.14        kkang       최초 생성
*/
package egovframework.example.bbs.vo;

import java.util.List;

import egovframework.example.cmmn.vo.AttachFileVo;

public class NoticeVo {
    private int noticeId;
    private String noticeType;
    private String noticeTitle;
    private String noticeContents;
    private int noticeInqCnt;
    private String noticeDispYn;
    private String noticeYn;
    private String useYn;
    private String cretUserId;
    private String modUserId;
    private String cretDt;
    private String modDt;
    private int total;



    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    private List<AttachFileVo> attachFileList;
    private List<AttachFileVo> attachDelFileList;


    public List<AttachFileVo> getAttachDelFileList() {
        return attachDelFileList;
    }

    public void setAttachDelFileList(List<AttachFileVo> attachDelFileList) {
        this.attachDelFileList = attachDelFileList;
    }

    public List<AttachFileVo> getAttachFileList() {
        return attachFileList;
    }

    public void setAttachFileList(List<AttachFileVo> attachFileList) {
        this.attachFileList = attachFileList;
    }
    public String getCretUserId() {
        return cretUserId;
    }
    public void setCretUserId(String cretUserId) {
        this.cretUserId = cretUserId;
    }
    public String getModUserId() {
        return modUserId;
    }
    public void setModUserId(String modUserId) {
        this.modUserId = modUserId;
    }
    public String getCretDt() {
        return cretDt;
    }
    public void setCretDt(String cretDt) {
        this.cretDt = cretDt;
    }
    public String getModDt() {
        return modDt;
    }
    public void setModDt(String modDt) {
        this.modDt = modDt;
    }
    public String getNoticeContents() {
        return noticeContents;
    }
    public void setNoticeContents(String noticeContents) {
        this.noticeContents = noticeContents;
    }
    public int getNoticeId() {
        return noticeId;
    }
    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }
    public String getNoticeType() {
        return noticeType;
    }
    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }
    public String getNoticeTitle() {
        return noticeTitle;
    }
    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public int getNoticeInqCnt() {
        return noticeInqCnt;
    }
    public void setNoticeInqCnt(int noticeInqCnt) {
        this.noticeInqCnt = noticeInqCnt;
    }
    public String getNoticeDispYn() {
        return noticeDispYn;
    }
    public void setNoticeDispYn(String noticeDispYn) {
        this.noticeDispYn = noticeDispYn;
    }
    public String getNoticeYn() {
        return noticeYn;
    }
    public void setNoticeYn(String noticeYn) {
        this.noticeYn = noticeYn;
    }
    public String getUseYn() {
        return useYn;
    }
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

}
