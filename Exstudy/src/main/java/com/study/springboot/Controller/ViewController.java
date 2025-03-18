package com.study.springboot.Controller;

import com.study.springboot.Ex07.Member;
import com.study.springboot.bean.PrinterA;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ViewController {
	// Ex08 Start
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "Lombok 사용하기";
	}
	@RequestMapping("/ex08/test3")
	public String test3(com.study.springboot.Ex08.Member member, Model model) {
		// 파라미터와 일치하는 빈을 만들어 사용 가능
		// View 페이지에서 model 을 사용하지 않고 member 사용
		return "/ex08/test3";
	}
	
	/*
	 * private final PrinterA printerA;
	 * 
	 * ViewController(PrinterA printerA) { this.printerA = printerA; }
	 * 
	 * @RequestMapping("/") public @ResponseBody String root() throws Exception {
	 * return "Form 데이터 전달받아 사용하기"; } // 스프링에서 자동처리, JSP/Servlet 전형적인 방법
	 * 
	 * @RequestMapping("/ex07/test1") public String test1(HttpServletRequest
	 * httpServletRequest, Model model) { String id =
	 * httpServletRequest.getParameter("id"); String name =
	 * httpServletRequest.getParameter("name"); model.addAttribute("id", id);
	 * model.addAttribute("name", name); return "/ex07/test1"; } // 파라미터(매개변수)가 많을
	 * 경우
	 * 
	 * @RequestMapping("/ex07/test2") public String test2(@RequestParam("id") String
	 * id, @RequestParam("name") String name, Model model) {
	 * model.addAttribute("id", id); model.addAttribute("name", name); return
	 * "ex07/test2"; // /WEB-INF/views/ex07/test2.jsp를 반환 } // 파라미터와 일치하는 빈 만들어서 사용,
	 * View 페이지에 model 을 사용하지 않고 member 사용 // 이전 Dto dto 쓰던 방식과 동일
	 * 
	 * @RequestMapping("/ex07/test3") public String test3(Member member, Model
	 * model) { return "/ex07/test3"; } // 패스 자체에 변수를 넣는 방법 @PathVaribale 어노테이션 사용
	 * 
	 * @RequestMapping("/ex07/test4/{studentId}/{name}") public String
	 * getStudent(@PathVariable String studentId, @PathVariable String name, Model
	 * model) { model.addAttribute("id", studentId);
	 * model.addAttribute("name",name); return "/ex07/test4"; } // Ex07 end
	 */
	/*
	 * First
	 * @RequestMapping("/") public @ResponseBody String root() throws Exception{
	 * return "JSP in Gradle"; }
	 * 
	 * @RequestMapping("/test1") // localhost:8081/test1 public String test1() {
	 * return "test1"; }
	 * 
	 * @RequestMapping("/test2") // localhost:8081/test2 public String test2() {
	 * return "test2"; }
	 */
}
