package com.mycompany.webapp.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch13BoardDaoInterface;


@Service
public class Ch13Service6 {
	private static final Logger logger = LoggerFactory.getLogger(Ch13Service6.class);
	
	//방법1. 이 방법이 더 편리.
	@Resource(name="ch13BoardDao5")
	private Ch13BoardDaoInterface boardDao5;
	
	//방법2.
	@Autowired	
	@Qualifier("Ch13BoardDao6Qualifier")
	private Ch13BoardDaoInterface boardDao6;
	
	public void method() { 
		logger.info("실행");
		boardDao5.insert();
		boardDao6.insert();
	}
}
