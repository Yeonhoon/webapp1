package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch14BoardDao;
import com.mycompany.webapp.dto.Ch14BoardDto;
import com.mycompany.webapp.dto.Ch14Pager;

@Service
public class Ch14BoardService {
	
	@Resource 
	private Ch14BoardDao boardDao;
	
	public List<Ch14BoardDto> getBoardList(){
		List<Ch14BoardDto> list = boardDao.selectAll();
		return list;
	}
	
	public List<Ch14BoardDto> getBoardList(Ch14Pager pager){
		List<Ch14BoardDto> list = boardDao.selectByPage(pager);
		return list;
		
	}
	
	public void saveBoard(Ch14BoardDto dto){
		boardDao.insert(dto);
	}
	
	public int getTotalRows() {
		int totalRows = boardDao.countAll();
		return totalRows;
	}

	public Ch14BoardDto getBoard(int bno) {
		Ch14BoardDto dto = boardDao.selectByPk(bno);
		return dto;
	}

	//게시물 업데이트
	public void updateBoard(Ch14BoardDto dto) {
		boardDao.update(dto);
	}
	
	//게시물 삭제
	public void deleteBoard(int bno) {
		boardDao.delete(bno);
	}

	//조회수 올리기
	public void addHitcount(int bno) {
		boardDao.updateHitcount(bno);
	}
	
	
}
