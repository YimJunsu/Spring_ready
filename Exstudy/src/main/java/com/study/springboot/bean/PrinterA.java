package com.study.springboot.bean;

import org.springframework.stereotype.Component;

@Component("printerA")
// 해당 클래스를 printerA 라는 이름으로 빈 등록
public class PrinterA implements Printer {
	@Override // 메서드를 재정의 어노테이션 - 오버라이딩
	public void print(String message) {
		
		System.out.println("Pringter A : " + message);
	}

}
