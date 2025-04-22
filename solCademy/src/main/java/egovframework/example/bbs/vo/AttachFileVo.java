/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: AttachFileVo.java
* @author		: kkang
* @date			: 2025.04.18
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.18        kkang       최초 생성
*/
package egovframework.example.bbs.vo;

public class AttachFileVo {
    private int attachFileId;
    private String bbsCodeId;
    private int bbsId;
    private String attachFileName;
    private String attachFileOriName;
    private int attachFileSize;
    private String attachFilePath;
    private String useYn;
    private String cretUserId;
    private String CretDt;
    private String modUserId;
    private String modDt;
    public int getAttachFileId() {
        return attachFileId;
    }
    public void setAttachFileId(int attachFileId) {
        this.attachFileId = attachFileId;
    }
    public String getBbsCodeId() {
        return bbsCodeId;
    }
    public void setBbsCodeId(String bbsCodeId) {
        this.bbsCodeId = bbsCodeId;
    }
    public int getBbsId() {
        return bbsId;
    }
    public void setBbsId(int bbsId) {
        this.bbsId = bbsId;
    }
    public String getAttachFileName() {
        return attachFileName;
    }
    public void setAttachFileName(String attachFileName) {
        this.attachFileName = attachFileName;
    }
    public String getAttachFileOriName() {
        return attachFileOriName;
    }
    public void setAttachFileOriName(String attachFileOriName) {
        this.attachFileOriName = attachFileOriName;
    }
    public int getAttachFileSize() {
        return attachFileSize;
    }
    public void setAttachFileSize(int attachFileSize) {
        this.attachFileSize = attachFileSize;
    }
    public String getAttachFilePath() {
        return attachFilePath;
    }
    public void setAttachFilePath(String attachFilePath) {
        this.attachFilePath = attachFilePath;
    }
    public String getUseYn() {
        return useYn;
    }
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }
    public String getCretUserId() {
        return cretUserId;
    }
    public void setCretUserId(String cretUserId) {
        this.cretUserId = cretUserId;
    }
    public String getCretDt() {
        return CretDt;
    }
    public void setCretDt(String cretDt) {
        CretDt = cretDt;
    }
    public String getModUserId() {
        return modUserId;
    }
    public void setModUserId(String modUserId) {
        this.modUserId = modUserId;
    }
    public String getModDt() {
        return modDt;
    }
    public void setModDt(String modDt) {
        this.modDt = modDt;
    }
    @Override
    public String toString() {
        return "AttachFileVo [attachFileId=" + attachFileId + ", bbsCodeId=" + bbsCodeId + ", bbsId=" + bbsId
                + ", attachFileName=" + attachFileName + ", attachFileOriName=" + attachFileOriName
                + ", attachFileSize=" + attachFileSize + ", attachFilePath=" + attachFilePath + ", useYn=" + useYn
                + ", cretUserId=" + cretUserId + ", CretDt=" + CretDt + ", modUserId=" + modUserId + ", modDt=" + modDt
                + "]";
    }



}
