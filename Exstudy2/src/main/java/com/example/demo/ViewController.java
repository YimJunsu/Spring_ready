package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.JDBCStudy.ISimpleBbsDao;
import com.example.demo.JDBCStudy.UserDao;

import jakarta.servlet.http.HttpServletRequest;

// 해당 클래스 빈으로 등록, 컨트롤러 용도로 사용하겠다.
@Controller
public class ViewController {
	// DI 주입
	@Autowired private UserDao userDao;
	@Autowired private ISimpleBbsDao iSimpleBbsDao;
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "redirect:list";
	}
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("list",iSimpleBbsDao.listDao());
		return "list";
	}
	@RequestMapping("/view")
	public String view(HttpServletRequest httpServletRequest, Model model) {
		String sId = httpServletRequest.getParameter("id");
		model.addAttribute("dto",iSimpleBbsDao.viewDao(sId));
		return "view";
	}
	@RequestMapping("/writeform")
	public String writeform() {
		return "writeform";
	}
	@RequestMapping("/write")
	public String write(Model model, HttpServletRequest httpServletRequest) {
		iSimpleBbsDao.writeDao(httpServletRequest.getParameter("writer"), httpServletRequest.getParameter("title"), httpServletRequest.getParameter("content"));
		return "redirect:list";
	}
	@RequestMapping("/delete")
	public String delete(HttpServletRequest httpServletRequest, Model model) {
		iSimpleBbsDao.deleteDao(httpServletRequest.getParameter("id"));
		return "redirect:list";
	}
	
	// JDBC 템플릿
	@GetMapping("/user")
	public String userlist (Model model) {
		model.addAttribute("users", userDao.list());
		return "userlist";
	}
	// FORM 유효성
	@RequestMapping("/insertForm")
	public String insert1() {
		return "createPage";
	}
	@RequestMapping("/create")
	// Valid 대신 Validated
	public String insert2(@ModelAttribute("dto") @Validated ContentDto contentDto, BindingResult result) {
		String page = "createDonePage";
		System.out.println(contentDto);
		
		//ContentValidator validator = new ContentValidator();
		//validator.validate(contentDto, result);
		if(result.hasErrors()) {
			System.out.println("getAllErrors : " + result.getAllErrors());
//			if(result.getFieldError("writer") != null) {
//				System.out.println("1:"+result.getFieldError("writer").getCode());
//			}
//			if(result.getFieldError("content") != null) {
//				System.out.println("2:"+result.getFieldError("content").getCode());
//			}
//			page = "createPage";
			if(result.getFieldError("writer") != null) {
				System.out.println("1:"+result.getFieldError("writer").getDefaultMessage());
			}
			if(result.getFieldError("content") != null) {
				System.out.println("2:"+result.getFieldError("content").getDefaultMessage());
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
