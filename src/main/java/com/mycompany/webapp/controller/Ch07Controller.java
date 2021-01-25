 package com.mycompany.webapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch07Board;

@Controller
@RequestMapping("/ch07") // 공통경로 설정하기

public class Ch07Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch07Controller.class);
	
	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch07/content";
	}
	
	@GetMapping("/method1")
	public String method1(Model model) {
		// 모델이라는 객체에 데이터들을  저장(addAttribute)해두면 method1에서 이 값들을 얻을 수 있음.
		model.addAttribute("name", "스트링");
		model.addAttribute("age", 26);
		model.addAttribute("job", "AI 개발자");
		return "ch07/el";

	}
	
	// 객체 생성 후 addAttribute
	@GetMapping("/method2")
	public String method2(Model model) {
		Ch07Board board = new Ch07Board();
		board.setNum(1);
		board.setTitle("title");
		board.setContent("content");
		board.setWriter("writer");
		board.setDate(new Date());
		
		//set으로 board라는 객체에 변수를 넣어주고 addAttribute로 model 객체에 넣어두기 
		model.addAttribute("board1",board);
		
		return "ch07/el";
	}
	
	
	// 컬렉션(List)
	@GetMapping("/method3")
	public String method3(Model model) {
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
		model.addAttribute("boardList",list);
		return "ch07/el";
	}
}
