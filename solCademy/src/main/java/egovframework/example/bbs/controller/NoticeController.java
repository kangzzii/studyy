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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import egovframework.example.bbs.service.NoticeService;
import egovframework.example.bbs.vo.BbsDefaultVo;
import egovframework.example.bbs.vo.NoticeVo;
import egovframework.example.cmmn.vo.AttachFileVo;

@Controller
@RequestMapping("/bbs/notice")
public class NoticeController {

    Logger log = LoggerFactory.getLogger(getClass());

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
        int defaultNumb = 10;
        List<NoticeVo> result = noticeService.getList(defaultNumb);
        model.addAttribute("resultList", result);
        return "tiles/notice/list";
    }

    @PostMapping("/list.do")
    @ResponseBody
    public Object changeList(@RequestParam int numb) {
        List<NoticeVo> result = noticeService.getList(numb);
        Gson gson = new Gson();
        return gson.toJson(result);
    }

    /**
    * @methodName	: doRegister
    * @author		: kkang
    * @desc			: 등록 페이지
    * @date			: 2025.04.15
    * @return
    */
    @GetMapping("/form.do")
    public String doRegister() {
        return "tiles/notice/form";
    }

    /**
    * @methodName	: getForm
    * @author		: kkang
    * @desc			: 글 상세 불러오기(수정페이지)
    * @date			: 2025.04.15
    * @param id
    * @return
    */
    @GetMapping("form/{id}.do")
    public String getForm(@PathVariable int id, Model model) {

        NoticeVo result = noticeService.getForm(id);
        model.addAttribute("result", result);   // form 내용
        Gson gson = new Gson();
        model.addAttribute("fileList", gson.toJson(result.getAttachFileList()));    // 파일내용
        return "tiles/notice/form";
    }

    /**
    * @methodName	: registForm
    * @author		: kkang
    * @desc			: 등록
    * @date			: 2025.04.15
    * @param param
    * @return
    */
    @PostMapping("/form.do")
    public String registForm(NoticeVo noticeVo, HttpSession session) {
//        log.info("==============noticeVo================= {}", noticeVo);
        String userId = (String) session.getAttribute("userId");
        noticeVo.setCretUserId(userId);
        noticeVo.setModUserId(userId);
        noticeService.registForm(noticeVo);
        return "redirect:/bbs/notice/list.do";
    }

    /**
    * @methodName	: updateForm
    * @author		: kkang
    * @desc			: 수정
    * @date			: 2025.04.15
    * @param id
    * @param param
    * @return
    */
    @PostMapping("/form/{id}.do")
    public String updateForm(@PathVariable int id, NoticeVo noticeVo, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
//        log.info("=====================noticeVo======================{}",noticeVo);
        noticeVo.setModUserId(userId);
        noticeService.updateForm(id, noticeVo);
        return "redirect:/bbs/notice/list.do";
    }
}
