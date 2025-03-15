package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.study.springboot.bean.Member;
import com.study.springboot.bean.PrinterB;

@SpringBootApplication // 스프링 실행 어노테이션
public class ExstudyApplication {

    private final Member member1;

    ExstudyApplication(Member member1) {
        this.member1 = member1;
    }
    public static void main(String[] args) {
        // Spring Boot 애플리케이션 실행
        ApplicationContext context = SpringApplication.run(ExstudyApplication.class, args);
        // 1. IoC 컨테이너 생성 - 과거 IoC 컨테이너를 수동으로 생성 / 현재는 자동, Bean 등록만
        //ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        // Member Bean 가져오기 (Spring IoC 컨테이너에서 관리)
        Member member1 = (Member) context.getBean("member1");
        member1.print();
        // Member Bean 가져오기
        Member member2 = context.getBean("hello", Member.class);
        member2.print();
        // PrinterB Bean 가져오기
        PrinterB printer = context.getBean("printerB", PrinterB.class);
        member1.setPrinter(printer);
        member1.print();
        // 싱글톤 확인
        if (member1 == member2) {
        	System.out.println("동일한 객체입니다.");
        } else {
			System.out.println("서로 다른 객체입니다.");
		}
    }
}
