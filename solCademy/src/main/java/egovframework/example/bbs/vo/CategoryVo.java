/**
* @packageName  : 중소기업기술마켓 정식플랫폼 구축
* @fileName     : CategoryVo.java
* @author       : kkang
* @date         : 2025.04.01
* @description  :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.01        kkang       최초 생성
*/
package egovframework.example.bbs.vo;
public class CategoryVo {

    private int categoryId;
    private String categoryNm;
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryNm() {
        return categoryNm;
    }
    public void setCategoryNm(String categoryNm) {
        this.categoryNm = categoryNm;
    }


}
