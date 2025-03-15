package com.study.springboot.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config {
	// 빈(Bean) : Spring 이 IOC 방식으로 관리하는 객체
	// 빈 팩토리(BeanFactory) : 스프링의 IOC를 담당하는 핵심 컨테이너
	// 어플리케이션 컨텍스트(ApplicationContext) : 빈 팩토리를 확장한 IOC 컨테이너
	
	@Bean
	@Primary
	public Member member1() {
		// Setter Injection (Setter 메서드를 이용한 의존성 주입)
		Member member1 = new Member();
		member1.setName("유재석");
		member1.setNickname("국민MC");
		member1.setPrinter(new PrinterA());
		
		return member1;
	}
	// 어노테이션에 이름을 주면 해당 클래스 빈 등록시 입력한 이름으로 빈의 이름 변경 가능
	@Bean(name="hello")
	public Member member2() {
		// Constructor Injection (생성자를 이용한 의존성 주입)
		return new Member("강호동" , "씨름선수" , new PrinterA());
	}
	@Bean
	public PrinterA printerA() {
		return new PrinterA();
	}
	// 지정하지 않으면 메서드 이름으로 빈 이름 등록
	@Bean
	public PrinterB printerB () {
		return new PrinterB();
	}
	
}
