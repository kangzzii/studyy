package egovframework.example.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TilesTest {

    @GetMapping("/main.do")
    public String doMain() {
        return "tiles/test/test";
    }

	@RequestMapping(value="/calendar.do")
	public String doCalendar() {
		return "tiles/calendar/main";
	}

	@RequestMapping(value="/tiles/popup.do")
	public String PopTiles() {
		return "popup/test/test";
	}
}
