/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: TreeMenuController.java
* @author		: kkang
* @date			: 2025.04.04
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.04        kkang       최초 생성
*/
package egovframework.example.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import egovframework.example.system.service.TreeMenuService;
import egovframework.example.system.vo.TreeMenuVo;


@Controller
@RequestMapping("/system/menu")
public class TreeMenuController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    TreeMenuService treeMenuService;
    /**
    * @methodName	: getList
    * @author		: kkang
    * @desc			: 리스트 출력
    * @date			: 2025.04.04
    * @return
    * @throws JsonProcessingException
    */
    @GetMapping("/list.do")
    public String getList(Model model, TreeMenuVo treeMenuVo) {
        List<TreeMenuVo> result = treeMenuService.getList();
        model.addAttribute("resultList", result);
        // 트리 그리는 데이터
//        ObjectMapper objectMapper = new ObjectMapper();
//        model.addAttribute("treeList",objectMapper.writeValueAsString(result));
        Gson gson = new Gson();
        model.addAttribute("treeList",gson.toJson(result));
        return "tiles/treeMenu/list";
    }

    /**
    * @methodName	: getForm
    * @author		: kkang
    * @desc			: 등록 페이지 출력, 단계1 값
    * @date			: 2025.04.07
    * @param model
    * @param treeMenuVo
    * @return
    */
    @GetMapping("/form.do")
    public String getForm(Model model, TreeMenuVo treeMenuVo) {
        List<TreeMenuVo> depth = treeMenuService.getDepthOne();
        model.addAttribute("depth", depth);
        return"tiles/treeMenu/form";
    }

    /**
    * @methodName	: editForm
    * @author		: kkang
    * @desc			: 수정 form
    * @date			: 2025.04.07
    * @param id
    * @param model
    * @return
    */
    @GetMapping("/form/{id}.do")
    public String editForm(@PathVariable("id") int id, Model model) {
        TreeMenuVo result = treeMenuService.getView(id);
        List<TreeMenuVo> depth = treeMenuService.getDepthOne();
        model.addAttribute("depth", depth);
        model.addAttribute("result", result);
        return "tiles/treeMenu/form";
    }



    /**
    * @methodName	: getDepth
    * @author		: kkang
    * @desc			: ajax 단계2,3 변경
    * @date			: 2025.04.07
    * @param id
    * @param step
    * @return
    */
    @RequestMapping("/getDepth.do")
    @ResponseBody
    public List<TreeMenuVo> getDepth(@RequestParam int id1, @RequestParam int id2) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("depth1Id", id1);
        param.put("depth2Id", id2);
        List<TreeMenuVo> depth = treeMenuService.getDepth(param);
        return depth;
    }

    /**
    * @methodName	: registMenu
    * @author		: kkang
    * @desc			: form 등록
    * @date			: 2025.04.07
    * @param param
    * @return
    */
    @PostMapping("/form.do")
    public String registMenu(@RequestParam Map<String, Object> param) { // vo로 받기바보야

        // 공백 체크
        if(param.get("menuNm").equals("")) {
            log.error("메뉴명을 입력하세요");
        } else if(param.get("menuUrl").equals("")) {
            log.error("메뉴url을 입력하세요");
        } else if(param.get("menuDesc").equals("")) {
            log.error("메뉴설명을 입력하세요");
        } else if(param.get("menuOrder").equals("")) {
            log.error("메뉴순서를 입력하세요");
        } else {
            treeMenuService.registMenu(param);
        }
        return "redirect:/system/menu/list.do";
    }

    /**
    * @methodName	: editMenu
    * @author		: kkang
    * @desc			: 메뉴 수정
    * @date			: 2025.04.07
    * @param id
    * @return
    */
    @PostMapping("/form/{id}.do")
    public String editMenu(@PathVariable("id") int id, @ModelAttribute TreeMenuVo treeMenuVo) {
     // 공백 체크
        if(treeMenuVo.getMenuNm().equals("")) {
            log.error("메뉴명을 입력하세요");
        } else {
            treeMenuVo.setMenuId(id);
            treeMenuService.editMenu(treeMenuVo);
        }
        return "redirect:/system/menu/list.do";
    }



}
