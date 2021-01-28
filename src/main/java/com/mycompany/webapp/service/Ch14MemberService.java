package com.mycompany.webapp.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch14MemberDao;
import com.mycompany.webapp.dto.Ch14MemberDto;


@Service
public class Ch14MemberService {
	private static final Logger logger = LoggerFactory.getLogger(Ch14MemberService.class);
	
	@Resource
	private Ch14MemberDao memberDao;
	
	public void join(Ch14MemberDto member) {
		memberDao.insert(member);
	}
	
	public String login(Ch14MemberDto member) {
		//DB의 멤버 정보 가져오기
		Ch14MemberDto dbMember = memberDao.selectByPk(member.getMid());
		if(dbMember == null) {
			return "wrongMid";
			
			//회원가입으로 인해 저장된 db의 password 값과 사용자가 브라우저에서 입력한 값이 같은지 확인
		} else if(dbMember.getMpassword().equals( member.getMpassword())) {
			return "success";
		} else {
			return "wrongMpassword";
			
		}
	}

	public Ch14MemberDto getMember(String mid) {
		Ch14MemberDto dto = memberDao.selectByPk(mid);
		return dto;
	}
	
	
	
}
