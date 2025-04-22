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
import egovframework.example.bbs.vo.AttachFileVo;
import egovframework.example.bbs.vo.BbsDefaultVo;
import egovframework.example.bbs.vo.NoticeVo;

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
        Map<String, Object> result = noticeService.getForm(id);
        model.addAttribute("result", result);
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
//    @PostMapping("/form.do")
//    @ResponseBody
//    public Object registForm(@RequestParam Map<String, Object> param, HttpSession session) {
//        String userId = (String) session.getAttribute("userId");
//        param.put("cretUserId", userId);
//        param.put("modUserId", userId);
//        //noticeService.registForm(param);
//
//        // 새로등록되는 게시물의 id값
////        String noticeIdStr = (String) param.get("noticeId");
////        Integer noticeId = Integer.parseInt(noticeIdStr);
//        Integer noticeId =  67;
//        Map<String, Object> result = new HashMap<>();
//        result.put("msg", "success");
//        result.put("noticeId", noticeId);
//        return result;
//    }

    @PostMapping("/form.do")
    public String registForm(NoticeVo noticeVo, HttpSession session) {
//        log.info("==============noticeVo================= {}", noticeVo);
        String userId = (String) session.getAttribute("userId");
        noticeVo.setCretUserId(userId);
        noticeVo.setModUserId(userId);
        noticeService.registForm(noticeVo);
        return "redirect:/bbs/notice/list.do";
    }


   @PostMapping("/fileUpload.do")
   @ResponseBody
   public Object fileUpload(@RequestBody List<Map<String, String>> param) {
       Map<String, String> result = new HashMap<String, String>();
       if(param!= null && !param.isEmpty()) {
           result.put("msg", "success");
           log.info("-----------------fileUpload--------------------{}",param);
          // noticeService.fileUpload(param);
       } else {
           result.put("msg", "err");
       }
       return result;
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
    public String updateForm(@PathVariable int id, @RequestParam Map<String, Object> param, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        param.put("modUserId", userId);
        noticeService.updateForm(param);
        return "redirect:/bbs/notice/list.do";
    }

}
