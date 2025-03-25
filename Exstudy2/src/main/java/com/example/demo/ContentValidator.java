package com.example.demo;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
public class ContentValidator{
	// @Vaild 어노테이션 사용으로 삭제 대신 주석처리
//public class ContentValidator implements Validator {
//	@Override
//	public boolean supports(Class<?> arg0) {
//		return ContentDto.class.isAssignableFrom(arg0); // 검증할 객체의 클래스 타입 정보
//	}
//	@Override
//	public void validate(Object obj, Errors errors) {
//		ContentDto dto = (ContentDto)obj;
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "writer is empty");
//		String sWriter = dto.getWriter();
//		// 값 길이 3글자 하면 오류
//		if(sWriter.length() < 3) {
//			errors.rejectValue("writer", "writer is too short.");
//		}
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "content is empty");
//		String sContent = dto.getContent();
//		if(sContent.length() < 3) {
//			errors.rejectValue("content", "Content is too short.");
//		}
//		ch01
//		String nWriter = dto.getWriter();
//		만약 비어있거나, 값이 없으면
//		if(nWriter==null || nWriter.trim().isEmpty()) {
//			System.out.println("Writer is null or empty");
//			errors.rejectValue("writer", "trouble");
//		}
//		String nContent = dto.getContent();
//		if(nContent==null || nContent.trim().isEmpty()) {
//			System.out.println("Content is null or empty");
//			errors.rejectValue("Content", "trouble");
//		}
	}
