package com.mycompany.webapp.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dto.Ch14MemberDto;

@Repository
public class Ch14MemberDao {
	private static final Logger logger = LoggerFactory.getLogger(Ch14MemberDao.class);
	
	@Resource
	private SqlSessionTemplate sst;
	
	public Ch14MemberDto selectByPk(String mid) {
		Ch14MemberDto member = sst.selectOne("members.selectByPk",mid);
		return member;
	}
	
	public int insert(Ch14MemberDto member) {
		int rows = sst.insert("members.insert",member);
		return rows;
	}
}

