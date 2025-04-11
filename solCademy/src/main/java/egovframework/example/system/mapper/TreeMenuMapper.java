/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: TreeMenuMapper.java
* @author		: kkang
* @date			: 2025.04.04
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.04        kkang       최초 생성
*/
package egovframework.example.treeMenu.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.treeMenu.vo.TreeMenuVo;

@Mapper
public interface TreeMenuMapper {
    // 리스트 출력
    List<TreeMenuVo> selectList(TreeMenuVo treeMenuVo);

    // 수정 출력
    TreeMenuVo selectView(int id);

    //단계1 출력
    List<TreeMenuVo> selectDepthOne();

    // 단계2,3 출력
    List<TreeMenuVo> selectDepth(Map<String, Object> param);

    // 등록
    void insertMenu(TreeMenuVo result);

    // 수정
    void updateMenu(TreeMenuVo treeMenuVo);
}
