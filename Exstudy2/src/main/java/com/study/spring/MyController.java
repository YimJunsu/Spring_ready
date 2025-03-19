package com.study.spring;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "Validator_initBinder (3)";
	}
	@RequestMapping("/insertForm")
	public String insert1() {
		return "createPage";
	}
	@RequestMapping("/create")
	public String insert2(@ModelAttribute("dto") @Validated ContentDto contentDto, BindingResult result) {
		String page = "createDonePage";
		System.out.println(contentDto);
		
		// 어노테이션 사용으로 필요가 없음
		//ContentValidator validator = new ContentValidator();
		//validator.validate(contentDto, result);
		if(result.hasErrors()) {
			System.out.println("getAllErros : " + result.getAllErrors());
//			if(result.getFieldError("writer") != null) {
//				System.out.println("1:"+result.getFieldError("writer").getCode());
//			}
//			if(result.getFieldError("content") != null ) {
//				System.out.println("2:"+result.getFieldError("content").getCode());
//			}
			if(result.getFieldError("writer") != null) {
				System.out.println("1:"+result.getFieldError("writer").getDefaultMessage());
			}
			if(result.getFieldError("content") != null) {
				System.out.println("1:"+result.getFieldError("content").getDefaultMessage());
			}
			
			page = "createPage";
		}
		return page;
	}
	
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(new ContentValidator());
//	}
}
