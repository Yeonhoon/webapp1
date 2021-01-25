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
@RequestMapping("/ch05") //컨트롤러의 경로는 반드시 requestMapping 사용해야 함. 요청 시 ch03이 있을 때 실행하겠다.
public class Ch05Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch05Controller.class);
	
	@GetMapping("/content")
	public String content() {
		logger.info("initiate");
		return "ch05/content";
	}
	
	@GetMapping("/method1")
	public String method1(@RequestHeader("User-Agent") String userAgent) { //ReuqestHeader를 통해 ""값을 userAgent 매개변수에 저장하라
		logger.info("실행");
		logger.info(userAgent);
		if(userAgent.contains("Edg")) {
			logger.info("브라우저의 종류: 엣지");
		} else if(userAgent.contains("Chrome")){
			logger.info("브라우저의 종류: 크롬");
		} else if(userAgent.contains("Trident")) {
			logger.info("브라우저의 종류: IE 11");
		}
		return "ch05/content";
		
	}
	
	//HttpServletRequest: 브라우저가 was에 요청을 하는 정보를 저장하는 객체
	@GetMapping("/method2")
	public String method2(HttpServletRequest request) { //ReuqestHeader를 통해 ""값을 userAgent 매개변수에 저장하라
		logger.info("실행");
		String userAgent = request.getHeader("User-Agent");
		logger.info(userAgent);
		if(userAgent.contains("Edg")) {
			logger.info("브라우저의 종류: 엣지");
		} else if(userAgent.contains("Chrome")){
			logger.info("브라우저의 종류: 크롬");
		} else if(userAgent.contains("Trident")) {
			logger.info("브라우저의 종류: IE 11");
		}
		return "ch05/content";
	}
	
	@GetMapping("/method3")
	public String method3(HttpServletRequest request, HttpServletResponse response) { //request를 통해 정보 얻기 //response: 브라우저로 어떤 것을 보내고자 할 때
		
		// response하려면 WAS 내에 쿠키를 만들어서 브라우저로 보낼 수 있게 해야 함.
		// 쿠키 생성: 
		// 쿠키는 작은 정보의 조각으로 쿠키 이름과  쿠키 값으로 구성됨
		Cookie cookie1 = new Cookie("memberId","white");
		Cookie cookie2 = new Cookie("loginStatus","ok");
		
		//쿠키를 응답 header에 추가하여 클라이언트(브라우저)로 보내기
		response.addCookie(cookie1); //응답에 '추가'하기 때문에 .addCookie
		response.addCookie(cookie2);
		// 응답 본문을 생성하는 뷰 리턴
		return "ch05/content";
		
	}
	
	@GetMapping("/method4")
	public String method4(HttpServletRequest request, HttpServletResponse response) { //request를 통해 정보 얻기 //response: 브라우저로 어떤 것을 보내고자 할 때
	
		//브라우저가 요청 헤더에 보낸 쿠키 값 얻기
			Cookie[] cookieArr = request.getCookies();
			for(Cookie cookie: cookieArr) {
				String name = cookie.getName(); //쿠키의 이름 얻기
				String value = cookie.getValue(); //쿠키의 값 얻기
				logger.info(name + ":" + value);
			}
		//응답 본문 생성 뷰 리턴
			return "ch05/content";

}
	
	//for문 안 쓰고 쿠키 가져오기
	@GetMapping("/method5")
	public String method5(@CookieValue("memberId")String memberId, @CookieValue("loginStatus") String loginStatus) { //request를 통해 정보 얻기 //response: 브라우저로 어떤 것을 보내고자 할 때
	
		//브라우저가 요청 헤더에 보낸 쿠키 값 얻기
			logger.info(memberId + ":" + memberId);
			logger.info("loginStatus:" + loginStatus);
		//응답 본문 생성 뷰 리턴
			return "ch05/content";

}
	
}
