package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ch02") //컨트롤러의 경로는 반드시 requestMapping 사용해야 함.
public class Ch02Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch02Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		logger.info("initiate content of ch02");
		return "ch02/content";
	}
	
	//@RequestMapping("/request1") //GET, POST 모두 지원
	@RequestMapping(value="/getMethod", method = RequestMethod.GET) //GET 방식만 지원하는 어노테이션 설정
//	@RequestMapping(value="/request1", method = RequestMethod.POST) //POST 방식만 지원하는 어노테이션 설정
	public String getMethod() {
		logger.info("initiate content of ch02 by GET");
		return "ch02/content";
	}
	
	@RequestMapping(value="/postMethod", method=RequestMethod.POST)
		public String postMethod() {
			logger.info("initiate content of ch02 by POST");
			return "ch02/content";
		}
	
	@GetMapping(value="/signUp")
	public String signUpForm() {
		logger.info("동기로 창 띄우기");
		return "ch02/signUpForm";
	}
	
	@GetMapping(value="/joinAsync")
	public String joinFormAsync() {
		logger.info("비동기로 창 띄우기");
		return "ch02/joinFormAsync";
	}
	
	@PostMapping(value="/signUp")
	public String signUp() {
		logger.info("signUp Processing");
		return "ch02/content";
	}
	
	
	
	}


