package com.study.Ex01;

class Member{
	String name;
	String nickname;
	public Member() {} // 기본생성자
	// Member 클래스가 유틸 클래스는 아니지만 만약 생성자 부분을 private 로 변경하면 강 결합에서 에러가, 약한 결합에서는 에러가 발생하지 않는다.
	
}

public class UnderstandDI {
	public static void main(String[] args) {
		
	}

	public static void memberUse1() {
		// 강한 결합 : 직접 생성
		Member m1 = new Member();
	}
	
	public static void memberUse2(Member m) {
		// 약한 결합 : 생성된 것을 주입 받음 - 의존 주입 (Dependency Injection = DI)
		Member m2 =  m;
	// 스프링에서 공통 기능에 대한 많은 클래스 들을 미리 만들고, 우리는 의존 주입을 통해 필요한 곳에서 사용 = 손쉽게 개발 가능
	}
}
