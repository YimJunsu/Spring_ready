package com.study.Ex06.mytest;

import java.util.HashMap;
import java.util.Map;

public class ModelUse {
	// 프로그램 시작점
	public static void main(String[] args) {
		// 모델 데이터 저장을 위한 HashMap 객체 생성
		Map<String, String> model = new HashMap<>();
		// root 메서드호출, model 에 데이터 추가 및 반환값 sReturn에 저장
		String sReturn = root(model);
		// printData 메서드 이용 sReturn과 model 전달
		printData(sReturn, model);
	}
	// 모델 추가 및 Hello 메서드 반환
	public static String root(Map model) {
		model.put("name1", "홍길동");
		model.put("name2", "전우치");
		return "Hello";
	}
	// 전달된 model 데이터 출력, 특정 문자열 출력
	public static void printData(String s, Map model) {
		String str1 = (String)model.get("name1");
		System.out.println(str1);
		System.out.println("WEB-INF/views/" + s + ".jsp");
	}
	
}
