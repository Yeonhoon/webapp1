package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.PracDao;
import com.mycompany.webapp.dto.PracDto;

@Service
public class PracService {

	
	@Resource
	private PracDao pracDao;
	
	public List<PracDto> getBoardList() {
		List<PracDto> dto = pracDao.heroBoard();
		return dto;
	}
}
