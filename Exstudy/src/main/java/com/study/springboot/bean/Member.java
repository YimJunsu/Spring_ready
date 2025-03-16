package com.study.springboot.bean;

import org.springframework.stereotype.Component;

@Component
// Member 클래스를 Bean 으로 등록 하겠다는 의미로 Component 사용
public class Member {
	private String name;
	private String nickname;
	private Printer printer;
	
	// 생성자
	public Member() {
		super();
	}
	public Member(String name, String nickname, Printer printer) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.printer = printer;
	}
	// 각 변수 세터
	public void setName(String name) {
		this.name = name;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	// 메서드
	public void print() {
		printer.print("Hello " + name + " : " + nickname);
	}
}
