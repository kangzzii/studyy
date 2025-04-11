/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: GlobalModelAtributes.java
* @author		: kkang
* @date			: 2025.04.11
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.11        kkang       최초 생성
*/
package egovframework.example.cmmn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.google.gson.Gson;

import egovframework.example.system.service.TreeMenuService;
import egovframework.example.system.vo.TreeMenuVo;

@ControllerAdvice
public class GlobalModelAtributes {
    @Autowired
    TreeMenuService treeMenuService;
    /**
    * @methodName	: getGlobalMenuList
    * @author		: kkang
    * @desc			: 메뉴 리스트
    * @date			: 2025.04.11
    * @param model
    * @param treeMenuVo
    */
    @ModelAttribute("treeList")
    public Object getGlobalMenuList(Model model, TreeMenuVo treeMenuVo){
        List<TreeMenuVo> result = treeMenuService.getList(treeMenuVo);
        Gson gson = new Gson();
        return gson.toJson(result);
    }
}
