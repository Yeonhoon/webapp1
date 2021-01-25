package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch14BoardDao;
import com.mycompany.webapp.dto.Ch14BoardDto;

@Service
public class Ch14BoardService {
	
	@Resource 
	private Ch14BoardDao boardDao;
	
	public List<Ch14BoardDto> getBoardList(){
		List<Ch14BoardDto> list = boardDao.selectAll();
		return list;
	}
	
	public void saveBoard(Ch14BoardDto dto){
		boardDao.insert(dto);
	}
	
	
	
}
