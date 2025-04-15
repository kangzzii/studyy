/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: NoticeController.java
* @author		: kkang
* @date			: 2025.04.14
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.14        kkang       최초 생성
*/
package egovframework.example.bbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.example.bbs.service.NoticeService;
import egovframework.example.bbs.vo.NoticeVo;

@Controller
@RequestMapping("/bbs/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    /**
    * @methodName	: getList
    * @author		: kkang
    * @desc			: 공지사항 리스트
    * @date			: 2025.04.14
    * @param model
    * @return
    */
    @GetMapping("/list.do")
    public String getList(Model model) {
        List<NoticeVo> result = noticeService.getList();
        model.addAttribute("resultList", result);
        return "tiles/notice/list";
    }

}
