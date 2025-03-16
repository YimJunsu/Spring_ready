package com.study.springboot.bean;

import org.springframework.stereotype.Component;

@Component("printerB")
//해당 클래스를 printerB라는 이름으로 빈 등록
public class PrinterB implements Printer{
	@Override
	public void print(String message) {
		
		System.out.println("Printer B : " + message);
	}
}
