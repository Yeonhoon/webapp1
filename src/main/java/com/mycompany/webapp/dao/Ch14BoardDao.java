package com.mycompany.webapp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dto.Ch14BoardDto;

@Repository
public class Ch14BoardDao {
	private static final Logger logger = LoggerFactory.getLogger(Ch14BoardDao.class);
	
	@Resource
	private SqlSessionTemplate sst;
	
	//selectAll(): mapper.xml에서 선언한 쿼리문의 select의 id와 동일.
	public List <Ch14BoardDto> selectAll() {
		List<Ch14BoardDto> list = sst.selectList("mybatis.mapper.boards.selectAll"); //""안에 mybatis namespace 이름이 와야함.
		return list;
	}
	
	public int insert(Ch14BoardDto boardDto) {
		int rows = sst.insert("mybatis.mapper.boards.insert", boardDto); // mapper.xml 경로 넣어주기
		// insert가 잘 되면 1 return함. insert, delete 등은 반영된 행수를 반환해야함.
		return rows;
	}
}
