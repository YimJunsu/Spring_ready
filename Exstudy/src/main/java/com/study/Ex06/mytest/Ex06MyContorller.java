package com.study.Ex06.mytest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Ex06MyContorller {
	// 두가지 타입의 리턴 방식
	@RequestMapping("/")
	// URL 호출이 / 내용 자체 String 리턴
	public @ResponseBody String root() throws Exception {
		return "Model & View"; // RESTful API 사용시 XML, JSON 데이터를 만들어 리턴 하면됨
	}
	@RequestMapping("/test1")
	public String test1(Model model) {
		// Model 객체 이용, view 로 Data 전달, 데이터만 설정 가능
		model.addAttribute("name", "홍길동"); // URL 호출 test1 들어오면 해당 메서드 실행
		return "test1";
	}
	@RequestMapping("/mv")
	// URL 호출이 mv면 test2 메서드 실행, 
			// String 값 리턴하지 않고 ModelAndView라는 객체 변수를 만들어 데이터 정보를 추가, 뷰 정보까지 담아 리턴
	public ModelAndView test2() {
		// 데이터와 뷰를 동시 설정
		ModelAndView mv = new ModelAndView();
		List<String> list = new ArrayList<>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		
		mv.addObject("lists", list); 		 // JSTL로 호출
		mv.addObject("ObjectTest", "테스트."); 
		mv.addObject("name", "유재석");	
		mv.setViewName("view/myView");
		
		return mv;
	}
}
