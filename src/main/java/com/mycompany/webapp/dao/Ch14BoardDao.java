package com.mycompany.webapp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dto.Ch14BoardDto;
import com.mycompany.webapp.dto.Ch14Pager;

@Repository
public class Ch14BoardDao {
	private static final Logger logger = LoggerFactory.getLogger(Ch14BoardDao.class);
	
	@Resource
	private SqlSessionTemplate sst;
	
	//selectAll(): mapper.xml에서 선언한 쿼리문의 select의 id와 동일.
	public List <Ch14BoardDto> selectAll() {
		List<Ch14BoardDto> list = sst.selectList("boards.selectAll"); //""안에 mybatis namespace 이름이 와야함.
		return list;
	}
	
	public int countAll() {
		int count = sst.selectOne("boards.countAll");
		return count; 
		
	}
	
	public List<Ch14BoardDto> selectByPage(Ch14Pager pager){
		List<Ch14BoardDto> list = sst.selectList("boards.selectByPage",pager);
		return list;
	}
	
	public int insert(Ch14BoardDto boardDto) {
		int rows = sst.insert("boards.insert", boardDto); // mapper.xml 경로 넣어주기
		// insert가 잘 되면 1 return함. insert, delete 등은 반영된 행수를 반환해야함.
		return rows;
	}

	public Ch14BoardDto selectByPk(int bno) {
		Ch14BoardDto dto = sst.selectOne("boards.selectByPk",bno); // bno라는 값을 넘겨줌
		return dto; //dto가 받은 값을 리턴함.
	}

	//반영된 행수를 DB에 전달하기
	public int update(Ch14BoardDto dto) {
		int rows = sst.update("boards.update", dto);
		return rows;
	}

	public int delete(int bno) {
		int rows = sst.delete("boards.delete", bno);
		return rows;
	}

	public int updateHitcount(int bno) {
		int rows = sst.update("boards.updateHitcount", bno);
		return rows;
	}
	
	
}
