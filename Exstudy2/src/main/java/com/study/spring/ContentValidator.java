package com.study.spring;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


// 폼 유효성 검증 , 어노 테이션 사용으로 필요 없어짐
//public class ContentValidator implements Validator{
	public class ContentValidator{
//	// 메서드 재정의 // 어노테이션 사용전
//	@Override
//	public boolean supports(Class<?> arg0) {
//		return ContentDto.class.isAssignableFrom(arg0); // 검증할 객체의 클래스 타입 정보
//	}
//	@Override
//	public void validate(Object obj, Errors errors) {
//		ContentDto dto = (ContentDto)obj;
//		
////		String sWriter = dto.getWriter(); // ContentDto에 Writer 가져오기
////		if(sWriter == null || sWriter.trim().isEmpty()) {
////		System.out.println("Writer 비었습니다.");
////		errors.rejectValue("writer", "trouble");
////		}
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "비어있습니다.");
//		// 스프링에서 제공하는 유틸 메서드
//		String sWriter = dto.getWriter();
//		if(sWriter.length() < 3) {
//			errors.rejectValue("writer", "writer가 너무 짧습니다.");
//		}
////		String sContent = dto.getContent();
////		if(sContent == null || sContent.trim().isEmpty()) {
////			System.out.println("Content 비었습니다.");
////			errors.rejectValue("content", "trouble");
////		}
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "content is empty");
//	} // 어노테이션 사용전
}

