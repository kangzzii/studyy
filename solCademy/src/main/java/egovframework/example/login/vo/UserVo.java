/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: LoginVo.java
* @author		: kkang
* @date			: 2025.04.11
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.11        kkang       최초 생성
*/
package egovframework.example.login.vo;

import java.util.Date;

public class UserVo {
    private int userSeq;
    private String userId;
    private String userNm;
    private String userPw;
    private String useYn;
    private int loginFailCnt;
    private String lastLoginDt;
    private String cretUserId;
    private String cretDt;
    private String modUserId;
    private String modDt;
    public int getUserSeq() {
        return userSeq;
    }
    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserNm() {
        return userNm;
    }
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }
    public String getUserPw() {
        return userPw;
    }
    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }
    public String getUseYn() {
        return useYn;
    }
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }
    public int getLoginFailCnt() {
        return loginFailCnt;
    }
    public void setLoginFailCnt(int loginFailCnt) {
        this.loginFailCnt = loginFailCnt;
    }
    public String getLastLoginDt() {
        return lastLoginDt;
    }
    public void setLastLoginDt(String lastLoginDt) {
        this.lastLoginDt = lastLoginDt;
    }
    public String getCretUserId() {
        return cretUserId;
    }
    public void setCretUserId(String cretUserId) {
        this.cretUserId = cretUserId;
    }
    public String getCretDt() {
        return cretDt;
    }
    public void setCretDt(String cretDt) {
        this.cretDt = cretDt;
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

}
