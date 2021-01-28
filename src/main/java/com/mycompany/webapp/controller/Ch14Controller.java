package com.mycompany.webapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Ch14BoardDto;
import com.mycompany.webapp.dto.Ch14EmployeeDto;
import com.mycompany.webapp.dto.Ch14MemberDto;
import com.mycompany.webapp.dto.Ch14OrderDto;
import com.mycompany.webapp.dto.Ch14OrderItemDto;
import com.mycompany.webapp.dto.Ch14Pager;
import com.mycompany.webapp.service.Ch14BoardService;
import com.mycompany.webapp.service.Ch14EmployeeService;
import com.mycompany.webapp.service.Ch14MemberService;
import com.mycompany.webapp.service.Ch14OrderService;

@Controller("ch14Controller") // 관리기능
@RequestMapping("/ch14")
public class Ch14Controller {

	// 필드
	private static final Logger logger = LoggerFactory.getLogger(Ch14Controller.class);

	@GetMapping("/content")
	public String content() {
		return "ch14/content";
	}

	@Resource // 자동으로 주입하기
	private DataSource datasource; // xml에서 datasource property 설정했기 때문에 객체가 이곳에 주입이 됨.

	@GetMapping("/conntest")
	public String connTest(Model model) {
		try {
			// 커넥션 풀에서 커넥션 대여
			Connection conn = datasource.getConnection();
			model.addAttribute("result", "연결 성공");
			// 대여한 커넨션 반납
			conn.close();
		} catch (SQLException e) {
			model.addAttribute("result", "연결 실패");
			e.printStackTrace();
		}
		return "ch14/conntest";
	}

	// json: JavaScript Object Notation 자바스크립트 객체 표기법.
	// json으로 객체 출력하기
	@GetMapping("/jsonresponse1")
	// void의 경우 응답을 메소드 안에서 직접 만들어야함.
	public void jsonresponse1(HttpServletResponse response) throws Exception {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();

		// json 라이브러리 이용방식
		JSONObject root = new JSONObject();
		root.put("name", "홍길동");
		root.put("age", 30);

		JSONObject car = new JSONObject(); // car는 객체이기 때문에 새로운 객체를 만듦
		car.put("kind", "Ferrari");
		car.put("color", "RED");
		root.put("car", car);

		// json으로 배열 출력하기
		JSONArray hobby = new JSONArray();
		hobby.put("영화");
		hobby.put("여행");
		hobby.put("게임");
		root.put("hobby", hobby);

		String json = root.toString();
		pw.println(json);
		/*
		 * 개노가다 방식 pw.println("{"); pw.println("\"name\":\"홍길동\",");
		 * pw.println("\"age\":30,");
		 * pw.println("\"car\":{\"kind\":\"Ferrari\", \"color\":\"red\"},");
		 * pw.println("\"hobby\": [\"영화 \", \"여행\", \"게임\"]"); pw.println("}");
		 * pw.flush(); pw.close();
		 */
	}

	@GetMapping("/jsonresponse2")
	public void jsonresponse2(HttpServletResponse response) throws Exception {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		JSONArray root = new JSONArray();
		for (int i = 1; i <= 3; i++) {
			JSONObject board = new JSONObject();
			board.put("bno", i);
			board.put("btitle", "제목" + i);
			board.put("bwriter", "글쓴이" + i);
			root.put(board);
		}
		String json = root.toString();
		pw.println(json);
//		pw.println("[");
//		pw.println("{\"bno\":1, \"btitle\": \"title1\",\"bwriter\": \"writer1\"},"); //배열안의 객체. 문자열인 경우 무조 건 \" 붙이기
//		pw.println("{\"bno\":2, \"btitle\": \"title2\",\"bwriter\": \"writer2\"},"); 
//		pw.println("{\"bno\":3, \"btitle\": \"title3\",\"bwriter\": \"writer3\"}"); 

//		pw.println("]");
		pw.flush();
		pw.close();
	}

	@Resource // 주입받기
	private Ch14EmployeeService employeeService;

