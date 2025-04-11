package egovframework.example.sample.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

	@GetMapping("/index.do")
	public String index() {
		return "index";
	}
	
	@PostMapping("/testForm.do")
	public String testForm(@RequestParam("actionType") String actionType, Model model) {

		model.addAttribute("statusType", actionType);
		// 수정 페이지 이동시
		if("edit".equals(actionType)) {
			model.addAttribute("username", "수정이름");
			model.addAttribute("userphone", "01012345678");
			model.addAttribute("addr", "수정주소주소주소");
			model.addAttribute("email", "수정이메일");
			model.addAttribute("txt", "수정내용내용");
		}
		return "testForm";
	}
	
	@PostMapping("/printLog.do")
	public String printLog(@RequestParam Map<String, String> params) {
		
		Logger log = LoggerFactory.getLogger(TestController.class);
		
		if ("edit".equals(params.get("actionType"))) {
			log.info("###### 수정 #####");
		} else {
			// 등록일때
			log.info("###### 등록 #####");
		}

		log.info("이름{}", params.get("username"));
		log.info("연락처{}", params.get("userphone"));
		log.info("주소{}", params.get("addr"));
		log.info("이메일{}", params.get("email"));
		log.info("내용{}", params.get("txt"));
		
		return "redirect:/index.do";
	}
}
