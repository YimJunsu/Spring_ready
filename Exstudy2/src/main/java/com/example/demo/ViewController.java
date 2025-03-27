package com.example.demo;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.JDBCStudy.ISimpleBbsDao;
import com.example.demo.JDBCStudy.MybatisUserDao;
import com.example.demo.JDBCStudy.UserDao;
import com.example.demo.transctionstudy.BuyAndLogService;
import com.example.demo.transctionstudy.IBuyTicketService;

import jakarta.servlet.http.HttpServletRequest;

// 해당 클래스 빈으로 등록, 컨트롤러 용도로 사용하겠다.
@Controller
public class ViewController {
	// DI 주입
	@Autowired private UserDao userDao;
	//@Autowired private ISimpleBbsDao iSimpleBbsDao;
	@Autowired private MybatisUserDao mybatisUserDao;
	@Autowired private ISimpleBbsSerive bbsSerive;
	//@Autowired private IBuyTicketService buyTicketService;
	@Autowired private BuyAndLogService buyAndLogService;
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "Transaction Propagation (4)";
	}
	
	@RequestMapping("/buy_ticket")
	public String buy_ticket() {
		return "buy_ticket";
	}
	@RequestMapping("/buy_ticket_card")
	public String buy_ticket_card(@RequestParam("consumerid") String consumerid, 
	                              @RequestParam("amount") String amount, 
	                              @RequestParam("error") String error, 
	                              Model model) {
	    // 서비스 메서드 호출
	    //int nResult = buyTicketService.buy(consumerid, Integer.parseInt(amount), error);
		int nResult = buyAndLogService.buy(consumerid, Integer.parseInt(amount), error);
	    // 디버깅용 로그
	    System.out.println("Result of buyTicketService.buy: " + nResult);

	    // 모델에 값 추가
	    model.addAttribute("consumerid", consumerid);
	    model.addAttribute("amount", amount);

	    // 결과에 따라 다른 페이지로 이동
	    if (nResult == 1) {
	        return "buy_ticket_end";
	    } else {
	        return "buy_ticket_error";
	    }
	}

	// mybatis
	@GetMapping("/user")
	public String userlist(Model model) {
		model.addAttribute("users", mybatisUserDao.mlist());
		return "userlist";
	}
	// JDBCtemplate -> mybatis
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("list",bbsSerive.list());
		
		int nTotalCount = bbsSerive.count();
		System.out.println("Count : " + nTotalCount);
		return "/list";
	}
	@RequestMapping("/view")
	public String view(HttpServletRequest httpServletRequest, Model model) {
		String sId = httpServletRequest.getParameter("id");
		model.addAttribute("dto",bbsSerive.view(sId));
		return "/view";
	}
	@RequestMapping("/writeform")
	public String writeform() {
		return "/writeform";
	}
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		String sName = request.getParameter("writer");
		String sTitle = request.getParameter("title");
		String sContent = request.getParameter("content");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("item1", sName);
		map.put("item2", sTitle);
		map.put("item3", sContent);
		
		int nResult = bbsSerive.write(map);
		System.out.println("Write : " + nResult);
		
		return "redirect:list";
	}
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		String sId = request.getParameter("id");
		int nResult = bbsSerive.delete(sId);
		System.out.println("Delete : " + nResult);
		
		return "redirect:list";
	}
	
//	// JDBC 템플릿
//	@GetMapping("/user")
//	public String userlist (Model model) {
//		model.addAttribute("users", userDao.list());
//		return "userlist";
//	}
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
