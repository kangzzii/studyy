/**
* @packageName  : 중소기업기술마켓 정식플랫폼 구축
* @fileName     : CategoryController.java
* @author       : kkang
* @date         : 2025.04.01
* @description  :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.01        kkang       최초 생성
*/

package egovframework.example.bbs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.bbs.service.CategoryService;
import egovframework.example.bbs.vo.CategoryVo;
import jdk.internal.org.jline.utils.Log;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: CategoryController.java
* @author		: kkang
* @date			: 2025.04.01
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.01        kkang       최초 생성
*/
@Controller
public class CategoryController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    CategoryService categoryService;

    /**
    * @methodName	: getList
    * @author		: kkang
    * @desc			: 리스트 조회
    * @date			: 2025.04.01
    * @return
    */
    @GetMapping("/categories.do")
    public String getList(Model model, CategoryVo categoryVo) {
        List<?> cateList = categoryService.getList(categoryVo);
        model.addAttribute("result", cateList);
        return "tiles/category/list";
    }

    /**
    * @methodName	: getDetail
    * @author		: kkang
    * @desc			: 상세 조회
    * @date			: 2025.04.01
    * @param id
    * @param model
    * @return
    */
    @GetMapping("/categories/{id}.do")
    public String getDetail(@PathVariable("id") int id, Model model, CategoryVo categoryVo){
        CategoryVo result = categoryService.getDetail(id);
        model.addAttribute("result", result);
        return "tiles/category/view";
    }

    /**
    * @methodName	: registerList
    * @author		: kkang
    * @desc			: 등록화면 조회
    * @date			: 2025.04.01
    * @return
    */
    @GetMapping("/categories/form.do")
    public String registerList() {
        return "tiles/category/view";
    }


    /**
    * @methodName	: modifyDetail
    * @author		: kkang
    * @desc			: 리스트 수정 , 리스트 등록
    * @date			: 2025.04.01
    * @param categoryVo
    * @return
    */
    @PostMapping("/categories/form.do")
    public String actionDetail (@ModelAttribute("categoryVo") CategoryVo categoryVo) {

        String Category_nm = categoryVo.getCategoryNm();
        if(categoryVo.getCategoryId() == 0) {
            categoryService.registerDetail(Category_nm);
        } else {
            categoryService.modifyDetail(categoryVo);
        }


        return "redirect:/categories.do";
    }


    /**
    * @methodName	: deleteDetail
    * @author		: kkang
    * @desc			: 상세 삭제
    * @date			: 2025.04.01
    * @param id
    * @return
    */
    @GetMapping("/categories/delete/{id}.do")
    public String deleteDetail(@PathVariable("id") int id) {
        categoryService.deleteDetail(id);
        return "redirect:/categories.do";
    }
}
