package study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TilesTest {
	@RequestMapping(value="/tiles/test.do")
	 public String tilesTest() {
        return "view/test";
    }
	@RequestMapping(value="/tiles/test2.do")
	 public String tilesTest2() {
       return "view/test2";
   }
}
