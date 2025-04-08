/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: CalendarController.java
* @author		: kkang
* @date			: 2025.04.03
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.03        kkang       최초 생성
*/
package egovframework.example.calendar.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class CalendarController {

    Logger log = LoggerFactory.getLogger(getClass());

    /**
    * @methodName	: calendar
    * @author		: kkang
    * @desc			: 캘린더 페이지
    * @date			: 2025.04.03
    * @return
    */
    @GetMapping(value="/calendar.do")
    public String calendar() {
        return "calendar/calendar/main";
    }

    /**
    * @methodName	: createEvent
    * @author		: kkang
    * @desc			: 이벤트 생성 데이터 받기
    * @date			: 2025.04.03
    */
    @RequestMapping(value="/ajax/createEvent.do", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String createEvent(@RequestBody Map<String, Object> result) {
        log.info("test------------------------{}",result);
//        calendarService.createEvent(result);
        return "";
    }

}
