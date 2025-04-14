/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: LoginController.java
* @author		: kkang
* @date			: 2025.04.11
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.11        kkang       최초 생성
*/
package egovframework.example.login.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.example.login.service.UserService;
import egovframework.example.login.vo.UserVo;

@Controller
public class LoginController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    UserService userService;

    /**
    * @methodName	: loginForm
    * @author		: kkang
    * @desc			: 로그인 화면
    * @date			: 2025.04.11
    * @return
    */
    @GetMapping("/loginForm.do")
    public String loginForm() {
        return "tiles/login/form";
    }

    /**
    * @methodName	: signupForm
    * @author		: kkang
    * @desc			: 회원가입 화면
    * @date			: 2025.04.11
    * @return
    */
    @RequestMapping("/signupForm.do")
    public String signupForm() {
        return "tiles/login/registForm";
    }

    /**
    * @methodName	: registSignup
    * @author		: kkang
    * @desc			: 회원가입 DB 전송
    * @date			: 2025.04.14
    * @param userVo
    * @return
    */
    @RequestMapping("/registSignup.do")
    @ResponseBody
    public Map<String, String> registSignup(@ModelAttribute UserVo userVo) {
        Map<String, String> result = new HashMap<String, String>();
        // 아이디 동일값 확인
        int isDuplicateId = userService.isDuplicateId(userVo.getUserId());
        userVo.setUserPw(encoder.encode(userVo.getUserPw()));
        if(isDuplicateId == 0) {
            result.put("res", "success");
            userService.registSignup(userVo);
        } else {
            result.put("res", "error");
        }

        return result;
    }

    /**
    * @methodName	: Dologin
    * @author		: kkang
    * @desc			: 로그인
    * @date			: 2025.04.14
    * @param userVo
    * @return
    */
    @RequestMapping("/login.do")
    @ResponseBody
    public Map<String, String> Dologin(@ModelAttribute UserVo userVo, HttpSession session){
        String userLogin = userService.doLogin(userVo);
        Map<String, String> result = new HashMap<String, String>();
        log.info("---------------------------{}", userLogin);
        if(userLogin.equals("success")) {
            result.put("status", "success");
            session.setAttribute("userId", userVo.getUserId());
        } else if(userLogin.equals("loginFailFullCnt")){    // 3번 이상 비밀번호 오류시
            result.put("status", "loginFailFullCnt");
        } else {
            result.put("status", "error");
        }
        return result;
    }

    /**
    * @methodName	: logout
    * @author		: kkang
    * @desc			: 로그아웃
    * @date			: 2025.04.14
    * @param session
    * @return
    */
    @GetMapping("/logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginForm.do";
    }
}
