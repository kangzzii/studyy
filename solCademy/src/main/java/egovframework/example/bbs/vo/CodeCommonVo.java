/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: CodeCommonVo.java
* @author		: kkang
* @date			: 2025.04.09
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.09        kkang       최초 생성
*/
package egovframework.example.bbs.vo;

public class CodeCommonVo {

    private String commonCodeId;
    private String groupCodeId;
    private String commonCodeNm;
    private String groupCodeNm;


    public String getCommonCodeId() {
        return commonCodeId;
    }
    public void setCommonCodeId(String commonCodeId) {
        this.commonCodeId = commonCodeId;
    }
    public String getGroupCodeId() {
        return groupCodeId;
    }
    public void setGroupCodeId(String groupCodeId) {
        this.groupCodeId = groupCodeId;
    }
    public String getCommonCodeNm() {
        return commonCodeNm;
    }
    public void setCommonCodeNm(String commonCodeNm) {
        this.commonCodeNm = commonCodeNm;
    }
    public String getGroupCodeNm() {
        return groupCodeNm;
    }
    public void setGroupCodeNm(String groupCodeNm) {
        this.groupCodeNm = groupCodeNm;
    }

}
