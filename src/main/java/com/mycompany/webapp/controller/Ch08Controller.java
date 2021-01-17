package com.mycompany.webapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch07Board;
import com.mycompany.webapp.dto.Ch08Board;

@Controller
@RequestMapping("/ch08") // 공통경로 설정하기

public class Ch08Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch08Controller.class);
	
	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch08/content";
	}
	
	@GetMapping("/method1")
	public String method1(HttpSession session) {
		session.setAttribute("name", "스트링");
		session.setAttribute("age", 26);
		session.setAttribute("job", "AI 개발자");
		return "ch08/el";

	}
	
	// 객체 생성 후 addAttribute
	@GetMapping("/method2")
	public String method2(HttpSession session) {
		Ch07Board board = new Ch07Board();
		board.setNum(1);
		board.setTitle("title");
		board.setContent("content");
		board.setWriter("writer");
		board.setDate(new Date());
		
		//set으로 board라는 객체에 변수를 넣어주고 addAttribute로 model 객체에 넣어두기 
		session.setAttribute("board1",board);
		
		return "ch08/el";
	}
	
	
	// 컬렉션(List)
	@GetMapping("/method3")
	public String method3(HttpSession session) {
		List <Ch07Board> list = new ArrayList<>();
		for(int i=1; i<=10; i++) {
			Ch07Board board = new Ch07Board();
			board.setNum(i);
			board.setTitle("title" + i);
			board.setContent("content" + i);
			board.setWriter("writer" + i);
			board.setDate(new Date());
			list.add(board);
		}
		session.setAttribute("boardList",list);
		return "ch08/el";
	}
	
	@PostMapping("/login")
	public String login(String uid, String upw, HttpSession session) {
		if(uid.equals("admin") && upw.equals("12345")) {
			session.setAttribute("loginStatus", uid);
		}
		
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		//개별적으로 데이터 삭제
//		session.removeAttribute("loginStatus");
		
		//세션 모든 데이터 삭제
		session.invalidate();
		return "redirect:/ch08/content";
	}
	
	@PostMapping("/boardWrite")
	public String boardWrite(Ch08Board board, HttpSession session) {
		//게시판에 로그인한 사람의 정보를 저장해둔 session에서 얻어 자동으로 입력하기
		String uid = (String) session.getAttribute("loginStatus"); //스트링으로 저장됐기 때문에 object 객체를 강제로 string으로 저장해야함
		if(uid != null) { //로그인이 되어 있다면
			board.setWriter(uid);
			logger.info("Title: " + board.getTitle());
			logger.info("내용: " + board.getContent());
			logger.info("작성자: " + board.getWriter());

			logger.info("게시물 저장 성공");
		} else {
			logger.info("로그인이 되어있지 않음");
		}
		return "redirect:/ch08/content";
	}
	
}
