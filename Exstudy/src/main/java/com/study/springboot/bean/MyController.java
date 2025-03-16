package com.study.springboot.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	@Autowired Member member1;
	@Autowired @Qualifier("printerB") Printer printer;
	@Autowired Member member2;
	// 해당 클래스의 객체를 찾아와 자동으로 연결, getInstance()처럼 싱긅톤 객체 생성
	@RequestMapping("/")
	public @ResponseBody String root() {
		// ResponseBody = JSON 이나 XML 등 Rest API 형태의 응답 할 경우 순수 문자열로 응답 할 경우 지정
		// 1. Member Bean 가져오기
		member1.print();
		// 2. PrinterB Bean 가져오기
		member1.setPrinter(printer);
		member1.print();
		// 3. 싱글톤 확인 
		if(member1==member2) {
			System.out.println("동일한 객체입니다.");
		}else {
			System.out.println("서로 다른 객체입니다.");
		} return "어노테이션 사용하기";
	}
	
}
