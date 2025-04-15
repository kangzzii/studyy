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

public class NoticeVo {
    private int noticeId;
    private String noticeType;
    private String noticeTitle;
    private String noticeContent;
    private int noticeInqCnt;
    private String noticeDispYn;
    private String noticeYn;
    private String noticeUseYn;
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
    public String getNoticeContent() {
        return noticeContent;
    }
    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
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
    public String getNoticeUseYn() {
        return noticeUseYn;
    }
    public void setNoticeUseYn(String noticeUseYn) {
        this.noticeUseYn = noticeUseYn;
    }

}
