package com.mycompany.webapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Ch09User;

@Controller
@RequestMapping("/ch09") // 공통경로 설정하기

public class Ch09Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Controller.class);
	
	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch09/content";
	}
	
	@PostMapping("/photoupload")
	public String fileUpload(Ch09User user) { //일일이 사용되는 매개변수를 불러오던가 DTO객체를 불러오던가
		//문자 파트 정보
		String id =  user.getUid();
		String name = user.getUname();
		String pw = user.getUpw();
		
		logger.info("id:" + id);
		logger.info("name:" + name);
		logger.info("password:" + pw);
		
		//파일 파트 정보(MultipartFile)
		MultipartFile uphoto = user.getUphoto();
		if(!uphoto.isEmpty()) {
			String originalFileName = uphoto.getOriginalFilename(); //파일 이름 가져오기
			String contentType = uphoto.getContentType(); //파일 타입 가져오기
			long size = uphoto.getSize(); //파일 사이즈 가져오기
			
			//파일 저장경로 설정
			String saveDirPath = "D:/MW/uploadfiles/"; //우선 이 경로가 db라고 가정
			//업로드 파일이 있는 저장위치 설정하기. 사용자에 상관없이 모두가 볼 수 있는 것은 resources 파일로 접근하게 해도 됨
			//사용자가 사용하면서 생기는 파일들을 저장하면 다른 사람들이 url로 접근하지 못함. 필요할때만 요청가능.
			
			//파일 저장 이름 설정
			String fileName = new Date().getTime() + "-" +  originalFileName;// 유일한 이름으로 바꿔줘야 함 방법1. 날짜 이용법, 2. 시간 ㅣ이용법
			String filePath = saveDirPath + fileName;
			File file = new File(filePath);
			
			try {
				uphoto.transferTo(file);//로 전송시키겠다
				
			} catch(Exception e) {
				return "redirect:/ch09/content";
			}
			logger.info("original File Name:" + originalFileName);
			logger.info("File Type:" + contentType);
			logger.info("size:" + size);
		}
		return "redirect:/ch09/content";
	}
	
	@GetMapping("/photolist") //메소드는 get방식으로 가져오기
	public String photoList(Model model) {
		String saveDirPath = "D:/MW/uploadfiles/";
		File dir = new File(saveDirPath);
		String [] fileNames = dir.list(); // 폴더 안에 있는 모든 파일 명을 배열로 내놓음
		model.addAttribute("fileNames",fileNames);
		
		return "ch09/photolist";
	}
	
	@GetMapping("/photodownload")
	public void photoDownlaod(String photo, HttpServletResponse response) {
		//photolist에서 photo라는 이름으로 요청을 했기 떄문에 똑같이 photo라는 이름으로 매개변수 선언
		//void인 이유는 다운로드하고 끝이기 떄문에 응답이 없음. 브라우저는 응답이 올때까지 기다리게 됨.
		//그래서 404페이지가 뜨는데 그 페이지에서는.jsp파일을 요구함 왜냐하면 web.xml에서 servletmapping에서 /로 되어있기 때문.
		//따라서 명시적으로 응답을 만들어내주어야함.		
//		response.setContentType("text/html; charset=UTF-8"); //응답으로 보내는 형식을 알려줌.
		
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
			
//			byte [] data = new byte[1024];
//			while(true) {
//				int readNum = is.read(data);
//				if(readNum ==-1) break;
//				os.write(data, 0, readNum);
//			}
			
			//Spring 기능으로 파일 복사 처리하기
			FileCopyUtils.copy(is, bos);
			
			bos.flush();
			bos.close();
			is.close();
			
			//스트링타입으로 반응
//			PrintWriter pw = response.getWriter();
//			pw.println("<html>");
//			pw.println("	<body>");
//			pw.println("photoDownload의 응답");
//			pw.println("	</body>");
//			pw.println("</html>");
//			
//			pw.flush();
//			pw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}