/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: TreeMenuServiceImpl.java
* @author		: kkang
* @date			: 2025.04.04
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.04        kkang       최초 생성
*/
package egovframework.example.system.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.example.system.mapper.TreeMenuMapper;
import egovframework.example.system.service.TreeMenuService;
import egovframework.example.system.vo.TreeMenuVo;

@Service
public class TreeMenuServiceImpl implements TreeMenuService {

    @Autowired
    TreeMenuMapper treeMenuMapper;

    Logger log = LoggerFactory.getLogger(getClass());
    /**
     *  리스트 출력
     */
    @Override
    public List<TreeMenuVo> getList(TreeMenuVo treeMenuVo) {
        return treeMenuMapper.selectList(treeMenuVo);
    }

    @Override
 // 수정 출력
    public TreeMenuVo getView(int id){
        return treeMenuMapper.selectView(id);
    }

    /**
     *  단계 1 출력
     */
    @Override
    public List<TreeMenuVo> getDepthOne(){
        return treeMenuMapper.selectDepthOne();
    }

    /**
     *  단계 2,3 출력
     */
    @Override
    public List<TreeMenuVo> getDepth (Map<String, Object> param){
        return treeMenuMapper.selectDepth(param);
    }

    /**
     *  등록
     */
    @Override
    public void registMenu(Map<String, Object> param){
        TreeMenuVo result = new TreeMenuVo();
        result.setMenuNm((String) param.get("menuNm"));
        result.setMenuUrl((String) param.get("menuUrl"));
        result.setMenuDesc((String) param.get("menuDesc"));
        result.setMenuDepth1(Integer.parseInt((String) param.get("menuDepth1")));
        result.setMenuDepth2(Integer.parseInt((String) param.get("menuDepth2")));
        result.setMenuDepth3(Integer.parseInt((String) param.get("menuDepth3")));
        result.setMenuOrder(Integer.parseInt((String) param.get("menuOrder")));
        result.setMenuPopYn((String) param.get("menuPopYn"));
        result.setMenuUseYn((String) param.get("menuUseYn"));
        result.setMenuParam((String) param.get("menuParam"));
        log.info("-----------------------------{}", result);
        treeMenuMapper.insertMenu(result);
    }

    /**
     *  수정
     */
    @Override
    public void editMenu(TreeMenuVo treeMenuVo) {
        treeMenuMapper.updateMenu(treeMenuVo);
    }

}
