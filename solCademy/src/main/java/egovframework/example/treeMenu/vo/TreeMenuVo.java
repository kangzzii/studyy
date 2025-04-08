/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: TreeMenuVo.java
* @author		: kkang
* @date			: 2025.04.04
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.04        kkang       최초 생성
*/
package egovframework.example.treeMenu.vo;

public class TreeMenuVo {
    private int menuId;
    private String menuNm;
    private String menuDesc;
    private int menuDepth1;
    private int menuDepth2;
    private int menuDepth3;
    private int menuOrder;
    private String menuUrl;
    private String menuParam;
    private String menuPopYn;
    private String menuUseYn;
    public int getMenuId() {
        return menuId;
    }
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    public String getMenuNm() {
        return menuNm;
    }
    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }
    public String getMenuDesc() {
        return menuDesc;
    }
    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }
    public int getMenuDepth1() {
        return menuDepth1;
    }
    public void setMenuDepth1(int menuDepth1) {
        this.menuDepth1 = menuDepth1;
    }
    public int getMenuDepth2() {
        return menuDepth2;
    }
    public void setMenuDepth2(int menuDepth2) {
        this.menuDepth2 = menuDepth2;
    }
    public int getMenuDepth3() {
        return menuDepth3;
    }
    public void setMenuDepth3(int menuDepth3) {
        this.menuDepth3 = menuDepth3;
    }
    public int getMenuOrder() {
        return menuOrder;
    }
    public void setMenuOrder(int menuOrder) {
        this.menuOrder = menuOrder;
    }
    public String getMenuUrl() {
        return menuUrl;
    }
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    public String getMenuParam() {
        return menuParam;
    }
    public void setMenuParam(String menuParam) {
        this.menuParam = menuParam;
    }
    public String getMenuPopYn() {
        return menuPopYn;
    }
    public void setMenuPopYn(String menuPopYn) {
        this.menuPopYn = menuPopYn;
    }
    public String getMenuUseYn() {
        return menuUseYn;
    }
    public void setMenuUseYn(String menuUseYn) {
        this.menuUseYn = menuUseYn;
    }


}
