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
package egovframework.example.category.vo;
public class CategoryVo {

    private int category_id;
    private String category_nm;
    public int getCategory_id() {
        return category_id;
    }
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
    public String getCategory_nm() {
        return category_nm;
    }
    public void setCategory_nm(String category_nm) {
        this.category_nm = category_nm;
    }
    
}
