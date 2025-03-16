package com.study.springboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ViewController {

	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "JSP in Gradle";
	}
	@RequestMapping("/test1") // localhost:8081/test1
	public String test1() {
		return "test1";
	}
	@RequestMapping("/test2") // localhost:8081/test2
	public String test2() {
		return "test2";
	}
}
