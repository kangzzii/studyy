package study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TilesTest {
	@RequestMapping(value="/tiles/test.do")
	 public String tilesTest() {
        return "tiles/test/test";
    }
	@RequestMapping(value="/tiles/test2.do")
	 public String tilesTest2() {
       return "tiles/test/test2";
   }
	
	@RequestMapping(value="/tiles/popup.do")
	public String PopTiles() {
		return "popup/test/test";
	}
}
