package com.mycompany.webapp.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dto.PracDto;

@Repository
public class PracDao {
	
	private SqlSessionTemplate sst;
	
	public List<PracDto> heroBoard() {
		List<PracDto> praclist = sst.selectList("mybatis.mapper.prac.heroBoard");
		return praclist;
	}
	
	
}
