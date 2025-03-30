package com.example.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;

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
	// 파일 업로드 경로 설정
    private static final String UPLOAD_DIR = "D:\\sourcetree_link\\Spring_ready\\Exstudy2\\src\\main\\resources\\static\\uploads";
	@RequestMapping("/")
	public String root() throws Exception{
		return "index";
	}
	@RequestMapping("/uploadform")
	public String uploadForm() {
		return "fileform";
	}
	// 파일 업로드 처리
    @RequestMapping("/uploadOk")
    public @ResponseBody String uploadOk(@RequestParam("file") MultipartFile file) {
        // 업로드 파일이 비어있는지 확인
        if (file.isEmpty()) {
            return "파일을 선택해 주세요.";
        }

        try {
            // 파일 이름 가져오기
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                return "파일 이름을 가져올 수 없습니다.";
            }

            // 파일 크기 제한 설정 (10MB)
            int maxSize = 1024 * 1024 * 10;  // 10MB
            if (file.getSize() > maxSize) {
                return "파일 크기가 너무 큽니다. 10MB 이하로 업로드 해 주세요.";
            }

            // static/uploads 폴더 경로 설정
            Path uploadPath = Paths.get(UPLOAD_DIR);

            // 폴더가 없다면 생성
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 파일을 서버에 저장
            Path filePath = uploadPath.resolve(originalFilename);
            file.transferTo(filePath.toFile());

            // 업로드된 파일 URL 제공
            String fileUrl = "/uploads/" + originalFilename;

            return "파일 업로드 성공: " + originalFilename + " <br> 다운로드 링크: <a href='" + fileUrl + "'>파일 다운로드</a>";

        } catch (IOException e) {
            e.printStackTrace();
            return "파일 업로드 실패: " + e.getMessage();
        }
    }
	// 시큐리티
	@RequestMapping("/guest/welcome")
	public String welcome1() {
		return "guest/welcome1";
	}
	@RequestMapping("/member/welcome")
	public String welcome2() {
		return "member/welcome2";
	}
	@RequestMapping("/admin/welcome")
	public String welcome3() {
		return "admin/welcome3";
	}
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "security/loginForm";
	}
	@RequestMapping("/loginError")
	public String loginError() {
		return "security/loginError";
	}
	// 트랜잭션 study
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
