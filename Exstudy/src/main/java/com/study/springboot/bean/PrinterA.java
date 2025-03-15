package com.study.springboot.bean;

public class PrinterA implements Printer {
	@Override // 메서드를 재정의 어노테이션 - 오버라이딩
	public void print(String message) {
		
		System.out.println("Pringter A : " + message);
	}

}