	@GetMapping("/employeeinfo")
	public void employee(int employee_id, HttpServletResponse response) throws IOException {
		Ch14EmployeeDto dto = employeeService.getEmployee(employee_id);

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();

		JSONObject root = new JSONObject();
		root.put("employee_id", dto.getEmployee_id());
		root.put("first_name", dto.getFirst_name());
		root.put("last_name", dto.getLast_name());
		String json = root.toString();
		pw.println(json);

		pw.flush();
		pw.close();
	}

	@Resource
	private Ch14BoardService boardService;

	@GetMapping("/boardlist")
	public String boardlist(Model model) {
		List<Ch14BoardDto> list = boardService.getBoardList();
		// 값 넘어오는지 확인하기.
//		for(int i =0; i<list.size(); i++) {
//			System.out.println(list.get(i).getBno());
//		}
		model.addAttribute("list", list);
		return "ch14/boardlist";
	}

	@GetMapping("/boardlist2")
	public String boardlis2(@RequestParam(defaultValue = "1") int pageNo, Model model) { // requestParam: default값 설정

		int totalRows = boardService.getTotalRows();
		Ch14Pager pager = new Ch14Pager(6, 5, totalRows, pageNo);
		List<Ch14BoardDto> list = boardService.getBoardList(pager);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		return "ch14/boardlist";
	}

	// 작성 양식 불러오기
	@GetMapping("/boardwrite")
	public String boardwriteform() {
		return "ch14/boardwrite";

	}

	// 작성한 게시물 db에 저장하기
	@PostMapping("/boardwrite")
	public String boardwrite(Ch14BoardDto dto, HttpSession session) throws IllegalStateException, IOException {
		
		//게시물 저장
		String mid = (String) session.getAttribute("sessionMid"); // 세션에 저장한 값 가져오기
		dto.setBwriter(mid);
		
		//사진 저장
		MultipartFile mf = dto.getBattach();
		if (!mf.isEmpty()) {
			dto.setBattachoname(mf.getOriginalFilename());
			String saveName = new Date().getTime() + "-" + mf.getOriginalFilename();
			dto.setBattachsname(saveName);
			dto.setBattachtype(mf.getContentType());

			// 파일 저장
			File saveFile = new File("D:/MW/uploadfiles/boards/" + saveName);
			mf.transferTo(saveFile);
		}

		boardService.saveBoard(dto);
		return "redirect:/ch14/boardlist2";
	}

	@GetMapping("/boardsave")
	public String boardsave() {
//		for(int i = 1; i <= 100 ; i++) {
//			Ch14BoardDto dto = new Ch14BoardDto();
//			dto.setBtitle("제목"+i);
//			dto.setBcontent("내용"+i);
//			dto.setBwriter("jyhoon94");
//			boardService.saveBoard(dto);
//		}
		return "redirect:/ch14/boardlist";
	}

	@GetMapping("/join")
	public String joinForm() {
		return "ch14/join";
	}

	@Resource
	private Ch14MemberService memberService;

	@PostMapping("/join")
	public String join(Ch14MemberDto dto) throws Exception {
		// 파일 정보 얻기
		MultipartFile mf = dto.getMphoto();
		if (!mf.isEmpty()) {
			dto.setMphotooname(mf.getOriginalFilename());
			String saveName = new Date().getTime() + "-" + mf.getOriginalFilename();
			dto.setMphotosname(saveName);
			dto.setMphototype(mf.getContentType());

			// 파일 저장
			File saveFile = new File("D:/MW/uploadfiles/members/" + saveName);
			mf.transferTo(saveFile);
		}
		// DB에 저장
		memberService.join(dto);
		return "redirect:/ch14/boardlist2";
	}

	@GetMapping("/login")
	public String loginForm() {
		return "ch14/login";
	}

	// json 으로 보낼 것이기 떄문에 void
	@PostMapping("/login")
	public void login(Ch14MemberDto dto, HttpServletResponse response, HttpSession session) throws Exception {
		// result의 종류: success, wrongid, wrongpw
		String result = memberService.login(dto);
		if (result.equals("success")) {
			session.setAttribute("sessionMid", dto.getMid());
		}

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();

		JSONObject root = new JSONObject();
		root.put("result", result);
		pw.println(root.toString());
		pw.flush();
		pw.close();
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/ch14/boardlist2";
	}

