package com.mycompany.webapp.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.LoginDto;
import com.mycompany.webapp.dto.Prac2Dto;
import com.mycompany.webapp.dto.PracBoardDto;
import com.mycompany.webapp.dto.PracDto;
import com.mycompany.webapp.dto.PracUpdown;
import com.mycompany.webapp.dto.WriteDto;

@Controller
@RequestMapping("/prac")
public class PracController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(PracController.class);
	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "prac/content";
	}
	
	@GetMapping("/login")
	public String login() {
		logger.info("로그인 창 호출하기");
		return "prac/loginForm";
	}
	
	@PostMapping("/loginAccess")
	public String loginAccess(HttpSession session, String uid, String upw) {
		logger.info("로그인 시도");
		
		LoginDto dto = new LoginDto(uid, upw);
		logger.info("id:" + dto.getUid());
		logger.info("pw:" + dto.getUpw());
		logger.info(dto.toString());
		
//		String uid = req.getParameter("uid");
//		String upw = req.getParameter("upw");
//		logger.info("id, pw:" + uid + " " + upw);
//		HttpSession session = req.getSession();		
//		user = new LoginDto(uid);
		session.setAttribute("loginStatus", dto);
		return "redirect:/prac/content";
	}
	
	@GetMapping("/logout")
	public String logOut(HttpSession session) {
		
		logger.info("로그아웃");
		session.invalidate();
		
		return "redirect:/prac/content";
	}
	
	@GetMapping("/write")
	public String write() {
		logger.info("글쓰기 페이지로 이동");
		return "prac/write";
	}
	
	@PostMapping("/savewrite")
	public String saveWrite(HttpSession session, String utitle, String uwriter, String ucontent, String udate, MultipartFile uphoto) {
		WriteDto dto = new WriteDto(utitle, uwriter, ucontent, udate, uphoto);
		session.setAttribute("write", dto);
		logger.info(dto.toString());
		return "redirect:/prac/content";
	}
	
	@Resource
	private DataSource datasource;
	
	@GetMapping("/conntest")
	public String conntest(Model model) {
		try {
			Connection conn = datasource.getConnection();
			model.addAttribute("result","연결 성공");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			model.addAttribute("result","연결 실패");
		}
		return "prac/conntest";
	}
	
	@GetMapping("/jsonobject")
	public void jsoinObject(HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		JSONObject root = new JSONObject();
		root.put("name","장연훈");
		root.put("취미","운동");
		
		JSONObject car = new JSONObject();
		car.put("brand", "Ferrari");
		car.put("Color", "Ferrari Red");
		car.put("price", 3000000);
		root.put("dreamcar",car);
		
		JSONArray list = new JSONArray();
		list.put("애쉬");
		list.put("리퍼");
		list.put("맥크리");
		root.put("OverWatch", list);
		
		String json = root.toString();
		pw.println(json);
		logger.info(json);
		
	}
	
	@GetMapping()
	
	
	
	

	//header 정보 얻기
	@RequestMapping("/header")
	public String header(@RequestHeader("User-Agent") String ua) {
		
		logger.info(ua);
		
		return "prac/content";
	}
	
	//httpServletRequest: RequestHeader를 통해 사용할수도 있지만 httpServlet 객체를 이용할 수도 있음
	 @RequestMapping("servletRequest")
	 public String httpServletRequest(HttpServletRequest request) {
		 String userAgent = request.getHeader("User-Agent");
		 
		 logger.info(userAgent);
		 return "prac/content";
	 }
	
	//httpServletRequest httpServletMethod 으로 쿠키 보내기
	 @RequestMapping("/cookie1")
	 public String cookie1(HttpServletRequest request, HttpServletResponse response) {
		 
		 Cookie cookieName = new Cookie("name","yeonhoon");
		 Cookie cookieAge = new Cookie("age","21");
		 response.addCookie(cookieName);
		 response.addCookie(cookieAge);
		 
		 Cookie[] cookieArr = request.getCookies();
		 for(Cookie cookie: cookieArr) {
			 String name = cookie.getName();
			 String value = cookie.getValue();
			 logger.info(name + ":" + value);
		 }
		 
		 return "prac/content";
	 }
	 
	 //forward와 redirect
	 
	 @RequestMapping("/forward")
	 public String forward() {
		 return "prac/forward";
	 }
	 
	 @RequestMapping("/redirectLogin")
	 public String redirect() {
		 return "redirect:/prac/content";
	 }
	 
	 @RequestMapping("/redirectInForward")
	 public String rif() {
		 logger.info("forward에서 redirect로 다시 돌아가기");
		 return "redirect:/prac/content";
	 }

	 
	 //Model
	 
	 @RequestMapping("/scala")
	 public String scala(Model model) {
		 model.addAttribute("name","장연훈");
		 model.addAttribute("age",28);
		 model.addAttribute("address","서울시 관악구");

		 return "prac/el";
	 
	 }
	 
	 @RequestMapping("/object")
	 public String object(Model model) {
		 //생성되는 객체는 DTO를 불러와서 사용
		 PracBoardDto dto = new PracBoardDto();
		 dto.setName("장연훈");
		 dto.setAge(21);
		 dto.setAddress("서울시 관악구 쑥고개로21길 30 경동오피스텔 406호");
		 
		 model.addAttribute("myBoard",dto);
		 return "prac/el";
	 }
	 
	 @RequestMapping("/collection")
	 public String collection(Model model) {
		 
		 List<PracBoardDto>myList = new ArrayList<>();
		 for(int i=1; i<10; i++) {
			 PracBoardDto dtoboard = new PracBoardDto();
			 dtoboard.setName("이름" + i);
			 dtoboard.setAge(i);
			 dtoboard.setAddress("주소" + i);
			 myList.add(dtoboard);
		 }
		 model.addAttribute("mydtoList",myList);
		 return "prac/el";
	 }
	 
	
	 
	 @PostMapping("/upload")
	 public String upload(PracUpdown photo) {
		 String fname = photo.getFname();
		 String lname = photo.getLname();
		 String id = photo.getId();
		 String pw = photo.getPw();
		 
		 logger.info("이름:" + fname);
		 logger.info("성:" + lname);
		 logger.info("id:" + id);
		 logger.info("pw:" + pw);
		 
		 MultipartFile user = photo.getPhoto();
		 if(!user.isEmpty()) {
			 String originalFilename = user.getOriginalFilename();
			 String contentType = user.getContentType();
			 long size = user.getSize();
			 
			 logger.info("file name:" + originalFilename);
			 logger.info("content Type:" + contentType);
			 logger.info("file size:" + size);
			 
			 String saveDir = "D:/MW/uploadfiles";
			 String fileName = new Date().getTime() + "-" + originalFilename;
			 String filePath = saveDir + fileName;
			 File file = new File(filePath);
			 
			 try {
				user.transferTo(file);
			} catch (Exception e) {
				return "redirect:/prac/content";
			} 
			 
		 }
		 return "redirect:/prac/content";
	 }
	 
	 
	 @RequestMapping("/modellogin")
		public String modelLogin(Model model) {
			model.addAttribute("id","jyhoon94");
			model.addAttribute("pw","Zpflrjs94!");
			
			return "prac/loginPrac";
		}
		
	@RequestMapping("/modellogin2")
		public String modelLogin2(Model model) {
			Prac2Dto dto = new Prac2Dto();
			
			dto.setId("jyhoon94");
			dto.setPw("Zpflrjs94!");
			
			model.addAttribute("dto",dto);
			
			return "prac/loginPrac";
		}
		
	@RequestMapping("/modellogin3")
		public String modelLogin3(Model model) {
			List <Prac2Dto> list = new ArrayList<>();
			
			Prac2Dto dto = new Prac2Dto();
			
			for(int i=1; i<10; i++) {
				dto.setId("id"+i);
				dto.setPw("pw"+i);
				list.add(dto);
			}
			
			model.addAttribute("list",list);
			return "prac/loginPrac";
		} 
	 
	@RequestMapping("/sessionlogin")
	public String sessionlogin(HttpSession session) {
		logger.info("session 페이지로 이동");
		return "prac/loginPrac";
	}
	
	@PostMapping("session1")
	public String session1(HttpSession session, String uid, String upw) {
		if(uid.equals("qwerty") && upw.equals("123456")) {
			session.setAttribute("loginStatus", "ok");
		}
		return "redirect:/prac/loginPrac";
	}
	
	@RequestMapping("session2")
	public String session2(HttpSession session) {
		session.invalidate();
		return "redirect:/prac/loginPrac";
	}
	
	
	
	
}
