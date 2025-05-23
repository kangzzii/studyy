package egovframework.example.sample.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/assignment")
public class Task3 {

	// 가짜 데이터
	String[] nameArr = {"테스트1","테스트2", "테스트3", "테스트4", "테스트5", "테스트6", "테스트7", "테스트8", "테스트9", "테스트10"};
    int[] ageArr = {10,30,24,25,36,68,89,33,2,14};
    String[] phoneArr = {"01012345678","01012345678", "01012345678", "01012345678", "01012345675", "01012345676", "0101234567", "01012345678", "01012345679", "010123456710"};
    String[] nationArr = {"한국","미국", "영국", "한국", "미국", "한국", "한국", "프랑스", "한국", "독일"};
    String[] genderArr = {"F","M", "F", "F", "F", "M", "F", "M", "F", "M"};
    String[] userYnArr = {"Y","Y", "N", "Y", "Y", "N", "N", "Y", "Y", "N"};

	public List<Task3Data> makeList() {
		List<Task3Data> userList = new ArrayList<>();
		for(int i=0; i<nameArr.length; i++) {
			userList.add(new Task3Data(nameArr[i], ageArr[i],phoneArr[i], nationArr[i], genderArr[i], userYnArr[i]));
		}
		return userList;
	}

	// 메소드 2
	@GetMapping(value= {"/first/list.do" , "/list.do","/second/list.do"})
	public String viewList(Model model) {
		model.addAttribute("userList", makeList());
		return "tiles/task/list";
	}

	@GetMapping("/second/ajaxList.do")
	public String ajaxViewList() {
		return "tiles/task/ajaxList";
	}

	@RequestMapping(value="/ajaxView.do", method=RequestMethod.POST)
	@ResponseBody
	public List<Task3Data> ajaxView() {
	    return makeList();
	}



}
