package com.mycompany.webapp.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.service.Ch13Service1;
import com.mycompany.webapp.service.Ch13Service2;
import com.mycompany.webapp.service.Ch13Service3;
import com.mycompany.webapp.service.Ch13Service4;
import com.mycompany.webapp.service.Ch13Service5;
import com.mycompany.webapp.service.Ch13Service6;
import com.mycompany.webapp.service.Ch13Service7;

@Controller("ch13Controller") //관리기능
@RequestMapping("/ch13")
public class Ch13Controller {
	
	//필드
	private static final Logger logger = LoggerFactory.getLogger(Ch13Controller.class);
	
	
//	private Ch13Service1 service1 = new Ch13Service1(); //service1자리에 본인 객체 및 자식 객체 사용 가능.
	//Service 없이는 Ch13Controller 사용 불가능.
	
	//해결방법 1. 설정파일 xml에 빈태그 추가
	//주입방법1: ch13Controller객체가 만들어질 떄 스프링이 관리하는 객체 중 저것이 있다면 자동으로  가져와라
	@Resource
	private Ch13Service1 service1;
	
	@Resource //방법1. 필드활용. 관리객체가 만들어있으면 자동으로 주입이 됨.
	private Ch13Service2 service2;
	
	@Resource
	private Ch13Service3 service3;
	
	@Resource
	private Ch13Service4 service4;
	
	@Resource
	private Ch13Service5 service5;
	
	@Resource
	private Ch13Service6 service6;
	
	@Resource
	private Ch13Service7 service7;
	//생성자 --------------------------------------------------
	//주입방법2: 생성자 처리
//	@Autowired
	/*
	 * public Ch13Controller(Ch13Service1 service1) { //매개값: ioc가 관리하는 객체 중 매개변수와 같은 객체가 있으면 찾아서 집어넣어라
	 *  this.service1 = service1; }
	 */
	
	//메소드 --------------------------------------------------
//	//방법3: setter를 만들어 service1를 받음.
//	
//	 @Autowired  //객체에 주입하겠다! 얘가 붙으면 자동으로 아래의 객체 자동으로 실행됨.
//	 public void setService1(Ch13Service1 service1) {
//		logger.info("실행"); 
//		this.service1 = service1; 
//	 }
//	 
//	 @Autowired
//	 public void setService2(Ch13Service2 service2) {
//		logger.info("실행"); 
//		this.service2 = service2; 
//	 }
	
	@Autowired //
	 public void setService3(Ch13Service3 service3) {
		logger.info("실행"); 
		this.service3 = service3; 
	 }
	
	@Autowired
	 public void setService4(Ch13Service4 service4) {
		logger.info("실행"); 
		this.service4 = service4; 
	 }

	 @GetMapping("/content")
	 public String content() {
		logger.info("실행");
		return "ch13/content";
	 }	

	@GetMapping("/service1")
	public String service1() {
		service1.method(); //service1 객체의 메소드에서 호출됨.
		logger.info("실행");
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/service2")
	public String service2() {
		service2.method(); //service1 객체의 메소드에서 호출됨.
		logger.info("실행");
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/service3")
	public String service3() {
		service3.method(); //service1 객체의 메소드에서 호출됨.
		logger.info("실행");
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/service4")
	public String service4() {
		service4.method(); //service1 객체의 메소드에서 호출됨.
		logger.info("실행");
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/service5")
	public String service5() {
		service4.method(); //service1 객체의 메소드에서 호출됨.
		logger.info("실행");
		return "redirect:/ch13/content";
	}
	@GetMapping("/service6")
	public String service6() {
		service4.method(); //service1 객체의 메소드에서 호출됨.
		logger.info("실행");
		return "redirect:/ch13/content";
	}
	
	@Value("${fileupload}")
	private String saveDir;
	
	
	@GetMapping("/fileupload")
	public String fileupload(@Value("${fileupload}") String savedir) {
		logger.info("실행");
//		String saveDir = "D:/MW/" //안좋은 방식
 		logger.info("fileupload:" + saveDir);
		service7.method();
		return "redirect:/ch13/content";
	}
	
	
	
}
