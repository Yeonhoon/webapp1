package com.mycompany.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch14BoardDto;
import com.mycompany.webapp.dto.Ch14EmployeeDto;
import com.mycompany.webapp.service.Ch14BoardService;
import com.mycompany.webapp.service.Ch14EmployeeService;


@Controller("ch14Controller") //관리기능
@RequestMapping("/ch14")
public class Ch14Controller {
	
	//필드
	private static final Logger logger = LoggerFactory.getLogger(Ch14Controller.class);
	
	@GetMapping("/content")
	public String content() {
		return "ch14/content";
	}
	
	@Resource //자동으로 주입하기
	private DataSource datasource; //xml에서 datasource property 설정했기 때문에 객체가 이곳에 주입이 됨.
	
	@GetMapping("/conntest")
	public String connTest(Model model) {
		try {
			//커넥션 풀에서 커넥션 대여
			Connection conn = datasource.getConnection();
			model.addAttribute("result", "연결 성공");
			//대여한 커넨션 반납
			conn.close();
		} catch (SQLException e) {
			model.addAttribute("result", "연결 실패");
			e.printStackTrace();
	}
		return "ch14/conntest";
	}
	
	//json: JavaScript Object Notation 자바스크립트 객체 표기법.
	//json으로 객체 출력하기
	@GetMapping("/jsonresponse1")
	// void의 경우 응답을 메소드 안에서 직접 만들어야함.
		public void jsonresponse1(HttpServletResponse response) throws Exception{
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw = response.getWriter();
			
			//json 라이브러리 이용방식
			JSONObject root = new JSONObject();
			root.put("name", "홍길동");
			root.put("age",30);
			
			JSONObject car = new JSONObject(); // car는 객체이기 때문에 새로운 객체를 만듦
			car.put("kind", "Ferrari");
			car.put("color", "RED");
			root.put("car", car);
			
			//json으로 배열 출력하기
			JSONArray hobby = new JSONArray();
			hobby.put("영화");
			hobby.put("여행");
			hobby.put("게임");
			root.put("hobby", hobby);
			
			String json = root.toString();
			pw.println(json);
			/* 개노가다 방식
			 * pw.println("{"); pw.println("\"name\":\"홍길동\","); pw.println("\"age\":30,");
			 * pw.println("\"car\":{\"kind\":\"Ferrari\", \"color\":\"red\"},");
			 * pw.println("\"hobby\": [\"영화 \", \"여행\", \"게임\"]"); pw.println("}");
			 * pw.flush(); pw.close();
			 */
		}
	
	
	
	@GetMapping("/jsonresponse2")
	public void jsonresponse2(HttpServletResponse response) throws Exception{
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		JSONArray root = new JSONArray();
		for(int i=1; i<=3; i++) {
			JSONObject board = new JSONObject();
			board.put("bno",i);
			board.put("btitle","제목"+i);
			board.put("bwriter","글쓴이"+i);
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
	
	
	@Resource //주입받기
	private Ch14EmployeeService employeeService;
	
	@GetMapping("/employeeinfo")
	public  void employee(int employee_id, HttpServletResponse response) throws IOException {
		Ch14EmployeeDto dto = employeeService.getEmployee(employee_id);
		
		response.setContentType("application/json; charset=UTF-8");	
		PrintWriter pw = response.getWriter();
		
		JSONObject root = new JSONObject();
		root.put("employee_id", dto.getEmployee_id());
		root.put("first_name", dto.getFirst_name());
		root.put("last_name",dto.getLast_name());
		String json = root.toString();
		pw.println(json);
		
		pw.flush();
		pw.close();
	}
	
	@Resource
	private Ch14BoardService boardService;
	
	@GetMapping("/boardlist")
	public String boardlist(Model model) {
		List <Ch14BoardDto> list = boardService.getBoardList();
		//값 넘어오는지 확인하기.
//		for(int i =0; i<list.size(); i++) {
//			System.out.println(list.get(i).getBno());
//		}
		model.addAttribute("list",list);
		return "ch14/boardlist";
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
	
}
