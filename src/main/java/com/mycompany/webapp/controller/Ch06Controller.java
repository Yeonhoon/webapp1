package com.mycompany.webapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ch06") //컨트롤러의 경로는 반드시 requestMapping 사용해야 함. 요청 시 ch03이 있을 때 실행하겠다.
public class Ch06Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch06Controller.class);
	
	@GetMapping("/content")
	public String content() {
		logger.info("initiate");
		return "ch06/content";
	}
	
	@GetMapping("/forward")
	public String forward() {
		logger.info("실행");
		return "ch06/forward";
	}
	
	@GetMapping("/redirect")
	public String redirect() {
		logger.info("실행");
		return "redirect:/ch06/content";
	}

	@GetMapping("/login")
	public String login() {
		logger.info("실행");
		return "redirect:/ch05/content"; //요청으로 들어온 login이 실행될 경우 redirect하여 보여줄 페이지
	}
	
	@GetMapping("/boardList")
	public String boardList() {
		logger.info("게시물 목록 생성");
		return "ch06/boardList"; //요청으로 들어온 login이 실행될 경우 redirect하여 보여줄 페이지
	}
	
	@GetMapping("/boardWrite")
	public String boardWrite() {
		logger.info("게시물 저장 완료");
		return "redirect:/ch06/boardList"; //요청으로 들어온 login이 실행될 경우 redirect하여 보여줄 페이지
	}
	
	
}
