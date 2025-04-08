/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: TreeMenuService.java
* @author		: kkang
* @date			: 2025.04.04
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.04        kkang       최초 생성
*/
package egovframework.example.treeMenu.service;

import java.util.List;
import java.util.Map;

import egovframework.example.treeMenu.vo.TreeMenuVo;

public interface TreeMenuService {

    // 리스트 출력
    List<TreeMenuVo> getList(TreeMenuVo treeMenuVo);

    //단계1 출력
    List<TreeMenuVo> getDepthOne();

    // 단계2,3 출력
    List<TreeMenuVo> getDepth(Map<String, Object> param);

    // 등록
    void registMenu(Map<String, Object> param);

    // 수정 출력
    TreeMenuVo getView(int id);

    // 수정
    void editMenu(TreeMenuVo treeMenuVo);
}
