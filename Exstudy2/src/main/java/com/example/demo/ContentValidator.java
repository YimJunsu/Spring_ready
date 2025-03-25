package com.example.demo;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ContentValidator implements Validator {
	@Override
	public boolean supports(Class<?> arg0) {
		return ContentDto.class.isAssignableFrom(arg0); // 검증할 객체의 클래스 타입 정보
	}
	@Override
	public void validate(Object obj, Errors errors) {
		ContentDto dto = (ContentDto)obj;
		String nWriter = dto.getWriter();
		if(nWriter==null || nWriter.trim().isEmpty()) {
			System.out.println("Writer is null or empty");
			errors.rejectValue("writer", "trouble");
		}
		String nContent = dto.getContent();
		if(nContent==null || nContent.trim().isEmpty()) {
			System.out.println("Content is null or empty");
			errors.rejectValue("Content", "trouble");
		}
	}
}