	// 사진에 대한 정보 얻어오기
	@GetMapping(value = "/mphoto")
	public void mphoto(String mid, HttpSession sesson, HttpServletResponse response) throws Exception {
		if (mid == null) {
			mid = (String) sesson.getAttribute("sessionMid");
		}

		Ch14MemberDto dto = memberService.getMember(mid);
		String filePath = null;

		if (dto.getMphotosname() != null) {
			String mphotosname = dto.getMphotosname();
			filePath = "D:/MW/uploadfiles/members/" + mphotosname;

			response.setContentType(dto.getMphototype());

			String mphotooname = dto.getMphotooname();
			mphotooname = new String(mphotooname.getBytes("UTF-8"), "ISO-8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + mphotooname + "\"");
		} else {
			filePath = "D:/MW/uploadfiles/members/defaultphoto.png";
			response.setContentType("image/png");
		}
		OutputStream os = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(os);
		InputStream is = new FileInputStream(filePath);
		FileCopyUtils.copy(is, bos);
		os.flush();
		os.close();
		is.close();
	}

	@GetMapping("/boardread")
	public String boardread(int bno, Model model) { // browser에서 요청한 값을 받은ㄱ ㅓㅅ.
		boardService.addHitcount(bno);
		Ch14BoardDto board = boardService.getBoard(bno);
		model.addAttribute("board", board);
		return "ch14/boardread";
	}

	@GetMapping("/boardupdate")
	public String boardUpateForm(int bno, Model model) {
		Ch14BoardDto board = boardService.getBoard(bno);
		model.addAttribute("board", board);
		return "ch14/boardupdate";
	}

	@PostMapping("/boardupdate")
	public String boardupdate(Ch14BoardDto dto) {
		// 서비스에게 업데이터 요청 전달
		// service에서 void이기 때문에 return 값이 없음.
		boardService.updateBoard(dto);

		return "redirect:/ch14/boardlist2";
	}

	@GetMapping("/boarddelete")
	public String boarddelte(int bno) {
		boardService.deleteBoard(bno);
		return "redirect:/ch14/boardlist";
	}

	// 첨부파일 다운로드
	@GetMapping("/battach")
	   public void mphoto(int bno,  HttpServletResponse response) throws Exception {
			System.out.println("hello");
			Ch14BoardDto dto = boardService.getBoard(bno);
			System.out.println(dto.toString());
			String battachsname = dto.getBattachsname();
			String filePath = "D:/MW/uploadfiles/boards/" + battachsname;

			response.setContentType(dto.getBattachtype());

			// 다운로드 위해 필요한 부분
			 String oname = dto.getBattachoname();
	         oname = new String(oname.getBytes("UTF-8"), "ISO-8859-1");
	         response.setHeader("Content-Disposition", "attachment; filename=\""+ oname +"\"");
	         OutputStream os = response.getOutputStream();
	         BufferedOutputStream bos = new BufferedOutputStream(os);
		     InputStream is = new FileInputStream(filePath);
		     FileCopyUtils.copy(is,bos);
	      	 bos.flush();
		     bos.close();
		     is.close();
		   }
	
	@Resource
	private Ch14OrderService orderService;
	
	@GetMapping("/order")
	public String order() {
		
		//주문 정보 얻기
		Ch14OrderDto order = new Ch14OrderDto();
		order.setMid("winter");
		order.setAddress("서울시 관악구 쑥고개로21길 ");
		
		//주문상품정보 얻기(장바구니에서 가져와야됨)
		List<Ch14OrderItemDto> list = new ArrayList<>();
		Ch14OrderItemDto oi1 = new Ch14OrderItemDto();
		
		oi1.setPid("다이아몬드");
		oi1.setAmount(100);
		list.add(oi1);
		
		Ch14OrderItemDto oi2 = new Ch14OrderItemDto();
		oi2.setPid("FERRARI");
		oi1.setAmount(20);
		list.add(oi2);
		
		orderService.order(order, list);
		return "ch14/content";
		
	}
	
	
	
}
