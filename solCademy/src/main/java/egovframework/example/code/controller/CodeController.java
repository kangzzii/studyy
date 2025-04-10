/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: CodeController.java
* @author		: kkang
* @date			: 2025.04.09
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.09        kkang       최초 생성
*/
package egovframework.example.code.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import egovframework.example.code.service.CodeService;
import egovframework.example.code.vo.CodeCommonVo;
import egovframework.example.code.vo.CodeGroupVo;

@Controller
@RequestMapping("/code")
public class CodeController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    CodeService codeService;

    /**
    * @methodName	: getList
    * @author		: kkang
    * @desc			: 리스트 페이지 출력
    * @date			: 2025.04.09
    * @param model
    * @return
    */
    @GetMapping("/list.do")
    public String getList(Model model) {
        // 그룹코드 리스트 출력
        List<CodeGroupVo> groupList = codeService.getGroupList();
        model.addAttribute("groupList", groupList);
        return "tiles/code/list";
    }

    /**
    * @methodName	: getCommonList
    * @author		: kkang
    * @desc			: ajax 공통코드 출력
    * @date			: 2025.04.09
    * @param id
    */
    @RequestMapping("/ajaxGroupCode.do")
    @ResponseBody
    public Object getCommonList(@RequestParam String id, Model model) {
        List<CodeCommonVo> commonList = codeService.getCommonList(id);
        Gson gson = new Gson();
        return gson.toJson(commonList);
    }

    /**
    * @methodName	: registGroupCode
    * @author		: kkang
    * @desc			: 그룹 코드 등록
    * @date			: 2025.04.09
    * @param codeGroupVo
    * @return
    */
    @PostMapping("/setGroupCode.do")
    @ResponseBody
    public String registGroupCode(@ModelAttribute CodeGroupVo codeGroupVo) {
        codeService.registGroupCode(codeGroupVo);
        return "redirect:/code/list.do";
    }

    /**
    * @methodName	: registCommonCode
    * @author		: kkang
    * @desc			: 공통코드 등록
    * @date			: 2025.04.10
    * @param codeCommonVo
    * @return
    */
    @RequestMapping("/setCommonCode.do")
    @ResponseBody
    public Map<String, String> registCommonCode(@ModelAttribute CodeCommonVo codeCommonVo) {
        Map<String, String> result = new HashMap<>();

        /*
         * String groupId = codeCommonVo.getGroupCodeId(); String commonId =
         * codeCommonVo.getCommonCodeId();
         *
         * List<CodeCommonVo> commonList = codeService.getCommonList(groupId); boolean
         * isDuplicate = commonList.stream().anyMatch(vo ->
         * vo.getCommonCodeId().equals(groupId+commonId));
         */
        // 공통코드 등록시 아이디 체크
        int isDuplicate = codeService.isDuplicate(codeCommonVo);


        if(isDuplicate > 0) {
            result.put("status", "error");
            result.put("msg", "동일아이디값 있지롱");
        } else {
            result.put("status", "success");
            codeService.registCommonCode(codeCommonVo);
        }
        return result;
    }
}
