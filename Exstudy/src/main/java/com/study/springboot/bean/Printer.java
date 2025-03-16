package com.study.springboot.bean;

import org.springframework.stereotype.Component;

// 스프링에 빈 등록 패키지 후 빈 등록됨
// 현재는 어노테이션 사용
public interface Printer {
	public void print(String message);
		
}
