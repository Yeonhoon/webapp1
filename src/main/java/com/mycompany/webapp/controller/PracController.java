package com.mycompany.webapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Prac2Dto;
import com.mycompany.webapp.dto.PracBoardDto;
import com.mycompany.webapp.dto.PracDto;
import com.mycompany.webapp.dto.PracUpdown;
import com.mycompany.webapp.service.PracService;

@Controller
@RequestMapping("/prac")
public class PracController {
	
	private static final Logger logger = LoggerFactory.getLogger(PracController.class);
	@GetMapping("/content")
	public String content() {
		logger.info("실행이 잘 되는군");
		return "prac/content";
	}
	
	
	@GetMapping("/myprac")
	public String myprac() {
		logger.info("실행이 잘 되는군");
		return "prac/myprac";
	}
	
	@GetMapping("/sync")
	public String sync() {
		logger.info("동기로 로그인 띄우기");
		return "prac/signinForm";
	}

	@GetMapping("/async")
	public String asyncSignin() {
		logger.info("비동기로 로그인 창 띄우기 성공");
		return "prac/asyncForm";
	}

	
	@PostMapping("/signinComplete")
	public String signinComplete () {
		logger.info("로그인 성공");
		return "prac/content";
	}
	
	@GetMapping("/asyncJoin")
	public String asyncJoin() {
		logger.info("회원가입 성공했으니 myprac으로 이동");
		return "prac/joinForm";
	}
	
	//DTO 구현하기
	@PostMapping("/myDTO")
	public String myDto(PracDto dto) {
		logger.info("param1:"+ dto.getParam1());
		logger.info("param1:"+ dto.getParam2());
		logger.info("param1:"+ dto.getParam3());
		logger.info("param1:"+ dto.getParam4());
		
		return "prac/content";
	}
	
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
	 
	 @PostMapping("login")
	 public String login(HttpSession session, String uid, String upw) {
		 if(uid.equals("jyhoon94") && upw.equals("Zpflrjs94!")) {
			 session.setAttribute("loginStatus", uid);
		 }
		 return "redirect: prac/content";
	 }
	 
	 @RequestMapping("logout")
	 public String logout(HttpSession session) {
		 session.removeAttribute("loginStatus");
		 session.invalidate();

		 return "redirect:prac/content";
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
	 
	 @GetMapping("/photolist")
	 public String photoList(Model model) {
		 String saveDir = "D:/MW/uploadfiles/";
		 File dir = new File(saveDir);
		 String [] fileNames = dir.list();
		 model.addAttribute("fileNames", fileNames);
		 
		 return "prac/photoupload";
	 }
	 
	 @GetMapping("/photodownload")
	public void photoDownlaod(String photo, HttpServletResponse response) {
			//photolist에서 photo라는 이름으로 요청을 했기 떄문에 똑같이 photo라는 이름으로 매개변수 선언
			//void인 이유는 다운로드하고 끝이기 떄문에 응답이 없음. 브라우저는 응답이 올때까지 기다리게 됨.
			//그래서 404페이지가 뜨는데 그 페이지에서는.jsp파일을 요구함 왜냐하면 web.xml에서 servletmapping에서 /로 되어있기 때문.
			//따라서 명시적으로 응답을 만들어내주어야함.		
//			response.setContentType("text/html; charset=UTF-8"); //응답으로 보내는 형식을 알려줌.
			
			// 응답 본문의 데이터의 종류를 응답 헤더에 추가하기
			response.setCharacterEncoding("image/jpeg"); //DB 하게 되면 달라짐.

			// 응답 본문의 데이터를 첨부파일로 다운로드할 수 있도록 응답 헤더에 추가하기
			// 여기서 파일이름이 한글이면 한글이 깨지는 현상 발생 ==> 파일이름을 추가할 때 변환작업해주어야함.
			
			try {
				photo = new String(photo.getBytes("UTF-8"),"ISO-8859-1");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + photo +  "\"");
				//인코딩을 다시하는 이유: HTTP 규약에 따라 헤더에는 UTF-8이 들어갈 수 없고 ISO타입(알파벳)만 들어갈 수 있음.
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
			
			try {
				OutputStream os = response.getOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(os);
				String saveDirPath = "D:/MW/uploadfiles/";
				String filePath = saveDirPath + photo;
				InputStream is = new FileInputStream(filePath);
				
//				byte [] data = new byte[1024];
//				while(true) {
//					int readNum = is.read(data);
//					if(readNum ==-1) break;
//					os.write(data, 0, readNum);
//				}
				
				//Spring 기능으로 파일 복사 처리하기
				FileCopyUtils.copy(is, bos);
				
				bos.flush();
				bos.close();
				is.close();
				
				//스트링타입으로 반응
//				PrintWriter pw = response.getWriter();
//				pw.println("<html>");
//				pw.println("	<body>");
//				pw.println("photoDownload의 응답");
//				pw.println("	</body>");
//				pw.println("</html>");
//				
//				pw.flush();
//				pw.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
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
	
	@Resource
	private PracService service1;
	
	@Autowired
	public void setService1(PracService service1) {
		logger.info("실행"); 
		this.service1 = service1;
		
	}
	
	
	
}
