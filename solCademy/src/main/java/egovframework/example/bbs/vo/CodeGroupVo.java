/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: CodeGroupVo.java
* @author		: kkang
* @date			: 2025.04.09
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.09        kkang       최초 생성
*/
package egovframework.example.bbs.vo;

public class CodeGroupVo {
    private String groupCodeId;
    private String groupCodeNm;

    private int groupCodeSeq;

    public String getGroupCodeId() {
        return groupCodeId;
    }
    public void setGroupCodeId(String groupCodeId) {
        this.groupCodeId = groupCodeId;
    }
    public String getGroupCodeNm() {
        return groupCodeNm;
    }
    public void setGroupCodeNm(String groupCodeNm) {
        this.groupCodeNm = groupCodeNm;
    }
    public int getGroupCodeSeq() {
        return groupCodeSeq;
    }
    public void setGroupCodeSeq(int groupCodeSeq) {
        this.groupCodeSeq = groupCodeSeq;
    }

}
